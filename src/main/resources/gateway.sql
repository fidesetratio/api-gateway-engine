-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: gateway
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.16.04.1

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
-- Table structure for table `authentication_provider`
--

DROP TABLE IF EXISTS `authentication_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authentication_provider` (
  `providerId` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `clientSecret` varchar(255) DEFAULT NULL,
  `provider_name` varchar(32) DEFAULT NULL,
  `type_provider` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`providerId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authentication_provider`
--

LOCK TABLES `authentication_provider` WRITE;
/*!40000 ALTER TABLE `authentication_provider` DISABLE KEYS */;
INSERT INTO `authentication_provider` VALUES (4,'N','123456','clientapp','InternalProvider','oauth2','http://localhost:8787/oauth/check_token');
/*!40000 ALTER TABLE `authentication_provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `links`
--

DROP TABLE IF EXISTS `links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `links` (
  `linkId` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` varchar(255) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  `context` varchar(32) DEFAULT NULL,
  `creationDateTime` datetime DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `permitall` varchar(255) DEFAULT NULL,
  `providerId` bigint(20) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `sensitiveHeaders` varchar(255) DEFAULT NULL,
  `service_id` varchar(32) DEFAULT NULL,
  `stripPrefix` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`linkId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `links`
--

LOCK TABLES `links` WRITE;
/*!40000 ALTER TABLE `links` DISABLE KEYS */;
/*!40000 ALTER TABLE `links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `roleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  KEY `FK5gs1esew9aj28627pj40dvmyn` (`category_id`),
  CONSTRAINT `FK5gs1esew9aj28627pj40dvmyn` FOREIGN KEY (`category_id`) REFERENCES `roles_categories` (`roleCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_categories`
--

DROP TABLE IF EXISTS `roles_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_categories` (
  `roleCategoryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_description` varchar(255) DEFAULT NULL,
  `category_name` varchar(32) DEFAULT NULL,
  `creationDateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`roleCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_categories`
--

LOCK TABLES `roles_categories` WRITE;
/*!40000 ALTER TABLE `roles_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmp`
--

DROP TABLE IF EXISTS `tmp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tmp` (
  `roleCategoryId` bigint(20) NOT NULL DEFAULT '0',
  `category_description` varchar(255) DEFAULT NULL,
  `category_name` varchar(32) DEFAULT NULL,
  `creationDateTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmp`
--

LOCK TABLES `tmp` WRITE;
/*!40000 ALTER TABLE `tmp` DISABLE KEYS */;
INSERT INTO `tmp` VALUES (2,'MPolicy','MPolicy','2019-04-06 09:18:34'),(3,'Problem Categroy','Problem','2019-05-18 19:40:24');
/*!40000 ALTER TABLE `tmp` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-02  6:06:45
