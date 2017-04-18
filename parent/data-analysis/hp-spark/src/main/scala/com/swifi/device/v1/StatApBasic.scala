package com.swifi.device.v1

import org.apache.spark.sql.SaveMode
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by neoThe on 2017/4/18.
  */
object StatApBasic {

  //盒子开机时长和关机次数
  case class ApOnLine(apmac: String,onLineDuration: String,shutDownTimes:String)
  //盒子开机位移距离
  case class ApOnLineMoveDistance(apmac: String,distance: Long,linkage : Int)
  //城市对应
  case class ApMacCity(apmac: String,cityCode: String)
  //线路对应
  case class ApMacLine(apmac: String,lineId:String,lineName: String)

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

    val sparkConf = new SparkConf().setAppName("StatApBasic Application " + filename)
    val sc = new SparkContext(sparkConf)

    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._

    //开机时长和关机次数
    val onLineFile = sc.textFile("/other/device/" + datedir + "/StatApOnLine/*")
    val onLineData = onLineFile.map(_.split(",")).filter (line => line.length>2).map(u =>ApOnLine(u(0),u(1),u(2))).toDF().distinct()

    //开机位移
    val onLineMoveDistanceFile = sc.textFile("/other/device/" + datedir + "/StatApBurstLbs/*")
    onLineMoveDistanceFile.map(_.split(",")).filter (line => line.length>8).map(u => ApOnLineMoveDistance(u(0),u(7).toLong,u(9).toInt)).toDF().registerTempTable("onLineMoveDistanceFile")
    val onLineMoveDistanceData = sqlContext.sql("select apmac as onLineApmac, min(linkage) as linkage,sum(distance) as distance from onLineMoveDistanceFile group by apmac")

    //计算gps上报次数
    val lbsFile = sc.textFile("/data/lbs/" + datedir + "/lbs_*.log*")
    lbsFile.map(_.split(",")).filter(line => line.length>2).map(u => u(2)).toDF().withColumnRenamed("_1","apmac").registerTempTable("lbsFile")
    val lbsData = sqlContext.sql("select apmac as lbsApmac,count(1) as lbsTimes from lbsFile group by apmac")

    //心跳次数
    val heartFile = sc.textFile("/data/heart/" + datedir + "/heart_*.log*")
    heartFile.map(_.split(",")).filter(line => line.length>2).map(u => u(2)).toDF().withColumnRenamed("_1","apmac").registerTempTable("heartFile")
    val heartData = sqlContext.sql("select apmac as heartApmac,count(1) as heartbeatTimes from heartFile group by apmac")

    //上网次数
    val authFile = sc.textFile("/data/auth/" + datedir + "/auth_*.log*")
    authFile.map(_.split(",")).filter(line => line.length>2).map(u => u(2)).toDF().withColumnRenamed("_1","apmac").registerTempTable("authFile")
    val authData = sqlContext.sql("select apmac as authApmac,count(1) as connTimes from authFile group by apmac")

    //城市
    val apMacCityFile = sc.textFile("/data/user/ap_city.txt")
    val apMacCityData = apMacCityFile.map(_.split(",")).filter(line => line.length>1).map(u => ApMacCity(u(0),u(1))).toDF().withColumnRenamed("apmac","cityApmac")

    //线路
    val apMacLineFile = sc.textFile("/data/user/mac_car_line.txt")
    val apMacLineData = apMacLineFile.map(_.split(",")).filter(line => line.length>1).map(u => ApMacLine(u(0),u(2),u(3))).toDF().withColumnRenamed("apmac","lineApmac")

    val basic = onLineData.join(onLineMoveDistanceData,$"apmac" === $"onLineApmac","left_outer").drop("onLineApmac")
    .join(lbsData,$"apmac" === $"lbsApmac","left_outer").drop("lbsApmac")
    .join(heartData,$"apmac" === $"heartApmac","left_outer").drop("heartApmac")
    .join(authData,$"apmac" === $"authApmac","left_outer").drop("authApmac")
    .join(apMacCityData,$"apmac" === $"cityApmac","left_outer").drop("cityApmac")
    .join(apMacLineData,$"apmac" === $"lineApmac","left_outer").drop("lineApmac")

    basic.registerTempTable("basicTable")

    //根据线路计算各项数据的平均值
    val basicAvg = sqlContext.sql("select lineId,lineName,avg(onLineDuration) as onlineAvg,avg(shutDownTimes) as shutDownAvg,avg(distance) as  distanceAvg ,avg(lbsTimes) as lbsAvg,avg(heartbeatTimes) as heartbeatAvg,avg(connTimes) as connAvg from basicTable group by lineId,lineName")

    basic.write.mode(SaveMode.Overwrite).json("/other/device/" + datedir + "/StatApBasic/")
    basicAvg.write.mode(SaveMode.Overwrite).json("/other/device/" + datedir + "/StatApLineAvg/")

  }

  def stringToLong(time:String): Long ={
    val sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss")
    val date = sdf.parse(time)
    date.getTime
  }

}
