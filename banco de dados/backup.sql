-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 12, 2016 at 04:50 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gerenciador_de_favoritos`
--
CREATE DATABASE IF NOT EXISTS `gerenciador_de_favoritos` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `gerenciador_de_favoritos`;

-- --------------------------------------------------------

--
-- Table structure for table `favorito`
--

CREATE TABLE `favorito` (
  `id` int(11) NOT NULL,
  `id_pasta` int(11) NOT NULL,
  `url` text NOT NULL,
  `titulo` varchar(300) NOT NULL,
  `descricao` longtext,
  `quant_acesso` int(11) DEFAULT '0',
  `data_acesso` datetime DEFAULT NULL,
  `data_criacao` datetime NOT NULL,
  `numEstrela` int(11) DEFAULT '0',
  `imagem` blob,
  `acesso_rapido` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `favorito`
--

INSERT INTO `favorito` (`id`, `id_pasta`, `url`, `titulo`, `descricao`, `quant_acesso`, `data_acesso`, `data_criacao`, `numEstrela`, `imagem`, `acesso_rapido`) VALUES
(7, 1, 'http://g1.globo.com/', 'G1', '', 3, '2016-11-12 13:55:20', '2016-06-20 01:26:31', 2, NULL, 0),
(8, 1, 'http://globoesporte.globo.com/', 'Globo Esporte', '', 0, NULL, '2016-06-20 01:27:11', 2, NULL, 0),
(9, 1, 'http://www.asp.net/entity-framework', 'entity', '', 0, NULL, '2016-06-20 01:35:34', 0, NULL, 0),
(10, 1, 'http://olhardigital.uol.com.br/pro/noticia/novo-supercomputador-chines-e-o-mais-rapido-do-mundo/59481', 'supercomputador', '', 3, '2016-11-12 14:30:38', '2016-06-20 01:35:53', 0, NULL, 0),
(11, 1, 'http://www.techtudo.com.br/', 'techtudo', '', 1, '2016-11-12 14:34:58', '2016-06-20 01:36:15', 0, NULL, 0),
(12, 1, 'http://www.infomoney.com.br/', 'money', '', 0, NULL, '2016-06-20 01:36:40', 0, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `favorito_tag`
--

CREATE TABLE `favorito_tag` (
  `id_favorito` int(11) NOT NULL,
  `tag_nome` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `pasta`
--

CREATE TABLE `pasta` (
  `id` int(11) NOT NULL,
  `id_pasta_pai` int(11) NOT NULL,
  `nome` text NOT NULL,
  `data_criacao` datetime DEFAULT NULL,
  `num_estrela` int(11) DEFAULT NULL,
  `publica` tinyint(1) DEFAULT NULL,
  `imagem` blob,
  `descricao` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pasta`
--

INSERT INTO `pasta` (`id`, `id_pasta_pai`, `nome`, `data_criacao`, `num_estrela`, `publica`, `imagem`, `descricao`) VALUES
(1, 1, 'Início', '2016-04-13 08:17:46', NULL, NULL, NULL, NULL),
(4, 4, 'Início', '2016-04-24 16:56:17', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pasta_tag`
--

CREATE TABLE `pasta_tag` (
  `id_pasta` int(11) NOT NULL,
  `tag_nome` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `authority` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `authority`) VALUES
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE `tag` (
  `nome` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`nome`) VALUES
('.Net'),
('3'),
('ASP.Net'),
('celular'),
('computador'),
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
-- Table structure for table `taxonomia`
--

CREATE TABLE `taxonomia` (
  `id` int(11) NOT NULL,
  `id_pasta_pai` int(11) NOT NULL,
  `nome` text NOT NULL,
  `data_criacao` datetime DEFAULT NULL,
  `num_estrela` int(11) DEFAULT NULL,
  `publica` tinyint(1) DEFAULT NULL,
  `imagem` blob,
  `descricao` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `taxonomia`
--

INSERT INTO `taxonomia` (`id`, `id_pasta_pai`, `nome`, `data_criacao`, `num_estrela`, `publica`, `imagem`, `descricao`) VALUES
(1, 1, 'home', NULL, NULL, NULL, NULL, NULL),
(2, 1, 'esportes', NULL, NULL, NULL, NULL, NULL),
(3, 1, 'tecnologia', NULL, NULL, NULL, NULL, NULL),
(4, 1, 'jogos', NULL, NULL, NULL, NULL, NULL),
(5, 1, 'notícia', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `taxonomia_tag`
--

CREATE TABLE `taxonomia_tag` (
  `tag_nome` varchar(200) NOT NULL,
  `id_taxonomia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `taxonomia_tag`
--

INSERT INTO `taxonomia_tag` (`tag_nome`, `id_taxonomia`) VALUES
('esporte', 2),
('futebol', 2),
('mma', 2),
('.Net', 3),
('celular', 3),
('computador', 3),
('digital', 3),
('informatica', 3),
('java', 3),
('games', 4),
('informação', 5),
('jornal', 5),
('jornalismo', 5),
('news', 5),
('portal', 5);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `email` varchar(45) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
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
-- Table structure for table `user_has_pasta`
--

CREATE TABLE `user_has_pasta` (
  `user_id` int(11) NOT NULL,
  `pasta_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_has_pasta`
--

INSERT INTO `user_has_pasta` (`user_id`, `pasta_id`) VALUES
(1, 1),
(2, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `favorito`
--
ALTER TABLE `favorito`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Favorito_Pasta1_idx` (`id_pasta`);

--
-- Indexes for table `favorito_tag`
--
ALTER TABLE `favorito_tag`
  ADD PRIMARY KEY (`id_favorito`,`tag_nome`),
  ADD KEY `fk_Favorito_has_Tag_Favorito_idx` (`id_favorito`),
  ADD KEY `fk_favorito_tag_tag1_idx` (`tag_nome`);

--
-- Indexes for table `pasta`
--
ALTER TABLE `pasta`
  ADD PRIMARY KEY (`id`,`id_pasta_pai`),
  ADD KEY `fk_Pasta_Pasta1_idx` (`id_pasta_pai`);

--
-- Indexes for table `pasta_tag`
--
ALTER TABLE `pasta_tag`
  ADD PRIMARY KEY (`id_pasta`,`tag_nome`),
  ADD KEY `fk_Pasta_has_Tag_Pasta1_idx` (`id_pasta`),
  ADD KEY `fk_pasta_tag_tag1_idx` (`tag_nome`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `authority_UNIQUE` (`authority`);

--
-- Indexes for table `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`nome`);

--
-- Indexes for table `taxonomia`
--
ALTER TABLE `taxonomia`
  ADD PRIMARY KEY (`id`,`id_pasta_pai`),
  ADD KEY `fk_Pasta_Pasta1_idx` (`id_pasta_pai`);

--
-- Indexes for table `taxonomia_tag`
--
ALTER TABLE `taxonomia_tag`
  ADD PRIMARY KEY (`tag_nome`,`id_taxonomia`),
  ADD KEY `fk_tag_has_taxonomia_taxonomia1_idx` (`id_taxonomia`),
  ADD KEY `fk_tag_has_taxonomia_tag1_idx` (`tag_nome`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`,`email`,`role_id`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD KEY `fk_user_role1_idx` (`role_id`);

--
-- Indexes for table `user_has_pasta`
--
ALTER TABLE `user_has_pasta`
  ADD PRIMARY KEY (`user_id`,`pasta_id`),
  ADD KEY `fk_user_has_pasta_pasta1_idx` (`pasta_id`),
  ADD KEY `fk_user_has_pasta_user1_idx` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `favorito`
--
ALTER TABLE `favorito`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `pasta`
--
ALTER TABLE `pasta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `taxonomia`
--
ALTER TABLE `taxonomia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `favorito`
--
ALTER TABLE `favorito`
  ADD CONSTRAINT `fk_Favorito_Pasta1` FOREIGN KEY (`id_pasta`) REFERENCES `pasta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `favorito_tag`
--
ALTER TABLE `favorito_tag`
  ADD CONSTRAINT `fk_Favorito_has_Tag_Favorito` FOREIGN KEY (`id_favorito`) REFERENCES `favorito` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_favorito_tag_tag1` FOREIGN KEY (`tag_nome`) REFERENCES `tag` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `pasta`
--
ALTER TABLE `pasta`
  ADD CONSTRAINT `fk_Pasta_Pasta1` FOREIGN KEY (`id_pasta_pai`) REFERENCES `pasta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `pasta_tag`
--
ALTER TABLE `pasta_tag`
  ADD CONSTRAINT `fk_Pasta_has_Tag_Pasta1` FOREIGN KEY (`id_pasta`) REFERENCES `pasta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pasta_tag_tag1` FOREIGN KEY (`tag_nome`) REFERENCES `tag` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `taxonomia`
--
ALTER TABLE `taxonomia`
  ADD CONSTRAINT `fk_Pasta_Pasta10` FOREIGN KEY (`id_pasta_pai`) REFERENCES `taxonomia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
