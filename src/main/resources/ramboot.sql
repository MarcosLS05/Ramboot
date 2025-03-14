-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: mysql-ramboot
-- Tiempo de generación: 14-03-2025 a las 18:23:05
-- Versión del servidor: 9.2.0
-- Versión de PHP: 8.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ramboot`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bebidas`
--

CREATE TABLE `bebidas` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `precio_unidad` decimal(10,2) NOT NULL,
  `stock` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `bebidas`
--

INSERT INTO `bebidas` (`id`, `nombre`, `precio_unidad`, `stock`) VALUES
(1, 'Jugo de Naranja', 1.76, 46),
(2, 'Jugo de Naranja', 1.98, 18),
(3, 'Té Frío', 2.88, 18),
(4, 'Zumo de Tomate', 2.31, 26),
(5, 'Té Frío', 2.04, 3),
(6, 'Café', 2.53, 44),
(7, 'Café', 1.93, 1),
(8, 'Zumo de Tomate', 2.47, 36),
(9, 'Café', 2.24, 41),
(10, 'Café', 1.37, 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bonos`
--

CREATE TABLE `bonos` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `id_zona` bigint DEFAULT NULL,
  `id_bebida` bigint DEFAULT NULL,
  `id_snack` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `bonos`
--

INSERT INTO `bonos` (`id`, `nombre`, `precio`, `id_zona`, `id_bebida`, `id_snack`) VALUES
(1, 'DayPass', 20.00, 1, 2, 3),
(2, 'DayPass', 30.00, 2, 5, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gcontrata`
--

CREATE TABLE `gcontrata` (
  `id` bigint NOT NULL,
  `creado_en` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `metodo_pago` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ticket` varchar(255) NOT NULL,
  `id_usuario` bigint DEFAULT NULL,
  `id_zona` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `gcontrata`
--

INSERT INTO `gcontrata` (`id`, `creado_en`, `metodo_pago`, `ticket`, `id_usuario`, `id_zona`) VALUES
(1, '2025-03-14 18:22:53', 'Paypal', 'MOZ616', 25, 2),
(2, '2025-03-14 18:22:53', 'Tarjeta Bancaria', 'RSQ801', 14, 2),
(3, '2025-03-14 18:22:53', 'Tarjeta Bancaria', 'BHC438', 47, 3),
(4, '2025-03-14 18:22:53', 'Bizum', 'LFS171', 29, 1),
(5, '2025-03-14 18:22:53', 'Tarjeta Bancaria', 'NWR978', 51, 3),
(6, '2025-03-14 18:22:53', 'Paypal', 'BYT635', 42, 3),
(7, '2025-03-14 18:22:53', 'Paypal', 'BFU762', 51, 1),
(8, '2025-03-14 18:22:53', 'Bizum', 'GED671', 49, 2),
(9, '2025-03-14 18:22:53', 'Efectivo', 'QFI133', 32, 2),
(10, '2025-03-14 18:22:53', 'Bizum', 'CFW355', 2, 1),
(11, '2025-03-14 18:22:53', 'Efectivo', 'COW161', 11, 3),
(12, '2025-03-14 18:22:53', 'Bizum', 'IKE030', 35, 2),
(13, '2025-03-14 18:22:53', 'Bizum', 'BWX714', 10, 3),
(14, '2025-03-14 18:22:53', 'Tarjeta Bancaria', 'TXI949', 44, 1),
(15, '2025-03-14 18:22:53', 'Paypal', 'MBG116', 44, 3),
(16, '2025-03-14 18:22:53', 'Efectivo', 'ZBL242', 38, 3),
(17, '2025-03-14 18:22:53', 'Tarjeta Bancaria', 'VIK930', 10, 2),
(18, '2025-03-14 18:22:53', 'Efectivo', 'IAL260', 18, 2),
(19, '2025-03-14 18:22:53', 'Efectivo', 'ACF952', 15, 2),
(20, '2025-03-14 18:22:53', 'Paypal', 'ICA647', 50, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `snacks`
--

CREATE TABLE `snacks` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `precio_unidad` decimal(10,2) NOT NULL,
  `stock` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `snacks`
--

INSERT INTO `snacks` (`id`, `nombre`, `precio_unidad`, `stock`) VALUES
(1, 'Frutas', 2.57, 33),
(2, 'Chips', 2.95, 47),
(3, 'Gominolas', 1.65, 31),
(4, 'Chocolates', 2.42, 28),
(5, 'Gominolas', 1.17, 5),
(6, 'Caramelos', 1.75, 26),
(7, 'Gominolas', 1.49, 11),
(8, 'Gominolas', 1.95, 7),
(9, 'Galletas', 1.93, 18),
(10, 'Chocolates', 2.77, 26);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `id` int NOT NULL,
  `titulo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`id`, `titulo`) VALUES
(1, 'Administrador'),
(2, 'Empleado'),
(3, 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint NOT NULL,
  `username` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido1` varchar(255) NOT NULL,
  `apellido2` varchar(255) NOT NULL,
  `DNI` varchar(20) NOT NULL,
  `feedback` text NOT NULL,
  `email` varchar(255) NOT NULL,
  `CP` varchar(10) NOT NULL,
  `creado_en` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ultimo_login_en` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `password` varchar(255) DEFAULT NULL,
  `telefono` bigint DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `saldo` decimal(10,2) DEFAULT '0.00',
  `id_tipousuario` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `username`, `nombre`, `apellido1`, `apellido2`, `DNI`, `feedback`, `email`, `CP`, `creado_en`, `ultimo_login_en`, `password`, `telefono`, `is_active`, `saldo`, `id_tipousuario`) VALUES
(1, 'usuario_nuevo', 'Juan', 'Pérez', 'Gómez', '12345678A', 'Este es un ejemplo de feedback', 'usuario@example.com', '28001', '2025-03-14 18:11:26', '2025-03-14 18:11:36', '123', 123456789, 1, 100.00, 1),
(2, 'LoloGamer6798', 'Ana', 'Moreno', 'Sanchez', '68153596O', 'Encontré la tienda en un directorio en línea', 'emailAna6838@gmail.com', '42946', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 19.30, 2),
(3, 'RositaRocks8524', 'Ignacio', 'Gomez', 'Gimenez', '94526038N', 'Encontré la tienda a través de una búsqueda en Google', 'emailIgnacio8861@gmail.com', '37041', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 22.69, 1),
(4, 'MartaMiau2177', 'Laura', 'Gonzalez', 'Feliu', '76164954N', 'Encontré la tienda en un directorio en línea', 'emailLaura8532@gmail.com', '12347', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 16.90, 1),
(5, 'RocioRocksOn8492', 'Sara', 'Escriche', 'Gonzalez', '92075093N', 'Encontré la tienda a través de una búsqueda en Google', 'emailSara2715@gmail.com', '15523', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 45.58, 2),
(6, 'PacoPwnz5012', 'Pepe', 'Sanchez', 'Escriche', '34250154S', 'Vi un anuncio en YouTube', 'emailPepe9058@gmail.com', '44690', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 47.93, 2),
(7, 'AnitaSparkles6526', 'Maria', 'Gimenez', 'Gimenez', '23837793Q', 'Encontré la tienda a través de una búsqueda en Google', 'emailMaria4124@gmail.com', '12620', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 37.22, 3),
(8, 'AnitaSparkles9132', 'Rosa', 'Escriche', 'Martinez', '72362726A', 'Encontré la tienda en un directorio en línea', 'emailRosa2778@gmail.com', '35332', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 35.23, 1),
(9, 'Pepito236595', 'Pepe', 'Martinez', 'Hermoso', '93243866T', 'Un familiar me habló de la tienda', 'emailPepe6922@gmail.com', '21887', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 38.29, 1),
(10, 'NachoKing3858', 'Manolo', 'Gomez', 'Lopez', '25387837E', 'Encontré la tienda en un directorio en línea', 'emailManolo1449@gmail.com', '43642', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 11.98, 3),
(11, 'LuisLuis235610', 'Ignacio', 'Lopez', 'Sancho', '69961231N', 'Encontré la tienda en un directorio en línea', 'emailIgnacio5016@gmail.com', '46028', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 1.33, 1),
(12, 'LoloGamer7400', 'Rosa', 'Garcia', 'Moreno', '50282471T', 'Vi un anuncio en Facebook', 'emailRosa9634@gmail.com', '11194', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 49.23, 2),
(13, 'Pepito234577', 'Rafa', 'Gomez', 'Rodriguez', '59959750V', 'Me enteré de la tienda a través de Instagram', 'emailRafa6747@gmail.com', '31332', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 0.45, 1),
(14, 'MeryMery6239', 'Paco', 'Martinez', 'Moreno', '18141845C', 'Vi un anuncio en Facebook', 'emailPaco4568@gmail.com', '41452', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 15.16, 2),
(15, 'ManoloMaster9750', 'Rafa', 'Gomez', 'Sanchez', '96152437A', 'Encontré la tienda en un directorio en línea', 'emailRafa3582@gmail.com', '26126', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 38.25, 1),
(16, 'LoloGamer7592', 'Ignacio', 'Feliu', 'Gomez', '83681258V', 'Un amigo me recomendó la tienda', 'emailIgnacio4962@gmail.com', '37668', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 22.52, 3),
(17, 'LuciLuv8423', 'Carmen', 'Vidal', 'Fernandez', '40253959X', 'Encontré la tienda a través de una búsqueda en Google', 'emailCarmen5105@gmail.com', '38186', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 46.37, 3),
(18, 'SaraSass9570', 'Pepe', 'Vidal', 'Lopez', '25649407P', 'Encontré la tienda en un directorio en línea', 'emailPepe6249@gmail.com', '32652', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 5.80, 2),
(19, 'RositaRocks6417', 'Lorenzo', 'Sanchez', 'Escriche', '77806506F', 'Vi un anuncio en YouTube', 'emailLorenzo4794@gmail.com', '25070', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 27.95, 3),
(20, 'PacoPwnz8275', 'Manolo', 'Martinez', 'Gomez', '77477636Z', 'Vi un anuncio en Facebook', 'emailManolo2633@gmail.com', '39931', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 13.28, 3),
(21, 'Carmenita123967', 'Carmen', 'Rodriguez', 'Vidal', '25753036C', 'Encontré la tienda a través de una búsqueda en Google', 'emailCarmen2831@gmail.com', '32143', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 23.04, 3),
(22, 'MartaMiau6062', 'Carmen', 'Gonzalez', 'Rodriguez', '90553647I', 'Vi un anuncio en Facebook', 'emailCarmen1134@gmail.com', '50304', '2025-03-14 18:17:02', '2025-03-14 18:17:02', NULL, NULL, 0, 16.29, 3),
(23, 'PacoPwnz6185', 'Luis', 'Moreno', 'Gonzalez', '75030777O', 'Encontré la tienda en un directorio en línea', 'emailLuis8390@gmail.com', '51615', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 30.46, 3),
(24, 'LoloGamer5334', 'Ana', 'Feliu', 'Gonzalez', '91841391A', 'Encontré la tienda a través de una búsqueda en Google', 'emailAna6636@gmail.com', '33101', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 20.05, 2),
(25, 'NachoKing7484', 'Rosa', 'Gonzalez', 'Pérez', '82100484M', 'Encontré la tienda en un directorio en línea', 'emailRosa3834@gmail.com', '51615', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 37.71, 2),
(26, 'MartaMiau9962', 'Carmen', 'Rodriguez', 'Feliu', '62338145I', 'Un familiar me habló de la tienda', 'emailCarmen1047@gmail.com', '18586', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 35.23, 3),
(27, 'RafaRafa996093', 'Ignacio', 'Pérez', 'Sanchez', '49560492J', 'Un familiar me habló de la tienda', 'emailIgnacio8711@gmail.com', '36383', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 9.05, 3),
(28, 'LuisLuis236642', 'Pepe', 'Sancho', 'Hermoso', '40373021U', 'Un familiar me habló de la tienda', 'emailPepe5834@gmail.com', '12141', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 17.46, 3),
(29, 'SaraSass5003', 'Marta', 'Garcia', 'Moreno', '68904789M', 'Un familiar me habló de la tienda', 'emailMarta7765@gmail.com', '37508', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 8.80, 1),
(30, 'Pepito231955', 'Paco', 'Gonzalez', 'Gimenez', '45883739M', 'Un familiar me habló de la tienda', 'emailPaco3204@gmail.com', '36164', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 46.40, 2),
(31, 'Carmenita121753', 'Rafa', 'Escriche', 'Pérez', '42877749U', 'Encontré la tienda en un foro o comunidad en línea', 'emailRafa3765@gmail.com', '41225', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 33.47, 2),
(32, 'LuciLuv1943', 'Maria', 'Lopez', 'Rodriguez', '80363261N', 'Encontré la tienda en un directorio en línea', 'emailMaria8069@gmail.com', '35004', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 14.70, 3),
(33, 'RafaRafa997463', 'Laura', 'Sanchez', 'Pérez', '22105140E', 'Un amigo me recomendó la tienda', 'emailLaura8093@gmail.com', '46461', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 41.79, 3),
(34, 'RositaRocks5398', 'Laura', 'Pérez', 'Sancho', '72745532E', 'Encontré la tienda en un directorio en línea', 'emailLaura1513@gmail.com', '45892', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 38.24, 2),
(35, 'RositaRocks8615', 'Lorenzo', 'Sancho', 'Lopez', '73047951I', 'Encontré la tienda en un foro o comunidad en línea', 'emailLorenzo9773@gmail.com', '10499', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 24.39, 2),
(36, 'MeryMery6383', 'Lorenzo', 'Lopez', 'Hermoso', '51439270Z', 'Me enteré de la tienda a través de Instagram', 'emailLorenzo1645@gmail.com', '27305', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 39.97, 3),
(37, 'RocioRocksOn1522', 'Maria', 'Gonzalez', 'Gonzalez', '44118351L', 'Un familiar me habló de la tienda', 'emailMaria6217@gmail.com', '23591', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 44.95, 1),
(38, 'MartaMiau1400', 'Ignacio', 'Escriche', 'Gonzalez', '60842744Z', 'Encontré la tienda a través de una búsqueda en Google', 'emailIgnacio1462@gmail.com', '46932', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 21.10, 2),
(39, 'RositaRocks5371', 'Rafa', 'Rodriguez', 'Pérez', '50852058L', 'Encontré la tienda en un foro o comunidad en línea', 'emailRafa7352@gmail.com', '48697', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 7.81, 3),
(40, 'RocioRocksOn6785', 'Laura', 'Moreno', 'Rodriguez', '27051909E', 'Vi un anuncio en Facebook', 'emailLaura8070@gmail.com', '45431', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 13.23, 1),
(41, 'Pepito232135', 'Rafa', 'Feliu', 'Escriche', '59895787L', 'Vi un anuncio en Facebook', 'emailRafa3146@gmail.com', '33986', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 14.05, 1),
(42, 'LoloGamer4467', 'Carmen', 'Sanchez', 'Sancho', '31666934W', 'Vi un anuncio en YouTube', 'emailCarmen7524@gmail.com', '26662', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 37.49, 3),
(43, 'RafaRafa991836', 'Pepe', 'Sancho', 'Rodriguez', '66288418N', 'Encontré la tienda en un directorio en línea', 'emailPepe6687@gmail.com', '17492', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 32.02, 2),
(44, 'Carmenita129190', 'Pepe', 'Gonzalez', 'Fernandez', '21277945T', 'Vi un anuncio en YouTube', 'emailPepe3378@gmail.com', '17915', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 18.62, 3),
(45, 'SaraSass3854', 'Luis', 'Pérez', 'Garcia', '23002321S', 'Encontré la tienda en un directorio en línea', 'emailLuis5745@gmail.com', '16602', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 31.33, 3),
(46, 'Pepito236576', 'Lorenzo', 'Gomez', 'Gomez', '34951291P', 'Encontré la tienda en un foro o comunidad en línea', 'emailLorenzo2561@gmail.com', '22786', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 11.98, 3),
(47, 'MeryMery8393', 'Paco', 'Rodriguez', 'Feliu', '74732131Q', 'Encontré la tienda en un directorio en línea', 'emailPaco3071@gmail.com', '27410', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 10.41, 1),
(48, 'Pepito239964', 'Pepe', 'Hermoso', 'Feliu', '53624406G', 'Encontré la tienda en un directorio en línea', 'emailPepe2453@gmail.com', '24312', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 24.82, 1),
(49, 'SaraSass8129', 'Laura', 'Lopez', 'Sanchez', '36768695I', 'Encontré la tienda en un directorio en línea', 'emailLaura5208@gmail.com', '39464', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 41.77, 1),
(50, 'Carmenita128630', 'Manolo', 'Gonzalez', 'Sancho', '21736112W', 'Vi un anuncio en Facebook', 'emailManolo5742@gmail.com', '17938', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 13.44, 3),
(51, 'LuisLuis235000', 'Rocio', 'Escriche', 'Gonzalez', '68255633F', 'Encontré la tienda a través de una búsqueda en Google', 'emailRocio1907@gmail.com', '20986', '2025-03-14 18:17:03', '2025-03-14 18:17:03', NULL, NULL, 0, 40.67, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `zona`
--

CREATE TABLE `zona` (
  `id` bigint NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `precio_hora` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `zona`
--

INSERT INTO `zona` (`id`, `titulo`, `precio_hora`) VALUES
(1, 'General', 1.00),
(2, 'Bootcamp', 3.50),
(3, 'Streamer Box', 5.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bebidas`
--
ALTER TABLE `bebidas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `bonos`
--
ALTER TABLE `bonos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `gcontrata`
--
ALTER TABLE `gcontrata`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `snacks`
--
ALTER TABLE `snacks`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `DNI` (`DNI`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `zona`
--
ALTER TABLE `zona`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `bebidas`
--
ALTER TABLE `bebidas`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `bonos`
--
ALTER TABLE `bonos`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `gcontrata`
--
ALTER TABLE `gcontrata`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `snacks`
--
ALTER TABLE `snacks`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de la tabla `zona`
--
ALTER TABLE `zona`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
