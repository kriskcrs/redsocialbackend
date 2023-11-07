-- drop schema redsocial;



-- -----------------------------------------------------
-- Schema redsocial
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `redsocial` DEFAULT CHARACTER SET utf8 ;
USE `redsocial` ;

-- -----------------------------------------------------
-- Table `redsocial`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redsocial`.`usuario` (
    `id_usuario` VARCHAR(50) NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `apellido` VARCHAR(100) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `fecha_nacimiento` DATE NOT NULL,
    `session` VARCHAR(200) NULL,
    `fecha_ingreso` DATETIME NULL,
    `requiere_cambio` varchar(1) NULL,
    PRIMARY KEY (`id_usuario`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `redsocial`.`foto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redsocial`.`foto` (
    `id_foto` VARCHAR(50) NOT NULL,
    `ip_server` VARCHAR(13) NOT NULL,
    `ruta` VARCHAR(300) NOT NULL,
    `usuario_id_usuario` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id_foto`),
    FOREIGN KEY (`usuario_id_usuario`)  REFERENCES `redsocial`.`usuario` (`id_usuario`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `redsocial`.`publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redsocial`.`publicacion` (
                                                         `id_publicacion` INT NOT NULL AUTO_INCREMENT,
                                                         `fecha_creacion` DATE NOT NULL,
                                                         `fecha_modificacion` DATE NULL,
                                                         `usuario_id_usuario` VARCHAR(50) NOT NULL,
    `descripcion` VARCHAR(100) NULL,
    `foto_id_foto` VARCHAR(50) NULL,
    PRIMARY KEY (`id_publicacion`),
    FOREIGN KEY (`usuario_id_usuario`) REFERENCES `redsocial`.`usuario` (`id_usuario`),
    FOREIGN KEY (`foto_id_foto`) REFERENCES `redsocial`.`foto` (`id_foto`)
    ) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `redsocial`.`comentario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redsocial`.`comentario` (
                                                        `id_comentario` INT NOT NULL AUTO_INCREMENT,
                                                        `texto` VARCHAR(300) NOT NULL,
    `publicacion_id_publicacion` INT NOT NULL,
    `usuario_id_usuario` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id_comentario`),
    INDEX (`usuario_id_usuario`),
    INDEX (`publicacion_id_publicacion`),
    FOREIGN KEY (`publicacion_id_publicacion`) REFERENCES `redsocial`.`publicacion` (`id_publicacion`),
    FOREIGN KEY (`usuario_id_usuario`) REFERENCES `redsocial`.`usuario` (`id_usuario`)
    ) ENGINE = InnoDB;





INSERT INTO redsocial.usuario
(id_usuario, nombre, apellido, password, fecha_nacimiento, `session`, fecha_ingreso, requiere_cambio)
VALUES('prueba@gmail.com', 'nombre', 'apellido', 'c4ca4238a0b923820dcc509a6f75849b', '1991-03-13', NULL, NULL,'0');





