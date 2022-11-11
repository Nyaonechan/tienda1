-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-11-2022 a las 14:59:16
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tiendaweb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos_carrito`
--

CREATE TABLE `articulos_carrito` (
  `id_producto` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `articulos_carrito`
--

INSERT INTO `articulos_carrito` (`id_producto`, `id_usuario`, `cantidad`) VALUES
(8, 21, 1),
(2, 19, 2),
(3, 19, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `imagen` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `descripcion`, `imagen`) VALUES
(1, 'FunkoPop', 'FunkoPop', 'cat1.jpg'),
(2, 'Books', 'Books', 'cat2.jpg'),
(3, 'Jewelry', 'Jewelry', 'cat3.jpg'),
(4, 'Clothes', 'Clothes', 'cat4.jpg'),
(5, 'Figures', 'Figures', 'cat5.jpg'),
(6, 'Board Games', 'Board Games', 'cat6.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL,
  `clave` varchar(20) NOT NULL,
  `valor` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`id`, `clave`, `valor`) VALUES
(1, 'num_factura', '7'),
(2, 'prefijo', 'ZA-'),
(3, 'sufijo', '-2022'),
(5, 'nombre', 'BeFreak S.L.'),
(6, 'direccion', 'Cl/Inventada n.40 Bj'),
(7, 'provincia', 'Zamora'),
(8, 'ciudad', 'Zamora');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuentos`
--

CREATE TABLE `descuentos` (
  `id` int(11) NOT NULL,
  `codigo` varchar(20) NOT NULL,
  `descuento` float NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `descuentos`
--

INSERT INTO `descuentos` (`id`, `codigo`, `descuento`, `fecha_inicio`, `fecha_fin`) VALUES
(1, 'cinco', 0.05, '2022-11-06', '2022-11-22'),
(2, 'diez', 0.1, '2022-11-06', '2022-11-13');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles_pedido`
--

CREATE TABLE `detalles_pedido` (
  `id` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `unidades` int(11) NOT NULL,
  `precio_unidad` float NOT NULL,
  `impuesto` float NOT NULL,
  `total` double NOT NULL,
  `estado` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalles_pedido`
--

INSERT INTO `detalles_pedido` (`id`, `id_pedido`, `producto_id`, `unidades`, `precio_unidad`, `impuesto`, `total`, `estado`) VALUES
(1, 1, 2, 2, 25, 0.21, 50, 'E'),
(2, 1, 15, 1, 35, 0.21, 35, 'E'),
(3, 2, 4, 1, 27, 0.21, 27, 'E'),
(4, 2, 18, 1, 30, 0.21, 30, 'E'),
(5, 3, 5, 1, 23, 0.21, 23, 'C'),
(6, 3, 6, 1, 30, 0.21, 30, 'C'),
(7, 3, 16, 1, 30, 0.21, 30, 'C'),
(8, 5, 27, 1, 100, 0.21, 100, 'E'),
(9, 6, 13, 1, 20, 0.21, 20, 'E'),
(10, 7, 43, 1, 50, 0.21, 50, 'E'),
(11, 7, 41, 1, 60, 0.21, 60, 'E'),
(12, 9, 2, 2, 25, 0.21, 50, 'P.E.'),
(13, 9, 3, 1, 30, 0.21, 30, 'P.E.'),
(14, 10, 43, 1, 50, 0.21, 50, 'P.E.'),
(15, 10, 42, 1, 45, 0.21, 45, 'P.E.'),
(20, 13, 27, 1, 100, 0.21, 100, 'P.E.'),
(22, 15, 53, 1, 20, 0.21, 20, 'P.E.'),
(23, 16, 53, 1, 20, 0.21, 20, 'P.E.'),
(24, 17, 15, 1, 35, 0.21, 35, 'P.E.'),
(25, 17, 12, 1, 25, 0.21, 25, 'P.E.'),
(26, 17, 2, 1, 25, 0.21, 25, 'P.E.'),
(27, 18, 7, 1, 21, 0.21, 21, 'P.E.'),
(28, 18, 45, 1, 20, 0.21, 20, 'P.E.'),
(29, 19, 18, 1, 30, 0.21, 30, 'P.E.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos_pago`
--

CREATE TABLE `metodos_pago` (
  `id` int(11) NOT NULL,
  `metodo_pago` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `metodos_pago`
--

INSERT INTO `metodos_pago` (`id`, `metodo_pago`) VALUES
(1, 'Paypal'),
(2, 'Card');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opciones_menu`
--

CREATE TABLE `opciones_menu` (
  `id` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `nombre_opcion` varchar(45) NOT NULL,
  `url_opcion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `opciones_menu`
--

INSERT INTO `opciones_menu` (`id`, `id_rol`, `nombre_opcion`, `url_opcion`) VALUES
(1, 2, 'Gestionar Productos', '/adminProductos'),
(2, 2, 'Gestionar Pedidos', '/adminPedidos'),
(3, 2, 'Gestionar Clientes', '/adminUsuarios?idRol=1'),
(4, 3, 'Gestionar Empleados', '/adminUsuarios?idRol=2'),
(5, 2, 'Gestionar Proveedores', '/adminProveedores'),
(6, 2, 'Crear Categorías', '/formCategoria'),
(7, 3, 'Modificar datos Configuación', '/adminConfiguracion'),
(8, 3, 'Visualizar Estadísticas', '/adminEstadisticas'),
(9, 3, 'Gestionar Descuentos', '/adminDescuentos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `metodo_pago` varchar(45) NOT NULL,
  `estado` varchar(45) NOT NULL,
  `num_factura` varchar(45) DEFAULT NULL,
  `descuento_id` int(11) DEFAULT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id`, `id_usuario`, `fecha`, `metodo_pago`, `estado`, `num_factura`, `descuento_id`, `total`) VALUES
(1, 1, '2022-10-25', 'Card', 'E', 'ZA-1-2022', NULL, 85),
(2, 3, '2022-10-26', 'Card', 'E', 'ZA-2-2022', NULL, 57),
(3, 1, '2022-10-27', 'Card', 'C', NULL, NULL, 83),
(4, 19, '2022-10-31', 'Card', 'E', 'ZA-4-2022', NULL, 100),
(5, 19, '2022-10-31', 'Card', 'E', 'ZA-3-2022', NULL, 100),
(6, 19, '2022-10-31', 'Card', 'E', 'ZA-5-2022', NULL, 20),
(7, 14, '2022-11-02', 'Paypal', 'E', 'ZA-6-2022', NULL, 110),
(8, 1, '2022-11-05', 'Paypal', 'P.E.', NULL, NULL, 80),
(9, 1, '2022-11-05', 'Paypal', 'P.E.', NULL, NULL, 80),
(10, 14, '2022-11-06', 'Paypal', 'P.E.', NULL, NULL, 95),
(13, 14, '2022-11-06', 'Paypal', 'P.E.', NULL, NULL, 100),
(15, 14, '2022-11-06', 'Paypal', 'P.E.', NULL, NULL, 20),
(17, 21, '2022-11-08', 'Paypal', 'P.E.', NULL, NULL, 85),
(18, 21, '2022-11-08', 'Paypal', 'P.E.', NULL, 2, 36.900001525878906),
(19, 21, '2022-11-08', 'Paypal', 'P.E.', NULL, 1, 28.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `categoria_id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `precio` double NOT NULL,
  `stock` int(11) NOT NULL,
  `impuesto` float DEFAULT NULL,
  `imagen` varchar(100) NOT NULL,
  `baja` tinyint(4) DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `valoracion_media` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `categoria_id`, `nombre`, `descripcion`, `precio`, `stock`, `impuesto`, `imagen`, `baja`, `fecha_alta`, `valoracion_media`) VALUES
(1, 1, 'FunkoPop! Harry Potter', 'FunkoPop Saga Harry Potter - 25cm', 30, 10, 0.21, 'harry-potter-funko-pop.jpg', 1, '2022-10-01', 0),
(2, 1, 'FunkoPop! Vegeta Niño', 'FunkoPop Saga Dragon Ball - 25cm', 25, 3, 0.21, 'funko-pop-vegeta-nino-dragon-ball-z.jpg', 0, '2022-09-01', 4),
(3, 1, 'FunkoPop! Thor', 'FunkoPop Thor - Love and Thunder - 25cm', 30, 8, 0.21, 'funko-pop-thor-love-thunder.jpg', 0, '2022-10-01', 0),
(4, 1, 'FunkoPop! Malefica', 'FunkoPop Saga Villains/Disney - 25cm', 27, 9, 0.21, 'funko-pop-malefica-con-cuervo-disney.jpg', 0, '2022-08-01', 0),
(5, 1, 'FunkoPop! Iron Man', 'FunkoPop Saga Endgame/Marvel- 25cm', 23, 9, 0.21, 'funko-pop-iron-man-endgame-marvel.jpg', 1, '2022-07-01', 0),
(6, 1, 'FunkoPop! Hermione Granger', 'FunkoPop Saga Harry Potter - 25cm', 30, 9, 0.21, 'funko-pop-hermione-leviosa-con-pluma-harry-potter.jpg', 1, '2022-05-01', 0),
(7, 1, 'FunkoPop! Hedwig', 'FunkoPop Saga Harry Potter - 25cm', 21, 9, 0.21, 'funko-pop-harry-potter-hedwig.jpg', 0, '2022-06-01', 0),
(8, 1, 'FunkoPop! Emily', 'FunkoPop La novia cadaver - 25cm', 22.5, 10, 0.21, 'funko-pop-emily-la-novia-cadaver.jpg', 0, '2022-03-01', 0),
(9, 1, 'FunkoPop! Cruella', 'FunkoPop Cruella Movie - 25cm', 23.5, 2, 0.21, 'funko-pop-cruella-con-baston-disney.jpg', 0, '2022-06-01', 0),
(10, 1, 'FunkoPop! Bella', 'FunkoPop Disney/La bella y la bestia - 25cm', 23.5, 10, 0.21, 'funko-pop-bella-la-bella-y-la-bestia-invernal-30-aniversario.jpg', 0, '2022-01-01', 0),
(11, 1, 'FunkoPop! Batman', 'FunkoPop Batman Movie - 25cm', 30, 10, 0.21, 'funko-pop-batman-25-cm-pelicula.jpg', 0, '2022-06-01', 0),
(12, 1, 'FunkoPop! Alicia', 'FunkoPop Disney/Alice in wonderland - 25cm', 25, 9, 0.21, 'funko-pop-alicia-en-el-pais-de-las-maravillas-disney.jpg', 0, '2022-02-01', 0),
(13, 1, 'FunkoPop! Paimon', 'FunkoPop Genshin Impact - 25cm', 20, 9, 0.21, 'figura-pop-paimon-genshin-impact.jpg', 0, '2022-03-01', 0),
(14, 1, 'FunkoPop! Traveller', 'FunkoPop Genshin Impact - 25cm', 20, 10, 0.21, 'figura-pop-aether-genshin-impact.jpg', 0, '2022-03-01', 0),
(15, 2, 'El universo de Makoto Shinkai', 'El universo de Makoto Shinkai', 35, 8, 0.21, 'libro-el-universo-de-makoto-shinkai.jpg', 0, '2022-01-22', 4),
(16, 2, 'El viaje de Chihiro', 'El viaje de Chihiro', 30, 9, 0.21, 'libro-el-viaje-de-chihiro.jpg', 0, '2022-10-18', 0),
(17, 2, 'Viaje en el Tiempo', 'Libro Escape Game Viaje en el Tiempo', 27, 10, 0.21, 'libro-escape-game-viaje-en-el-tiempo.jpg', 0, '2022-10-18', 0),
(18, 2, 'Japon desconocido', 'Libro Japon desconocido, guía de lugares y hechos fuera del circuito convencional', 30, 8, 0.21, 'libro-japon-desconocido-guia-de-lugares-y-hechos-fuera-del-circuito-convencional.jpg', 0, '2022-03-18', 0),
(19, 2, 'Japon para otakus', 'Libro Japon para otakus', 30, 10, 0.21, 'libro-japon-para-otakus.jpg', 0, '2022-10-18', 0),
(20, 2, 'K-pop Rookie', 'Libro K-pop Rookie, breve historia del k-pop para novatos', 30, 10, 0.21, 'libro-k-pop-rookie-breve-historia-del-k-pop-para-novatos.jpg', 0, '2022-09-18', 0),
(21, 2, 'La historia del Final Fantasy VI', 'Libro La historia del Final Fantasy VI, la divina epopeya', 40, 10, 0.21, 'libro-la-historia-de-final-fantasy-vi-la-divina-epopeya.jpg', 0, '2022-02-18', 0),
(22, 2, 'La industria del anime en España', 'Libro La industria del anime en España, de Heidi a Dragon Ball', 30, 10, 0.21, 'libro-la-industria-del-anime-en-espana-de-heidi-a-dragon-ball.jpg', 0, '2022-05-18', 0),
(23, 2, 'Las 100 mejores películas anime', 'Libro Las 100 mejores películas anime', 30, 10, 0.21, 'libro-las-100-mejores-peliculas-anime.jpg', 0, '2022-10-18', 0),
(24, 2, 'Los secretos de la cultura kawaii', 'Libro Los secretos de la cultura kawaii', 15, 10, 0.21, 'libro-los-secretos-de-la-cultura-kawaii.jpg', 0, '2022-01-01', 0),
(25, 2, 'Los secretos de las magical girls', 'Libro Los secretos de las magical girls', 15, 10, 0.21, 'libro-los-secretos-de-las-magical-girls.jpg', 0, '2022-02-18', 0),
(26, 2, 'Zelda: Detrás de la leyenda', 'Libro Zelda arte y artefactos en castellano', 60, 10, 0.21, 'libro-zelda-arte-artefactos-castellano.jpg', 0, '2022-05-18', 0),
(27, 2, 'Set Deluxe 7 libros Harry Potter', 'Set Deluxe 7 libros Harry Potter', 100, 7, 0.21, 'set-deluxe-7-libros-harry-potter.jpg', 0, '2022-04-18', 0),
(39, 3, 'Anillo Snitch Dorada', 'Anillo Snitch Dorada - Harry Potter – plata de ley', 15, 5, 0.21, 'anillo-snitch-harry-potter-plata-de-ley.jpg', 0, '2022-01-01', 0),
(40, 3, 'Anillo Unico', 'Anillo Único – El señor de los anillos – plata de ley', 20, 10, 0.21, 'anillo-unico-el-senor-de-los-anillos.jpg', 0, '2022-11-02', 0),
(41, 3, 'Colgante Auryn', 'Colgante Auryn – La historia interminable – plata de ley', 60, 2, 0.21, 'colgante-auryn-plata-de-ley-la-historia-interminable.jpg', 0, '2022-09-03', 4),
(42, 3, 'Colgante Rosa Encantada', 'Colgante Rosa Encantada – La Bella y la Bestia – plata de ley', 45, 7, 0.21, 'colgante-la-bella-y-la-bestia-rosa-encantada-rosada.jpg', 0, '2022-11-04', 0),
(43, 4, 'Sudadera Nintendo Nes', 'Sudadera Chica Nintendo Mano Nes', 50, -1, 0.21, 'sudadera-chica-nintendo-mando-nes.jpg', 0, '2022-03-05', 5),
(44, 4, 'Chaqueta Beisbol Gryffindor', 'Chaqueta Beisbol Gryffindor – Harry Potter – Crest', 70, 10, 0.21, 'chaqueta-beisbol-gryffindor-harry-potter-crest.jpg', 0, '2022-11-06', 0),
(45, 4, 'Camiseta Malefica', 'Camiseta Chica Malefica Cara Disney', 20, 4, 0.21, 'camiseta-chica-malefica-cara-disney.jpg', 0, '2022-07-07', 0),
(46, 4, 'Calcetines Mickey Mouse', 'Calcetines Mickey Mouse Disney Classic', 10, 10, 0.21, 'calcetines-mickey-mouse-disney-classic.jpg', 0, '2022-11-08', 0),
(53, 5, 'Figura Blancanieves', 'Figura Blancanieves - Banpresto QPosket - Disnney', 20, 1, 0.21, 'figura-blancanieves-banpresto-q-posket-a-disney-14-cm.jpg', 0, '2022-11-08', 0),
(63, 6, 'Cluedo Pesadilla antes de Navidad', 'Cluedo Pesadilla antes de Navidad - castellano', 60, 5, 0.21, 'cluedo-pesadilla-antes-de-navidad-castellano.jpg', 0, '2022-11-10', 0),
(64, 6, 'Domino Harry Potter', 'Domino Harry Potter personajes chibi', 30, 5, 0.21, 'domino-harry-potter-personajes-chibi.jpg', 0, '2022-11-10', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `localidad` varchar(45) NOT NULL,
  `provincia` varchar(45) NOT NULL,
  `telefono` varchar(9) NOT NULL,
  `cif` varchar(9) NOT NULL,
  `email` varchar(45) NOT NULL,
  `baja` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`id`, `nombre`, `direccion`, `localidad`, `provincia`, `telefono`, `cif`, `email`, `baja`) VALUES
(1, 'Banpresto', 'Cl Redonda 32', 'Zamora', 'Zamora', '636598524', 'B49656585', 'banpresto@gmail.com', 0),
(2, 'TusFiguras', 'Cl Cuadrada 62', 'Zamora', 'Zamora', '635236485', 'B08652145', 'tusfiguras@gmail.com', 0),
(3, 'VisteFriki', 'Cl Triangulo 74', 'Zamora', 'Zamora', '785265415', 'A48756254', 'vistefriki@gmail.com', 0),
(4, 'BrillaFriki', 'Cl Redondel 21', 'Zamora', 'Zamora', '741147753', 'A47568452', 'brillafriki@gmail.com', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `rol` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`) VALUES
(1, 'Anonimo'),
(2, 'Cliente'),
(3, 'Empleado'),
(4, 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido1` varchar(45) NOT NULL,
  `apellido2` varchar(45) NOT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `provincia` varchar(45) DEFAULT NULL,
  `localidad` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `dni` varchar(45) NOT NULL,
  `imagen` varchar(45) DEFAULT NULL,
  `baja` tinyint(1) DEFAULT NULL,
  `fecha_alta` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `id_rol`, `email`, `clave`, `nombre`, `apellido1`, `apellido2`, `direccion`, `provincia`, `localidad`, `telefono`, `dni`, `imagen`, `baja`, `fecha_alta`) VALUES
(1, 1, 'cl1@gmail.com', 'c5OFkiPC+vtsmAUsKE0yD4oPdRKp3q0M', 'Cliente', 'Cliente', 'Cliente', 'Avenida 1 numero 1', 'Ciudad1', 'Ciudad1', '654987321', '78945612A', NULL, 0, '2022-10-28'),
(2, 2, 'emp@gmail.com', 'L0g5s/NZsaBZ7nC0+AubIg==', 'Empleado', 'Empleado', 'Empleado', 'Avenida 2 numero 2', 'Ciudad2', 'Ciudad2', '987456321', '74125896B', NULL, 0, '2022-10-28'),
(3, 3, 'admin@gmail.com', 'cxzD5dgDfO1IO4khoAoPww==', 'Admin', 'Admin', 'Admin', 'Avenida 3 numero 3', 'Ciudad3', 'Ciudad3', '693528741', '75342189C', NULL, 0, '2021-01-01'),
(10, 1, 'pedro@gmail.com', 's2+Td8CD/1Z/cz6X85OCPw==', 'Pedro', 'Perez', 'Perez', 'Cl Primera 28', 'Zamora', 'Zamora', '789654123', '14725836S', NULL, 0, '2022-11-03'),
(11, 2, 'pedrolo@gmail.com', 'iMjTn6PV5Z6j+BLLLmi+WA==', 'Pedro', 'Lopez', 'Perez', 'Cl Segunda', 'Zamora', 'Zamora', '789789789', '11236654L', NULL, 0, '2022-10-28'),
(13, 1, 'lau@gmail.com', 'c1n2/YwJhzhABDTswbC5qg==', 'Laura', 'Dominguez', 'Sierra', 'Cl Larga 22 1E', 'Zamora', 'Zamora', '654448822', '74125895L', NULL, 0, '2022-10-31'),
(14, 1, 'ana@gmail.com', 'RFUMwMJPYxhs99c8/rqXaQ==', 'Ana', 'Cuesta', 'Gago', 'Cl Corta 3 4B', 'Zamora', 'Zamora', '665544985', '77844553X', NULL, 0, '2022-10-31'),
(19, 1, 'anixx89@gmail.com', 'GgE04juGgjJZivoPUGF88g==\n', 'Ana', 'Perez', 'Rodriguez', 'Cl Valladolid 54 6A', 'Zamora', 'Zamora', '698545858', '78954548C', NULL, 0, '2022-10-31'),
(21, 1, 'tere@gmail.com', '3hXyQ7jPQrOi3CKjiNIYeQ==', 'Teresa', 'Ramos', 'Carrion', 'Cl Cuarta', 'Zamora', 'Zamora', '698965324', '78569542P', 'usermujer2.jpg', 0, '2022-11-03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoraciones`
--

CREATE TABLE `valoraciones` (
  `id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `valoracion` int(11) NOT NULL,
  `comentario` varchar(255) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `valoraciones`
--

INSERT INTO `valoraciones` (`id`, `producto_id`, `usuario_id`, `valoracion`, `comentario`, `fecha`) VALUES
(2, 2, 1, 4, 'wqeqeqw', '2022-11-05'),
(3, 41, 14, 4, 'Es perfecto!', '2022-11-08'),
(4, 2, 21, 4, 'Envío rápido y buena calidad', '2022-11-08'),
(5, 43, 14, 5, 'Es muy calentita y muy cómoda', '2022-11-09'),
(7, 15, 1, 5, 'La ilustración de este libro es perfecta.', '2022-11-09'),
(8, 15, 1, 4, 'Y la trama increíble', '2022-11-09');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulos_carrito`
--
ALTER TABLE `articulos_carrito`
  ADD KEY `FK_id_producto2` (`id_producto`),
  ADD KEY `FK_id_usuario_idx` (`id_usuario`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `CLAVE` (`clave`);

--
-- Indices de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_id_producto_idx` (`producto_id`),
  ADD KEY `FK_id_pedido_idx` (`id_pedido`);

--
-- Indices de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_id_rol_idx` (`id_rol`),
  ADD KEY `FK_id_rol_idx1` (`id_rol`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_id_usuario_idx` (`id_usuario`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_id_categoria_idx` (`categoria_id`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_id_rol_idx` (`id_rol`);

--
-- Indices de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulos_carrito`
--
ALTER TABLE `articulos_carrito`
  ADD CONSTRAINT `FK_id_producto2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_id_usuario2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  ADD CONSTRAINT `FK_id_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_id_producto` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  ADD CONSTRAINT `FK_id_rol1` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `FK_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `FK_id_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `FK_id_rol` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
