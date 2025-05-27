-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: baixedb
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `baido`
--

DROP TABLE IF EXISTS `baido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baido` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `diaChi` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `soLuong` int NOT NULL,
  `giaTien` decimal(10,2) NOT NULL,
  `tienIch` text,
  `anhBai` varchar(255) DEFAULT NULL,
  `trangThai` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baido`
--

LOCK TABLES `baido` WRITE;
/*!40000 ALTER TABLE `baido` DISABLE KEYS */;
INSERT INTO `baido` VALUES (1,'Bãi xe ACCD','1 Lê Lợi, Q1',3,100000.00,'Bảo vệ 24/7, Camera','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747919669/lkdilvsbdxkfu6qk6aau.png','Hoạt động'),(2,'Bãi xe B','20 Trần Hưng Đạo, Q5',3,20000.00,'Wifi miễn phí',NULL,'Hoạt động'),(3,'Bãi xe C','75 Nguyễn Trãi, Q3',3,15000.00,'Có mái che',NULL,'Bảo trì'),(4,'1','1',1,1.00,'1','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747660890/efp8gsc6l4efrornhgpg.jpg','Hoạt động'),(5,'1','2',3,3.00,'4','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747661090/fygsjxdfw2tq5xfrrgqv.jpg','Hoạt động'),(6,'1','2',3,3.00,'4','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747661107/ztmo1fpqqk3jcq5x3nzp.jpg','Hoạt động'),(7,'1','2',3,3.00,'4','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747661226/rhoi7fbwh8sgjhdq7sta.jpg','Hoạt động'),(8,'Sang','Sang',12,12.00,'12','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747661250/txgay9hirwil0cbvwe6m.jpg','Hoạt động'),(9,'33','3',3,3.00,'3','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747663672/ahlexdnfkdgprtxxf6b7.jpg','Hoạt động'),(10,'333','3',3,3.00,'3','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747663687/wthwe6j3ieiwbwq6wy3g.jpg','Hoạt động'),(11,'1','1',1,1.00,'1','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747664005/q6qlawvhwkhlih13bg6m.jpg','Hoạt động'),(12,'1','1',1,1.00,'1','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747664024/ld3ruj50c0gtv2vwwunn.jpg','Hoạt động'),(13,'Bình','1',1,1.00,'1','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747671326/zlinrkil6qwzhabfvmis.jpg','Hoạt động'),(14,'Sang26/5','1',30,50000.00,NULL,'https://res.cloudinary.com/dbhhpljbo/image/upload/v1748268622/sfx1jrta4oe5semokfrq.png','Hoạt động');
/*!40000 ALTER TABLE `baido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baotri`
--

DROP TABLE IF EXISTS `baotri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baotri` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idBaiDo` int DEFAULT NULL,
  `thoiGianBatDau` datetime NOT NULL,
  `thoiGianKetThuc` datetime NOT NULL,
  `tinhTrang` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idBaiDo` (`idBaiDo`),
  CONSTRAINT `baotri_ibfk_1` FOREIGN KEY (`idBaiDo`) REFERENCES `baido` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baotri`
--

LOCK TABLES `baotri` WRITE;
/*!40000 ALTER TABLE `baotri` DISABLE KEYS */;
INSERT INTO `baotri` VALUES (1,3,'2025-05-08 08:00:00','2025-05-09 08:00:00','Thay mái che'),(2,3,'2025-05-15 09:00:00','2025-05-15 18:00:00','Vệ sinh định kỳ'),(3,3,'2025-06-01 07:00:00','2025-06-01 17:00:00','Bảo trì hệ thống camera');
/*!40000 ALTER TABLE `baotri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idBaiDo` int DEFAULT NULL,
  `idChoDo` int DEFAULT NULL,
  `idNguoiDung` int DEFAULT NULL,
  `thanhTien` decimal(10,2) NOT NULL,
  `thoiGianDat` datetime NOT NULL,
  `thoiGianBatDau` datetime NOT NULL,
  `thoiGianKetThuc` datetime NOT NULL,
  `trangThai` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idBaiDo` (`idBaiDo`),
  KEY `idChoDo` (`idChoDo`),
  KEY `idNguoiDung` (`idNguoiDung`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`idBaiDo`) REFERENCES `baido` (`id`),
  CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`idChoDo`) REFERENCES `chodo` (`id`),
  CONSTRAINT `booking_ibfk_3` FOREIGN KEY (`idNguoiDung`) REFERENCES `nguoidung` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,1,1,1,10000.00,'2025-05-09 08:00:00','2025-05-10 07:00:00','2025-05-10 09:00:00','Hoàn thành'),(2,1,2,2,20000.00,'2025-05-09 09:00:00','2025-05-10 08:00:00','2025-05-10 10:00:00','Đang đặt'),(3,1,3,1,20000.00,'2025-05-09 10:00:00','2025-05-10 09:00:00','2025-05-10 11:00:00','Hủy'),(4,1,1,1,10000.00,'2025-05-22 19:43:16','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(5,1,1,1,10000.00,'2025-05-22 19:43:17','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(6,1,1,1,10000.00,'2025-05-22 19:43:18','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(7,1,1,1,10000.00,'2025-05-22 19:43:19','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(8,1,1,1,10000.00,'2025-05-22 19:43:19','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(9,1,1,1,10000.00,'2025-05-22 19:43:19','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(10,1,1,1,10000.00,'2025-05-22 19:43:20','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(11,1,1,1,10000.00,'2025-05-22 19:43:20','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(12,1,1,1,10000.00,'2025-05-22 19:43:20','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(13,1,1,1,10000.00,'2025-05-22 19:43:20','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(14,1,1,1,10000.00,'2025-05-22 19:43:20','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(15,1,1,1,10000.00,'2025-05-22 19:43:20','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(16,1,1,1,10000.00,'2025-05-22 19:43:21','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(17,1,1,1,10000.00,'2025-05-22 19:44:16','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(18,1,1,1,10000.00,'2025-05-22 19:44:17','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(19,1,2,1,10000.00,'2025-05-22 19:44:19','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(20,1,2,1,10000.00,'2025-05-22 19:44:19','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(21,1,2,1,10000.00,'2025-05-22 19:44:19','2025-05-22 19:43:06','2025-05-22 20:15:00','Đang đặt'),(22,1,1,3,100000.00,'2025-05-23 19:37:41','2025-05-23 19:37:33','2025-05-23 20:15:00','Đang đặt'),(23,1,1,3,100000.00,'2025-05-23 19:37:42','2025-05-23 19:37:33','2025-05-23 20:15:00','Đang đặt'),(24,1,1,3,100000.00,'2025-05-23 19:37:43','2025-05-23 19:37:33','2025-05-23 20:15:00','Đang đặt'),(25,1,1,3,100000.00,'2025-05-23 19:37:43','2025-05-23 19:37:33','2025-05-23 20:15:00','Đang đặt'),(26,1,1,3,100000.00,'2025-05-23 19:37:43','2025-05-23 19:37:33','2025-05-23 20:15:00','Đang đặt'),(27,2,6,1,20000.00,'2025-05-25 11:31:58','2025-05-25 11:31:54','2025-05-25 12:00:00','Đang đặt'),(28,1,2,3,14400000.00,'2025-05-25 19:07:46','2025-05-25 19:07:39','2025-05-31 19:07:39','Đang đặt'),(29,1,1,3,12000000.00,'2025-05-25 19:23:36','2025-05-25 19:23:30','2025-05-30 19:23:30','Đang đặt'),(30,6,15,3,72.00,'2025-05-25 19:32:31','2025-05-25 19:32:24','2025-05-26 19:32:24','Đang đặt'),(31,6,14,3,432.00,'2025-05-25 19:39:36','2025-05-25 19:39:31','2025-05-31 19:39:31','Đang đặt'),(32,4,10,3,144.00,'2025-05-25 19:40:48','2025-05-25 19:40:43','2025-05-31 19:40:43','Đang đặt'),(33,6,16,3,3.00,'2025-05-25 19:45:35','2025-05-25 19:45:03','2025-05-25 20:00:00','Đang đặt'),(34,2,4,3,480000.00,'2025-05-25 20:13:09','2025-05-25 20:13:03','2025-05-26 20:13:03','Đang đặt'),(35,2,6,3,2900000.00,'2025-05-25 21:10:55','2025-05-25 21:10:43','2025-05-31 21:45:00','Đang đặt'),(36,10,36,3,3.00,'2025-05-25 21:13:38','2025-05-25 21:13:29','2025-05-25 21:45:00','Đang đặt'),(37,6,15,3,72.00,'2025-05-26 20:20:50','2025-05-26 20:20:43','2025-05-27 20:20:43','Đang đặt'),(38,6,15,3,72.00,'2025-05-26 20:21:17','2025-05-26 20:20:43','2025-05-27 20:20:43','Đang đặt'),(39,1,2,3,26400000.00,'2025-05-26 20:22:53','2025-06-19 20:22:40','2025-06-30 20:22:40','Đang đặt'),(40,3,8,3,15000.00,'2025-05-26 20:50:08','2025-05-26 20:49:39','2025-05-26 21:45:00','Đang đặt'),(41,3,9,3,15000.00,'2025-05-26 21:06:49','2025-05-26 21:06:41','2025-05-26 21:45:00','Đang đặt'),(42,14,41,3,1200000.00,'2025-05-26 21:10:33','2025-05-26 21:10:28','2025-05-27 21:10:28','Đang đặt'),(43,14,42,3,1200000.00,'2025-05-26 21:14:29','2025-05-26 21:14:25','2025-05-27 21:14:25','Đang đặt'),(44,14,70,3,50000.00,'2025-05-26 21:15:48','2025-05-26 21:15:18','2025-05-26 21:30:00','Đang đặt'),(45,14,70,3,50000.00,'2025-05-26 21:15:48','2025-05-26 21:15:18','2025-05-26 21:30:00','Đang đặt'),(46,14,65,3,50000.00,'2025-05-26 21:26:41','2025-05-26 21:26:34','2025-05-26 21:30:00','Đang đặt'),(47,14,43,3,50000.00,'2025-05-26 21:29:48','2025-05-26 21:29:41','2025-05-26 21:30:00','Đang đặt'),(48,14,44,3,50000.00,'2025-05-26 21:32:25','2025-05-26 21:32:16','2025-05-26 21:45:00','Đang đặt'),(49,14,43,3,50000.00,'2025-05-26 21:46:39','2025-05-26 21:46:25','2025-05-26 22:00:00','Đang đặt'),(50,14,44,3,50000.00,'2025-05-26 21:47:34','2025-05-26 21:47:27','2025-05-26 22:00:00','Đang đặt'),(51,14,43,3,50000.00,'2025-05-26 22:12:12','2025-05-26 22:12:06','2025-05-26 22:15:00','Đang đặt'),(52,14,44,3,50000.00,'2025-05-26 22:16:41','2025-05-26 22:16:35','2025-05-26 22:30:00','Đang đặt'),(53,14,45,3,50000.00,'2025-05-26 22:24:19','2025-05-26 22:24:12','2025-05-26 22:30:00','Đang đặt'),(54,14,49,3,50000.00,'2025-05-26 22:28:47','2025-05-26 22:28:41','2025-05-26 22:30:00','Đang đặt'),(55,14,51,3,50000.00,'2025-05-26 22:30:21','2025-05-26 22:30:15','2025-05-26 22:45:00','Đang đặt'),(56,14,51,3,50000.00,'2025-05-26 22:30:23','2025-05-26 22:30:15','2025-05-26 22:45:00','Đang đặt'),(57,14,51,3,50000.00,'2025-05-26 22:33:37','2025-05-26 22:30:15','2025-05-26 22:45:00','Đang đặt'),(58,14,51,3,50000.00,'2025-05-26 22:33:55','2025-05-26 22:30:15','2025-05-26 22:45:00','Đang đặt'),(59,14,51,3,50000.00,'2025-05-26 22:34:08','2025-05-26 22:30:15','2025-05-26 22:45:00','Đang đặt'),(60,14,51,3,50000.00,'2025-05-26 22:34:31','2025-05-26 22:30:15','2025-05-26 22:45:00','Đang đặt'),(61,14,48,3,50000.00,'2025-05-26 22:41:56','2025-05-26 22:41:51','2025-05-26 22:45:00','Đang đặt'),(62,14,49,3,50000.00,'2025-05-26 23:02:31','2025-05-26 23:02:25','2025-05-26 23:15:00','Đang đặt'),(63,14,43,3,50000.00,'2025-05-26 23:06:34','2025-05-26 23:06:23','2025-05-26 23:30:00','Đang đặt'),(64,14,44,3,50000.00,'2025-05-26 23:10:35','2025-05-26 23:10:31','2025-05-26 23:45:00','Đang đặt'),(65,14,47,3,50000.00,'2025-05-26 23:12:21','2025-05-26 23:12:16','2025-05-26 23:15:00','da_dat'),(66,14,51,3,1200000.00,'2025-05-26 23:13:12','2025-05-26 23:13:07','2025-05-27 23:13:07','da_huy'),(67,14,46,3,50000.00,'2025-05-26 23:23:47','2025-05-26 23:23:42','2025-05-26 23:45:00','da_dat'),(68,14,46,3,50000.00,'2025-05-26 23:25:09','2025-05-26 23:25:02','2025-05-26 23:30:00','da_huy'),(69,14,52,3,50000.00,'2025-05-26 23:25:31','2025-05-26 23:25:26','2025-05-26 23:30:00','da_dat'),(70,14,46,3,50000.00,'2025-05-26 23:29:05','2025-05-26 23:29:00','2025-05-26 23:30:00','da_huy'),(71,14,51,3,50000.00,'2025-05-26 23:29:28','2025-05-26 23:29:24','2025-05-26 23:45:00','da_dat'),(72,14,46,3,50000.00,'2025-05-26 23:31:32','2025-05-26 23:31:28','2025-05-26 23:45:00','da_dat'),(73,14,44,3,50000.00,'2025-05-27 12:11:32','2025-05-27 12:11:27','2025-05-27 12:15:00','da_dat'),(74,14,45,3,1200000.00,'2025-05-27 12:23:40','2025-05-27 12:23:35','2025-05-28 12:23:35','da_dat'),(75,14,46,3,50000.00,'2025-05-27 12:28:32','2025-05-27 12:28:24','2025-05-27 12:45:00','da_dat'),(76,14,45,3,50000.00,'2025-05-27 12:30:35','2025-05-27 12:30:30','2025-05-27 12:45:00','da_dat'),(77,14,46,3,50000.00,'2025-05-27 12:32:25','2025-05-27 12:32:21','2025-05-27 13:00:00','da_dat'),(78,14,46,3,50000.00,'2025-05-27 12:37:44','2025-05-27 12:37:40','2025-05-27 13:15:00','da_huy'),(79,14,46,3,50000.00,'2025-05-27 12:38:01','2025-05-27 12:37:55','2025-05-27 13:00:00','da_dat');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chodo`
--

DROP TABLE IF EXISTS `chodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chodo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idBaiDo` int DEFAULT NULL,
  `viTri` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `trangThai` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idBaiDo` (`idBaiDo`),
  CONSTRAINT `chodo_ibfk_1` FOREIGN KEY (`idBaiDo`) REFERENCES `baido` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chodo`
--

LOCK TABLES `chodo` WRITE;
/*!40000 ALTER TABLE `chodo` DISABLE KEYS */;
INSERT INTO `chodo` VALUES (1,1,'A1','Bình thường'),(2,1,'2','Bình thường'),(3,1,'3','Bảo trì'),(4,2,'A1','Bình thường'),(5,2,'A2','Bảo trì'),(6,2,'3','Bình thường'),(7,3,'1','Bảo trì'),(8,3,'2','Bình thường'),(9,3,'3','Bình thường'),(10,4,'1','Bình thường'),(11,5,'1','Bình thường'),(12,5,'2','Bình thường'),(13,5,'3','Bình thường'),(14,6,'1','Bình thường'),(15,6,'2','Bình thường'),(16,6,'3','Bình thường'),(17,7,'1','Bình thường'),(18,7,'2','Bình thường'),(19,7,'3','Bình thường'),(20,8,'1','Bình thường'),(21,8,'2','Bình thường'),(22,8,'3','Bình thường'),(23,8,'4','Bình thường'),(24,8,'5','Bình thường'),(25,8,'6','Bình thường'),(26,8,'7','Bình thường'),(27,8,'8','Bình thường'),(28,8,'9','Bình thường'),(29,8,'10','Bình thường'),(30,8,'11','Bình thường'),(31,8,'12','Bình thường'),(32,9,'1','Bình thường'),(33,9,'2','Bình thường'),(34,9,'3','Bình thường'),(35,10,'1','Bình thường'),(36,10,'2','Bình thường'),(37,10,'3','Bình thường'),(38,11,'1','Bình thường'),(39,12,'1','Bình thường'),(40,13,'1','Bình thường'),(41,14,'1','Bình thường'),(42,14,'2','Bình thường'),(43,14,'3','Bình thường'),(44,14,'4','Bình thường'),(45,14,'5','Bình thường'),(46,14,'6','Bình thường'),(47,14,'7','Bình thường'),(48,14,'8','Bình thường'),(49,14,'9','Bình thường'),(50,14,'10','Bình thường'),(51,14,'11','Bình thường'),(52,14,'12','Bình thường'),(53,14,'13','Bình thường'),(54,14,'14','Bình thường'),(55,14,'15','Bình thường'),(56,14,'16','Bình thường'),(57,14,'17','Bình thường'),(58,14,'18','Bình thường'),(59,14,'19','Bình thường'),(60,14,'20','Bình thường'),(61,14,'21','Bình thường'),(62,14,'22','Bình thường'),(63,14,'23','Bình thường'),(64,14,'24','Bình thường'),(65,14,'25','Bình thường'),(66,14,'26','Bình thường'),(67,14,'27','Bình thường'),(68,14,'28','Bình thường'),(69,14,'29','Bình thường'),(70,14,'30','Bình thường');
/*!40000 ALTER TABLE `chodo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danhgia`
--

DROP TABLE IF EXISTS `danhgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danhgia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idNguoiDung` int DEFAULT NULL,
  `idBaiDo` int DEFAULT NULL,
  `rating` int NOT NULL,
  `binhLuan` text,
  `thoiGianDanhGia` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idNguoiDung` (`idNguoiDung`),
  KEY `idBaiDo` (`idBaiDo`),
  CONSTRAINT `danhgia_ibfk_1` FOREIGN KEY (`idNguoiDung`) REFERENCES `nguoidung` (`id`),
  CONSTRAINT `danhgia_ibfk_2` FOREIGN KEY (`idBaiDo`) REFERENCES `baido` (`id`),
  CONSTRAINT `danhgia_chk_1` CHECK ((`rating` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhgia`
--

LOCK TABLES `danhgia` WRITE;
/*!40000 ALTER TABLE `danhgia` DISABLE KEYS */;
INSERT INTO `danhgia` VALUES (1,1,1,5,'Rộng rãi, dễ đỗ','2025-05-10 09:00:00'),(2,2,2,4,'Ổn áp, nhân viên thân thiện','2025-05-10 09:30:00'),(3,2,3,3,'Đang bảo trì nên hơi bất tiện','2025-05-10 10:00:00');
/*!40000 ALTER TABLE `danhgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idNguoiDung` int DEFAULT NULL,
  `idBooking` int DEFAULT NULL,
  `phuongThuc` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `thoiGianThanhToan` datetime NOT NULL,
  `trangThai` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `maGD` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idNguoiDung` (`idNguoiDung`),
  KEY `idBooking` (`idBooking`),
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`idNguoiDung`) REFERENCES `nguoidung` (`id`),
  CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`idBooking`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (1,NULL,NULL,'VNPAY','2025-05-26 23:25:52','thanh_cong','14980919'),(2,NULL,NULL,'VNPAY','2025-05-26 23:25:52','thanh_cong','14980919'),(3,NULL,NULL,'VNPAY','2025-05-26 23:29:44','thanh_cong','14980926'),(4,NULL,NULL,'VNPAY','2025-05-26 23:29:44','thanh_cong','14980926'),(5,NULL,NULL,'VNPAY','2025-05-26 23:31:46','thanh_cong','14980930'),(6,NULL,NULL,'VNPAY','2025-05-26 23:31:46','thanh_cong','14980930'),(7,NULL,NULL,'VNPAY','2025-05-27 12:11:57','thanh_cong','14981830'),(8,NULL,NULL,'VNPAY','2025-05-27 12:11:57','thanh_cong','14981830'),(9,NULL,NULL,'VNPAY','2025-05-27 12:23:59','thanh_cong','14981857'),(10,NULL,NULL,'VNPAY','2025-05-27 12:23:59','thanh_cong','14981857'),(11,3,75,'VNPAY','2025-05-27 12:28:50','thanh_cong','14981867'),(12,3,75,'VNPAY','2025-05-27 12:28:50','thanh_cong','14981867'),(13,3,76,'VNPAY','2025-05-27 12:32:03','thanh_cong','14981875'),(14,3,77,'VNPAY','2025-05-27 12:32:42','thanh_cong','14981877'),(15,3,77,'VNPAY','2025-05-27 12:32:42','thanh_cong','14981877'),(16,3,77,'VNPAY','2025-05-27 12:37:33','thanh_cong','14981877'),(17,3,79,'VNPAY','2025-05-27 12:38:17','thanh_cong','14981890');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoidung`
--

DROP TABLE IF EXISTS `nguoidung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nguoidung` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hoTen` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `taiKhoan` varchar(50) NOT NULL,
  `matKhau` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `SDT` varchar(15) NOT NULL,
  `cccd` varchar(20) NOT NULL,
  `hieuXe` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `bienSo` varchar(20) NOT NULL,
  `mauXe` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `anhXe` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `vaiTro` varchar(10) NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `taiKhoan` (`taiKhoan`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `cccd` (`cccd`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoidung`
--

LOCK TABLES `nguoidung` WRITE;
/*!40000 ALTER TABLE `nguoidung` DISABLE KEYS */;
INSERT INTO `nguoidung` VALUES (1,'Nguyễn Văn A','user1','$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO','user1@example.com','0911111111','111111111','Honda','59X1-123.45','Đỏ',NULL,NULL,'ROLE_USER',1),(2,'Trần Thị B','user2','$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO','user2@example.com','0922222222','222222222','Toyota','51H-456.78','Trắng',NULL,NULL,'ROLE_USER',1),(3,'Admin C','admin','$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO','admin@example.com','0933333333','333333333','Mazda','51A-999.99','Đen',NULL,NULL,'ROLE_ADMIN',1),(4,'Trần Huỳnh Sang','Sang','$2a$10$RHtJeeKrQaVsr6zTvtPZXeMrETGgsliLi01K6KW08zjv4kIDL8stq','2251010079sang@ou.edu.vn','0912146801','111','111','111','111','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747649718/ens4rlkmr25ro5yhbfdm.jpg','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747649716/vth8wqeelhgifjmo4z9h.jpg','ROLE_USER',1),(5,'1','1','$2a$10$DOYn11k7vIWl3QLqTv6vn.MnjdRfUBJ6R.bQjimtNyIKz4iYxCCwC','1@gmail.com','1','1','1','1','1','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747657157/vm6w1gte3pvk3kenckzz.jpg','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747657155/ivjipy01ol8vksceqydc.jpg','ROLE_USER',1),(8,'Trần Huỳnh Sang','SangPro','$2a$10$A7hMWytDILTkztgldLFg5elzklGzPgOoHOjSEwaHTckGhtNvOrZy2','2251010079sang2@ou.edu.vn','0912121212','0912121212','SSS','69H1-33333','Đỏ','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747663943/vbhrafofu6d1aax5qlce.jpg','https://res.cloudinary.com/dbhhpljbo/image/upload/v1747663941/nd4qfl8fkledpjmwybzf.jpg','ROLE_USER',1);
/*!40000 ALTER TABLE `nguoidung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thongbao`
--

DROP TABLE IF EXISTS `thongbao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thongbao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idNguoiDung` int DEFAULT NULL,
  `noiDung` text NOT NULL,
  `thoiGianThongBao` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idNguoiDung` (`idNguoiDung`),
  CONSTRAINT `thongbao_ibfk_1` FOREIGN KEY (`idNguoiDung`) REFERENCES `nguoidung` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thongbao`
--

LOCK TABLES `thongbao` WRITE;
/*!40000 ALTER TABLE `thongbao` DISABLE KEYS */;
INSERT INTO `thongbao` VALUES (1,1,'Bạn đã đặt chỗ thành công.','2025-05-10 07:00:00'),(2,2,'Thanh toán thành công.','2025-05-10 10:00:00'),(3,3,'Chỗ đỗ đã bị hủy.','2025-05-10 11:00:00');
/*!40000 ALTER TABLE `thongbao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-27 13:15:26
