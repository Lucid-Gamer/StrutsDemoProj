-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: spring_demo_db
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `com_ldgr_dbrd_menu_mst`
--

DROP TABLE IF EXISTS `com_ldgr_dbrd_menu_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `com_ldgr_dbrd_menu_mst` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(30) NOT NULL,
  `menu_tab_id` varchar(50) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 0,
  `maker_cd` int(11) DEFAULT NULL,
  `maker_dt` date DEFAULT curdate(),
  `author_cd` int(11) DEFAULT NULL,
  `author_dt` date DEFAULT curdate(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `com_ldgr_dbrd_menu_mst`
--

LOCK TABLES `com_ldgr_dbrd_menu_mst` WRITE;
/*!40000 ALTER TABLE `com_ldgr_dbrd_menu_mst` DISABLE KEYS */;
INSERT INTO `com_ldgr_dbrd_menu_mst` VALUES (1,100,'User Management','usrMngmnt',1,0,'2024-12-16',0,'2024-12-16'),(2,200,'Building Management','bldngMngmnt',1,0,'2024-12-16',0,'2024-12-16'),(3,300,'Apartment Management','aptmntMngmnt',1,0,'2024-12-16',0,'2024-12-16'),(4,400,'Occupant Management','occpntMngmnt',1,0,'2024-12-16',0,'2024-12-16');
/*!40000 ALTER TABLE `com_ldgr_dbrd_menu_mst` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-19 18:36:50
