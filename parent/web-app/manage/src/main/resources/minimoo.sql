-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: minimoo
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `moo_lbs_day`
--

DROP TABLE IF EXISTS `moo_lbs_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moo_lbs_day` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MAC` varchar(255) DEFAULT NULL COMMENT 'mac',
  `LINE_NAME` varchar(255) DEFAULT NULL COMMENT '默认线路',
  `STATR_TIME` varchar(255) DEFAULT NULL COMMENT '开始时间',
  `END_TIME` varchar(255) DEFAULT NULL COMMENT '结束时间',
  `ACTIVE` int(11) DEFAULT NULL COMMENT '活跃时长',
  `MAX_DELAYED` int(11) DEFAULT NULL COMMENT '最大延时',
  `UP_TIMES` int(11) DEFAULT NULL COMMENT '上传次数',
  `JUDGE_LINE_NAME` varchar(255) DEFAULT NULL COMMENT '系统判断得出的线路',
  `DATE` varchar(25) DEFAULT NULL COMMENT '统计某日的GPS上报结果',
  `ANALYSIS_DATE` datetime DEFAULT NULL COMMENT '执行统计的日期时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8036 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `moo_lbs_line`
--

DROP TABLE IF EXISTS `moo_lbs_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moo_lbs_line` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LINE_ID` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL COMMENT '线路名称',
  `LINE_NAME` varchar(255) DEFAULT '' COMMENT '线路名称',
  `FRONT_NAME` varchar(255) DEFAULT NULL COMMENT '首发站名称',
  `FRONT_SPELL` varchar(255) DEFAULT NULL COMMENT '起点拼音',
  `TERMINAL_NAME` varchar(255) DEFAULT NULL COMMENT '终点站名称',
  `TERMINAL_SPELL` varchar(255) DEFAULT NULL COMMENT '终点站拼音名',
  `COMPANY` varchar(255) DEFAULT NULL COMMENT '公交车公司名称',
  `LENGTH` varchar(255) DEFAULT NULL COMMENT '线路长度',
  `TIME_DESC` varchar(255) DEFAULT NULL COMMENT '用文字来描述无特定发车规则（即发车无规律）的发车信息',
  `START_TIME` varchar(255) DEFAULT NULL COMMENT '早班车时间',
  `END_TIME` varchar(255) DEFAULT NULL COMMENT '末班车时间，20:00',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述性信息',
  `TOTAL_PRICE` varchar(255) DEFAULT NULL COMMENT '总票价',
  `LOOP` varchar(11) DEFAULT '0' COMMENT '是否环线，0-否，1-是',
  `AUTO` varchar(11) DEFAULT '1' COMMENT '是否自动（无人）售票，0-人工售票，1-无人售票',
  `IC_CARD` varchar(11) DEFAULT '0' COMMENT '是否可以使用电子售票（公交卡），0-不可以使用，1-可以使用',
  `DOUBLE_DECK` varchar(11) DEFAULT '0' COMMENT '是否双层, 0-否, 1-是',
  `EXPRESS_WAY` varchar(11) DEFAULT '0' COMMENT '公共交通线是否路经高速道路，0-不经过，1-经过',
  `STATUS` varchar(11) DEFAULT '1' COMMENT '线路状态, 0-停运，1-正常, 包含在bus标识内',
  `AIR` varchar(11) DEFAULT '0' COMMENT '是否有空调，0-非空调车，1-空调车',
  `COMMUTATION_TICKET` varchar(11) DEFAULT '0' COMMENT '是否可用月票. 0-不可用;1-可用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8149 DEFAULT CHARSET=utf8 COMMENT='线路信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `moo_sys_resource`
--

DROP TABLE IF EXISTS `moo_sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moo_sys_resource` (
  `RESOURCE_ID` varchar(255) NOT NULL DEFAULT '',
  `RESOURCE_NAME` varchar(255) DEFAULT NULL,
  `RESOURCES` varchar(255) DEFAULT NULL,
  `ATTRIBUTE` varchar(255) DEFAULT NULL,
  `P_RESOURCE_ID` varchar(255) DEFAULT NULL,
  `ISMENU` varchar(255) DEFAULT NULL,
  `ICON` varchar(255) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `moo_sys_resource_role`
--

DROP TABLE IF EXISTS `moo_sys_resource_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moo_sys_resource_role` (
  `RELATION_ID` varchar(255) NOT NULL DEFAULT '',
  `ROLE_ID` varchar(255) DEFAULT NULL,
  `RESOURCE_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`RELATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `moo_sys_role`
--

DROP TABLE IF EXISTS `moo_sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moo_sys_role` (
  `ROLE_ID` varchar(255) NOT NULL DEFAULT '',
  `ROLE_NAME` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `ROLE_CODE` varchar(255) DEFAULT NULL COMMENT '权限唯一标识码',
  `P_ROLE_ID` varchar(255) DEFAULT NULL,
  `NUMBER` int(11) DEFAULT NULL COMMENT '人数',
  `CRE_DATE` datetime DEFAULT NULL,
  `CRE_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `moo_sys_user`
--

DROP TABLE IF EXISTS `moo_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moo_sys_user` (
  `U_ID` varchar(255) NOT NULL DEFAULT '',
  `U_NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `U_ACCOUNT` varchar(255) NOT NULL COMMENT '管理员账号',
  `U_PASSWORD` varchar(255) NOT NULL COMMENT '登陆密码',
  `REG_DATE` datetime DEFAULT NULL COMMENT '开户时间',
  `QQ_NUMBER` varchar(255) DEFAULT NULL,
  `PHONE_NUMBER` varchar(255) NOT NULL,
  `ROLE_ID` varchar(255) DEFAULT NULL COMMENT '权限编号',
  `U_STATE` int(2) DEFAULT NULL COMMENT '1--正常 2--锁定',
  `DEL` int(2) NOT NULL DEFAULT '1' COMMENT '删除位 0-删除 1-未删除',
  PRIMARY KEY (`U_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_device_monitor`
--

DROP TABLE IF EXISTS `t_device_monitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_device_monitor` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AP_MAC` varchar(12) DEFAULT NULL,
  `ONLINE_TIME_LENGTH` int(11) DEFAULT NULL COMMENT '开机时长（秒）',
  `SHUT_DOWN_TIMES` int(11) DEFAULT NULL COMMENT '关机次数',
  `LINKAGE` int(11) DEFAULT NULL COMMENT '合理值 值越小越精确',
  `MOVE_DISTANCE` int(11) DEFAULT NULL COMMENT '移动距离',
  `GPS_TIMES` int(11) DEFAULT NULL COMMENT 'GPS上报次数',
  `HEARTBEAT_TIMES` int(11) DEFAULT NULL COMMENT '心跳次数',
  `CONN_TIMES` int(11) DEFAULT NULL COMMENT '联网次数',
  `CITY` varchar(12) DEFAULT NULL COMMENT '城市',
  `RECORD_DATE` date DEFAULT NULL COMMENT '统计时间',
  `MANUFACTURER` varchar(12) DEFAULT NULL COMMENT '盒子制造商',
  `LINE_NAME` varchar(25) DEFAULT NULL COMMENT '公交线路',
  `AUTH_TIMES` int(11) DEFAULT NULL COMMENT '认证次数',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `record_date` (`RECORD_DATE`,`AP_MAC`)
) ENGINE=InnoDB AUTO_INCREMENT=18137 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_lbs_minute`
--

DROP TABLE IF EXISTS `t_lbs_minute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_lbs_minute` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `COMPANY` varchar(50) DEFAULT NULL COMMENT '公司',
  `CITY` varchar(10) DEFAULT NULL COMMENT '城市',
  `BUS_NUM` int(255) DEFAULT NULL COMMENT '车辆数',
  `DATE` date DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=639 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_line_monitor`
--

DROP TABLE IF EXISTS `t_line_monitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_line_monitor` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LINE_ID` varchar(255) DEFAULT NULL COMMENT '线路ID',
  `LINE_NAME` varchar(255) DEFAULT NULL COMMENT '线路名',
  `AVG_ONLINE` bigint(20) DEFAULT NULL COMMENT '平均在线时长',
  `AVG_SHUTDOWN` bigint(20) DEFAULT NULL COMMENT '平均关机次数',
  `AVG_DISTANCE` bigint(20) DEFAULT NULL COMMENT '平均位移',
  `AVG_LBS` bigint(20) DEFAULT NULL COMMENT '平均上报GPS次数',
  `AVG_HEARTBEAT` bigint(20) DEFAULT NULL COMMENT '平均心跳上报次数',
  `AVG_CONN` bigint(20) DEFAULT NULL COMMENT '平均联网次数',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-28 21:49:45
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: minimoo
-- ------------------------------------------------------
-- Server version 5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `moo_sys_resource`
--

DROP TABLE IF EXISTS `moo_sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moo_sys_resource` (
  `RESOURCE_ID` varchar(255) NOT NULL DEFAULT '',
  `RESOURCE_NAME` varchar(255) DEFAULT NULL,
  `RESOURCES` varchar(255) DEFAULT NULL,
  `ATTRIBUTE` varchar(255) DEFAULT NULL,
  `P_RESOURCE_ID` varchar(255) DEFAULT NULL,
  `ISMENU` varchar(255) DEFAULT NULL,
  `ICON` varchar(255) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moo_sys_resource`
--

LOCK TABLES `moo_sys_resource` WRITE;
/*!40000 ALTER TABLE `moo_sys_resource` DISABLE KEYS */;
INSERT INTO `moo_sys_resource` VALUES ('000001','首页','/home.moo',NULL,'0','N',NULL),('000002','系统设置','/',NULL,'0','Y','fa-desktop'),('000003','系统管理员','/menu/system/user.moo',NULL,'000002','Y',NULL),('000004','资源管理','/menu/system/resources.moo',NULL,'000002','Y',NULL),('000005','角色管理','/menu/system/role.moo',NULL,'000002','Y',NULL),('000006','修改权限','/system/edit_roles_resource.moo',NULL,'000005','N',NULL),('000013','重置密码','/system/reset_user_pwd.moo',NULL,'000003','N',NULL),('000014','LBS','/',NULL,'0','Y','fa-connectdevelop'),('000015','上报情况统计','/menu/lbs/time_dimension.moo',NULL,'000014','Y',NULL),('000016','线路地图','/menu/lbs/linetest.moo',NULL,'000014','Y',NULL),('000017','车辆数统计','/menu/lbs/bus_num.moo',NULL,'000014','Y',NULL),('000018','高德','/menu/lbs/gaode.moo',NULL,'000014','Y',NULL),('000019','滴滴','/menu/lbs/didi.moo',NULL,'000014','Y',NULL),('000020','车来了','/menu/lbs/chelaile.moo',NULL,'000014','Y',NULL),('33390156536872960','设备监控','/',NULL,'0','Y','fa-desktop'),('33391364751753216','实时设备监控','/menu/device/ap_real.moo',NULL,'33390156536872960','Y',NULL),('33504851753697280','信号强度监控','/menu/device/ap_signal.moo',NULL,'33390156536872960','Y',NULL);
/*!40000 ALTER TABLE `moo_sys_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moo_sys_resource_role`
--

DROP TABLE IF EXISTS `moo_sys_resource_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moo_sys_resource_role` (
  `RELATION_ID` varchar(255) NOT NULL DEFAULT '',
  `ROLE_ID` varchar(255) DEFAULT NULL,
  `RESOURCE_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`RELATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moo_sys_resource_role`
--

LOCK TABLES `moo_sys_resource_role` WRITE;
/*!40000 ALTER TABLE `moo_sys_resource_role` DISABLE KEYS */;
INSERT INTO `moo_sys_resource_role` VALUES ('29591838142234624','29591836742909952','0'),('29591838142496768','29591836742909952','000001'),('31578419919585280','31578279839006720','0'),('31578419919847424','31578279839006720','000014'),('31578419919847425','31578279839006720','000001'),('31578419919847426','31578279839006720','000018'),('31578419920109568','31578279839006720','000017'),('31578419920109569','31578279839006720','000019'),('31578419920371712','31578279839006720','000020'),('33504860621504512','000001','000003'),('33504860626485248','000001','000014'),('33504860626747392','000001','000002'),('33504860627009536','000001','000013'),('33504860627271680','000001','000001'),('33504860627533824','000001','000018'),('33504860627795968','000001','000006'),('33504860628058112','000001','000017'),('33504860628320256','000001','000005'),('33504860628582400','000001','000016'),('33504860628844544','000001','000004'),('33504860629106688','000001','000015'),('33504860629368832','000001','000019'),('33504860629893120','000001','33504851753697280'),('33504860630155264','000001','33391364751753216'),('33504860630679552','000001','0'),('33504860631203840','000001','33390156536872960'),('33504860631465984','000001','000020');
/*!40000 ALTER TABLE `moo_sys_resource_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moo_sys_role`
--

DROP TABLE IF EXISTS `moo_sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moo_sys_role` (
  `ROLE_ID` varchar(255) NOT NULL DEFAULT '',
  `ROLE_NAME` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `ROLE_CODE` varchar(255) DEFAULT NULL COMMENT '权限唯一标识码',
  `P_ROLE_ID` varchar(255) DEFAULT NULL,
  `NUMBER` int(11) DEFAULT NULL COMMENT '人数',
  `CRE_DATE` datetime DEFAULT NULL,
  `CRE_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moo_sys_role`
--

LOCK TABLES `moo_sys_role` WRITE;
/*!40000 ALTER TABLE `moo_sys_role` DISABLE KEYS */;
INSERT INTO `moo_sys_role` VALUES ('000001','系统管理员','ADMIN','0',1,'2016-06-29 14:43:38','系统初始化'),('31578279839006720','业务员','YEWUYUAN',NULL,NULL,'2016-12-20 14:28:39','admin');
/*!40000 ALTER TABLE `moo_sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moo_sys_user`
--

DROP TABLE IF EXISTS `moo_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moo_sys_user` (
  `U_ID` varchar(255) NOT NULL DEFAULT '',
  `U_NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `U_ACCOUNT` varchar(255) NOT NULL COMMENT '管理员账号',
  `U_PASSWORD` varchar(255) NOT NULL COMMENT '登陆密码',
  `REG_DATE` datetime DEFAULT NULL COMMENT '开户时间',
  `QQ_NUMBER` varchar(255) DEFAULT NULL,
  `PHONE_NUMBER` varchar(255) NOT NULL,
  `ROLE_ID` varchar(255) DEFAULT NULL COMMENT '权限编号',
  `U_STATE` int(2) DEFAULT NULL COMMENT '1--正常 2--锁定',
  `DEL` int(2) NOT NULL DEFAULT '1' COMMENT '删除位 0-删除 1-未删除',
  PRIMARY KEY (`U_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moo_sys_user`
--

LOCK TABLES `moo_sys_user` WRITE;
/*!40000 ALTER TABLE `moo_sys_user` DISABLE KEYS */;
INSERT INTO `moo_sys_user` VALUES ('00001','尼奥','admin','e10adc3949ba59abbe56e057f20f883e','2016-06-29 10:46:22','1037000132','15120022835','000001',1,1),('31578391119134720','许陆晓','xuluxiao111','dadcd4b076b59d2ca19494edd4bef626','2016-12-20 14:35:44',NULL,'15120022835','31578279839006720',1,1),('31578401554300928','陈业明','chenyeming111','9bb5e4fcb65ffc5476336ed6e9a37842','2016-12-20 14:36:23',NULL,'15120022835','31578279839006720',1,1),('31578417111498752','雒云龙','luoyunlong111','c186628c776c6a3020b8d92cc567d01e','2016-12-20 14:37:23',NULL,'15120022812','000001',1,1);
/*!40000 ALTER TABLE `moo_sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-28 21:54:59
