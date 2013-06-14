-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Ven 14 Juin 2013 à 22:32
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `projet_java`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `CLIENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) NOT NULL,
  `STATUT` varchar(255) NOT NULL,
  `ADRESSE` varchar(255) NOT NULL,
  `CONTRAT` varchar(255) NOT NULL,
  `DATE_DEB_CONTRAT` date DEFAULT NULL,
  `DATE_FIN_CONTRAT` date DEFAULT NULL,
  `LOGIN` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `LONGITUDE` float DEFAULT NULL,
  `LATITUDE` float DEFAULT NULL,
  PRIMARY KEY (`CLIENT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`CLIENT_ID`, `NOM`, `STATUT`, `ADRESSE`, `CONTRAT`, `DATE_DEB_CONTRAT`, `DATE_FIN_CONTRAT`, `LOGIN`, `PASSWORD`, `LONGITUDE`, `LATITUDE`) VALUES
(1, 'Marcus', 'SARL', 'Albert Maltus', 'annuel', '2013-06-04', '2014-06-04', 'marcus', '1a1dc91c907325c69271ddf0c944bc72', 3.8682, 43.6095);

-- --------------------------------------------------------

--
-- Structure de la table `contrat`
--

CREATE TABLE IF NOT EXISTS `contrat` (
  `CONTRAT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE_DEBUT` date DEFAULT NULL,
  `DATE_FIN` date DEFAULT NULL,
  `TYPE` enum('annuel','mensuel','hebdomadaire','') DEFAULT NULL,
  `FK_CLIENT_ID` int(11) NOT NULL,
  PRIMARY KEY (`CONTRAT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `contrat`
--

INSERT INTO `contrat` (`CONTRAT_ID`, `DATE_DEBUT`, `DATE_FIN`, `TYPE`, `FK_CLIENT_ID`) VALUES
(1, '2013-06-14', '2014-06-14', 'annuel', 1);

-- --------------------------------------------------------

--
-- Structure de la table `intervention`
--

CREATE TABLE IF NOT EXISTS `intervention` (
  `INTERVENTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ETAT` varchar(255) NOT NULL,
  `NATURE` varchar(255) NOT NULL,
  `DATE` date DEFAULT NULL,
  `TYPE` varchar(255) NOT NULL,
  `FK_CLIENT_ID` int(11) DEFAULT NULL,
  `FK_TECHNICIEN_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`INTERVENTION_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=39 ;

--
-- Contenu de la table `intervention`
--

INSERT INTO `intervention` (`INTERVENTION_ID`, `ETAT`, `NATURE`, `DATE`, `TYPE`, `FK_CLIENT_ID`, `FK_TECHNICIEN_ID`) VALUES
(29, 'Planifiée', 'curative', '2013-06-04', 'Electromecanique', 1, 1),
(30, 'Planifiée', 'curative', '2013-06-22', 'Electromecanique', 1, 1),
(31, 'Planifiée', 'curative', '2013-06-08', 'Magnetique', 1, 3),
(32, 'Planifiée', 'curative', '2013-06-09', 'Magnetique', 1, 3),
(33, 'Planifiée', 'curative', '2013-06-03', 'Informatique', 1, 2),
(34, 'Planifiée', 'curative', '2013-06-01', 'Informatique', 1, 2),
(35, 'Planifiée', 'préventive', '2013-06-20', 'préventive', 1, 1),
(36, 'Planifiée', 'préventive', '2013-06-27', 'préventive', 1, 1),
(37, 'Planifiée', 'préventive', '2013-06-27', 'préventive', 1, 2),
(38, 'Planifiée', 'préventive', '2013-06-27', 'préventive', 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `liste_piece`
--

CREATE TABLE IF NOT EXISTS `liste_piece` (
  `LISTE_PIECE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `INTERVENTION_ID` int(11) NOT NULL,
  `PIECE_ID` int(11) NOT NULL,
  `NOMBRE` int(11) NOT NULL,
  PRIMARY KEY (`LISTE_PIECE_ID`),
  UNIQUE KEY `INTERVENTION_ID` (`INTERVENTION_ID`,`PIECE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `piece`
--

CREATE TABLE IF NOT EXISTS `piece` (
  `PIECE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) NOT NULL,
  `PRIX` float NOT NULL,
  `QTE_STOCK` int(11) DEFAULT '0',
  PRIMARY KEY (`PIECE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `piece`
--

INSERT INTO `piece` (`PIECE_ID`, `NOM`, `PRIX`, `QTE_STOCK`) VALUES
(1, 'Moteur', 1500, 5),
(2, 'Piston', 200, 10),
(3, 'Switch Ethernet', 20, 50),
(4, 'Cuve', 30, 15),
(5, 'Carte mère', 100, 8),
(6, 'Etiqueteuse', 80, 70);

-- --------------------------------------------------------

--
-- Structure de la table `technicien`
--

CREATE TABLE IF NOT EXISTS `technicien` (
  `TECHNICIEN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) NOT NULL,
  `PRENOM` varchar(255) NOT NULL,
  `ADRESSE` varchar(255) NOT NULL,
  `SPECIALITE` varchar(255) NOT NULL,
  `LOGIN` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  PRIMARY KEY (`TECHNICIEN_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `technicien`
--

INSERT INTO `technicien` (`TECHNICIEN_ID`, `NOM`, `PRENOM`, `ADRESSE`, `SPECIALITE`, `LOGIN`, `PASSWORD`) VALUES
(1, 'Henri', 'Jacobe', 'Rue de la rue d''henri', 'Electromecanique', 'henri', '205a67af6ae6f2de521d4f1dfa77acf4'),
(2, 'Jean', 'Jacques', 'L''adresse de Jean Paul', 'Informatique', 'jean38', '522e6bf0be8955a0de9531dad8f01ccb'),
(3, 'Paul', 'Joy', 'Avenue team joy', 'Magnetique', 'paul', '79dbc8ab691980586e8411c24d1d89ee');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
