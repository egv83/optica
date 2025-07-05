-- optica.paciente definition

CREATE TABLE `paciente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(80) DEFAULT NULL,
  `apellido paterno` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `apellido_materno` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `nombre_completo` varchar(160) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `ocupacion` varchar(80) DEFAULT NULL,
  `id_ciudad` int DEFAULT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;