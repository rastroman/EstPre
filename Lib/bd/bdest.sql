/*
SQLyog Community v9.30 
MySQL - 5.5.16 : Database - proyectoest
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`proyectoest` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `proyectoest`;

/*Table structure for table `amef` */

DROP TABLE IF EXISTS `amef`;

CREATE TABLE `amef` (
  `idAMEF` int(11) NOT NULL AUTO_INCREMENT,
  `idParte` int(11) NOT NULL,
  `idFormato` int(11) NOT NULL,
  `codeAmef` varchar(80) NOT NULL,
  PRIMARY KEY (`idAMEF`),
  KEY `ParteAMEF` (`idParte`),
  KEY `FormatoAmef` (`idFormato`),
  CONSTRAINT `FormatoAmef` FOREIGN KEY (`idFormato`) REFERENCES `formato` (`idFormato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ParteAMEF` FOREIGN KEY (`idParte`) REFERENCES `parte` (`idParte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=latin1;

/*Data for the table `amef` */

insert  into `amef`(`idAMEF`,`idParte`,`idFormato`,`codeAmef`) values (1,1,3,'AMEF712.X02A.023'),(9,9,3,'test'),(18,18,3,'test'),(19,19,3,'test'),(20,20,3,'AMEF56456746'),(30,30,3,'test'),(38,38,3,'testing'),(39,39,3,'testing'),(41,41,3,'testing'),(51,51,3,'test'),(53,53,3,'test'),(54,54,3,'55test'),(56,56,3,'1test'),(58,58,3,'53'),(60,60,3,'test'),(61,61,3,'test'),(63,63,3,'test'),(69,69,3,'test');

/*Table structure for table `anexos` */

DROP TABLE IF EXISTS `anexos`;

CREATE TABLE `anexos` (
  `idAnexos` int(11) NOT NULL AUTO_INCREMENT,
  `idParte` int(11) NOT NULL,
  `nombreAnexo` varchar(100) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  PRIMARY KEY (`idAnexos`),
  KEY `ParteAnexo` (`idParte`),
  CONSTRAINT `ParteAnexo` FOREIGN KEY (`idParte`) REFERENCES `parte` (`idParte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `anexos` */

/*Table structure for table `aprobo` */

DROP TABLE IF EXISTS `aprobo`;

CREATE TABLE `aprobo` (
  `idAprobo` int(11) NOT NULL AUTO_INCREMENT,
  `idPersona` int(11) NOT NULL,
  PRIMARY KEY (`idAprobo`),
  KEY `PersonaAprobo` (`idPersona`),
  CONSTRAINT `PersonaAprobo` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `aprobo` */

insert  into `aprobo`(`idAprobo`,`idPersona`) values (1,6),(3,7),(2,8);

/*Table structure for table `bl200` */

DROP TABLE IF EXISTS `bl200`;

CREATE TABLE `bl200` (
  `idBL200` int(11) NOT NULL AUTO_INCREMENT,
  `idMaquina` int(11) NOT NULL,
  `idHojaCondiciones` int(11) NOT NULL,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `idFormato` int(11) NOT NULL,
  `idDepartamento` int(10) NOT NULL,
  `idMaterial` int(10) NOT NULL,
  `presionBalanza` double NOT NULL,
  `RPM` double NOT NULL,
  `alturaTroquel` double NOT NULL,
  `liberacion` varchar(40) NOT NULL,
  `presionNiveladorEnt` varchar(50) NOT NULL,
  `presionNiveladorSal` varchar(50) NOT NULL,
  `sujecionTroquelSup` varchar(80) NOT NULL,
  `sujecionTroquelInf` varchar(80) NOT NULL,
  `Observaciones` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`idBL200`),
  KEY `MaquinaBL200` (`idMaquina`),
  KEY `HojaCondBL200` (`idHojaCondiciones`),
  KEY `TroquelBL200` (`idTroquel`),
  KEY `SecuenciaBL200` (`idSecuencia`),
  KEY `FormatoBL200` (`idFormato`),
  KEY `FK_bl200_departamento` (`idDepartamento`),
  KEY `FK_bl200_material` (`idMaterial`),
  CONSTRAINT `FK_bl200_departamento` FOREIGN KEY (`idDepartamento`) REFERENCES `departamento` (`idDepartamento`),
  CONSTRAINT `FK_bl200_material` FOREIGN KEY (`idMaterial`) REFERENCES `material` (`idMaterial`),
  CONSTRAINT `FormatoBL200` FOREIGN KEY (`idFormato`) REFERENCES `formato` (`idFormato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `HojaCondBL200` FOREIGN KEY (`idHojaCondiciones`) REFERENCES `hojacondiciones` (`idHojaCondiciones`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MaquinaBL200` FOREIGN KEY (`idMaquina`) REFERENCES `maquina` (`idTipoMaquina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `SecuenciaBL200` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelBL200` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `bl200` */

insert  into `bl200`(`idBL200`,`idMaquina`,`idHojaCondiciones`,`idTroquel`,`idSecuencia`,`idFormato`,`idDepartamento`,`idMaterial`,`presionBalanza`,`RPM`,`alturaTroquel`,`liberacion`,`presionNiveladorEnt`,`presionNiveladorSal`,`sujecionTroquelSup`,`sujecionTroquelInf`,`Observaciones`) values (3,1,3,3,2,9,1,1,21,22,2,'ON','21','21','23','65','564');

/*Table structure for table `bl400` */

DROP TABLE IF EXISTS `bl400`;

CREATE TABLE `bl400` (
  `idBL400` int(11) NOT NULL AUTO_INCREMENT,
  `idMaquina` int(11) NOT NULL,
  `idHojaCondiciones` int(11) NOT NULL,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `idFormato` int(11) NOT NULL,
  `idDepartamento` int(11) NOT NULL,
  `idMaterial` int(11) NOT NULL,
  `presionBalanza` double NOT NULL,
  `rpm` double NOT NULL,
  `alturaTroquel` double NOT NULL,
  `angAlim` varchar(60) NOT NULL,
  `pilotRoll` varchar(60) NOT NULL,
  `presionNiveladorEnt` varchar(50) NOT NULL,
  `presionNiveladorSal` varchar(50) NOT NULL,
  `sujecionTroquelSup` varchar(80) NOT NULL,
  `sujecionTroquelInf` varchar(80) NOT NULL,
  `Observaciones` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`idBL400`),
  KEY `Maquina` (`idMaquina`),
  KEY `HojaCond` (`idHojaCondiciones`),
  KEY `Troquel` (`idTroquel`),
  KEY `SecuenciaBL400` (`idSecuencia`),
  KEY `FormatoBL400` (`idFormato`),
  KEY `FK_bl400_departamento` (`idDepartamento`),
  KEY `FK_bl400_material` (`idMaterial`),
  CONSTRAINT `FK_bl400_departamento` FOREIGN KEY (`idDepartamento`) REFERENCES `departamento` (`idDepartamento`),
  CONSTRAINT `FK_bl400_material` FOREIGN KEY (`idMaterial`) REFERENCES `material` (`idMaterial`),
  CONSTRAINT `FormatoBL400` FOREIGN KEY (`idFormato`) REFERENCES `formato` (`idFormato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `HojaCondBL400` FOREIGN KEY (`idHojaCondiciones`) REFERENCES `hojacondiciones` (`idHojaCondiciones`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MaquinaBL400` FOREIGN KEY (`idMaquina`) REFERENCES `maquina` (`idTipoMaquina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `SecuenciaBL400` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelBL400` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `bl400` */

insert  into `bl400`(`idBL400`,`idMaquina`,`idHojaCondiciones`,`idTroquel`,`idSecuencia`,`idFormato`,`idDepartamento`,`idMaterial`,`presionBalanza`,`rpm`,`alturaTroquel`,`angAlim`,`pilotRoll`,`presionNiveladorEnt`,`presionNiveladorSal`,`sujecionTroquelSup`,`sujecionTroquelInf`,`Observaciones`) values (3,2,3,6,3,10,1,1,541,233,21,'23','323','211','521','12','21','21');

/*Table structure for table `bl800` */

DROP TABLE IF EXISTS `bl800`;

CREATE TABLE `bl800` (
  `idBL800` int(11) NOT NULL AUTO_INCREMENT,
  `idMaquina` int(11) NOT NULL,
  `idHojaCondiciones` int(11) NOT NULL,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `idFormato` int(11) NOT NULL,
  `idDepartamento` int(11) NOT NULL,
  `idMaterial` int(11) NOT NULL,
  `presionBalanza` double NOT NULL,
  `rpm` double NOT NULL,
  `alturaTroquel` double NOT NULL,
  `angAlim` varchar(60) NOT NULL,
  `pilotRoll` varchar(60) NOT NULL,
  `presionNiveladorEnt` varchar(50) NOT NULL,
  `presionNiveladorSal` varchar(50) NOT NULL,
  `sujecionTroquelSup` varchar(80) NOT NULL,
  `sujecionTroquelInf` varchar(80) NOT NULL,
  `speedSetting` varchar(80) NOT NULL,
  `alineacion` varchar(80) NOT NULL,
  `liberacion` varchar(80) NOT NULL,
  `Observaciones` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`idBL800`),
  KEY `Maquina` (`idMaquina`),
  KEY `HojaCond` (`idHojaCondiciones`),
  KEY `Troquel` (`idTroquel`),
  KEY `SecuenciaBL400` (`idSecuencia`),
  KEY `FormatoBL400` (`idFormato`),
  KEY `FK_bl400_departamento` (`idDepartamento`),
  KEY `FK_bl400_material` (`idMaterial`),
  CONSTRAINT `bl800_ibfk_1` FOREIGN KEY (`idDepartamento`) REFERENCES `departamento` (`idDepartamento`),
  CONSTRAINT `bl800_ibfk_2` FOREIGN KEY (`idMaterial`) REFERENCES `material` (`idMaterial`),
  CONSTRAINT `bl800_ibfk_3` FOREIGN KEY (`idFormato`) REFERENCES `formato` (`idFormato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `bl800_ibfk_4` FOREIGN KEY (`idHojaCondiciones`) REFERENCES `hojacondiciones` (`idHojaCondiciones`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `bl800_ibfk_5` FOREIGN KEY (`idMaquina`) REFERENCES `maquina` (`idTipoMaquina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `bl800_ibfk_6` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `bl800_ibfk_7` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Data for the table `bl800` */

insert  into `bl800`(`idBL800`,`idMaquina`,`idHojaCondiciones`,`idTroquel`,`idSecuencia`,`idFormato`,`idDepartamento`,`idMaterial`,`presionBalanza`,`rpm`,`alturaTroquel`,`angAlim`,`pilotRoll`,`presionNiveladorEnt`,`presionNiveladorSal`,`sujecionTroquelSup`,`sujecionTroquelInf`,`speedSetting`,`alineacion`,`liberacion`,`Observaciones`) values (3,3,3,3,1,5,1,1,21,23,32,'21','20','20','20','32','12','15','541','ON','45');

/*Table structure for table `caracteristicaplan` */

DROP TABLE IF EXISTS `caracteristicaplan`;

CREATE TABLE `caracteristicaplan` (
  `idCaracteristicaPlan` int(11) NOT NULL AUTO_INCREMENT,
  `tipoCaracteristica` varchar(45) NOT NULL,
  `NombreCaracteristica` int(11) NOT NULL,
  `idJig` int(11) DEFAULT NULL,
  `idNormasTolerancia` int(11) NOT NULL,
  PRIMARY KEY (`idCaracteristicaPlan`),
  KEY `MaquinaCaracteristicaP` (`idJig`),
  KEY `NormasCaracteristicasP` (`idNormasTolerancia`),
  CONSTRAINT `MaquinaCaracteristicaP` FOREIGN KEY (`idJig`) REFERENCES `jig` (`idJig`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `NormasCaracteristicasP` FOREIGN KEY (`idNormasTolerancia`) REFERENCES `normastolerancias` (`idnormasTolerancias`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `caracteristicaplan` */

/*Table structure for table `caracteristicaprocesos` */

DROP TABLE IF EXISTS `caracteristicaprocesos`;

CREATE TABLE `caracteristicaprocesos` (
  `idCaracteristicaProcesos` int(11) NOT NULL AUTO_INCREMENT,
  `idCaracteristicas` int(11) NOT NULL,
  `idProcesos` int(11) NOT NULL,
  `idMatrizCaracteristicas` int(10) DEFAULT NULL,
  PRIMARY KEY (`idCaracteristicaProcesos`),
  KEY `CaracteristicasProcesos` (`idCaracteristicas`),
  KEY `ProcesosCara` (`idProcesos`),
  KEY `FK_caracteristicaprocesos_matrizcaracteristicas` (`idMatrizCaracteristicas`),
  CONSTRAINT `CaracteristicasProcesos` FOREIGN KEY (`idCaracteristicas`) REFERENCES `caracteristicas` (`idCaracteristicas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_caracteristicaprocesos_matrizcaracteristicas` FOREIGN KEY (`idMatrizCaracteristicas`) REFERENCES `matrizcaracteristicas` (`idMatrizCaracteristicas`),
  CONSTRAINT `ProcesosCara` FOREIGN KEY (`idProcesos`) REFERENCES `procesos` (`idProcesos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=latin1;

/*Data for the table `caracteristicaprocesos` */

insert  into `caracteristicaprocesos`(`idCaracteristicaProcesos`,`idCaracteristicas`,`idProcesos`,`idMatrizCaracteristicas`) values (1,1,2,1),(25,10,2,9),(26,10,17,9),(27,10,16,9),(28,10,25,9),(29,10,29,9),(30,10,24,9),(31,11,2,10),(32,11,16,10),(33,11,29,10),(36,13,4,12),(37,13,16,12),(43,16,4,15),(44,16,16,15),(45,16,19,15),(46,16,27,15),(47,16,27,15),(48,16,7,15),(71,23,4,22),(72,23,16,22),(73,23,17,22),(74,24,6,23),(75,24,18,23),(76,24,23,23),(79,26,2,26),(80,26,3,26),(81,26,18,26),(82,26,29,26),(129,37,3,37),(130,37,16,37),(139,39,2,39),(140,39,4,39),(141,39,29,39),(142,40,2,40),(143,40,16,40),(144,40,6,40),(145,40,25,40),(146,40,4,40),(159,43,2,43),(160,43,4,43),(161,43,6,43),(162,43,7,43),(163,43,16,43),(164,43,18,43),(165,43,20,43),(171,45,26,45),(172,45,27,45),(173,45,2,45),(174,45,29,45),(175,45,16,45),(183,47,2,47),(184,47,27,47),(185,47,7,47),(186,47,29,47),(187,48,2,48),(188,48,7,48),(189,48,16,48),(190,48,29,48),(191,48,3,48),(197,50,3,50),(198,50,7,50),(199,50,16,50),(218,54,2,54),(219,54,3,54),(220,54,4,54),(221,54,16,54),(222,54,29,54),(223,54,17,54),(224,54,30,54),(225,54,24,54),(226,54,31,54),(227,54,23,54);

/*Table structure for table `caracteristicas` */

DROP TABLE IF EXISTS `caracteristicas`;

CREATE TABLE `caracteristicas` (
  `idCaracteristicas` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(300) NOT NULL,
  `numero` int(11) NOT NULL,
  `grado` varchar(45) NOT NULL,
  `observaciones` varchar(150) NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `idMatrizCaracteristicas` int(11) NOT NULL,
  PRIMARY KEY (`idCaracteristicas`),
  KEY `matrizCaracteristicas` (`idMatrizCaracteristicas`),
  CONSTRAINT `matrizCaracteristicas` FOREIGN KEY (`idMatrizCaracteristicas`) REFERENCES `matrizcaracteristicas` (`idMatrizCaracteristicas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

/*Data for the table `caracteristicas` */

insert  into `caracteristicas`(`idCaracteristicas`,`descripcion`,`numero`,`grado`,`observaciones`,`tipo`,`idMatrizCaracteristicas`) values (1,'prueba',1,'na','na','na',1),(10,'test',5,'test','test','test',9),(11,'test',7,'test','test','test',10),(13,'test',6,'test','test','test',12),(16,'test',55,'test','test','tesrt',15),(23,'test',54,'test','test','test',22),(24,'test',54,'test','test','test',23),(26,'testing',6,'testing','testing','testing',26),(37,'test',6,'test','test','test',37),(39,'test',1332,'test','test','test',39),(40,'test',54,'test','tes','test',40),(43,'test',53153,'test','teest','test',43),(45,'test',231,'test','test','test',45),(47,'test',2,'test','test','test',47),(48,'cgy',15,'g','rf','f',48),(50,'test',2212,'test','test','test',50),(54,'test',25,'12','testing','test',54);

/*Table structure for table `causafalla` */

DROP TABLE IF EXISTS `causafalla`;

CREATE TABLE `causafalla` (
  `idCausaFalla` int(11) NOT NULL AUTO_INCREMENT,
  `causaFalla` varchar(350) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idCausaFalla`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `causafalla` */

insert  into `causafalla`(`idCausaFalla`,`causaFalla`,`descripcion`) values (1,'Altura de insertos diferente','Causa de planicidad fuera de norma');

/*Table structure for table `clienteparte` */

DROP TABLE IF EXISTS `clienteparte`;

CREATE TABLE `clienteparte` (
  `idClienteParte` int(11) NOT NULL AUTO_INCREMENT,
  `idParte` int(11) NOT NULL,
  `idClientes` int(11) NOT NULL,
  PRIMARY KEY (`idClienteParte`),
  KEY `ClienteCl` (`idClientes`),
  KEY `ParteCl` (`idParte`),
  CONSTRAINT `ClienteCl` FOREIGN KEY (`idClientes`) REFERENCES `clientes` (`idClientes`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ParteCl` FOREIGN KEY (`idParte`) REFERENCES `parte` (`idParte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

/*Data for the table `clienteparte` */

insert  into `clienteparte`(`idClienteParte`,`idParte`,`idClientes`) values (1,19,1),(3,20,1),(14,38,1),(15,39,1),(18,41,1),(31,51,1),(33,53,1),(34,54,1),(37,56,1),(39,58,1),(41,60,1),(42,61,1),(44,63,1),(48,69,1);

/*Table structure for table `clientes` */

DROP TABLE IF EXISTS `clientes`;

CREATE TABLE `clientes` (
  `idClientes` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCliente` varchar(45) NOT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `ubicacion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`idClientes`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `clientes` */

insert  into `clientes`(`idClientes`,`nombreCliente`,`telefono`,`ubicacion`) values (1,'Nissan Aguascalientes','01 449 910 4111','Carretera Federal Lagos de Moreno Kilometro 7.5 Aguascalientes');

/*Table structure for table `controldeteccion` */

DROP TABLE IF EXISTS `controldeteccion`;

CREATE TABLE `controldeteccion` (
  `idControlDeteccion` int(11) NOT NULL AUTO_INCREMENT,
  `controlDeteccion` varchar(350) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idControlDeteccion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `controldeteccion` */

insert  into `controldeteccion`(`idControlDeteccion`,`controlDeteccion`,`descripcion`) values (1,'Se tiene HTE de mtto preventivo y predictivo a troqueles','Detección de la falla \'Planicidad fuera de norma\''),(2,'AltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetección','AltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetección');

/*Table structure for table `controlprevencion` */

DROP TABLE IF EXISTS `controlprevencion`;

CREATE TABLE `controlprevencion` (
  `idControlPrevencion` int(11) NOT NULL AUTO_INCREMENT,
  `controlPrevencion` varchar(350) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idControlPrevencion`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `controlprevencion` */

insert  into `controlprevencion`(`idControlPrevencion`,`controlPrevencion`,`descripcion`) values (1,'Se tiene sistema ILU y plan de capacitación para técnicos de troq.													','Prevención de la causa \'Altura de insertos diferente\'');

/*Table structure for table `departamento` */

DROP TABLE IF EXISTS `departamento`;

CREATE TABLE `departamento` (
  `idDepartamento` int(11) NOT NULL AUTO_INCREMENT,
  `nombreDepartamento` varchar(90) NOT NULL,
  `nombreAbr` varchar(45) NOT NULL,
  PRIMARY KEY (`idDepartamento`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `departamento` */

insert  into `departamento`(`idDepartamento`,`nombreDepartamento`,`nombreAbr`) values (1,'INGENIERÍA','Ing.'),(2,'PRODUCCIÓN','Prod.'),(3,'TROQUELES','Troq.'),(4,'ASEGURAMIENTO DE CALIDAD','A.Q.'),(5,'CONTROL DE PRODUCCIÓN','C. P.'),(6,'JEFE DE DEPARTAMENTO','J. Depto.'),(7,'RECURSOS HUMANOS','RH');

/*Table structure for table `efectofalla` */

DROP TABLE IF EXISTS `efectofalla`;

CREATE TABLE `efectofalla` (
  `idEfectoFalla` int(11) NOT NULL AUTO_INCREMENT,
  `efectoFalla` varchar(350) NOT NULL,
  `descripcion` varchar(300) NOT NULL,
  PRIMARY KEY (`idEfectoFalla`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `efectofalla` */

insert  into `efectofalla`(`idEfectoFalla`,`efectoFalla`,`descripcion`) values (1,'No se Podrá ensamblar en el siguiente Proceso','Efecto por planicidad fuera de norma');

/*Table structure for table `elaboro` */

DROP TABLE IF EXISTS `elaboro`;

CREATE TABLE `elaboro` (
  `idElaboro` int(11) NOT NULL AUTO_INCREMENT,
  `idPersona` int(11) NOT NULL,
  PRIMARY KEY (`idElaboro`),
  KEY `PersonaElaboro` (`idPersona`),
  CONSTRAINT `PersonaElaboro` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `elaboro` */

insert  into `elaboro`(`idElaboro`,`idPersona`) values (1,1);

/*Table structure for table `equipo` */

DROP TABLE IF EXISTS `equipo`;

CREATE TABLE `equipo` (
  `idEquipo` int(11) NOT NULL AUTO_INCREMENT,
  `nombreEquipo` varchar(45) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  PRIMARY KEY (`idEquipo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `equipo` */

insert  into `equipo`(`idEquipo`,`nombreEquipo`,`descripcion`) values (1,'Estandar','Equipo estandar para formatos'),(3,'Equipo de prueba','solo es para realizar pruebas');

/*Table structure for table `especificacionlamina` */

DROP TABLE IF EXISTS `especificacionlamina`;

CREATE TABLE `especificacionlamina` (
  `idEspecificacionLamina` int(11) NOT NULL AUTO_INCREMENT,
  `idParte` int(11) NOT NULL,
  `idFormato` int(11) NOT NULL,
  `idMaterial` int(10) DEFAULT NULL,
  PRIMARY KEY (`idEspecificacionLamina`),
  KEY `ParteEspLam` (`idParte`),
  KEY `FormatoEspeLam` (`idFormato`),
  KEY `FK_especificacionlamina_material` (`idMaterial`),
  CONSTRAINT `FK_especificacionlamina_material` FOREIGN KEY (`idMaterial`) REFERENCES `material` (`idMaterial`),
  CONSTRAINT `FormatoEspeLam` FOREIGN KEY (`idFormato`) REFERENCES `formato` (`idFormato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ParteEspLam` FOREIGN KEY (`idParte`) REFERENCES `parte` (`idParte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

/*Data for the table `especificacionlamina` */

insert  into `especificacionlamina`(`idEspecificacionLamina`,`idParte`,`idFormato`,`idMaterial`) values (23,38,8,1),(24,39,8,1),(27,41,8,1),(40,51,8,1),(42,53,8,1),(43,54,8,1),(46,56,8,1),(48,58,8,1),(50,60,8,1),(51,61,8,1),(53,63,8,1),(57,69,8,1);

/*Table structure for table `falla` */

DROP TABLE IF EXISTS `falla`;

CREATE TABLE `falla` (
  `idFalla` int(11) NOT NULL AUTO_INCREMENT,
  `idProcesos` int(11) NOT NULL,
  `idModoFalla` int(11) NOT NULL,
  `idEfectoFalla` int(11) NOT NULL,
  `idCausaFalla` int(11) NOT NULL,
  `idControlPrevencion` int(11) NOT NULL,
  `idControlDeteccion` int(11) NOT NULL,
  `idGradoFalla` int(10) NOT NULL,
  PRIMARY KEY (`idFalla`),
  KEY `procesosFalla` (`idProcesos`),
  KEY `modoFalla` (`idModoFalla`),
  KEY `efectoFalla` (`idEfectoFalla`),
  KEY `causaFalla` (`idCausaFalla`),
  KEY `controlPrevenFalla` (`idControlPrevencion`),
  KEY `controlDetenFalla` (`idControlDeteccion`),
  KEY `FK_falla_gradofalla` (`idGradoFalla`),
  CONSTRAINT `causaFalla` FOREIGN KEY (`idCausaFalla`) REFERENCES `causafalla` (`idCausaFalla`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `controlDetenFalla` FOREIGN KEY (`idControlDeteccion`) REFERENCES `controldeteccion` (`idControlDeteccion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `controlPrevenFalla` FOREIGN KEY (`idControlPrevencion`) REFERENCES `controlprevencion` (`idControlPrevencion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `efectoFalla` FOREIGN KEY (`idEfectoFalla`) REFERENCES `efectofalla` (`idEfectoFalla`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_falla_gradofalla` FOREIGN KEY (`idGradoFalla`) REFERENCES `gradofalla` (`idGradoFalla`),
  CONSTRAINT `modoFalla` FOREIGN KEY (`idModoFalla`) REFERENCES `modofalla` (`idModoFalla`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `procesosFalla` FOREIGN KEY (`idProcesos`) REFERENCES `procesos` (`idProcesos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `falla` */

/*Table structure for table `fallaamef` */

DROP TABLE IF EXISTS `fallaamef`;

CREATE TABLE `fallaamef` (
  `idFallaParte` int(11) NOT NULL AUTO_INCREMENT,
  `severidad` int(11) NOT NULL,
  `ocurrencia` int(11) NOT NULL,
  `deteccion` int(11) NOT NULL,
  `idFalla` int(11) NOT NULL,
  `idAMEF` int(11) NOT NULL,
  `accionesRecomendadas` varchar(150) DEFAULT NULL,
  `responsable` varchar(45) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `grado` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idFallaParte`),
  KEY `fallaAmeffalla` (`idFalla`),
  KEY `partefallaamef` (`idAMEF`),
  CONSTRAINT `fallaAmeffalla` FOREIGN KEY (`idFalla`) REFERENCES `falla` (`idFalla`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `partefallaamef` FOREIGN KEY (`idAMEF`) REFERENCES `amef` (`idAMEF`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Esta tabla, realmente tiene el labor de llevar de una forma ';

/*Data for the table `fallaamef` */

/*Table structure for table `formato` */

DROP TABLE IF EXISTS `formato`;

CREATE TABLE `formato` (
  `idFormato` int(11) NOT NULL AUTO_INCREMENT,
  `tipoFormato` varchar(100) NOT NULL,
  `codigoRevision` varchar(85) NOT NULL,
  `mapaProceso` varchar(85) NOT NULL,
  PRIMARY KEY (`idFormato`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `formato` */

insert  into `formato`(`idFormato`,`tipoFormato`,`codigoRevision`,`mapaProceso`) values (1,'Matriz de Características','F611.008 Rev. E 02-Jun-03','MAC 611.02'),(2,'Diagrama de Flujo','F611.101 Rev. E 02-Jun-03','MAC 611.02'),(3,'AMEF','F611.108 Rev. 3 04-May-07','MAC 611.03'),(4,'Plan de Control','F611.104 Rev. E 02-Jun-03','MAC611.04'),(5,'BL800','F712.049 Rev. 4 30-Nov-10','PAC423.01'),(6,'TD200B','F712.051 Rev. 3 04-Dic-10','PAC423.01'),(7,'Hoja de Procesos','F712.002 Rev. 2  23-Abr-10','PAC423.01'),(8,'Hoja de Especifiación de Lámina','F711.045 Rev. 1 8-Nov-08','PAC423.01'),(9,'BL200','F712.047 Rev. 4 08-Jul-11','PAC423.01'),(10,'BL400','F712.048 Rev. 4 05-Ago-11','PAC423.01'),(11,'TD400','F712.052 Rev. 4 5-Ago-11','PAC423.01'),(12,'TD800','F712.052 Rev. 4 5-Ago-11','PAC423.01'),(13,'TF500','F712.053 Rev. 4 08-Jul-11','PAC423.01'),(14,'TF1500','F712.012 Rev. 4 8-Jul-11','PAC423.01'),(15,'TD200A','F712.050 Rev. 4 08-Jul-11','PAC423.01');

/*Table structure for table `formatoparte` */

DROP TABLE IF EXISTS `formatoparte`;

CREATE TABLE `formatoparte` (
  `idFormatoParte` int(11) NOT NULL AUTO_INCREMENT,
  `idFormato` int(11) NOT NULL,
  `idParte` int(11) NOT NULL,
  `idElaboro` int(11) DEFAULT NULL,
  `idAprobo` int(11) DEFAULT NULL,
  `idEquipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFormatoParte`),
  KEY `formatoParteRel` (`idFormato`),
  KEY `parteFormatoRel` (`idParte`),
  KEY `formatoParteElaborador` (`idElaboro`),
  KEY `formatoParteAprobador` (`idAprobo`),
  KEY `formatoParteEquipo` (`idEquipo`),
  CONSTRAINT `Aprobo` FOREIGN KEY (`idAprobo`) REFERENCES `persona` (`idPersona`),
  CONSTRAINT `Elaboro` FOREIGN KEY (`idElaboro`) REFERENCES `persona` (`idPersona`),
  CONSTRAINT `formatoParteEquipo` FOREIGN KEY (`idEquipo`) REFERENCES `equipo` (`idEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `formatoParteRel` FOREIGN KEY (`idFormato`) REFERENCES `formato` (`idFormato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `parteFormatoRel` FOREIGN KEY (`idParte`) REFERENCES `parte` (`idParte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `formatoparte` */

/*Table structure for table `gradofalla` */

DROP TABLE IF EXISTS `gradofalla`;

CREATE TABLE `gradofalla` (
  `idGradoFalla` int(10) NOT NULL AUTO_INCREMENT,
  `gradoFalla` varchar(50) NOT NULL DEFAULT '0',
  `descripcion` varchar(300) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idGradoFalla`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `gradofalla` */

insert  into `gradofalla`(`idGradoFalla`,`gradoFalla`,`descripcion`) values (1,'NA','No Aplica');

/*Table structure for table `historial` */

DROP TABLE IF EXISTS `historial`;

CREATE TABLE `historial` (
  `idHistorial` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  `componente` varchar(500) NOT NULL,
  `razon` varchar(100) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idHistorial`),
  KEY `UsuarioHistorial` (`idUsuario`),
  CONSTRAINT `UsuarioHistorial` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=latin1;

/*Data for the table `historial` */

insert  into `historial`(`idHistorial`,`idUsuario`,`componente`,`razon`,`fecha`) values (3,1,'ProbandoModelo','Creación de Modelo','2011-12-29'),(11,1,'Prueba','Creación de modelo','2011-12-29'),(12,1,'Prueba2','Creación de modelo','2011-12-29'),(13,1,'TEST','Creación de modelo','2011-12-29'),(14,1,'Pruebat','Creación de modelo','2011-12-29'),(15,1,'Probando','Creación de Modo - Falla','2011-12-29'),(16,1,'Probando\nsdfsdfsdf\nsdf\nsdf\nsdf','Creación de Modo - Falla','2011-12-29'),(19,1,'Este es un efecto de prueba, no tiene validez','Creación de Falla - Efecto','2011-12-29'),(20,1,'dsadsa osdfsdfbdsadsa osdfsdfbdsadsa osdfsdfbdsadsa osdfsdfbdsadsa osdfsdfbdsadsa osdfsdfbdsadsa osdfsdfb','Creación de Falla - Prevención','2011-12-29'),(21,1,'dsadsa osdfsdfbdsadsa osdfsdfbdsadsa osdfsdfbdsadsa osdfsdfbdsadsa osdfsdfb','Creación de Falla - Prevención','2011-12-29'),(22,1,'AltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetecciónAltaDetección','Creación de Falla - Prevención','2011-12-29'),(23,1,'Alta Causa, Alta Causa, Alta Causa, Alta Causa, Alta Causa, Alta Causa, Alta Causa, Alta Causa, Alta Causa, Alta Causa, Alta Causa, Alta Causa, Alta Causa, Alta Causa, Alta Causa, Alta Causa, Alta Causa, ','Creación de Falla - Causa','2011-12-29'),(24,1,'Recursos Humanos','Creación de Falla - Causa','2011-12-29'),(25,1,'Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado Alta grado ','Creación de Falla - Grado','2011-12-29'),(26,1,'Alta grado Alta grado Alta grado Alta grado Alta g','Creación de Falla - Grado','2011-12-29'),(27,1,'OtroTest','Creación de Modelo','2011-12-29'),(28,1,'TroquelPruba','Creación de Troquel','2011-12-29'),(29,1,'Troquelin','Creación de Troquel','2011-12-29'),(30,1,'Test','Creación de cuenta de usuario','2011-12-29'),(31,1,'ProcesoPrueba','Creación de Proceso','2011-12-29'),(32,1,'probando','Creación de Proceso','2011-12-29'),(33,1,'Inteligencia Escolar','Alta de Cliente','2011-12-29'),(34,1,'Probando Histo','Alta de Caracteristica','2011-12-30'),(35,1,'Testing history','Alta de Caracteristica','2011-12-30'),(36,1,'Testsdsdf','Alta de Caracteristica','2011-12-30'),(37,1,'Testing History','Alta de Caracteristica','2011-12-30'),(38,1,'Testing History','Alta de Caracteristica','2011-12-30'),(39,1,'540851HK0B','Cambio de nivel de Ingeniería T82535 a T82536','2012-01-02'),(40,1,'540851HK0B','Cambio de nivel de Ingeniería T82536 a T82537','2012-01-02'),(41,1,'Alejandro Montes','Alta de persona en el sistema ','2012-01-02'),(42,1,'Alejandro Montes','Alta de persona en el sistema ','2012-01-02'),(43,1,'Alejandro Montes','Alta de persona en el sistema ','2012-01-02'),(44,1,'Alejandro Montes','Alta de persona en el sistema ','2012-01-02'),(45,1,'Equipo de Prueba','Alta de equipo Equipo de Prueba en el sistema','2012-01-02'),(46,1,'Calidad','Creación de cuenta de usuario','2012-01-03'),(47,1,'540851HK0B','Establecimiento de nuevo Elaborador y Aprobador','2012-01-04'),(48,1,'540851HK0B','Establecimiento de nuevo Elaborador y Aprobador','2012-01-04'),(49,1,'540851HK0B','Establecimiento de nuevo Elaborador y Aprobador','2012-01-04'),(50,1,'540851HK0B','Establecimiento de nuevo Elaborador y Aprobador','2012-01-04'),(51,1,'540851HK0B','Establecimiento de nuevo Elaborador y Aprobador','2012-01-04'),(52,1,'540851HK0B','Establecimiento de nuevo Elaborador y Aprobador','2012-01-04'),(53,1,'540851HK0B','Establecimiento de nuevo Elaborador y Aprobador','2012-01-04'),(54,1,'540851HK0B','Establecimiento de nuevo Elaborador y Aprobador','2012-01-04'),(55,1,'540851HK0B','Establecimiento de nuevo Elaborador y Aprobador','2012-01-04'),(56,1,'540851HK0B','Establecimiento de nuevo Elaborador y Aprobador','2012-01-04'),(57,1,'TEST','Cambio de número de Parte TEST a PROBANDO en el sistema','2012-01-04'),(58,1,'TEST','Cambio de número de Parte TEST a PROBANDO en el sistema','2012-01-04'),(59,1,'Proceso Prueba','Creación de Proceso','2012-01-04'),(60,1,'PROBANDO','Cambio de nivel de Ingeniería TEST a NivFicticio','2012-01-04'),(61,1,'PROBANDO','Cambio de nivel de Ingeniería TEST a TEST','2012-01-04'),(62,1,'Equipo de prueba','Alta de equipo Equipo de prueba en el sistema','2012-01-05'),(63,1,'540851HK0B','Establecimiento de nuevo Elaborador y Aprobador','2012-01-05'),(64,1,'540851HK0B','Establecimiento de nuevo Elaborador y Aprobador','2012-01-05'),(65,1,'540851HK0B','Establecimiento de equipo para AMEF de la parte','2012-01-05'),(66,1,'540851HK0B','Establecimiento de equipo para AMEF de la parte','2012-01-05'),(67,1,'Juan','Creación de cuenta de usuario','2012-01-05'),(68,1,'SP231-440PQ','Registro de Material SP231-440PQ del proveedor NICOMETAL en el sistema','2012-01-06'),(69,1,'SPDLFOA456','Registro de Material SPDLFOA456 del proveedor PRUEBA en el sistema','2012-01-06'),(70,1,'MATERIALSPDEPRUEBA','Registro de Material MATERIALSPDEPRUEBA del proveedor PROBANDO en el sistema','2012-01-06'),(71,1,'454d45','Registro de Material 454d45 del proveedor SegundaPrueba en el sistema','2012-01-06'),(72,1,'PROBANDO REGISTRO DE ELEMENTOS','Registro de Material PROBANDO REGISTRO DE ELEMENTOS del proveedor LAMINA DE PRUEBA en el sistema','2012-01-06'),(73,1,'probando','Registro de Material probando del proveedor Lamina de prueba en el sistema','2012-01-06'),(74,1,'Material de prueba','Registro de Material Material de prueba del proveedor Lamina de prueba en el sistema','2012-01-06'),(75,1,'Prueba','Registro de Material Prueba del proveedor Probando  en el sistema','2012-01-06'),(76,1,'Prueba','Registro de Material Prueba del proveedor Probando  en el sistema','2012-01-06'),(77,1,'5120245','Creación de parte','2012-01-09'),(78,1,'656254TRY','Creación de parte','2012-01-09'),(79,1,'PARTETEST','Creación de parte','2012-01-09'),(80,1,'PARTEPRUEBA','Creación de parte','2012-01-09'),(81,1,'5451DFD','Creación de parte','2012-01-09'),(82,1,'PROBANDOPIEZA','Creación de parte','2012-01-09'),(83,1,'PROB','Creación de parte','2012-01-09'),(84,1,'454','Creación de parte','2012-01-09'),(85,1,'5451','Creación de parte','2012-01-09'),(86,1,'5845125','Creación de parte','2012-01-09'),(87,1,'PARTEPRUEBA','Creación de parte','2012-01-09'),(88,1,'654651','Creación de parte','2012-01-09'),(89,1,'45465','Creación de parte','2012-01-09'),(90,1,'6545','Creación de parte','2012-01-09'),(91,1,'54','Creación de parte','2012-01-09'),(92,1,'54','Creación de parte','2012-01-09'),(93,1,'58','Creación de parte','2012-01-09'),(94,1,'5546','Creación de parte','2012-01-09'),(95,1,'Inspección por muestreo','Creación de Proceso','2012-01-10'),(96,1,'Transporte a almacén de plantilla','Creación de Proceso','2012-01-10'),(97,1,'Inspección','Creación de Proceso','2012-01-10'),(98,1,'Inspección','Creación de Proceso','2012-01-10'),(99,1,'Preparación de Rollo y Troquel','Creación de Proceso','2012-01-10'),(100,1,'PiBL 800','Creación de Proceso','2012-01-10'),(101,1,'FLT TD200','Creación de Proceso','2012-01-10'),(102,1,'DR','Creación de Proceso','2012-01-11'),(103,1,'RST','Creación de Proceso','2012-01-11'),(104,1,'TRIM','Creación de Proceso','2012-01-11'),(105,1,'FLGBEED','Creación de Proceso','2012-01-11'),(106,1,'CamPi','Creación de Proceso','2012-01-11'),(107,1,'Prueba','Creación de Proceso','2012-01-11'),(108,1,'PiBL 800','Creación de Proceso','2012-01-11'),(109,1,'FLT TD200','Creación de Proceso','2012-01-11'),(110,1,'DR TF1500','Creación de Proceso','2012-01-11'),(111,1,'RST','Creación de Proceso','2012-01-11'),(112,1,'TRIM','Creación de Proceso','2012-01-11'),(113,1,'FLGBEED','Creación de Proceso','2012-01-11'),(114,1,'CamPi','Creación de Proceso','2012-01-11'),(115,1,'FLT','Creación de Proceso','2012-01-11'),(116,1,'Pi','Creación de Proceso','2012-01-11'),(117,1,'TEST','Cambio de número de Parte PROBANDO a NECESARIO en el sistema','2012-01-11'),(118,1,'Preparación de Rollo BL400','Creación de Proceso','2012-01-11'),(119,1,'Almacén de Plantilla','Creación de Proceso','2012-01-11'),(120,1,'Almacén de partes Estampadas','Creación de Proceso','2012-01-11'),(121,1,'Transporte a almacén de partes Estampadas','Creación de Proceso','2012-01-11'),(122,1,'TEST','Cambio de número de Parte NECESARIO a 1521 en el sistema','2012-01-11'),(123,1,'Ensamble','Creación de cuenta de usuario','2012-01-11'),(124,1,'prueb','Alta de Caracteristica','2012-01-12'),(125,1,'prueb','Alta de Caracteristica','2012-01-12'),(126,1,'prueb','Alta de Caracteristica','2012-01-12'),(127,1,'estaeslaprueba','Alta de Caracteristica','2012-01-12'),(128,1,'probandoghsd','Alta de Caracteristica','2012-01-12'),(129,1,'aspjjdsl','Alta de Caracteristica','2012-01-12'),(130,1,'probandomasivo','Alta de Caracteristica','2012-01-12'),(131,1,'Desc','Alta de Caracteristica','2012-01-12'),(132,1,'sdsafd','Alta de Caracteristica','2012-01-12'),(133,1,'Prueba 3','Alta de Caracteristica','2012-01-13'),(134,1,'test5','Alta de Caracteristica','2012-01-13'),(135,1,'probandounavezmas','Alta de Caracteristica','2012-01-13'),(136,1,'falla general','Alta de Caracteristica','2012-01-13'),(137,1,'Descripción','Alta de Caracteristica','2012-01-13'),(138,1,'esa','Alta de Caracteristica','2012-01-13'),(139,1,'testsra','Alta de Caracteristica','2012-01-13'),(140,1,'Aseg Calidad','Creación de cuenta de usuario','2012-01-16'),(141,1,'6544','Cambio de nivel de Ingeniería 6544 a sad','2012-01-16'),(142,1,'65454','Cambio de nivel de Ingeniería T82535 a T82535','2012-01-16'),(143,1,'PiRst','Creación de Proceso','2012-01-23'),(144,1,'test','Creación de Modelo','2012-02-02'),(145,1,'BL400TEST','Creación de Proceso','2012-02-02'),(146,1,'Diagrama de procesos','540851HK0B','2012-02-10'),(147,1,'Diagrama de procesos','540851HK0B','2012-02-10'),(148,1,'540851HK0B','Diagrama de procesos','2012-02-10'),(149,1,'540851HK0B','Diagrama de procesos','2012-02-10'),(150,1,'testing models','Creación de Modelo','2012-02-14'),(151,1,'testing td400800','Creación de Proceso','2012-02-14');

/*Table structure for table `hojacondiciones` */

DROP TABLE IF EXISTS `hojacondiciones`;

CREATE TABLE `hojacondiciones` (
  `idHojaCondiciones` int(11) NOT NULL AUTO_INCREMENT,
  `idParte` int(11) NOT NULL,
  PRIMARY KEY (`idHojaCondiciones`),
  KEY `ParteHojaCond` (`idParte`),
  CONSTRAINT `ParteHojaCond` FOREIGN KEY (`idParte`) REFERENCES `parte` (`idParte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `hojacondiciones` */

insert  into `hojacondiciones`(`idHojaCondiciones`,`idParte`) values (3,69);

/*Table structure for table `hojaprocesos` */

DROP TABLE IF EXISTS `hojaprocesos`;

CREATE TABLE `hojaprocesos` (
  `idHojaProcesos` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idHojaProcesos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `hojaprocesos` */

/*Table structure for table `jig` */

DROP TABLE IF EXISTS `jig`;

CREATE TABLE `jig` (
  `idJig` int(11) NOT NULL AUTO_INCREMENT,
  `nombreMaquina` varchar(100) NOT NULL,
  PRIMARY KEY (`idJig`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `jig` */

/*Table structure for table `maquina` */

DROP TABLE IF EXISTS `maquina`;

CREATE TABLE `maquina` (
  `idMaquina` int(11) NOT NULL AUTO_INCREMENT,
  `idTipoMaquina` int(11) NOT NULL,
  PRIMARY KEY (`idMaquina`),
  KEY `nombreMaqTipo` (`idTipoMaquina`),
  CONSTRAINT `nombreMaqTipo` FOREIGN KEY (`idTipoMaquina`) REFERENCES `tipomaquina` (`idTipoMaquina`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `maquina` */

insert  into `maquina`(`idMaquina`,`idTipoMaquina`) values (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9);

/*Table structure for table `maquinaproceso` */

DROP TABLE IF EXISTS `maquinaproceso`;

CREATE TABLE `maquinaproceso` (
  `idMaquinaProceso` int(11) NOT NULL AUTO_INCREMENT,
  `idProcesos` int(11) NOT NULL,
  `idMaquina` int(11) NOT NULL,
  PRIMARY KEY (`idMaquinaProceso`),
  KEY `ProcesoMaquina` (`idProcesos`),
  KEY `Maquina` (`idMaquina`),
  CONSTRAINT `Maquina` FOREIGN KEY (`idMaquina`) REFERENCES `maquina` (`idMaquina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ProcesoMaquina` FOREIGN KEY (`idProcesos`) REFERENCES `procesos` (`idProcesos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `maquinaproceso` */

insert  into `maquinaproceso`(`idMaquinaProceso`,`idProcesos`,`idMaquina`) values (2,16,3),(3,17,5),(4,18,9),(5,19,9),(6,20,9),(7,21,9),(8,22,9),(9,23,4),(10,24,4),(11,29,1),(12,30,2),(13,31,6);

/*Table structure for table `material` */

DROP TABLE IF EXISTS `material`;

CREATE TABLE `material` (
  `idMaterial` int(11) NOT NULL AUTO_INCREMENT,
  `nombreMaterial` varchar(80) NOT NULL,
  `proveedor` varchar(45) NOT NULL,
  `espesor` int(11) NOT NULL,
  `anchoRollo` int(11) NOT NULL,
  `ts` int(45) DEFAULT NULL,
  `yp` int(45) DEFAULT NULL,
  `el` int(45) DEFAULT NULL,
  `periodC` varchar(45) DEFAULT NULL,
  `periodSi` varchar(45) DEFAULT NULL,
  `periodMn` varchar(45) DEFAULT NULL,
  `periodP` varchar(45) DEFAULT NULL,
  `periodS` varchar(45) DEFAULT NULL,
  `toleranciaAncho` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idMaterial`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `material` */

insert  into `material`(`idMaterial`,`nombreMaterial`,`proveedor`,`espesor`,`anchoRollo`,`ts`,`yp`,`el`,`periodC`,`periodSi`,`periodMn`,`periodP`,`periodS`,`toleranciaAncho`) values (1,'SP231-440PQ','NICOMETAL',5,158,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `materialespecificacion` */

DROP TABLE IF EXISTS `materialespecificacion`;

CREATE TABLE `materialespecificacion` (
  `idMaterialEspecificacion` int(11) NOT NULL AUTO_INCREMENT,
  `idEspecificacionLamina` int(11) NOT NULL,
  `PasoAlimentacion` double NOT NULL,
  `PesoBlanking` double NOT NULL,
  `PesoParte` double NOT NULL,
  `AprovMaterial` double NOT NULL,
  PRIMARY KEY (`idMaterialEspecificacion`),
  KEY `ObtenerParte` (`idEspecificacionLamina`),
  CONSTRAINT `ObtenerParte` FOREIGN KEY (`idEspecificacionLamina`) REFERENCES `especificacionlamina` (`idEspecificacionLamina`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

/*Data for the table `materialespecificacion` */

insert  into `materialespecificacion`(`idMaterialEspecificacion`,`idEspecificacionLamina`,`PasoAlimentacion`,`PesoBlanking`,`PesoParte`,`AprovMaterial`) values (23,23,25,24,23,95.833),(24,24,252,26,25,96.154),(27,27,2,23,21,91.304),(40,40,25,23,21,91.304),(42,42,15,23,21,91.304),(43,43,2,12,11,91.667),(46,46,15,21,20,95.238),(48,48,1,21,20,95.238),(50,50,25,21,20,95.238),(51,51,21,122,118,96.721),(53,53,25,25,23,92),(57,57,2,21,20,95.238);

/*Table structure for table `matrizcaracteristicas` */

DROP TABLE IF EXISTS `matrizcaracteristicas`;

CREATE TABLE `matrizcaracteristicas` (
  `idMatrizCaracteristicas` int(11) NOT NULL AUTO_INCREMENT,
  `idParte` int(11) NOT NULL,
  `idFormato` int(11) NOT NULL,
  PRIMARY KEY (`idMatrizCaracteristicas`),
  KEY `parteMatrizC` (`idParte`),
  KEY `formatoMatriz` (`idFormato`),
  CONSTRAINT `formatoMatriz` FOREIGN KEY (`idFormato`) REFERENCES `formato` (`idFormato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `parteMatrizC` FOREIGN KEY (`idParte`) REFERENCES `parte` (`idParte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

/*Data for the table `matrizcaracteristicas` */

insert  into `matrizcaracteristicas`(`idMatrizCaracteristicas`,`idParte`,`idFormato`) values (1,1,1),(9,9,1),(10,19,1),(12,20,1),(15,30,1),(22,38,1),(23,39,1),(26,41,1),(37,51,1),(39,53,1),(40,54,1),(43,56,1),(45,58,1),(47,60,1),(48,61,1),(50,63,1),(54,69,1);

/*Table structure for table `modelo` */

DROP TABLE IF EXISTS `modelo`;

CREATE TABLE `modelo` (
  `idModelo` int(11) NOT NULL AUTO_INCREMENT,
  `nombreModelo` varchar(80) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idModelo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `modelo` */

insert  into `modelo`(`idModelo`,`nombreModelo`,`descripcion`) values (1,'X02A',''),(2,'HS','Sentra'),(3,'modeloPrueba','modeloPrueba'),(4,'test','test'),(5,'testing models','testing');

/*Table structure for table `modofalla` */

DROP TABLE IF EXISTS `modofalla`;

CREATE TABLE `modofalla` (
  `idModoFalla` int(11) NOT NULL AUTO_INCREMENT,
  `modoFalla` varchar(500) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`idModoFalla`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `modofalla` */

insert  into `modofalla`(`idModoFalla`,`modoFalla`,`descripcion`) values (1,'Planicidad fuera de norma','Modo falla de TD');

/*Table structure for table `niveling` */

DROP TABLE IF EXISTS `niveling`;

CREATE TABLE `niveling` (
  `idNivelIng` int(11) NOT NULL AUTO_INCREMENT,
  `nivelIngenieria` varchar(95) NOT NULL,
  PRIMARY KEY (`idNivelIng`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `niveling` */

insert  into `niveling`(`idNivelIng`,`nivelIngenieria`) values (1,'T82535'),(2,'TEST'),(3,'NivFicticio'),(4,'TEST'),(6,'T82535');

/*Table structure for table `normastolerancias` */

DROP TABLE IF EXISTS `normastolerancias`;

CREATE TABLE `normastolerancias` (
  `idnormasTolerancias` int(11) NOT NULL AUTO_INCREMENT,
  `nombreNormaTolerancia` varchar(100) NOT NULL,
  `grado` varchar(45) NOT NULL,
  `evaluacionTecnica` varchar(45) NOT NULL,
  `tamaño` varchar(45) NOT NULL,
  `frecuencia` varchar(45) NOT NULL,
  `responsable` varchar(45) NOT NULL,
  `metodoControl` varchar(45) NOT NULL,
  `mantenimiento` varchar(45) NOT NULL,
  `pokayoke` varchar(45) NOT NULL,
  `planReaccion` varchar(45) NOT NULL,
  PRIMARY KEY (`idnormasTolerancias`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `normastolerancias` */

/*Table structure for table `parte` */

DROP TABLE IF EXISTS `parte`;

CREATE TABLE `parte` (
  `idParte` int(11) NOT NULL AUTO_INCREMENT,
  `idModelo` int(11) NOT NULL,
  `idNivelIng` int(11) NOT NULL,
  `nombreParte` varchar(100) NOT NULL,
  `numeroParte` varchar(100) NOT NULL,
  PRIMARY KEY (`idParte`),
  KEY `ModeloParte` (`idModelo`),
  KEY `nivelIngenieriaParte` (`idNivelIng`),
  CONSTRAINT `ModeloParte` FOREIGN KEY (`idModelo`) REFERENCES `modelo` (`idModelo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `nivelIngenieriaParte` FOREIGN KEY (`idNivelIng`) REFERENCES `niveling` (`idNivelIng`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=latin1 COMMENT='Esta tabla debe estar referenciada a las demas, siendo el to';

/*Data for the table `parte` */

insert  into `parte`(`idParte`,`idModelo`,`idNivelIng`,`nombreParte`,`numeroParte`) values (1,1,1,'WASH-SPL','540851HK0B'),(9,1,6,'test','test'),(18,1,6,'test','test1'),(19,1,6,'text','testg'),(20,2,6,'Testing Part','Parte Prueba'),(30,1,6,'test','564'),(38,1,6,'testing','testing'),(39,1,6,'testing','testing5'),(41,1,6,'testing','testing56'),(51,2,6,'test','test56'),(53,1,6,'test','test565'),(54,1,6,'testing','testing4'),(56,2,6,'test','86456'),(58,1,6,'test23','testads'),(60,1,6,'test5','t561'),(61,2,6,'test','t54656'),(63,1,4,'test','test54sda'),(69,1,6,'test','test54');

/*Table structure for table `persona` */

DROP TABLE IF EXISTS `persona`;

CREATE TABLE `persona` (
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `idDepartamento` int(11) NOT NULL,
  `nombrePersona` varchar(105) NOT NULL,
  `nombreAbr` varchar(45) NOT NULL,
  PRIMARY KEY (`idPersona`),
  KEY `DepartamentoPersona` (`idDepartamento`),
  CONSTRAINT `DepartamentoPersona` FOREIGN KEY (`idDepartamento`) REFERENCES `departamento` (`idDepartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `persona` */

insert  into `persona`(`idPersona`,`idDepartamento`,`nombrePersona`,`nombreAbr`) values (1,1,'GERARDO LÓPEZ','G. López'),(2,2,'OMAR DOMÍNGUEZ','O. Domínguez'),(3,4,'JESÚS PADILLA','J. Padilla'),(4,5,'ARTURO ELVIRA','A. Elvira'),(5,3,'FRANCISCO UGALDE','F. Ugalde'),(6,6,'GUILLERMO SANTOS','G. Santos'),(7,6,'VÍCTOR SÁNCHEZ','V. Sánchez'),(8,6,'HUGO OLVERA','H. Olvera');

/*Table structure for table `personaequipo` */

DROP TABLE IF EXISTS `personaequipo`;

CREATE TABLE `personaequipo` (
  `idPersonaEquipo` int(11) NOT NULL AUTO_INCREMENT,
  `idPersona` int(11) NOT NULL,
  `idEquipo` int(11) NOT NULL,
  PRIMARY KEY (`idPersonaEquipo`),
  KEY `PersonaEquipo` (`idPersona`),
  KEY `EquipoPersona` (`idEquipo`),
  CONSTRAINT `EquipoPersona` FOREIGN KEY (`idEquipo`) REFERENCES `equipo` (`idEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PersonaEquipo` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `personaequipo` */

insert  into `personaequipo`(`idPersonaEquipo`,`idPersona`,`idEquipo`) values (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,8,3),(7,6,3),(8,7,3),(9,5,3),(10,4,3);

/*Table structure for table `plancontrol` */

DROP TABLE IF EXISTS `plancontrol`;

CREATE TABLE `plancontrol` (
  `idPlanControl` int(11) NOT NULL AUTO_INCREMENT,
  `idParte` int(11) NOT NULL,
  `idFormato` int(11) NOT NULL,
  PRIMARY KEY (`idPlanControl`),
  KEY `planParte` (`idParte`),
  KEY `FormatoPlanC` (`idFormato`),
  CONSTRAINT `FormatoPlanC` FOREIGN KEY (`idFormato`) REFERENCES `formato` (`idFormato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `planParte` FOREIGN KEY (`idParte`) REFERENCES `parte` (`idParte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `plancontrol` */

/*Table structure for table `plancontrolcaracteristicas` */

DROP TABLE IF EXISTS `plancontrolcaracteristicas`;

CREATE TABLE `plancontrolcaracteristicas` (
  `idPlanControlCaracteristicas` int(11) NOT NULL AUTO_INCREMENT,
  `idPlanControl` int(11) NOT NULL,
  `idCaracteristicaPlan` int(11) NOT NULL,
  `idProcesosParte` int(11) NOT NULL,
  PRIMARY KEY (`idPlanControlCaracteristicas`),
  KEY `PlandeControlCara` (`idPlanControl`),
  KEY `CaracteristicasPlan` (`idCaracteristicaPlan`),
  KEY `ProcesoCara` (`idProcesosParte`),
  CONSTRAINT `CaracteristicasPlan` FOREIGN KEY (`idCaracteristicaPlan`) REFERENCES `caracteristicaplan` (`idCaracteristicaPlan`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PlandeControlCara` FOREIGN KEY (`idPlanControl`) REFERENCES `plancontrol` (`idPlanControl`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ProcesoCara` FOREIGN KEY (`idProcesosParte`) REFERENCES `procesosparte` (`idProcesosParte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `plancontrolcaracteristicas` */

/*Table structure for table `procesos` */

DROP TABLE IF EXISTS `procesos`;

CREATE TABLE `procesos` (
  `idProcesos` int(11) NOT NULL AUTO_INCREMENT,
  `nombreProceso` varchar(100) NOT NULL,
  `nombreAbr` varchar(45) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idProcesos`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

/*Data for the table `procesos` */

insert  into `procesos`(`idProcesos`,`nombreProceso`,`nombreAbr`,`descripcion`) values (2,'Recibo y Almacén de Rollo','R. y A. de rollo','Proceso común para los números de parte'),(3,'Inspección por muestreo','Insp. Muestreo','Inspecciones realizadas cada cierto número de piezas. '),(4,'Transporte a almacén de plantilla','Transporte Plantilla','Transporte al almacén'),(6,'Inspección','Inspección','Inspecciones continuas'),(7,'Preparación de Rollo y Troquel','Prep. Rollo y Troquel','Fase intermediaria '),(16,'PiBL 800','PiBL 800','Punzonado Blanking'),(17,'FLT TD200','FLT TD200','Aplanado'),(18,'DR TF1500','DR TF1500','Embutido'),(19,'RST','RST','Restrike'),(20,'TRIM','TRIM','Corte de TRIM'),(21,'FLGBEED','FLGBEED','Pestaña y Embos'),(22,'CamPi','CamPi','Punzonado con Leva'),(23,'FLT','FLT','Planicidad corona'),(24,'Pi','Pi','Punzonado'),(25,'Preparación de Rollo BL400','Preparación BL400','Preparación del Rollo para Blanking 400'),(26,'Almacén de Plantilla','Almacén de Plantilla','Almacén de Plantilla'),(27,'Almacén de partes Estampadas','Almacén Estampado','Proceso de Almacenado '),(28,'Transporte a almacén de partes Estampadas','Transporte a  Almacén de Estampado','Proceso de Almacenado '),(29,'PiRst','PiRst','Pinzonado y Restrike'),(30,'BL400TEST','BL400','Proceso Prueba'),(31,'testing td400800','test','test');

/*Table structure for table `procesosparte` */

DROP TABLE IF EXISTS `procesosparte`;

CREATE TABLE `procesosparte` (
  `idProcesosParte` int(11) NOT NULL AUTO_INCREMENT,
  `idProcesos` int(11) NOT NULL,
  `idParte` int(11) NOT NULL,
  `numeroProceso` int(10) DEFAULT NULL,
  PRIMARY KEY (`idProcesosParte`),
  KEY `ProcesoParte` (`idParte`),
  KEY `ParteProceso` (`idProcesos`),
  CONSTRAINT `ParteProceso` FOREIGN KEY (`idProcesos`) REFERENCES `procesos` (`idProcesos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ProcesoParte` FOREIGN KEY (`idParte`) REFERENCES `parte` (`idParte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=492 DEFAULT CHARSET=latin1;

/*Data for the table `procesosparte` */

insert  into `procesosparte`(`idProcesosParte`,`idProcesos`,`idParte`,`numeroProceso`) values (1,2,1,1),(58,2,9,1),(59,6,9,2),(60,25,9,3),(61,16,9,4),(62,17,9,5),(63,29,9,6),(64,23,9,7),(65,24,9,8),(66,2,19,1),(67,3,19,2),(68,6,19,3),(69,16,19,4),(70,29,19,5),(71,17,19,6),(72,23,19,7),(79,26,20,1),(80,3,20,2),(81,6,20,3),(82,4,20,4),(83,19,20,5),(84,16,20,6),(85,29,20,7),(86,16,20,8),(87,17,20,9),(88,23,20,10),(132,6,30,1),(133,27,30,2),(134,27,30,3),(135,4,30,4),(136,7,30,5),(137,30,30,6),(138,16,30,7),(139,19,30,8),(185,2,38,1),(186,3,38,2),(187,6,38,3),(188,4,38,4),(189,25,38,5),(190,16,38,6),(191,30,38,7),(192,29,38,8),(193,18,38,9),(194,24,38,10),(195,17,38,11),(196,30,38,12),(197,29,38,13),(198,2,39,1),(199,26,39,2),(200,6,39,3),(201,25,39,4),(202,16,39,5),(203,29,39,6),(204,30,39,7),(205,19,39,8),(206,18,39,9),(207,20,39,10),(208,17,39,11),(209,23,39,12),(210,24,39,13),(223,2,41,1),(224,3,41,2),(225,4,41,3),(226,16,41,4),(227,29,41,5),(228,30,41,6),(229,20,41,7),(230,18,41,8),(326,2,51,1),(327,3,51,2),(328,16,51,3),(329,29,51,4),(330,30,51,5),(341,2,53,1),(342,3,53,2),(343,4,53,3),(344,25,53,4),(345,30,53,5),(346,29,53,6),(347,16,53,7),(348,18,53,8),(349,19,53,9),(350,20,53,10),(351,2,54,1),(352,6,54,2),(353,4,54,3),(354,25,54,4),(355,18,54,5),(356,24,54,6),(357,29,54,7),(358,16,54,8),(359,30,54,9),(376,2,56,1),(377,6,56,2),(378,4,56,3),(379,7,56,4),(380,16,56,5),(381,29,56,6),(382,30,56,7),(383,18,56,8),(384,19,56,9),(385,20,56,10),(391,26,58,1),(392,2,58,2),(393,27,58,3),(394,4,58,4),(395,7,58,5),(396,16,58,6),(397,29,58,7),(398,30,58,8),(416,2,60,1),(417,26,60,2),(418,27,60,3),(419,4,60,4),(420,7,60,5),(421,16,60,6),(422,29,60,7),(423,30,60,8),(424,2,61,1),(425,3,61,2),(426,7,61,3),(427,16,61,4),(428,29,61,5),(429,30,61,6),(439,2,63,1),(440,26,63,2),(441,27,63,3),(442,3,63,4),(443,6,63,5),(444,4,63,6),(445,25,63,7),(446,7,63,8),(447,16,63,9),(448,29,63,10),(449,30,63,11),(450,17,63,12),(451,24,63,13),(452,23,63,14),(482,2,69,1),(483,3,69,2),(484,4,69,3),(485,16,69,4),(486,29,69,5),(487,30,69,6),(488,17,69,7),(489,23,69,8),(490,24,69,9),(491,31,69,10);

/*Table structure for table `procesostipo` */

DROP TABLE IF EXISTS `procesostipo`;

CREATE TABLE `procesostipo` (
  `idProcesosTipo` int(11) NOT NULL AUTO_INCREMENT,
  `idProcesos` int(11) NOT NULL,
  `idTipoProceso` int(11) NOT NULL,
  PRIMARY KEY (`idProcesosTipo`),
  KEY `ProcesosTipo` (`idProcesos`),
  KEY `tipoProceso` (`idTipoProceso`),
  CONSTRAINT `ProcesosTipo` FOREIGN KEY (`idProcesos`) REFERENCES `procesos` (`idProcesos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipoProceso` FOREIGN KEY (`idTipoProceso`) REFERENCES `tipoproceso` (`idTipoProceso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

/*Data for the table `procesostipo` */

insert  into `procesostipo`(`idProcesosTipo`,`idProcesos`,`idTipoProceso`) values (1,2,8),(2,3,2),(3,4,3),(4,6,2),(5,7,4),(14,16,5),(15,17,7),(16,18,6),(17,19,6),(18,20,6),(19,21,6),(20,22,6),(21,23,7),(22,24,7),(23,25,4),(24,26,8),(25,27,8),(26,28,8),(27,29,5),(28,30,5),(29,31,7);

/*Table structure for table `revcalidad` */

DROP TABLE IF EXISTS `revcalidad`;

CREATE TABLE `revcalidad` (
  `idRevCalidad` int(11) NOT NULL AUTO_INCREMENT,
  `idPersona` int(11) NOT NULL,
  PRIMARY KEY (`idRevCalidad`),
  KEY `RevCalidadPersona` (`idPersona`),
  CONSTRAINT `RevCalidadPersona` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `revcalidad` */

/*Table structure for table `revingenieria` */

DROP TABLE IF EXISTS `revingenieria`;

CREATE TABLE `revingenieria` (
  `idRevIngenieria` int(11) NOT NULL AUTO_INCREMENT,
  `idPersona` int(11) NOT NULL,
  PRIMARY KEY (`idRevIngenieria`),
  KEY `RevIngPersona` (`idPersona`),
  CONSTRAINT `RevIngPersona` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `revingenieria` */

insert  into `revingenieria`(`idRevIngenieria`,`idPersona`) values (1,6);

/*Table structure for table `revision` */

DROP TABLE IF EXISTS `revision`;

CREATE TABLE `revision` (
  `idRevision` int(11) NOT NULL AUTO_INCREMENT,
  `idParte` int(11) NOT NULL,
  `rev` varchar(45) NOT NULL,
  `fecha` varchar(50) NOT NULL,
  `motivo` varchar(600) NOT NULL,
  `idRevIngenieria` int(11) DEFAULT NULL,
  `idRevCalidad` int(11) DEFAULT NULL,
  `idRevProduccion` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRevision`),
  KEY `ParteRevision` (`idParte`),
  KEY `RevIng` (`idRevIngenieria`),
  KEY `RevProd` (`idRevProduccion`),
  KEY `RevCalidad` (`idRevCalidad`),
  CONSTRAINT `FK_revision_persona` FOREIGN KEY (`idRevIngenieria`) REFERENCES `persona` (`idPersona`),
  CONSTRAINT `FK_revision_persona_2` FOREIGN KEY (`idRevCalidad`) REFERENCES `persona` (`idPersona`),
  CONSTRAINT `FK_revision_persona_3` FOREIGN KEY (`idRevProduccion`) REFERENCES `persona` (`idPersona`),
  CONSTRAINT `ParteRevision` FOREIGN KEY (`idParte`) REFERENCES `parte` (`idParte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

/*Data for the table `revision` */

insert  into `revision`(`idRevision`,`idParte`,`rev`,`fecha`,`motivo`,`idRevIngenieria`,`idRevCalidad`,`idRevProduccion`) values (9,9,'test','12/02/15','test',NULL,NULL,NULL),(16,38,'test','12/02/10','testing ',2,7,5),(17,39,'test','12/02/10','test',NULL,NULL,NULL),(19,41,'rev','12/02/06','test',3,2,8),(31,51,'test','12/02/10','test',NULL,NULL,NULL),(32,53,'test','12/02/13','test',NULL,NULL,NULL),(33,54,'test','12/02/13','test',NULL,NULL,NULL),(34,54,'test1','12/02/13','test',1,7,3),(36,56,'test','12/02/14','test',NULL,NULL,NULL),(38,58,'test','12/02/13','test',NULL,NULL,NULL),(39,58,'test2','12/02/13','test',2,3,8),(42,60,'test','12/02/13','test',NULL,NULL,NULL),(43,60,'tests','12/02/13','test',2,8,5),(44,61,'test','12/02/13','test',2,5,6),(46,63,'test','12/02/14','testing part',3,4,8),(53,69,'test','12/02/14','test',NULL,NULL,NULL),(54,69,'testdd','12/02/14','test',2,7,4);

/*Table structure for table `revproduccion` */

DROP TABLE IF EXISTS `revproduccion`;

CREATE TABLE `revproduccion` (
  `idRevProduccion` int(11) NOT NULL AUTO_INCREMENT,
  `idPersona` int(11) NOT NULL,
  PRIMARY KEY (`idRevProduccion`),
  KEY `RevProduccionPersona` (`idPersona`),
  CONSTRAINT `RevProduccionPersona` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `revproduccion` */

/*Table structure for table `secuencia` */

DROP TABLE IF EXISTS `secuencia`;

CREATE TABLE `secuencia` (
  `idSecuencia` int(11) NOT NULL AUTO_INCREMENT,
  `numeroSecuencia` varchar(45) NOT NULL,
  PRIMARY KEY (`idSecuencia`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `secuencia` */

insert  into `secuencia`(`idSecuencia`,`numeroSecuencia`) values (1,'1'),(2,'2'),(3,'3'),(4,'4'),(5,'5'),(6,'6'),(7,'7'),(8,'8'),(9,'9'),(10,'10'),(11,'11'),(12,'12'),(13,'13'),(14,'14'),(15,'15'),(16,'16'),(17,'17'),(18,'18'),(19,'19'),(20,'20'),(21,'21'),(22,'22'),(23,'23'),(24,'24'),(25,'25'),(26,'26'),(27,'27'),(28,'28'),(29,'29'),(30,'30');

/*Table structure for table `td200a` */

DROP TABLE IF EXISTS `td200a`;

CREATE TABLE `td200a` (
  `idTD200A` int(11) NOT NULL AUTO_INCREMENT,
  `idMaquina` int(11) NOT NULL,
  `idHojaCondiciones` int(11) NOT NULL,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `idFormato` int(11) NOT NULL,
  `idDepartamento` int(10) NOT NULL,
  `idMaterial` int(10) NOT NULL,
  `presionBalanza` int(11) NOT NULL,
  `alturaTroquel` int(11) NOT NULL,
  `presionColchon` varchar(50) NOT NULL,
  `aceiteEmbutido` varchar(80) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  `sujecionTroquInf` varchar(80) NOT NULL,
  `longPerno` varchar(50) NOT NULL,
  `personalEstampado` varchar(100) NOT NULL,
  `observaciones` varchar(600) NOT NULL,
  `prensa` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idTD200A`),
  KEY `MaquinaTD200A` (`idMaquina`),
  KEY `HojaCondicionesTD200A` (`idHojaCondiciones`),
  KEY `TroquelTD200A` (`idTroquel`),
  KEY `SecuenciaTD200A` (`idSecuencia`),
  KEY `FormatoTD200` (`idFormato`),
  KEY `FK_td200a_departamento` (`idDepartamento`),
  KEY `FK_td200a_material` (`idMaterial`),
  CONSTRAINT `FK_td200a_departamento` FOREIGN KEY (`idDepartamento`) REFERENCES `departamento` (`idDepartamento`),
  CONSTRAINT `FK_td200a_material` FOREIGN KEY (`idMaterial`) REFERENCES `material` (`idMaterial`),
  CONSTRAINT `FormatoTD200` FOREIGN KEY (`idFormato`) REFERENCES `formato` (`idFormato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `HojaCondicionesTD200A` FOREIGN KEY (`idHojaCondiciones`) REFERENCES `hojacondiciones` (`idHojaCondiciones`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MaquinaTD200A` FOREIGN KEY (`idMaquina`) REFERENCES `maquina` (`idMaquina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `SecuenciaTD200A` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelTD200A` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `td200a` */

insert  into `td200a`(`idTD200A`,`idMaquina`,`idHojaCondiciones`,`idTroquel`,`idSecuencia`,`idFormato`,`idDepartamento`,`idMaterial`,`presionBalanza`,`alturaTroquel`,`presionColchon`,`aceiteEmbutido`,`sujecionTroqSup`,`sujecionTroquInf`,`longPerno`,`personalEstampado`,`observaciones`,`prensa`) values (4,4,3,3,5,15,1,1,20121,5412,'521','21','21','12','1512','51','51','TDM 200 (A-01)'),(6,4,3,3,6,15,1,1,54,54,'5','4','5','54','54','5','54','TDM 200 (A-01)');

/*Table structure for table `td200b` */

DROP TABLE IF EXISTS `td200b`;

CREATE TABLE `td200b` (
  `idTD200B` int(11) NOT NULL AUTO_INCREMENT,
  `idMaquina` int(11) NOT NULL,
  `idHojaCondiciones` int(11) NOT NULL,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `idFormato` int(11) NOT NULL,
  `idDepartamento` int(10) NOT NULL,
  `idMaterial` int(10) NOT NULL,
  `presionBalanza` int(11) NOT NULL,
  `alturaTroquel` int(11) NOT NULL,
  `presionColchon` varchar(50) NOT NULL,
  `aceiteEmbutido` varchar(80) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  `sujecionTroquInf` varchar(80) NOT NULL,
  `longPerno` varchar(50) NOT NULL,
  `personalEstampado` varchar(100) NOT NULL,
  `observaciones` varchar(600) NOT NULL,
  `prensa` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idTD200B`),
  KEY `MaquinaTD200A` (`idMaquina`),
  KEY `HojaCondicionesTD200A` (`idHojaCondiciones`),
  KEY `TroquelTD200A` (`idTroquel`),
  KEY `SecuenciaTD200A` (`idSecuencia`),
  KEY `FormatoTD200` (`idFormato`),
  KEY `FK_td200a_departamento` (`idDepartamento`),
  KEY `FK_td200a_material` (`idMaterial`),
  CONSTRAINT `td200b_ibfk_1` FOREIGN KEY (`idDepartamento`) REFERENCES `departamento` (`idDepartamento`),
  CONSTRAINT `td200b_ibfk_2` FOREIGN KEY (`idMaterial`) REFERENCES `material` (`idMaterial`),
  CONSTRAINT `td200b_ibfk_3` FOREIGN KEY (`idFormato`) REFERENCES `formato` (`idFormato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td200b_ibfk_4` FOREIGN KEY (`idHojaCondiciones`) REFERENCES `hojacondiciones` (`idHojaCondiciones`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td200b_ibfk_5` FOREIGN KEY (`idMaquina`) REFERENCES `maquina` (`idMaquina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td200b_ibfk_6` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td200b_ibfk_7` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Data for the table `td200b` */

insert  into `td200b`(`idTD200B`,`idMaquina`,`idHojaCondiciones`,`idTroquel`,`idSecuencia`,`idFormato`,`idDepartamento`,`idMaterial`,`presionBalanza`,`alturaTroquel`,`presionColchon`,`aceiteEmbutido`,`sujecionTroqSup`,`sujecionTroquInf`,`longPerno`,`personalEstampado`,`observaciones`,`prensa`) values (3,4,3,3,4,15,1,1,21,21,'120','2122','51','51','212','12','5454','TDM 200 (B-01)');

/*Table structure for table `td400800` */

DROP TABLE IF EXISTS `td400800`;

CREATE TABLE `td400800` (
  `idTD400800` int(11) NOT NULL AUTO_INCREMENT,
  `idMaquina` int(11) NOT NULL,
  `idHojaCondiciones` int(11) NOT NULL,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `idFormato` int(11) NOT NULL,
  `idDepartamento` int(10) NOT NULL,
  `idMaterial` int(10) NOT NULL,
  `presionBalanza` int(11) NOT NULL,
  `alturaTroquel` int(11) NOT NULL,
  `presionColchon` varchar(50) NOT NULL,
  `aceiteEmbutido` varchar(80) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  `sujecionTroquInf` varchar(80) NOT NULL,
  `longPerno` varchar(50) NOT NULL,
  `personalEstampado` varchar(100) NOT NULL,
  `observaciones` varchar(600) NOT NULL,
  `prensa` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idTD400800`),
  KEY `MaquinaTD200A` (`idMaquina`),
  KEY `HojaCondicionesTD200A` (`idHojaCondiciones`),
  KEY `TroquelTD200A` (`idTroquel`),
  KEY `SecuenciaTD200A` (`idSecuencia`),
  KEY `FormatoTD200` (`idFormato`),
  KEY `FK_td200a_departamento` (`idDepartamento`),
  KEY `FK_td200a_material` (`idMaterial`),
  CONSTRAINT `td400800_ibfk_1` FOREIGN KEY (`idDepartamento`) REFERENCES `departamento` (`idDepartamento`),
  CONSTRAINT `td400800_ibfk_2` FOREIGN KEY (`idMaterial`) REFERENCES `material` (`idMaterial`),
  CONSTRAINT `td400800_ibfk_3` FOREIGN KEY (`idFormato`) REFERENCES `formato` (`idFormato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td400800_ibfk_4` FOREIGN KEY (`idHojaCondiciones`) REFERENCES `hojacondiciones` (`idHojaCondiciones`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td400800_ibfk_5` FOREIGN KEY (`idMaquina`) REFERENCES `maquina` (`idMaquina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td400800_ibfk_6` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td400800_ibfk_7` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Data for the table `td400800` */

insert  into `td400800`(`idTD400800`,`idMaquina`,`idHojaCondiciones`,`idTroquel`,`idSecuencia`,`idFormato`,`idDepartamento`,`idMaterial`,`presionBalanza`,`alturaTroquel`,`presionColchon`,`aceiteEmbutido`,`sujecionTroqSup`,`sujecionTroquInf`,`longPerno`,`personalEstampado`,`observaciones`,`prensa`) values (3,4,3,3,4,15,1,1,21,21,'120','2122','51','51','212','12','5454','TDM 200 (B-01)');

/*Table structure for table `tf1500` */

DROP TABLE IF EXISTS `tf1500`;

CREATE TABLE `tf1500` (
  `idTF1500` int(11) NOT NULL AUTO_INCREMENT,
  `idHojaCondiciones` int(11) NOT NULL,
  `idMaquina` int(11) NOT NULL,
  `idTF1500D1` int(11) NOT NULL,
  `idTF1500D2` int(11) NOT NULL,
  `idTF1500D3` int(11) NOT NULL,
  `idTF1500D4` int(11) NOT NULL,
  `idTF1500D5` int(11) NOT NULL,
  `idTF1500D6` int(11) NOT NULL,
  `idFormato` int(11) NOT NULL,
  `aceiteEmbutido` varchar(80) NOT NULL,
  `frecuenciaRociado` varchar(80) NOT NULL,
  `dimCerradaBarra` int(11) NOT NULL,
  `carreraAlimentador` int(11) NOT NULL,
  `carreraElevacion` int(11) NOT NULL,
  `carreraSujetador` int(11) NOT NULL,
  `snpRack` varchar(80) NOT NULL,
  `snpCantidad` varchar(80) NOT NULL,
  `deteccionDb1` varchar(45) NOT NULL,
  `deteccionDb2` varchar(45) NOT NULL,
  `copasSuccion` varchar(90) NOT NULL,
  `alturaTroquel` int(11) NOT NULL,
  `presionBalanza` int(11) NOT NULL,
  `observaciones` varchar(600) NOT NULL,
  `matriz` varchar(45) NOT NULL,
  `aut1on` varchar(45) NOT NULL,
  `aut1off` varchar(45) NOT NULL,
  `aut2on` varchar(45) NOT NULL,
  `aut2off` varchar(45) NOT NULL,
  `aut3on` varchar(45) NOT NULL,
  `aut3off` varchar(45) NOT NULL,
  `aut4on` varchar(45) NOT NULL,
  `aut4off` varchar(45) NOT NULL,
  `aut5on` varchar(45) NOT NULL,
  `aut5off` varchar(45) NOT NULL,
  `aut6on` varchar(45) NOT NULL,
  `aut6off` varchar(45) NOT NULL,
  PRIMARY KEY (`idTF1500`),
  KEY `TF1500D1` (`idTF1500D1`),
  KEY `TF1500D2` (`idTF1500D2`),
  KEY `TF1500D3` (`idTF1500D3`),
  KEY `TF1500D4` (`idTF1500D4`),
  KEY `TF1500D5` (`idTF1500D5`),
  KEY `TF1500D6` (`idTF1500D6`),
  KEY `HojaCondTF1500` (`idHojaCondiciones`),
  KEY `FormatoTF1500` (`idFormato`),
  KEY `MaquinaTF1500` (`idMaquina`),
  CONSTRAINT `FormatoTF1500` FOREIGN KEY (`idFormato`) REFERENCES `formato` (`idFormato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `HojaCondTF1500` FOREIGN KEY (`idHojaCondiciones`) REFERENCES `hojacondiciones` (`idHojaCondiciones`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MaquinaTF1500` FOREIGN KEY (`idMaquina`) REFERENCES `maquina` (`idMaquina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TF1500D1` FOREIGN KEY (`idTF1500D1`) REFERENCES `tf1500d1` (`idTF1500D1`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TF1500D2` FOREIGN KEY (`idTF1500D2`) REFERENCES `tf1500d2` (`idTF1500D2`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TF1500D3` FOREIGN KEY (`idTF1500D3`) REFERENCES `tf1500d3` (`idTF1500D3`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TF1500D4` FOREIGN KEY (`idTF1500D4`) REFERENCES `tf1500d4` (`idTF1500D4`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TF1500D5` FOREIGN KEY (`idTF1500D5`) REFERENCES `tf1500d5` (`idTF1500D5`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TF1500D6` FOREIGN KEY (`idTF1500D6`) REFERENCES `tf1500d6` (`idTF1500D6`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf1500` */

/*Table structure for table `tf1500d1` */

DROP TABLE IF EXISTS `tf1500d1`;

CREATE TABLE `tf1500d1` (
  `idTF1500D1` int(11) NOT NULL AUTO_INCREMENT,
  `idSecuencia` int(11) NOT NULL,
  `idTroquel` int(11) NOT NULL,
  `longPerno` varchar(80) NOT NULL,
  `presColchon` varchar(80) NOT NULL,
  `sujecionTroqInf` varchar(80) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  PRIMARY KEY (`idTF1500D1`),
  KEY `Troquel1500d1` (`idTroquel`),
  KEY `Secuencia1500d1` (`idSecuencia`),
  CONSTRAINT `Secuencia1500d1` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Troquel1500d1` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf1500d1` */

/*Table structure for table `tf1500d2` */

DROP TABLE IF EXISTS `tf1500d2`;

CREATE TABLE `tf1500d2` (
  `idTF1500D2` int(11) NOT NULL AUTO_INCREMENT,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `longPerno` varchar(80) NOT NULL,
  `presColchon` varchar(80) NOT NULL,
  `sujecionTroqInf` varchar(80) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  PRIMARY KEY (`idTF1500D2`),
  KEY `TroquelTF1500D2` (`idTroquel`),
  KEY `Secuencia1500d2` (`idSecuencia`),
  CONSTRAINT `Secuencia1500d2` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelTF1500D2` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf1500d2` */

/*Table structure for table `tf1500d3` */

DROP TABLE IF EXISTS `tf1500d3`;

CREATE TABLE `tf1500d3` (
  `idTF1500D3` int(11) NOT NULL AUTO_INCREMENT,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `longPerno` varchar(80) NOT NULL,
  `presColchon` varchar(80) NOT NULL,
  `sujecionTroqInf` varchar(80) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  PRIMARY KEY (`idTF1500D3`),
  KEY `TroquelTF1500D3` (`idTroquel`),
  KEY `Secuencia1500d3` (`idSecuencia`),
  CONSTRAINT `Secuencia1500d3` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelTF1500D3` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf1500d3` */

/*Table structure for table `tf1500d4` */

DROP TABLE IF EXISTS `tf1500d4`;

CREATE TABLE `tf1500d4` (
  `idTF1500D4` int(11) NOT NULL AUTO_INCREMENT,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `longPerno` varchar(80) NOT NULL,
  `presColchon` varchar(80) NOT NULL,
  `sujecionTroqInf` varchar(80) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  PRIMARY KEY (`idTF1500D4`),
  KEY `TroquelTF1500D4` (`idTroquel`),
  KEY `SecuenciaTF1500D4` (`idSecuencia`),
  CONSTRAINT `SecuenciaTF1500D4` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelTF1500D4` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf1500d4` */

/*Table structure for table `tf1500d5` */

DROP TABLE IF EXISTS `tf1500d5`;

CREATE TABLE `tf1500d5` (
  `idTF1500D5` int(11) NOT NULL AUTO_INCREMENT,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `longPerno` varchar(80) NOT NULL,
  `presColchon` varchar(80) NOT NULL,
  `sujecionTroqInf` varchar(80) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  PRIMARY KEY (`idTF1500D5`),
  KEY `TroquelTF1500D5` (`idTroquel`),
  KEY `Secuencia1500d5` (`idSecuencia`),
  CONSTRAINT `Secuencia1500d5` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelTF1500D5` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf1500d5` */

/*Table structure for table `tf1500d6` */

DROP TABLE IF EXISTS `tf1500d6`;

CREATE TABLE `tf1500d6` (
  `idTF1500D6` int(11) NOT NULL AUTO_INCREMENT,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `longPerno` varchar(80) NOT NULL,
  `presColchon` varchar(80) NOT NULL,
  `sujecionTroqInf` varchar(80) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  PRIMARY KEY (`idTF1500D6`),
  KEY `TroquelTF1500D6` (`idTroquel`),
  KEY `SecuenciaTF1500D6` (`idSecuencia`),
  CONSTRAINT `SecuenciaTF1500D6` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelTF1500D6` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf1500d6` */

/*Table structure for table `tf500` */

DROP TABLE IF EXISTS `tf500`;

CREATE TABLE `tf500` (
  `idTF500` int(11) NOT NULL AUTO_INCREMENT,
  `idMaquina` int(11) NOT NULL,
  `idHojaCondiciones` int(11) NOT NULL,
  `idTF500D1` int(11) NOT NULL,
  `idTF500D2` int(11) NOT NULL,
  `idTF500D3` int(11) NOT NULL,
  `idTF500D4` int(11) NOT NULL,
  `idTF500D5` int(11) NOT NULL,
  `IDTF500D6` int(11) NOT NULL,
  `IDTF500D7` int(11) NOT NULL,
  `idFormato` int(11) NOT NULL,
  `aceiteEmbutido` varchar(90) NOT NULL,
  `frecuenciaRociado` varchar(95) NOT NULL,
  `distanciaAbrazadera` varchar(95) NOT NULL,
  `carreraAlimentacion` int(11) NOT NULL,
  `carreraAlza` int(11) NOT NULL,
  `carreraDedos` int(11) NOT NULL,
  `rack` varchar(45) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `deteccionDb` varchar(45) NOT NULL,
  `carreraBandaMag` varchar(90) NOT NULL,
  `alturaTroquel` int(11) NOT NULL,
  `rpm` int(11) NOT NULL,
  `presionSobreCarga` varchar(95) NOT NULL,
  `presionBalanza` int(11) NOT NULL,
  `aut1on` varchar(45) NOT NULL,
  `aut1off` varchar(45) NOT NULL,
  `aut2on` varchar(45) NOT NULL,
  `aut2off` varchar(45) NOT NULL,
  `aut3on` varchar(45) NOT NULL,
  `aut3off` varchar(45) NOT NULL,
  `aut4on` varchar(45) NOT NULL,
  `aut4off` varchar(45) NOT NULL,
  PRIMARY KEY (`idTF500`),
  KEY `MaquinaTF500` (`idMaquina`),
  KEY `HojaCondicionesTF500` (`idHojaCondiciones`),
  KEY `td1TF500` (`idTF500D1`),
  KEY `td2TF500` (`idTF500D2`),
  KEY `td3TF500` (`idTF500D3`),
  KEY `td4TF500` (`idTF500D4`),
  KEY `td5TF500` (`idTF500D5`),
  KEY `td6TF500` (`IDTF500D6`),
  KEY `td7TF500` (`IDTF500D7`),
  KEY `FormatoTF500` (`idFormato`),
  CONSTRAINT `FormatoTF500` FOREIGN KEY (`idFormato`) REFERENCES `formato` (`idFormato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `HojaCondicionesTF500` FOREIGN KEY (`idHojaCondiciones`) REFERENCES `hojacondiciones` (`idHojaCondiciones`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MaquinaTF500` FOREIGN KEY (`idMaquina`) REFERENCES `maquina` (`idMaquina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td1TF500` FOREIGN KEY (`idTF500D1`) REFERENCES `tf500d1` (`idTF500D1`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td2TF500` FOREIGN KEY (`idTF500D2`) REFERENCES `tf500d2` (`idTF500D2`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td3TF500` FOREIGN KEY (`idTF500D3`) REFERENCES `tf500d3` (`idTF500D3`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td4TF500` FOREIGN KEY (`idTF500D4`) REFERENCES `tf500d4` (`idTF500D4`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td5TF500` FOREIGN KEY (`idTF500D5`) REFERENCES `tf500d5` (`idTF500D5`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td6TF500` FOREIGN KEY (`IDTF500D6`) REFERENCES `tf500d6` (`idTF500D6`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `td7TF500` FOREIGN KEY (`IDTF500D7`) REFERENCES `tf500d7` (`idTF500D7`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf500` */

/*Table structure for table `tf500d1` */

DROP TABLE IF EXISTS `tf500d1`;

CREATE TABLE `tf500d1` (
  `idTF500D1` int(11) NOT NULL AUTO_INCREMENT,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `longPerno` varchar(45) NOT NULL,
  `presCilindroNitrogeno` varchar(90) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  `sujecionTroqInf` varchar(80) NOT NULL,
  PRIMARY KEY (`idTF500D1`),
  KEY `TroquelTF500D1` (`idTroquel`),
  KEY `SecuenciaTF500D1` (`idSecuencia`),
  CONSTRAINT `SecuenciaTF500D1` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelTF500D1` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf500d1` */

/*Table structure for table `tf500d2` */

DROP TABLE IF EXISTS `tf500d2`;

CREATE TABLE `tf500d2` (
  `idTF500D2` int(11) NOT NULL AUTO_INCREMENT,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `longPerno` varchar(45) NOT NULL,
  `presCilindroNitrogeno` varchar(90) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  `sujecionTroqInf` varchar(80) NOT NULL,
  PRIMARY KEY (`idTF500D2`),
  KEY `Troquel` (`idTroquel`),
  KEY `SecuenciasTF500D2` (`idSecuencia`),
  CONSTRAINT `SecuenciasTF500D2` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelesTF500D2` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf500d2` */

/*Table structure for table `tf500d3` */

DROP TABLE IF EXISTS `tf500d3`;

CREATE TABLE `tf500d3` (
  `idTF500D3` int(11) NOT NULL AUTO_INCREMENT,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `longPerno` varchar(45) NOT NULL,
  `presCilindroNitrogeno` varchar(90) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  `sujecionTroqInf` varchar(80) NOT NULL,
  PRIMARY KEY (`idTF500D3`),
  KEY `Troquel` (`idTroquel`),
  KEY `SecuenciaTF500D3` (`idSecuencia`),
  CONSTRAINT `SecuenciaTF500D3` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelTF500D3` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf500d3` */

/*Table structure for table `tf500d4` */

DROP TABLE IF EXISTS `tf500d4`;

CREATE TABLE `tf500d4` (
  `idTF500D4` int(11) NOT NULL AUTO_INCREMENT,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `longPerno` varchar(45) NOT NULL,
  `presCilindroNitrogeno` varchar(90) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  `sujecionTroqInf` varchar(80) NOT NULL,
  PRIMARY KEY (`idTF500D4`),
  KEY `Troquel` (`idTroquel`),
  KEY `SecuenciaTF500D4` (`idSecuencia`),
  CONSTRAINT `SecuenciaTF500D4` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelTF500D4` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf500d4` */

/*Table structure for table `tf500d5` */

DROP TABLE IF EXISTS `tf500d5`;

CREATE TABLE `tf500d5` (
  `idTF500D5` int(11) NOT NULL AUTO_INCREMENT,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `longPerno` varchar(45) NOT NULL,
  `presCilindroNitrogeno` varchar(90) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  `sujecionTroqInf` varchar(80) NOT NULL,
  PRIMARY KEY (`idTF500D5`),
  KEY `Troquel` (`idTroquel`),
  KEY `SecuenciaTF500D5` (`idSecuencia`),
  CONSTRAINT `SecuenciaTF500D5` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelTF500D5` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf500d5` */

/*Table structure for table `tf500d6` */

DROP TABLE IF EXISTS `tf500d6`;

CREATE TABLE `tf500d6` (
  `idTF500D6` int(11) NOT NULL AUTO_INCREMENT,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `longPerno` varchar(45) NOT NULL,
  `presCilindroNitrogeno` varchar(90) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  `sujecionTroqInf` varchar(80) NOT NULL,
  PRIMARY KEY (`idTF500D6`),
  KEY `Troquel` (`idTroquel`),
  KEY `SecuenciaTF500D6` (`idSecuencia`),
  CONSTRAINT `SecuenciaTF500D6` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelTF500D6` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf500d6` */

/*Table structure for table `tf500d7` */

DROP TABLE IF EXISTS `tf500d7`;

CREATE TABLE `tf500d7` (
  `idTF500D7` int(11) NOT NULL AUTO_INCREMENT,
  `idTroquel` int(11) NOT NULL,
  `idSecuencia` int(11) NOT NULL,
  `longPerno` varchar(45) NOT NULL,
  `presCilindroNitrogeno` varchar(90) NOT NULL,
  `sujecionTroqSup` varchar(80) NOT NULL,
  `sujecionTroqInf` varchar(80) NOT NULL,
  PRIMARY KEY (`idTF500D7`),
  KEY `Troquel` (`idTroquel`),
  KEY `SecuenciaTF500D7` (`idSecuencia`),
  CONSTRAINT `SecuenciaTF500D7` FOREIGN KEY (`idSecuencia`) REFERENCES `secuencia` (`idSecuencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TroquelTF500D7` FOREIGN KEY (`idTroquel`) REFERENCES `troquel` (`idTroquel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tf500d7` */

/*Table structure for table `tipomaquina` */

DROP TABLE IF EXISTS `tipomaquina`;

CREATE TABLE `tipomaquina` (
  `idTipoMaquina` int(11) NOT NULL AUTO_INCREMENT,
  `nombreMaquina` varchar(100) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `numeroMaquina` varchar(100) NOT NULL,
  PRIMARY KEY (`idTipoMaquina`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `tipomaquina` */

insert  into `tipomaquina`(`idTipoMaquina`,`nombreMaquina`,`capacidad`,`numeroMaquina`) values (1,'Blanking 200',200,'BL200'),(2,'Blanking 400',400,'BL400'),(3,'Blanking 800',800,'BL800'),(4,'Tandem 200 (Tipo A)',200,'TD200A'),(5,'Tandem 200 (Tipo B)',200,'TD200B'),(6,'Tandem 400',400,'TD400'),(7,'Tandem 800',800,'TD800'),(8,'Transfer 500',500,'TF500'),(9,'Transfer 1500',1500,'TF1500');

/*Table structure for table `tipoproceso` */

DROP TABLE IF EXISTS `tipoproceso`;

CREATE TABLE `tipoproceso` (
  `idTipoProceso` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipoProceso`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `tipoproceso` */

insert  into `tipoproceso`(`idTipoProceso`,`tipo`) values (1,'Entrada Material'),(2,'Inspecciones'),(3,'Transporte'),(4,'Preparaciones'),(5,'Blanking'),(6,'Transfer'),(7,'Tandem'),(8,'Almacén y Entradas');

/*Table structure for table `troquel` */

DROP TABLE IF EXISTS `troquel`;

CREATE TABLE `troquel` (
  `idTroquel` int(11) NOT NULL AUTO_INCREMENT,
  `nombreTroquel` varchar(100) NOT NULL,
  `dFR` int(11) NOT NULL,
  `dLR` int(11) NOT NULL,
  `dH` int(11) NOT NULL,
  `pesoSup` int(11) NOT NULL,
  `pesoTotal` int(11) NOT NULL,
  `piezaGolpe` int(11) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idTroquel`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `troquel` */

insert  into `troquel`(`idTroquel`,`nombreTroquel`,`dFR`,`dLR`,`dH`,`pesoSup`,`pesoTotal`,`piezaGolpe`,`descripcion`) values (3,'D1-PIBL',1090,770,810,480,1320,3,'Utilizado para Pinzonado'),(6,'Troquelin',65,65,5454,554,542,4521,'5454');

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuarioTipo` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `FK_usuario_usuariotipo` (`idUsuarioTipo`),
  CONSTRAINT `FK_usuario_usuariotipo` FOREIGN KEY (`idUsuarioTipo`) REFERENCES `usuariotipo` (`idUsuarioTipo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

insert  into `usuario`(`idUsuario`,`idUsuarioTipo`,`nombre`,`pass`) values (1,1,'Alejandro','2027'),(7,1,'F.UGALDE','1234'),(8,3,'Test','2020'),(9,3,'Calidad','1234'),(10,1,'Juan','1010'),(11,3,'Ensamble','2020'),(12,3,'Aseg Calidad','000');

/*Table structure for table `usuariotipo` */

DROP TABLE IF EXISTS `usuariotipo`;

CREATE TABLE `usuariotipo` (
  `idUsuarioTipo` int(10) NOT NULL AUTO_INCREMENT,
  `tipoUsuario` varchar(50) NOT NULL,
  PRIMARY KEY (`idUsuarioTipo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `usuariotipo` */

insert  into `usuariotipo`(`idUsuarioTipo`,`tipoUsuario`) values (1,'Administrador'),(2,'Consultor'),(3,'Visitante');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
