package com.swifi.device.v1

import com.swifi.device.test.StatOpenColse._
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.functions.udf
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 场内场外关机统计
  * 关机位置为关机时间前后100秒内最近的LBS记录位置
  * 关机位置离终始站距离小于1000米为场内反正为场外
  * Created by neoThe on 2017/4/20.
  */
object StatOpenColse {

  //开关机记录
  case class dsdOpenInfo(openTime: Long,openStat: String,dsdApMac: String)
  case class dsdCloseInfo(closeTime: Long,closeStat :String,dsdApMac: String)
  //经纬度上报记录
  case class lbsInfo(time: Long,lat: Double,lng: Double,lbsApMac: String)

  def main(args: Array[String]): Unit = {
    val dformat = new java.text.SimpleDateFormat("yyyy/MM/dd")
    val fformat = new java.text.SimpleDateFormat("yyyyMMdd")
    //取得昨日的日期
    var datedir = dformat.format(new java.util.Date().getTime - 86400000)
    var filename = fformat.format(new java.util.Date().getTime - 86400000)
    if(args.length > 0){
      datedir = args(0)
      filename = datedir.replaceAll("/","")
    }

    //判断是场外还是场内
    val dist: ((Double,Double,Double,Double,Double,Double) => String) = (lat1: Double,lng1: Double,lat2: Double,lng2: Double,lat3: Double,lng3: Double) => { judgeInOrOut(lat1,lng1,lat2,lng2,lat3,lng3) }
    val distfunc = udf(dist)


    val sparkConf = new SparkConf().setAppName("StatBasicByAp Application " + filename)
    val sc = new SparkContext(sparkConf)

    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._

    //开关机记录
    val dsdFile = sc.textFile("/data/dsd/" + datedir + "/dsd_*.log*")
    //经纬度上报记录
    val lbsFile = sc.textFile("/data/lbs/" + datedir + "/lbs_*.log*")
    //线路信息
    val roadFile = sc.textFile("/data/user/mac_road.txt")



    //开关机记录
    val dsdOpenData = dsdFile.map(_.split(",")).filter(u => u.length == 7).map(u => dsdOpenInfo(toLong(u(3)),u(5),u(2))).toDF().distinct().filter($"openTime".!==(0))
    dsdOpenData.registerTempTable("dsdOpen")
    val dsdCloseData = dsdFile.map(_.split(",")).filter(u => u.length == 7).map(u => dsdCloseInfo(toLong(u(4)),u(6),u(2))).toDF().distinct().filter($"closeTime".!==(0))
    dsdCloseData.registerTempTable("dsdClose")

    //经纬度上报记录
    lbsFile.map(_.split(",")).filter(u => u.length == 11).map(u => lbsInfo(toLong(u(8)),toDouble(u(5)),toDouble(u(4)),u(2))).toDF().distinct()
      .filter($"lat".!==(0)).registerTempTable("lbs")

    //车辆终始站信息
    val roadData = roadFile.map(_.split(",")).filter(u => u.length == 7).map(u => macRoadInfo(u(0),toDouble(u(3)),toDouble(u(4)),toDouble(u(5)),toDouble(u(6)))).toDF().distinct()

    //计算最小时间差（开机记录时间和经纬度上报时间）
    sqlContext.sql("select dsdOpen.dsdApMac,dsdOpen.openTime,dsdOpen.openStat,MIN(ABS(dsdOpen.openTime-lbs.time)) as oInter from dsdOpen " +
      "left join lbs on dsdOpen.dsdApMac = lbs.lbsApMac group by dsdOpen.dsdApMac,dsdOpen.openTime,dsdOpen.openStat")
      .filter($"oInter".isNotNull)
      .registerTempTable("leftOpenTable")
    //计算最小时间差（关机机记录时间和经纬度上报时间）
    sqlContext.sql("select dsdClose.dsdApMac,dsdClose.closeTime,dsdClose.closeStat,MIN(ABS(dsdClose.closeTime-lbs.time)) as cInter from dsdClose " +
      "left join lbs on dsdClose.dsdApMac = lbs.lbsApMac group by dsdClose.dsdApMac,dsdClose.closeTime,dsdClose.closeStat")
      .filter($"cInter".isNotNull)
      .registerTempTable("leftCloseTable")

    //查询开机时车辆所在的经纬度
    val temp_1 = sqlContext.sql("select leftOpenTable.*,lbs.lat as oLat,lbs.lng as oLng from leftOpenTable left join lbs " +
      "on leftOpenTable.dsdApMac = lbs.lbsApMac and (leftOpenTable.openTime - oInter = lbs.time or leftOpenTable.openTime + oInter = lbs.time) where oInter < 100").filter($"oLat".isNotNull).drop("openStat")
    //查询关机时车辆所在的经纬度
    val temp_2 = sqlContext.sql("select leftCloseTable.*,lbs.lat as cLat,lbs.lng as cLng from leftCloseTable left join lbs " +
      "on leftCloseTable.dsdApMac = lbs.lbsApMac and (leftCloseTable.closeTime - cInter = lbs.time or leftCloseTable.closeTime + cInter = lbs.time) where cInter < 100").filter($"cLat".isNotNull).drop("closeStat")

    val temp_3 = dsdOpenData.join(temp_1,Seq("dsdApMac","openTime"),"left_outer").filter($"openTime".!== (0))

    val temp_4 = temp_2.join(roadData,$"dsdApMac" === $"apMac","left_outer").drop("apMac")
      .withColumn("dist",distfunc($"cLat",$"cLng",$"loc1Lat",$"loc1Lng",$"loc2Lat",$"loc2Lng")).drop("loc1Lat").drop("loc1Lng").drop("loc2Lat").drop("loc2Lng")

    val result = dsdCloseData.join(temp_4,Seq("dsdApMac","closeTime"),"left_outer")


    result.write.mode(SaveMode.Overwrite).json("/other/device/" + datedir + "/StatApOpenClose")
  }

  def toLong(str: String): Long = {
    try
      str.toLong
    catch {
      case e:Exception => 0l
    }
  }
  def toDouble(str: String): Double = {
    try
      str.toDouble
    catch {
      case e:Exception => 0d
    }
  }
  def timeToLong(time:String): Long ={
    val sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss")
    val date = sdf.parse(time)
    date.getTime
  }

  def lantitudeLongitudeDist(lat1: Double,lng1: Double,lat2: Double,lng2: Double): Double ={
    if(lat1>0 && lng1>0 && lat2>0 && lng2>0){
      val pk = 180 / 3.14169
      val a1 = lat1 / pk
      val a2 = lng1 / pk
      val b1 = lat2 / pk
      val b2 = lng2 / pk
      val t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2)
      val t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2)
      val t3 = Math.sin(a1) * Math.sin(b1)
      val tt = Math.acos(t1 + t2 + t3)
      6366000 * tt
    }else{
      -1d
    }
  }
  def judgeInOrOut(locLat: Double,locLng: Double,sLat: Double,sLng: Double,eLat: Double,eLng: Double): String ={
    val dist_1 = lantitudeLongitudeDist(locLat,locLng,sLat,sLng)
    val dist_2 = lantitudeLongitudeDist(locLat,locLng,eLat,eLng)

    if(dist_1 < 1000 || dist_2 < 1000){
      "<1000"
    }else{
      ">1000"
    }
  }
}
