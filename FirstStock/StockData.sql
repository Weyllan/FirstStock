-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Mer 10 Mai 2017 à 20:16
-- Version du serveur :  10.1.22-MariaDB-
-- Version de PHP :  7.0.16-3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `StockData`
--

-- --------------------------------------------------------

--
-- Structure de la table `buy`
--

CREATE TABLE `buy` (
  `id_buy` int(11) NOT NULL,
  `date_buy` date NOT NULL,
  `quantity_buy` int(11) NOT NULL,
  `raw_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `buy`
--

INSERT INTO `buy` (`id_buy`, `date_buy`, `quantity_buy`, `raw_name`) VALUES
(96, '2017-04-01', 150, 'clavier'),
(97, '2017-04-01', 150, 'clavier'),
(98, '2017-04-01', 600, 'clavier');

-- --------------------------------------------------------

--
-- Structure de la table `cash`
--

CREATE TABLE `cash` (
  `quantity_cash` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `cash`
--

INSERT INTO `cash` (`quantity_cash`) VALUES
(98100);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id_commande` int(11) NOT NULL,
  `date` date NOT NULL,
  `quantity_sold` int(30) NOT NULL,
  `product_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `commande`
--

INSERT INTO `commande` (`id_commande`, `date`, `quantity_sold`, `product_name`) VALUES
(3, '2017-04-28', 150, 'ordinateur'),
(4, '2017-04-29', 100, 'ordinateur'),
(6, '2017-04-30', 125, 'ordinateur'),
(7, '2017-05-01', 200, 'ordinateur'),
(12, '2017-05-02', 150, 'ordinateur'),
(13, '2017-05-03', 100, 'ordinateur'),
(15, '2017-05-04', 50, 'ordinateur'),
(16, '2017-05-05', 100, 'ordinateur'),
(17, '2017-05-06', 150, 'ordinateur'),
(18, '2017-05-07', 200, 'ordinateur'),
(19, '2017-05-08', 250, 'ordinateur'),
(20, '2017-05-09', 230, 'ordinateur'),
(21, '2017-05-10', 30, 'Webcam'),
(22, '2017-05-10', 20, 'chaise');

-- --------------------------------------------------------

--
-- Structure de la table `production_order`
--

CREATE TABLE `production_order` (
  `id_production` int(10) NOT NULL,
  `production_date` date NOT NULL,
  `quantity` int(11) NOT NULL,
  `product_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `production_order`
--

INSERT INTO `production_order` (`id_production`, `production_date`, `quantity`, `product_name`) VALUES
(1, '2017-05-10', 30, 'Webcam');

-- --------------------------------------------------------

--
-- Structure de la table `product_raw`
--

CREATE TABLE `product_raw` (
  `product_id` varchar(20) NOT NULL,
  `raw_id` varchar(20) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `product_raw`
--

INSERT INTO `product_raw` (`product_id`, `raw_id`, `quantity`) VALUES
('ordinateur', 'processeur', 1),
('ordinateur', 'clavier', 1),
('ordinateur', 'ecran', 1),
('Webcam', 'Lentille', 1),
('chaise', 'bois', 1);

-- --------------------------------------------------------

--
-- Structure de la table `product_type`
--

CREATE TABLE `product_type` (
  `product_name` varchar(20) NOT NULL,
  `price` double NOT NULL,
  `product_stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `product_type`
--

INSERT INTO `product_type` (`product_name`, `price`, `product_stock`) VALUES
('chaise', 10, 10),
('ordinateur', 200, 50),
('Webcam', 30, 10);

-- --------------------------------------------------------

--
-- Structure de la table `raw_type`
--

CREATE TABLE `raw_type` (
  `raw_name` varchar(20) NOT NULL,
  `price_buy` double NOT NULL,
  `raw_stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `raw_type`
--

INSERT INTO `raw_type` (`raw_name`, `price_buy`, `raw_stock`) VALUES
('bois', 5, 5),
('clavier', 20, 1650),
('ecran', 100, 0),
('Lentille', 10, 20),
('processeur', 200, 0);

-- --------------------------------------------------------

--
-- Structure de la table `use_raw`
--

CREATE TABLE `use_raw` (
  `id_using` int(11) NOT NULL,
  `product_name` varchar(20) NOT NULL,
  `raw_name` varchar(20) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `use_raw`
--

INSERT INTO `use_raw` (`id_using`, `product_name`, `raw_name`, `date`) VALUES
(1, 'Webcam', 'Lentille', '2017-05-10');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `buy`
--
ALTER TABLE `buy`
  ADD PRIMARY KEY (`id_buy`),
  ADD KEY `raw_name` (`raw_name`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_commande`),
  ADD KEY `product_name` (`product_name`);

--
-- Index pour la table `production_order`
--
ALTER TABLE `production_order`
  ADD PRIMARY KEY (`id_production`),
  ADD KEY `product_name` (`product_name`);

--
-- Index pour la table `product_raw`
--
ALTER TABLE `product_raw`
  ADD KEY `product_id` (`product_id`),
  ADD KEY `raw_id` (`raw_id`);

--
-- Index pour la table `product_type`
--
ALTER TABLE `product_type`
  ADD PRIMARY KEY (`product_name`);

--
-- Index pour la table `raw_type`
--
ALTER TABLE `raw_type`
  ADD PRIMARY KEY (`raw_name`);

--
-- Index pour la table `use_raw`
--
ALTER TABLE `use_raw`
  ADD PRIMARY KEY (`id_using`),
  ADD KEY `raw_name` (`raw_name`,`product_name`),
  ADD KEY `product_name` (`product_name`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `buy`
--
ALTER TABLE `buy`
  MODIFY `id_buy` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;
--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT pour la table `production_order`
--
ALTER TABLE `production_order`
  MODIFY `id_production` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `use_raw`
--
ALTER TABLE `use_raw`
  MODIFY `id_using` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `buy`
--
ALTER TABLE `buy`
  ADD CONSTRAINT `buy_ibfk_1` FOREIGN KEY (`raw_name`) REFERENCES `raw_type` (`raw_name`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`product_name`) REFERENCES `product_type` (`product_name`);

--
-- Contraintes pour la table `production_order`
--
ALTER TABLE `production_order`
  ADD CONSTRAINT `production_order_ibfk_1` FOREIGN KEY (`product_name`) REFERENCES `product_type` (`product_name`);

--
-- Contraintes pour la table `product_raw`
--
ALTER TABLE `product_raw`
  ADD CONSTRAINT `product_raw_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product_type` (`product_name`),
  ADD CONSTRAINT `product_raw_ibfk_2` FOREIGN KEY (`raw_id`) REFERENCES `raw_type` (`raw_name`);

--
-- Contraintes pour la table `use_raw`
--
ALTER TABLE `use_raw`
  ADD CONSTRAINT `use_raw_ibfk_1` FOREIGN KEY (`product_name`) REFERENCES `production_order` (`product_name`),
  ADD CONSTRAINT `use_raw_ibfk_2` FOREIGN KEY (`raw_name`) REFERENCES `raw_type` (`raw_name`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
