CREATE DATABASE  IF NOT EXISTS `sda_online_store` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sda_online_store`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: sda_online_store
-- ------------------------------------------------------
-- Server version	8.0.16

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
  `id_address` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `id_user` varchar(45) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_address`),
  KEY `FKpmtr515lcs96s5mnrhbakm097` (`id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Oradea','Romania','410473','admin','Dimitrie Cantemir, nr. 21'),(2,'Oradea','Romania','410474','guest','Gheorghe Doja, nr.9');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `id_category` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `id_super_category` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_category`),
  KEY `FKt5oyo65617kfismdk9yh77v7s` (`id_super_category`)
) ENGINE=MyISAM AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'TV, Audio-Video & Photo',NULL),(2,'Laptops, Tablets & Phones',NULL),(3,'PC, Peripherals & Software',NULL),(4,'Home Appliances',NULL),(5,'Gaming',NULL),(6,'Books',NULL),(7,'Televisions',1),(8,'TV Accessorys',1),(9,'Audio',1),(10,'Laptops',2),(11,'Laptop Accessorys',2),(12,'Tablets',2),(13,'Phones',2),(14,'Desktop PCs',3),(15,'Screens',3),(16,'Peripherals',3),(17,'PC Components',3),(18,'Consoles',5),(19,'PC gaming',5),(20,'Games',5),(21,'Fiction',6),(22,'Psychology',6),(23,'History',6),(24,'IT',6),(25,'HD & Full HD TVs',7),(26,'4K Ultra HD TVs',7),(27,'Cables and Adapters',8),(28,'Soundbars',9),(29,'Home Cinemas',9),(30,'Asus',10),(31,'Apple',10),(32,'HP',10),(33,'Acer',10),(34,'External HDDs',11),(35,'Apple',12),(36,'Huawai',12),(37,'Samsung',12),(38,'Apple',13),(39,'Huawai',13),(40,'Samsung',13),(41,'Mouses',16),(42,'Keyboards',16),(43,'Motherboards',17),(44,'Video Cards',17),(45,'Xboxs',18),(46,'Playstations',18),(47,'Gaming Desktops',19),(48,'Gaming Laptops',19),(49,'Xbox Games',20),(50,'Playstation Games',20),(51,'PC Games',20),(52,'Refrigerators',4),(53,'Stoves',4),(54,'Washing Machines',4),(55,'Air-conditioning devices',4);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `total_cost` double NOT NULL,
  `id_status` int(11) DEFAULT NULL,
  `id_user` varchar(45) DEFAULT NULL,
  `date_of_order` date DEFAULT NULL,
  PRIMARY KEY (`id_order`),
  KEY `FKhjqx4af4r3yjk6vr1ukk7fykw` (`id_status`),
  KEY `FKgjl21ndpbn5ndl6qwbx14y8wq` (`id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_item` (
  `id_order_item` int(11) NOT NULL AUTO_INCREMENT,
  `product_price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `id_order` int(11) DEFAULT NULL,
  `id_product` int(11) DEFAULT NULL,
  `id_user` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_order_item`),
  KEY `FK91bchbncxidkjypdysx5pvwyb` (`id_order`),
  KEY `FKhpraxq28c9p8aljm2jebpun9s` (`id_product`),
  KEY `FKmapf29bt6jk73sn5354pnbmav` (`id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `id_product` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `item_price` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT 'default',
  `id_category` int(11) DEFAULT NULL,
  `id_supplier` int(11) DEFAULT NULL,
  `date_added` date DEFAULT NULL,
  PRIMARY KEY (`id_product`),
  KEY `FK5cxv31vuhc7v32omftlxa8k3c` (`id_category`),
  KEY `FK7vecnfptx4ologqg55y3v7mbm` (`id_supplier`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Televizor Smart LED Star-Light, 81 cm, 32DM6600, HD',549.1,'Televizor LED Smart Star-Light','Televizor LED Smart Star-Light',25,1,'2020-02-13'),(2,'Televizor LED Smart Samsung, 108 cm, 43RU7102, 4K Ultra HD',1499.99,'Televizor LED Smart Samsung','Televizor LED Smart Samsung',25,2,'2020-02-13'),(3,'Televizor LED Smart LG, 108 cm, 43UM7100PLB, 4K Ultra HD',1499.99,'Televizor LED Smart LG','Televizor LED Smart LG',25,3,'2020-02-13'),(4,'Televizor LED Smart Samsung, 108 cm, 43RU7102, 4K Ultra HD',1499.99,'Televizor LED Smart Samsung 4K','Televizor LED Smart Samsung 4k',26,2,'2020-02-14'),(5,'Televizor LED Smart Philips, 126 cm, 50PUS6804/12, 4K Ultra HD',2199.99,'Televizor LED Smart Philips 4K','Televizor LED Smart Philips 4k',26,4,'2020-02-14'),(39,'Tet Product 30description',0,'Tet Product 30','default',26,3,'2020-02-23');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `status` (
  `id_stauts` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_stauts`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'In progress'),(2,'Order placed'),(3,'Delivered');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `supplier` (
  `id_supplier` int(11) NOT NULL AUTO_INCREMENT,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_supplier`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,NULL,'Star-Light'),(2,NULL,'Samsung'),(3,NULL,'LG'),(4,NULL,'Philips');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `admin` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id_address` int(11) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `logo` varchar(45) DEFAULT 'default',
  PRIMARY KEY (`username`),
  KEY `FKmowmggc5uiyuix3pmqay8nf0h` (`id_address`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin',_binary '','Flaviu','Pop','$2a$10$7Yz61vmQNb54mZMiNCVF3OXomZ/H4cgOZhxbXpvzHvMUcNqoIE3x2',1,'popflaviufelician@yahoo.com','Flaviu'),('guest',_binary '\0','Test','Bot','$2a$10$ftcaktuPoZvGpNZZY3DgquOu2QiGhqwHjNJlWNzva3ATlkLlhohnm',2,'testbot@gmail.com','default');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sda_online_store'
--

--
-- Dumping routines for database 'sda_online_store'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-26 17:26:01
