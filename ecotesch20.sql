-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-07-2020 a las 07:19:56
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ecotesch20`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `nomprod` varchar(50) NOT NULL,
  `codbarras` bigint(50) NOT NULL,
  `descri` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nomprod`, `codbarras`, `descri`) VALUES
(1, 'Coca cola', 7501055302451, 'Refresco de cola de 1 litro sabor original');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `regbotella`
--

CREATE TABLE `regbotella` (
  `id` int(11) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `codbarras` bigint(150) NOT NULL,
  `fecha` varchar(15) NOT NULL,
  `cantidad` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `regbotella`
--

INSERT INTO `regbotella` (`id`, `usuario`, `codbarras`, `fecha`, `cantidad`) VALUES
(1, 'MeTJ12', 7501055302451, '2020-jul.-02', '1'),
(2, 'MeTJ12', 7501055302451, '2020-jul.-03', '1'),
(3, 'MeTJ12', 7501055302451, '2020-jul.-03', '1'),
(4, 'MeTJ12', 7501055302451, '2020-jul.-03', '1'),
(5, 'Abisai', 7501055302451, '2020-jul.-03', '1'),
(6, 'prueba@gmail.com', 7501055302451, '2020-jul.-19', '1'),
(7, 'prueba@gmail.com', 7501055302451, '2020-jul.-19', '1'),
(8, '', 7501055302451, '2020-jul.-19', '1'),
(9, '', 7501055302451, '2020-jul.-19', '1'),
(10, 'prueba@gmail.com', 7501055302451, '2020-jul.-19', '1'),
(11, '', 7501055302451, '2020-jul.-19', '1'),
(12, 'prueba@gmail.com', 7501055302451, '2020-jul.-19', '1'),
(13, 'prueba@gmail.com', 7501055302451, '2020-jul.-19', '1'),
(14, 'prueba@gmail.com', 7501055302451, '2020-jul.-19', '1'),
(15, 'prueba@gmail.com', 7501055302451, '2020-jul.-19', '1'),
(16, 'prueba@gmail.com', 7501055302451, '2020-jul.-19', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resultadoquest`
--

CREATE TABLE `resultadoquest` (
  `id` bigint(20) NOT NULL,
  `calificacion` varchar(30) NOT NULL,
  `usuario` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `resultadoquest`
--

INSERT INTO `resultadoquest` (`id`, `calificacion`, `usuario`) VALUES
(1, '4', 'MeTJ12'),
(2, '3', 'MeTJ12'),
(3, '2', 'MeTJ12'),
(4, '1', 'prueba@gmail.com'),
(5, '2', 'prueba@gmail.com'),
(6, '2', 'ecotesch@gmail.com'),
(7, '4', 'prueba@gmail.com'),
(8, '2', 'prueba@gmail.com'),
(9, '2', 'prueba@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `correo` varchar(60) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `fecha_reg` varchar(15) NOT NULL,
  `puntos` bigint(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `usuario`, `correo`, `pass`, `fecha_reg`, `puntos`) VALUES
(1, 'Mauricio Espinosa', 'MeTJ12', 'mauricioe138@gmail.com', '5d5b53417c8ff7d13aa16623d384763a', '2020-jul.-02', 3),
(2, 'Abisal Rojas', 'Abisai', 'ecotesch@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2020-jul.-03', 1),
(3, 'Prueba', 'Admin', 'prueba@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2020-07-19', 4),
(4, 'Mauro Espinosa', 'Mauro', 'mauricio_et@tesch.edu.mx', '81dc9bdb52d04dc20036dbd8313ed055', '2020-jul.-19', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `regbotella`
--
ALTER TABLE `regbotella`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `resultadoquest`
--
ALTER TABLE `resultadoquest`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `regbotella`
--
ALTER TABLE `regbotella`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `resultadoquest`
--
ALTER TABLE `resultadoquest`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
