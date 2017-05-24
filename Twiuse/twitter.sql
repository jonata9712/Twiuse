-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 24-Maio-2017 às 22:29
-- Versão do servidor: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `twitter`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `follows`
--

CREATE TABLE `follows` (
  `idPessoa` int(11) NOT NULL,
  `idSeguidor` int(11) NOT NULL,
  `idRelacionamento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `follows`
--

INSERT INTO `follows` (`idPessoa`, `idSeguidor`, `idRelacionamento`) VALUES
(1, 2, 1),
(2, 1, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa`
--

CREATE TABLE `pessoa` (
  `id` int(11) NOT NULL,
  `senha` char(60) NOT NULL,
  `usuario` char(60) NOT NULL,
  `nome` char(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pessoa`
--

INSERT INTO `pessoa` (`id`, `senha`, `usuario`, `nome`) VALUES
(1, '6456456456', 'matheus', 'matheus'),
(2, '654564', 'fulano', 'fulano'),
(3, 'fsadfasd', 'dsfdsdfasd', 'asdsafdasd'),
(4, '74567', 'rt675756', '567456'),
(5, 'cu', 'cu', 'cu'),
(6, 'yy', 'yyy', 'yyy'),
(7, '654564', 'ww', 'www'),
(8, '99', '9', '999999999999'),
(9, 'sss', 'ss', 'sss'),
(10, 'hhh', 'hh', 'hhh'),
(11, 'dd', 'dd', 'ddd'),
(12, 'tyertyer', 'erty', 'yert'),
(13, 'xxx', 'xxx', 'xxxxx'),
(14, 'bbbbb', 'bbbb', 'bbbbb'),
(15, 'hhhhhh', 'hghhhhh', 'hhhhhhhhhhhh'),
(16, 'ccccccccccc', 'ccccccccccc', 'ccccccccccc'),
(17, '777', '777', '777');

-- --------------------------------------------------------

--
-- Estrutura da tabela `twitter`
--

CREATE TABLE `twitter` (
  `idTwitter` int(11) NOT NULL,
  `mensagem` varchar(140) NOT NULL,
  `idPessoa` int(11) NOT NULL,
  `dataTwitter` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `twitter`
--

INSERT INTO `twitter` (`idTwitter`, `mensagem`, `idPessoa`, `dataTwitter`) VALUES
(1, 'olá, bem vindo ao meu twitter ^^', 1, '2017-05-06 21:12:09'),
(2, 'Obrigado ^^', 2, '2017-05-06 21:12:17'),
(3, 'Que tédio :O', 1, '2017-05-06 21:26:06');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `follows`
--
ALTER TABLE `follows`
  ADD PRIMARY KEY (`idRelacionamento`),
  ADD UNIQUE KEY `idRelacionamento` (`idRelacionamento`),
  ADD KEY `idPessoa` (`idPessoa`),
  ADD KEY `idSeguidor` (`idSeguidor`);

--
-- Indexes for table `pessoa`
--
ALTER TABLE `pessoa`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `usuario` (`usuario`);

--
-- Indexes for table `twitter`
--
ALTER TABLE `twitter`
  ADD PRIMARY KEY (`idTwitter`),
  ADD UNIQUE KEY `idTwitter` (`idTwitter`),
  ADD KEY `idPessoa` (`idPessoa`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `follows`
--
ALTER TABLE `follows`
  MODIFY `idRelacionamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `pessoa`
--
ALTER TABLE `pessoa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `twitter`
--
ALTER TABLE `twitter`
  MODIFY `idTwitter` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `follows`
--
ALTER TABLE `follows`
  ADD CONSTRAINT `follows_ibfk_1` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa` (`id`),
  ADD CONSTRAINT `follows_ibfk_2` FOREIGN KEY (`idSeguidor`) REFERENCES `pessoa` (`id`);

--
-- Limitadores para a tabela `twitter`
--
ALTER TABLE `twitter`
  ADD CONSTRAINT `twitter_ibfk_1` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
