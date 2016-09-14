CREATE DATABASE  IF NOT EXISTS `gerenciador_de_favoritos` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `gerenciador_de_favoritos`;


-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 14-Set-2016 às 12:59
-- Versão do servidor: 5.7.9
-- PHP Version: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gerenciador_de_favoritos`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `favorito`
--

DROP TABLE IF EXISTS `favorito`;
CREATE TABLE IF NOT EXISTS `favorito` (
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
  KEY `fk_Favorito_Pasta1_idx` (`id_pasta`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `favorito`
--

INSERT INTO `favorito` (`id`, `id_pasta`, `url`, `titulo`, `descricao`, `quant_acesso`, `data_acesso`, `data_criacao`, `numEstrela`, `imagem`, `acesso_rapido`) VALUES
(7, 1, 'http://g1.globo.com/', 'G1', '', 1, '2016-06-20 01:26:34', '2016-06-20 01:26:31', 2, NULL, 0),
(8, 1, 'http://globoesporte.globo.com/', 'Globo Esporte', '', 0, NULL, '2016-06-20 01:27:11', 2, NULL, 0),
(9, 1, 'http://www.asp.net/entity-framework', 'entity', '', 0, NULL, '2016-06-20 01:35:34', 0, NULL, 0),
(10, 1, 'http://olhardigital.uol.com.br/pro/noticia/novo-supercomputador-chines-e-o-mais-rapido-do-mundo/59481', 'supercomputador', '', 0, NULL, '2016-06-20 01:35:53', 0, NULL, 0),
(11, 1, 'http://www.techtudo.com.br/', 'techtudo', '', 0, NULL, '2016-06-20 01:36:15', 0, NULL, 0),
(12, 1, 'http://www.infomoney.com.br/', 'money', '', 0, NULL, '2016-06-20 01:36:40', 0, NULL, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `favorito_tag`
--

DROP TABLE IF EXISTS `favorito_tag`;
CREATE TABLE IF NOT EXISTS `favorito_tag` (
  `id_favorito` int(11) NOT NULL,
  `tag_nome` varchar(200) NOT NULL,
  PRIMARY KEY (`id_favorito`,`tag_nome`),
  KEY `fk_Favorito_has_Tag_Favorito_idx` (`id_favorito`),
  KEY `fk_favorito_tag_tag1_idx` (`tag_nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pasta`
--

DROP TABLE IF EXISTS `pasta`;
CREATE TABLE IF NOT EXISTS `pasta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pasta_pai` int(11) NOT NULL,
  `nome` text NOT NULL,
  `data_criacao` datetime DEFAULT NULL,
  `num_estrela` int(11) DEFAULT NULL,
  `publica` tinyint(1) DEFAULT NULL,
  `imagem` blob,
  `descricao` text,
  PRIMARY KEY (`id`,`id_pasta_pai`),
  KEY `fk_Pasta_Pasta1_idx` (`id_pasta_pai`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `pasta`
--

INSERT INTO `pasta` (`id`, `id_pasta_pai`, `nome`, `data_criacao`, `num_estrela`, `publica`, `imagem`, `descricao`) VALUES
(1, 1, 'Início', '2016-04-13 08:17:46', NULL, NULL, NULL, NULL),
(4, 4, 'Início', '2016-04-24 16:56:17', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pasta_tag`
--

DROP TABLE IF EXISTS `pasta_tag`;
CREATE TABLE IF NOT EXISTS `pasta_tag` (
  `id_pasta` int(11) NOT NULL,
  `tag_nome` varchar(200) NOT NULL,
  PRIMARY KEY (`id_pasta`,`tag_nome`),
  KEY `fk_Pasta_has_Tag_Pasta1_idx` (`id_pasta`),
  KEY `fk_pasta_tag_tag1_idx` (`tag_nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `authority_UNIQUE` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `role`
--

INSERT INTO `role` (`id`, `authority`) VALUES
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tag`
--

DROP TABLE IF EXISTS `tag`;
CREATE TABLE IF NOT EXISTS `tag` (
  `nome` varchar(200) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tag`
--

INSERT INTO `tag` (`nome`) VALUES
('3'),
('ASP.Net'),
('digital'),
('esporte'),
('futebol'),
('games'),
('informação'),
('informatica'),
('java'),
('jogos'),
('jornal'),
('jornalísmo'),
('mma'),
('news'),
('notícia'),
('portal'),
('programacao'),
('programming'),
('tag teste'),
('teste');

-- --------------------------------------------------------

--
-- Estrutura da tabela `taxonomia`
--

DROP TABLE IF EXISTS `taxonomia`;
CREATE TABLE IF NOT EXISTS `taxonomia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pasta_pai` int(11) NOT NULL,
  `nome` text NOT NULL,
  `data_criacao` datetime DEFAULT NULL,
  `num_estrela` int(11) DEFAULT NULL,
  `publica` tinyint(1) DEFAULT NULL,
  `imagem` blob,
  `descricao` text,
  PRIMARY KEY (`id`,`id_pasta_pai`),
  KEY `fk_Pasta_Pasta1_idx` (`id_pasta_pai`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `taxonomia`
--

INSERT INTO `taxonomia` (`id`, `id_pasta_pai`, `nome`, `data_criacao`, `num_estrela`, `publica`, `imagem`, `descricao`) VALUES
(1, 1, 'home', NULL, NULL, NULL, NULL, NULL),
(2, 1, 'esportes', NULL, NULL, NULL, NULL, NULL),
(3, 1, 'tecnologia', NULL, NULL, NULL, NULL, NULL),
(4, 1, 'jogos', NULL, NULL, NULL, NULL, NULL),
(5, 1, 'notícia', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `taxonomia_tag`
--

DROP TABLE IF EXISTS `taxonomia_tag`;
CREATE TABLE IF NOT EXISTS `taxonomia_tag` (
  `tag_nome` varchar(200) NOT NULL,
  `id_taxonomia` int(11) NOT NULL,
  PRIMARY KEY (`tag_nome`,`id_taxonomia`),
  KEY `fk_tag_has_taxonomia_taxonomia1_idx` (`id_taxonomia`),
  KEY `fk_tag_has_taxonomia_tag1_idx` (`tag_nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `taxonomia_tag`
--

INSERT INTO `taxonomia_tag` (`tag_nome`, `id_taxonomia`) VALUES
('esporte', 2),
('futebol', 2),
('mma', 2),
('ASP.Net', 3),
('digital', 3),
('informatica', 3),
('java', 3),
('programacao', 3),
('programming', 3),
('games', 4),
('informação', 5),
('jornal', 5),
('jornalismo', 5),
('news', 5),
('portal', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `email` varchar(45) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`email`,`role_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_user_role1_idx` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `enable`, `email`, `role_id`) VALUES
(1, 'MyUser', '1234', 1, 'user@gmail.com', 1),
(2, 'marco', '1234', 1, 'marco@gmail.com', 1),
(3, 'allan', '1234', 1, 'allan@mail.com', 1),
(5, 'allan', '1234', 1, 'allan@mail.net', 1),
(6, 'allan', '1234', 1, 'teste@mail.com', 1),
(7, 'teste123', '1234', 1, 'teste123@gmail.com', 1),
(12, 'teste123', '1234', 1, 'teste1234@gmail.com', 1),
(14, 'teste', '1234', 1, 'teste@teste.com', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `user_has_pasta`
--

DROP TABLE IF EXISTS `user_has_pasta`;
CREATE TABLE IF NOT EXISTS `user_has_pasta` (
  `user_id` int(11) NOT NULL,
  `pasta_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`pasta_id`),
  KEY `fk_user_has_pasta_pasta1_idx` (`pasta_id`),
  KEY `fk_user_has_pasta_user1_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `user_has_pasta`
--

INSERT INTO `user_has_pasta` (`user_id`, `pasta_id`) VALUES
(1, 1),
(2, 4);

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `favorito`
--
ALTER TABLE `favorito`
  ADD CONSTRAINT `fk_Favorito_Pasta1` FOREIGN KEY (`id_pasta`) REFERENCES `pasta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `favorito_tag`
--
ALTER TABLE `favorito_tag`
  ADD CONSTRAINT `fk_Favorito_has_Tag_Favorito` FOREIGN KEY (`id_favorito`) REFERENCES `favorito` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_favorito_tag_tag1` FOREIGN KEY (`tag_nome`) REFERENCES `tag` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `pasta`
--
ALTER TABLE `pasta`
  ADD CONSTRAINT `fk_Pasta_Pasta1` FOREIGN KEY (`id_pasta_pai`) REFERENCES `pasta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `pasta_tag`
--
ALTER TABLE `pasta_tag`
  ADD CONSTRAINT `fk_Pasta_has_Tag_Pasta1` FOREIGN KEY (`id_pasta`) REFERENCES `pasta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pasta_tag_tag1` FOREIGN KEY (`tag_nome`) REFERENCES `tag` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `taxonomia`
--
ALTER TABLE `taxonomia`
  ADD CONSTRAINT `fk_Pasta_Pasta10` FOREIGN KEY (`id_pasta_pai`) REFERENCES `taxonomia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `taxonomia_tag`
--
ALTER TABLE `taxonomia_tag`
  ADD CONSTRAINT `fk_tag_has_taxonomia_tag1` FOREIGN KEY (`tag_nome`) REFERENCES `tag` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tag_has_taxonomia_taxonomia1` FOREIGN KEY (`id_taxonomia`) REFERENCES `taxonomia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `user_has_pasta`
--
ALTER TABLE `user_has_pasta`
  ADD CONSTRAINT `fk_user_has_pasta_pasta1` FOREIGN KEY (`pasta_id`) REFERENCES `pasta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_user_has_pasta_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
