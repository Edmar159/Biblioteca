-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 18, 2016 at 06:16 
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `biblioteca`
--

-- --------------------------------------------------------

--
-- Table structure for table `associado`
--

CREATE TABLE `associado` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(64) NOT NULL,
  `tipo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `associado`
--

INSERT INTO `associado` (`codigo`, `nome`, `endereco`, `email`, `senha`, `tipo`) VALUES
(1, 'funcionario', 'Av. BPS, 1303, Bairro Pinheirinho, Itajubá - MG', 'funcionario@unifei.edu.br', '123456', 'funcionario'),
(2, 'João Maciente', 'Rua da Batata, 21, Pinheirinho', 'joao_maciente@unifei.edu.br', '123456', 'Grad'),
(3, 'Batata Assada', 'Rua da Batata, 666', 'batata@assada.com', '987654', 'Posgrad');

-- --------------------------------------------------------

--
-- Table structure for table `empresta`
--

CREATE TABLE `empresta` (
  `codigo` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `isbn` int(11) NOT NULL,
  `data_emprestimo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `empresta`
--

INSERT INTO `empresta` (`codigo`, `numero`, `isbn`, `data_emprestimo`) VALUES
(3, 1, 2, '03/09/2016'),
(2, 3, 2, '23/03/2016');

-- --------------------------------------------------------

--
-- Table structure for table `exemplar`
--

CREATE TABLE `exemplar` (
  `isbn` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `preco` double NOT NULL,
  `situacao` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exemplar`
--

INSERT INTO `exemplar` (`isbn`, `numero`, `preco`, `situacao`) VALUES
(1, 1, 134.2, 'disponivel'),
(1, 2, 134.2, 'disponivel'),
(1, 3, 134.2, 'disponivel'),
(2, 1, 230, 'emprestado'),
(2, 2, 230, 'disponivel'),
(2, 3, 230, 'emprestado');

-- --------------------------------------------------------

--
-- Table structure for table `historico`
--

CREATE TABLE `historico` (
  `codigo` int(11) NOT NULL,
  `isbn` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `data_emprestimo` varchar(10) NOT NULL,
  `data_devolucao` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `historico`
--

INSERT INTO `historico` (`codigo`, `isbn`, `numero`, `data_emprestimo`, `data_devolucao`) VALUES
(2, 1, 1, '05/10/2016', '18/10/2016'),
(1, 1, 2, '14/10/2016', '18/10/2016');

-- --------------------------------------------------------

--
-- Table structure for table `publicacao`
--

CREATE TABLE `publicacao` (
  `isbn` int(11) NOT NULL,
  `titulo` varchar(200) NOT NULL,
  `autor` varchar(200) NOT NULL,
  `editora` varchar(100) NOT NULL,
  `ano` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `publicacao`
--

INSERT INTO `publicacao` (`isbn`, `titulo`, `autor`, `editora`, `ano`) VALUES
(1, 'Calculo - Vol. 1', 'James Stewart', 'Trilha', 2013),
(2, 'Organizacao Estruturada de Computadores - 5 Ed.', 'Andrew Tanenbaum', 'Pearson Education', 2011),
(3, 'Batata', 'Batatinha', 'Batata', 1966);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `associado`
--
ALTER TABLE `associado`
  ADD PRIMARY KEY (`codigo`);

--
-- Indexes for table `empresta`
--
ALTER TABLE `empresta`
  ADD PRIMARY KEY (`isbn`,`numero`,`codigo`),
  ADD KEY `fk_associado` (`codigo`);

--
-- Indexes for table `exemplar`
--
ALTER TABLE `exemplar`
  ADD PRIMARY KEY (`isbn`,`numero`);

--
-- Indexes for table `historico`
--
ALTER TABLE `historico`
  ADD PRIMARY KEY (`isbn`,`numero`,`codigo`),
  ADD KEY `fk_assoc_hist` (`codigo`);

--
-- Indexes for table `publicacao`
--
ALTER TABLE `publicacao`
  ADD PRIMARY KEY (`isbn`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `empresta`
--
ALTER TABLE `empresta`
  ADD CONSTRAINT `fk_associado` FOREIGN KEY (`codigo`) REFERENCES `associado` (`codigo`),
  ADD CONSTRAINT `fk_exemplar` FOREIGN KEY (`isbn`,`numero`) REFERENCES `exemplar` (`isbn`, `numero`);

--
-- Constraints for table `exemplar`
--
ALTER TABLE `exemplar`
  ADD CONSTRAINT `fk_publicacao` FOREIGN KEY (`isbn`) REFERENCES `publicacao` (`isbn`);

--
-- Constraints for table `historico`
--
ALTER TABLE `historico`
  ADD CONSTRAINT `fk_assoc_hist` FOREIGN KEY (`codigo`) REFERENCES `associado` (`codigo`),
  ADD CONSTRAINT `fk_exemp_hist` FOREIGN KEY (`isbn`,`numero`) REFERENCES `exemplar` (`isbn`, `numero`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
