-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.18-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para db_empresa
CREATE DATABASE IF NOT EXISTS `db_empresa` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `db_empresa`;

-- Volcando estructura para tabla db_empresa.categoria
CREATE TABLE IF NOT EXISTS `categoria` (
  `cod_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) DEFAULT NULL,
  `imagen` text DEFAULT NULL,
  `extension` varchar(50) DEFAULT NULL,
  `publicado` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;


-- Volcando estructura para tabla db_empresa.producto
CREATE TABLE IF NOT EXISTS `producto` (
  `cod_producto` int(11) NOT NULL AUTO_INCREMENT,
  `cod_categoria` int(11) DEFAULT 0,
  `nombre` varchar(250) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `imagen` text DEFAULT NULL,
  `extension` varchar(50) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `publicado` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_producto`),
  KEY `FK__categoria` (`cod_categoria`),
  CONSTRAINT `FK__categoria` FOREIGN KEY (`cod_categoria`) REFERENCES `categoria` (`cod_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
