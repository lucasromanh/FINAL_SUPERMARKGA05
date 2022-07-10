-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: supermark_ga05
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `pagotarjeta`
--

DROP TABLE IF EXISTS `pagotarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagotarjeta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `banco_emisor` varchar(50) DEFAULT NULL,
  `numero_tarjeta` int NOT NULL,
  `nombre_tarjeta` varchar(30) DEFAULT NULL,
  `fecha_vencimiento` date NOT NULL,
  `cod_seg` int NOT NULL,
  `id_comprobante` int NOT NULL,
  `id_cliente` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comprobante_pt_fk_idx` (`id_comprobante`),
  KEY `cliente_pt_fk_idx` (`id_cliente`),
  CONSTRAINT `cliente_pt_fk` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `comprobante_pt_fk` FOREIGN KEY (`id_comprobante`) REFERENCES `comprobante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagotarjeta`
--

LOCK TABLES `pagotarjeta` WRITE;
/*!40000 ALTER TABLE `pagotarjeta` DISABLE KEYS */;
INSERT INTO `pagotarjeta` VALUES (1,'macro',1231231231,'JOANA MARTINEZ DE OZ','2023-03-12',123,2,2);
/*!40000 ALTER TABLE `pagotarjeta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-10 12:16:14
