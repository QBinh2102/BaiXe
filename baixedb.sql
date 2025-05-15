-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: baixedb
-- ------------------------------------------------------
-- Server version	8.0.42

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baido`
--

LOCK TABLES `baido` WRITE;
/*!40000 ALTER TABLE `baido` DISABLE KEYS */;
INSERT INTO `baido` VALUES (1,'Bãi xe A','1 Lê Lợi, Q1',3,10000.00,'Bảo vệ 24/7, Camera',NULL,'Hoạt động'),(2,'Bãi xe B','20 Trần Hưng Đạo, Q5',3,20000.00,'Wifi miễn phí',NULL,'Hoạt động'),(3,'Bãi xe C','75 Nguyễn Trãi, Q3',3,15000.00,'Có mái che',NULL,'Bảo trì');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,1,1,1,10000.00,'2025-05-09 08:00:00','2025-05-10 07:00:00','2025-05-10 09:00:00','Hoàn thành'),(2,1,2,2,20000.00,'2025-05-09 09:00:00','2025-05-10 08:00:00','2025-05-10 10:00:00','Đang đặt'),(3,1,3,1,20000.00,'2025-05-09 10:00:00','2025-05-10 09:00:00','2025-05-10 11:00:00','Hủy');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chodo`
--

LOCK TABLES `chodo` WRITE;
/*!40000 ALTER TABLE `chodo` DISABLE KEYS */;
INSERT INTO `chodo` VALUES (1,1,'1','Bình thường'),(2,1,'2','Bình thường'),(3,1,'3','Bảo trì'),(4,2,'1','Bình thường'),(5,2,'2','Bảo trì'),(6,2,'3','Bình thường'),(7,3,'1','Bảo trì'),(8,3,'2','Bình thường'),(9,3,'3','Bình thường');
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
  PRIMARY KEY (`id`),
  KEY `idNguoiDung` (`idNguoiDung`),
  KEY `idBooking` (`idBooking`),
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`idNguoiDung`) REFERENCES `nguoidung` (`id`),
  CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`idBooking`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (1,1,1,'Tiền mặt','2025-05-10 09:05:00','Đã thanh toán'),(2,2,2,'Chuyển khoản','2025-05-10 10:05:00','Đã thanh toán'),(3,3,3,'Thẻ','2025-05-10 11:05:00','Đã hủy');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoidung`
--

LOCK TABLES `nguoidung` WRITE;
/*!40000 ALTER TABLE `nguoidung` DISABLE KEYS */;
INSERT INTO `nguoidung` VALUES (1,'Nguyễn Văn A','user1','$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO','user1@example.com','0911111111','111111111','Honda','59X1-123.45','Đỏ',NULL,NULL,'ROLE_USER',1),(2,'Trần Thị B','user2','$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO','user2@example.com','0922222222','222222222','Toyota','51H-456.78','Trắng',NULL,NULL,'ROLE_USER',1),(3,'Admin C','admin','$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO','admin@example.com','0933333333','333333333','Mazda','51A-999.99','Đen',NULL,NULL,'ROLE_ADMIN',1);
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

-- Dump completed on 2025-05-13 20:28:00
