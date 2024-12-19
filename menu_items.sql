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
-- Table structure for table `com_ldgr_dbrd_menu_items_mst`
--

DROP TABLE IF EXISTS `com_ldgr_dbrd_menu_items_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `com_ldgr_dbrd_menu_items_mst` (
  `id` int(11) NOT NULL,
  `menu_item_name` varchar(30) DEFAULT NULL,
  `menu_item_name_id` varchar(50) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 0,
  `maker_cd` int(11) DEFAULT NULL,
  `maker_dt` date DEFAULT curdate(),
  `author_cd` int(11) DEFAULT NULL,
  `author_dt` date DEFAULT curdate(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `com_ldgr_dbrd_menu_items_mst`
--

LOCK TABLES `com_ldgr_dbrd_menu_items_mst` WRITE;
/*!40000 ALTER TABLE `com_ldgr_dbrd_menu_items_mst` DISABLE KEYS */;
INSERT INTO `com_ldgr_dbrd_menu_items_mst` VALUES (1,'User Maker','100_UserMaker',100,1,0,'2024-12-16',0,'2024-12-16'),(2,'User Author','100_UserAuthor',100,1,0,'2024-12-16',0,'2024-12-16'),(3,'User Reader','100_UserReader',100,1,0,'2024-12-16',0,'2024-12-16'),(4,'User Updater','100_UserUpdater',100,1,0,'2024-12-16',0,'2024-12-16'),(5,'User Deleter','100_UserDeleter',100,1,0,'2024-12-16',0,'2024-12-16'),(6,'Building Maker','200_BuildingMaker',200,1,0,'2024-12-16',0,'2024-12-16'),(7,'Building Author','200_BuildingAuthor',200,1,0,'2024-12-16',0,'2024-12-16'),(8,'Building Reader','200_BuildingReader',200,1,0,'2024-12-16',0,'2024-12-16'),(9,'Building Updater','200_BuildingUpdater',200,1,0,'2024-12-16',0,'2024-12-16'),(10,'Building Deleter','200_BuildingDeleter',200,1,0,'2024-12-16',0,'2024-12-16'),(11,'Apartment Maker','300_ApartmentMaker',300,1,0,'2024-12-16',0,'2024-12-16'),(12,'Apartment Author','300_ApartmentAuthor',300,1,0,'2024-12-16',0,'2024-12-16'),(13,'Apartment Reader','300_ApartmentReader',300,1,0,'2024-12-16',0,'2024-12-16'),(14,'Apartment Updater','300_ApartmentUpdater',300,1,0,'2024-12-16',0,'2024-12-16'),(15,'Apartment Deleter','300_ApartmentDeleter',300,1,0,'2024-12-16',0,'2024-12-16'),(16,'Occupant Maker','400_OccupantMaker',400,1,0,'2024-12-16',0,'2024-12-16'),(17,'Occupant Author','400_OccupantAuthor',400,1,0,'2024-12-16',0,'2024-12-16'),(18,'Occupant Reader','400_OccupantReader',400,1,0,'2024-12-16',0,'2024-12-16'),(19,'Occupant Updater','400_OccupantUpdater',400,1,0,'2024-12-16',0,'2024-12-16'),(20,'Occupant Deleter','400_OccupantDeleter',400,1,0,'2024-12-16',0,'2024-12-16');
/*!40000 ALTER TABLE `com_ldgr_dbrd_menu_items_mst` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-19 18:36:10
