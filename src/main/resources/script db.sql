-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema redsocial
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema redsocial
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `redsocial` DEFAULT CHARACTER SET utf8 ;
USE `redsocial` ;

-- -----------------------------------------------------
-- Table `redsocial`.`foto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redsocial`.`foto` (
    `id_foto` VARCHAR(50) NOT NULL,
    `ip_server` VARCHAR(13) NOT NULL,
    `ruta` VARCHAR(300) NOT NULL,
    PRIMARY KEY (`id_foto`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `redsocial`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redsocial`.`usuario` (
    `id_usuario` VARCHAR(50) NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `apellido` VARCHAR(100) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `fecha_nacimiento` DATE NOT NULL,
    `session` VARCHAR(200) NULL DEFAULT NULL,
    `fecha_ingreso` DATETIME NULL DEFAULT NULL,
    `requiere_cambio` VARCHAR(1) NULL DEFAULT NULL,
    `foto_id_foto` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id_usuario`),
    INDEX `fk_usuario_foto1_idx` (`foto_id_foto` ASC) VISIBLE,
    CONSTRAINT `fk_usuario_foto1`
    FOREIGN KEY (`foto_id_foto`)
    REFERENCES `redsocial`.`foto` (`id_foto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `redsocial`.`publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redsocial`.`publicacion` (
    `id_publicacion` INT NOT NULL AUTO_INCREMENT,
    `fecha_creacion` DATE NOT NULL,
    `fecha_modificacion` DATE NULL DEFAULT NULL,
    `usuario_id_usuario` VARCHAR(50) NOT NULL,
    `descripcion` VARCHAR(100) NULL DEFAULT NULL,
    `foto_id_foto` VARCHAR(50) NOT NULL,
    `emoji` INT NULL,
    PRIMARY KEY (`id_publicacion`),
    INDEX `usuario_id_usuario` (`usuario_id_usuario` ASC) VISIBLE,
    INDEX `fk_publicacion_foto1_idx` (`foto_id_foto` ASC) VISIBLE,
    CONSTRAINT `publicacion_ibfk_1`
    FOREIGN KEY (`usuario_id_usuario`)
    REFERENCES `redsocial`.`usuario` (`id_usuario`),
    CONSTRAINT `fk_publicacion_foto1`
    FOREIGN KEY (`foto_id_foto`)
    REFERENCES `redsocial`.`foto` (`id_foto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `redsocial`.`comentario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redsocial`.`comentario` (
    `id_comentario` INT NOT NULL AUTO_INCREMENT,
    `texto` VARCHAR(300) NOT NULL,
    `publicacion_id_publicacion` INT NOT NULL,
    `usuario_id_usuario` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id_comentario`),
    INDEX `publicacion_id_publicacion` (`publicacion_id_publicacion` ASC) VISIBLE,
    INDEX `usuario_id_usuario` (`usuario_id_usuario` ASC) VISIBLE,
    CONSTRAINT `comentario_ibfk_1`
    FOREIGN KEY (`publicacion_id_publicacion`)
    REFERENCES `redsocial`.`publicacion` (`id_publicacion`),
    CONSTRAINT `comentario_ibfk_2`
    FOREIGN KEY (`usuario_id_usuario`)
    REFERENCES `redsocial`.`usuario` (`id_usuario`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO redsocial.foto (id_foto, ip_server, ruta) VALUES('perfil.png', '10.10', '\\perfil.png');
