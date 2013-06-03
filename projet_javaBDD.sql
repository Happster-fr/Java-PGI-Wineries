-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Lun 03 Juin 2013 à 15:17
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
  PRIMARY KEY (`CLIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `piece`
--

INSERT INTO `piece` (`PIECE_ID`, `NOM`, `PRIX`, `QTE_STOCK`) VALUES
(1, 'Moteu', 1500, 5),
(2, 'Piston', 200, 0),
(3, 'Switch Ethernet', 20, 0);

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
  PRIMARY KEY (`TECHNICIEN_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `technicien`
--

INSERT INTO `technicien` (`TECHNICIEN_ID`, `NOM`, `PRENOM`, `ADRESSE`, `SPECIALITE`) VALUES
(1, 'Jean', 'Jacques', 'L''adresse de Jean Paul', 'Informatique'),
(2, 'Henri', 'Jacobe', 'Rue de la rue d''henri', 'Electromécanique'),
(3, 'Paul', 'Joy', 'Avenue team joy', 'Magnétique');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
