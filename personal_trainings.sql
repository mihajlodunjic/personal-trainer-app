-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 11, 2025 at 07:39 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `personal_trainings`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity`
--

CREATE TABLE `activity` (
  `idActivity` int(11) NOT NULL,
  `category` enum('ledja','grudi','ramena','biceps','triceps','stomak','kvadriceps','zadnjaloza','gluteus','list') NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `idClient` int(11) NOT NULL,
  `c_name` varchar(20) NOT NULL,
  `c_lastName` varchar(20) NOT NULL,
  `gender` enum('musko','zensko') NOT NULL,
  `birthday` date NOT NULL,
  `c_mobilePhone` varchar(30) NOT NULL,
  `idGym` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`idClient`, `c_name`, `c_lastName`, `gender`, `birthday`, `c_mobilePhone`, `idGym`) VALUES
(4, 'Marko', 'Petrovic', 'musko', '1990-05-15', '061-234-5678', 1);

-- --------------------------------------------------------

--
-- Table structure for table `gym`
--

CREATE TABLE `gym` (
  `idGym` int(11) NOT NULL,
  `g_name` varchar(40) NOT NULL,
  `address` varchar(60) NOT NULL,
  `equipmentLevel` enum('nizak','srednji','visok') NOT NULL,
  `g_mobilePhone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gym`
--

INSERT INTO `gym` (`idGym`, `g_name`, `address`, `equipmentLevel`, `g_mobilePhone`) VALUES
(1, 'Fit Gym', '123 Fitness Street', 'visok', '123-456-7890'),
(7, 'Powerhouse', '456 Muscle Ave', 'srednji', '234-567-8901'),
(8, 'Urban Strength', '789 Power Blvd', 'visok', '345-678-9012'),
(9, 'Quick Fit', '321 Cardio Lane', 'nizak', '456-789-0123'),
(10, 'Pro Fit', '654 Gym Road', 'srednji', '567-890-1234');

-- --------------------------------------------------------

--
-- Table structure for table `sertificate`
--

CREATE TABLE `sertificate` (
  `idSertificate` int(11) NOT NULL,
  `s_name` varchar(50) NOT NULL,
  `publisher` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `trainer`
--

CREATE TABLE `trainer` (
  `idTrainer` int(11) NOT NULL,
  `userName` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `t_name` varchar(30) NOT NULL,
  `t_lastName` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `trainer`
--

INSERT INTO `trainer` (`idTrainer`, `userName`, `password`, `t_name`, `t_lastName`) VALUES
(12, 'miki', 'miki', 'mihajlollll', 'dunjic');

-- --------------------------------------------------------

--
-- Table structure for table `trainersertificate`
--

CREATE TABLE `trainersertificate` (
  `idTrainer` int(11) NOT NULL,
  `idSertificate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `workoutitem`
--

CREATE TABLE `workoutitem` (
  `idWorkoutRecord` int(11) NOT NULL,
  `itemSN` int(11) NOT NULL,
  `intensity` enum('nizak','srednji','visok') NOT NULL,
  `numOfSeries` int(11) NOT NULL,
  `mass` double NOT NULL,
  `comment` varchar(150) DEFAULT NULL,
  `idActivity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `workoutrecord`
--

CREATE TABLE `workoutrecord` (
  `idWorkoutRecord` int(11) NOT NULL,
  `workoutDate` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `duration` time NOT NULL,
  `avgIntensity` enum('nizak','srednji','visok') NOT NULL,
  `idTrainer` int(11) NOT NULL,
  `idClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`idActivity`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`),
  ADD KEY `idTeretanaFK` (`idGym`);

--
-- Indexes for table `gym`
--
ALTER TABLE `gym`
  ADD PRIMARY KEY (`idGym`);

--
-- Indexes for table `sertificate`
--
ALTER TABLE `sertificate`
  ADD PRIMARY KEY (`idSertificate`);

--
-- Indexes for table `trainer`
--
ALTER TABLE `trainer`
  ADD PRIMARY KEY (`idTrainer`);

--
-- Indexes for table `trainersertificate`
--
ALTER TABLE `trainersertificate`
  ADD PRIMARY KEY (`idTrainer`,`idSertificate`),
  ADD KEY `idSertifikatFK` (`idSertificate`);

--
-- Indexes for table `workoutitem`
--
ALTER TABLE `workoutitem`
  ADD PRIMARY KEY (`idWorkoutRecord`,`itemSN`),
  ADD KEY `idAktivnostFK` (`idActivity`);

--
-- Indexes for table `workoutrecord`
--
ALTER TABLE `workoutrecord`
  ADD PRIMARY KEY (`idWorkoutRecord`),
  ADD KEY `idTrenerFK` (`idTrainer`),
  ADD KEY `idKlijentFK` (`idClient`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activity`
--
ALTER TABLE `activity`
  MODIFY `idActivity` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `gym`
--
ALTER TABLE `gym`
  MODIFY `idGym` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `sertificate`
--
ALTER TABLE `sertificate`
  MODIFY `idSertificate` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `trainer`
--
ALTER TABLE `trainer`
  MODIFY `idTrainer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `workoutrecord`
--
ALTER TABLE `workoutrecord`
  MODIFY `idWorkoutRecord` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `idTeretanaFK` FOREIGN KEY (`idGym`) REFERENCES `gym` (`idGym`) ON UPDATE CASCADE;

--
-- Constraints for table `trainersertificate`
--
ALTER TABLE `trainersertificate`
  ADD CONSTRAINT `idSertifikatFK` FOREIGN KEY (`idSertificate`) REFERENCES `sertificate` (`idSertificate`),
  ADD CONSTRAINT `idTrenerFK1` FOREIGN KEY (`idTrainer`) REFERENCES `trainer` (`idTrainer`) ON UPDATE CASCADE;

--
-- Constraints for table `workoutitem`
--
ALTER TABLE `workoutitem`
  ADD CONSTRAINT `idAktivnostFK` FOREIGN KEY (`idActivity`) REFERENCES `activity` (`idActivity`) ON UPDATE CASCADE,
  ADD CONSTRAINT `idEvidencijaFK` FOREIGN KEY (`idWorkoutRecord`) REFERENCES `workoutrecord` (`idWorkoutRecord`) ON UPDATE CASCADE;

--
-- Constraints for table `workoutrecord`
--
ALTER TABLE `workoutrecord`
  ADD CONSTRAINT `idKlijentFK` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`) ON UPDATE CASCADE,
  ADD CONSTRAINT `idTrenerFK` FOREIGN KEY (`idTrainer`) REFERENCES `trainer` (`idTrainer`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
