-- phpMyAdmin SQL Dump
-- version 4.6.4deb1
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Jeu 06 Avril 2017 à 09:16
-- Version du serveur :  5.7.17-0ubuntu0.16.10.1
-- Version de PHP :  7.0.15-0ubuntu0.16.10.4

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

-- --------------------------------------------------------

--
-- Structure de la table `cash`
--

CREATE TABLE `cash` (
  `quantity_cash` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
('ordinateur', 'ecran', 1);

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
('ordinateur', 1500, 0);

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
('clavier', 20, 0),
('ecran', 100, 0),
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
  MODIFY `id_buy` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_commande` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `production_order`
--
ALTER TABLE `production_order`
  MODIFY `id_production` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `use_raw`
--
ALTER TABLE `use_raw`
  MODIFY `id_using` int(11) NOT NULL AUTO_INCREMENT;
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
