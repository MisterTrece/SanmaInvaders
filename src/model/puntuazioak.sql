-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-05-2026 a las 13:02:03
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `puntuazioak`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jokalaria`
--

CREATE TABLE `jokalaria` (
  `IZENA` varchar(3) NOT NULL,
  `ONTZIMOTA` enum('RED','BLUE','GREEN') NOT NULL DEFAULT 'RED',
  `PUNTUAZIOA` int(11) NOT NULL DEFAULT 0,
  `ID` int(11) NOT NULL
) ;

--
-- Volcado de datos para la tabla `jokalaria`
--

INSERT INTO `jokalaria` (`IZENA`, `ONTZIMOTA`, `PUNTUAZIOA`, `ID`) VALUES
('MAR', 'RED', 300, 1),
('MAR', 'RED', 300, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `jokalaria`
--
ALTER TABLE `jokalaria`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `jokalaria`
--
ALTER TABLE `jokalaria`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
