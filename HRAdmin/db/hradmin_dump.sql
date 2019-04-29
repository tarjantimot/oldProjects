CREATE DATABASE  IF NOT EXISTS `hradmin` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci */;
USE `hradmin`;
-- MySQL dump 10.13  Distrib 8.0.14, for Win64 (x86_64)
--
-- Host: localhost    Database: hradmin
-- ------------------------------------------------------
-- Server version	8.0.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(45) COLLATE utf8_hungarian_ci NOT NULL,
  `postal_code` varchar(45) COLLATE utf8_hungarian_ci NOT NULL,
  `city` varchar(85) COLLATE utf8_hungarian_ci NOT NULL,
  `street` varchar(45) COLLATE utf8_hungarian_ci NOT NULL,
  `street_number` varchar(10) COLLATE utf8_hungarian_ci NOT NULL,
  `floor` int(11) DEFAULT NULL,
  `door` int(11) DEFAULT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `address_employee_idx` (`employee_id`),
  CONSTRAINT `address_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_type` varchar(45) COLLATE utf8_hungarian_ci NOT NULL,
  `c_value` varchar(45) COLLATE utf8_hungarian_ci NOT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `contact_employee_fk_idx` (`employee_id`),
  CONSTRAINT `contact_employee_fk` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` date NOT NULL,
  `expiry_date` date NOT NULL,
  `path` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `contract_employee_fk_idx` (`employee_id`),
  CONSTRAINT `contract_employee_fk` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) COLLATE utf8_hungarian_ci NOT NULL,
  `last_name` varchar(45) COLLATE utf8_hungarian_ci NOT NULL,
  `birth_place` varchar(85) COLLATE utf8_hungarian_ci NOT NULL,
  `birth_date` date NOT NULL,
  `status` varchar(25) COLLATE utf8_hungarian_ci NOT NULL,
  `status_last_modified` date NOT NULL,
  `pdata_id` int(11) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_pdata_fk_idx` (`pdata_id`),
  KEY `employee_job_fk_idx` (`job_id`),
  CONSTRAINT `employee_job_fk` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `employee_pdata_fk` FOREIGN KEY (`pdata_id`) REFERENCES `pdata` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (9,'asdasdasd','asdasd','asdasdasd','2019-04-01','asdasd','2019-04-02',13,31);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_title` varchar(45) COLLATE utf8_hungarian_ci NOT NULL,
  `job_description` varchar(3000) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `min_salary` double NOT NULL,
  `max_salary` double NOT NULL,
  `worker_num` int(11) NOT NULL,
  `location` varchar(85) COLLATE utf8_hungarian_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (3,'UIX designer','','',260000,320000,0,'Budapest'),(25,'SAP UI5 Developer','','',200000,280000,1,'Budapest'),(26,'IT Architect','','',380000,520000,1,'Szeged'),(31,'Test',NULL,'',150000,170000,4,'Budapest');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pdata`
--

DROP TABLE IF EXISTS `pdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pdata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tax_num` varchar(15) COLLATE utf8_hungarian_ci NOT NULL,
  `identification_num` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `insurrance_num` varchar(15) COLLATE utf8_hungarian_ci NOT NULL,
  `personal_num` varchar(15) COLLATE utf8_hungarian_ci NOT NULL,
  `other` varchar(45) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `other_type` varchar(45) COLLATE utf8_hungarian_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pdata`
--

LOCK TABLES `pdata` WRITE;
/*!40000 ALTER TABLE `pdata` DISABLE KEYS */;
INSERT INTO `pdata` VALUES (7,'asdsadad','asdsadad','asdsadad','asdsadad','asdsadad','asdsadad'),(9,'XYCYXCYXC','XYCYXCYXC','XYCYXCYXC','XYCYXCYXC','XYCYXCYXC','XYCYXCYXC'),(10,'ASDASDA','ASDASDA','ASDASDA','ASDASDA','ASDASDA','ASDASDA'),(11,'asdasdasda','asdasdasda','asdasdasda','asdasdasda','asdasdasda','asdasdasda'),(12,'asdaasd','asdaasd','asdaasd','asdaasd','asdaasd','asdaasd'),(13,'asdasd','asdasd','asdasd','asdasd','asdasd','asdasd');
/*!40000 ALTER TABLE `pdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `salary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `amount` double NOT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `salary_employee_fk_idx` (`employee_id`),
  CONSTRAINT `salary_employee_fk` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-15 15:58:21
