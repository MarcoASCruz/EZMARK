CREATE DATABASE  IF NOT EXISTS `gerenciador_de_favoritos` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `gerenciador_de_favoritos`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: gerenciador_de_favoritos
-- ------------------------------------------------------
-- Server version	5.5.27-log

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
-- Table structure for table `favorito`
--

DROP TABLE IF EXISTS `favorito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorito` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pasta` int(11) NOT NULL,
  `url` text NOT NULL,
  `titulo` varchar(300) NOT NULL,
  `descricao` longtext,
  `quant_acesso` int(11) DEFAULT '0',
  `data_acesso` datetime DEFAULT NULL,
  `data_criacao` datetime NOT NULL,
  `numEstrela` int(11) DEFAULT '0',
  `imagem` blob,
  `acesso_rapido` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_Favorito_Pasta1_idx` (`id_pasta`),
  CONSTRAINT `fk_Favorito_Pasta1` FOREIGN KEY (`id_pasta`) REFERENCES `pasta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorito`
--

LOCK TABLES `favorito` WRITE;
/*!40000 ALTER TABLE `favorito` DISABLE KEYS */;
INSERT INTO `favorito` VALUES (7,1,'http://g1.globo.com/','G1','',1,'2016-06-20 01:26:34','2016-06-20 01:26:31',2,NULL,0),(8,1,'http://globoesporte.globo.com/','Globo Esporte','',0,NULL,'2016-06-20 01:27:11',2,NULL,0),(9,1,'http://www.asp.net/entity-framework','entity','',0,NULL,'2016-06-20 01:35:34',0,NULL,0),(10,1,'http://olhardigital.uol.com.br/pro/noticia/novo-supercomputador-chines-e-o-mais-rapido-do-mundo/59481','supercomputador','',0,NULL,'2016-06-20 01:35:53',0,NULL,0),(11,1,'http://www.techtudo.com.br/','techtudo','',0,NULL,'2016-06-20 01:36:15',0,NULL,0),(12,1,'http://www.infomoney.com.br/','money','',0,NULL,'2016-06-20 01:36:40',0,NULL,0);
/*!40000 ALTER TABLE `favorito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorito_tag`
--

DROP TABLE IF EXISTS `favorito_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorito_tag` (
  `id_favorito` int(11) NOT NULL,
  `tag_nome` varchar(200) NOT NULL,
  PRIMARY KEY (`id_favorito`,`tag_nome`),
  KEY `fk_Favorito_has_Tag_Favorito_idx` (`id_favorito`),
  KEY `fk_favorito_tag_tag1_idx` (`tag_nome`),
  CONSTRAINT `fk_Favorito_has_Tag_Favorito` FOREIGN KEY (`id_favorito`) REFERENCES `favorito` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_favorito_tag_tag1` FOREIGN KEY (`tag_nome`) REFERENCES `tag` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorito_tag`
--

LOCK TABLES `favorito_tag` WRITE;
/*!40000 ALTER TABLE `favorito_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorito_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasta`
--

DROP TABLE IF EXISTS `pasta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pasta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pasta_pai` int(11) NOT NULL,
  `nome` text NOT NULL,
  `data_criacao` datetime DEFAULT NULL,
  `num_estrela` int(11) DEFAULT NULL,
  `publica` tinyint(1) DEFAULT NULL,
  `imagem` blob,
  `descricao` text,
  PRIMARY KEY (`id`,`id_pasta_pai`),
  KEY `fk_Pasta_Pasta1_idx` (`id_pasta_pai`),
  CONSTRAINT `fk_Pasta_Pasta1` FOREIGN KEY (`id_pasta_pai`) REFERENCES `pasta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasta`
--

LOCK TABLES `pasta` WRITE;
/*!40000 ALTER TABLE `pasta` DISABLE KEYS */;
INSERT INTO `pasta` VALUES (1,1,'Início','2016-04-13 08:17:46',NULL,NULL,NULL,NULL),(4,4,'Início','2016-04-24 16:56:17',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `pasta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasta_tag`
--

DROP TABLE IF EXISTS `pasta_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pasta_tag` (
  `id_pasta` int(11) NOT NULL,
  `tag_nome` varchar(200) NOT NULL,
  PRIMARY KEY (`id_pasta`,`tag_nome`),
  KEY `fk_Pasta_has_Tag_Pasta1_idx` (`id_pasta`),
  KEY `fk_pasta_tag_tag1_idx` (`tag_nome`),
  CONSTRAINT `fk_Pasta_has_Tag_Pasta1` FOREIGN KEY (`id_pasta`) REFERENCES `pasta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pasta_tag_tag1` FOREIGN KEY (`tag_nome`) REFERENCES `tag` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasta_tag`
--

LOCK TABLES `pasta_tag` WRITE;
/*!40000 ALTER TABLE `pasta_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `pasta_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `authority_UNIQUE` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `nome` varchar(200) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES ('3'),('tag teste'),('teste');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxonomia`
--

DROP TABLE IF EXISTS `taxonomia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taxonomia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pasta_pai` int(11) NOT NULL,
  `nome` text NOT NULL,
  `data_criacao` datetime DEFAULT NULL,
  `num_estrela` int(11) DEFAULT NULL,
  `publica` tinyint(1) DEFAULT NULL,
  `imagem` blob,
  `descricao` text,
  PRIMARY KEY (`id`,`id_pasta_pai`),
  KEY `fk_Pasta_Pasta1_idx` (`id_pasta_pai`),
  CONSTRAINT `fk_Pasta_Pasta10` FOREIGN KEY (`id_pasta_pai`) REFERENCES `taxonomia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxonomia`
--

LOCK TABLES `taxonomia` WRITE;
/*!40000 ALTER TABLE `taxonomia` DISABLE KEYS */;
/*!40000 ALTER TABLE `taxonomia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxonomia_tag`
--

DROP TABLE IF EXISTS `taxonomia_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taxonomia_tag` (
  `tag_nome` varchar(200) NOT NULL,
  `id_taxonomia` int(11) NOT NULL,
  PRIMARY KEY (`tag_nome`,`id_taxonomia`),
  KEY `fk_tag_has_taxonomia_taxonomia1_idx` (`id_taxonomia`),
  KEY `fk_tag_has_taxonomia_tag1_idx` (`tag_nome`),
  CONSTRAINT `fk_tag_has_taxonomia_tag1` FOREIGN KEY (`tag_nome`) REFERENCES `tag` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tag_has_taxonomia_taxonomia1` FOREIGN KEY (`id_taxonomia`) REFERENCES `taxonomia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxonomia_tag`
--

LOCK TABLES `taxonomia_tag` WRITE;
/*!40000 ALTER TABLE `taxonomia_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `taxonomia_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `email` varchar(45) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`email`,`role_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_user_role1_idx` (`role_id`),
  CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'MyUser','1234',1,'user@gmail.com',1),(2,'marco','1234',1,'marco@gmail.com',1),(3,'allan','1234',1,'allan@mail.com',1),(5,'allan','1234',1,'allan@mail.net',1),(6,'allan','1234',1,'teste@mail.com',1),(7,'teste123','1234',1,'teste123@gmail.com',1),(12,'teste123','1234',1,'teste1234@gmail.com',1),(14,'teste','1234',1,'teste@teste.com',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_pasta`
--

DROP TABLE IF EXISTS `user_has_pasta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_has_pasta` (
  `user_id` int(11) NOT NULL,
  `pasta_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`pasta_id`),
  KEY `fk_user_has_pasta_pasta1_idx` (`pasta_id`),
  KEY `fk_user_has_pasta_user1_idx` (`user_id`),
  CONSTRAINT `fk_user_has_pasta_pasta1` FOREIGN KEY (`pasta_id`) REFERENCES `pasta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_pasta_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_pasta`
--

LOCK TABLES `user_has_pasta` WRITE;
/*!40000 ALTER TABLE `user_has_pasta` DISABLE KEYS */;
INSERT INTO `user_has_pasta` VALUES (1,1),(2,4);
/*!40000 ALTER TABLE `user_has_pasta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-21 19:57:58
