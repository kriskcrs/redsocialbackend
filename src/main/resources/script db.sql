-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema redsocial
-- -----------------------------------------------------

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
    PRIMARY KEY (`id_usuario`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `redsocial`.`foto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redsocial`.`foto` (
    `id_foto` VARCHAR(50) NOT NULL,
    `ip_server` VARCHAR(13) NOT NULL,
    `ruta` VARCHAR(50) NOT NULL,
    `usuario_id_usuario` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id_foto`),
    INDEX `fk_foto_usuario1_idx` (`usuario_id_usuario` ASC) VISIBLE,
    CONSTRAINT `fk_foto_usuario1`
    FOREIGN KEY (`usuario_id_usuario`)
    REFERENCES `redsocial`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `redsocial`.`publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redsocial`.`publicacion` (
                                                         `id_publicacion` INT NOT NULL,
                                                         `fecha_creacion` DATE NOT NULL,
                                                         `fecha_modificacion` DATE NULL,
                                                         `usuario_id_usuario` VARCHAR(50) NOT NULL,
    `foto_id_foto` VARCHAR(50) NULL,
    PRIMARY KEY (`id_publicacion`),
    INDEX `fk_publicacion_usuario1_idx` (`usuario_id_usuario` ASC) VISIBLE,
    INDEX `fk_publicacion_foto1_idx` (`foto_id_foto` ASC) VISIBLE,
    CONSTRAINT `fk_publicacion_usuario1`
    FOREIGN KEY (`usuario_id_usuario`)
    REFERENCES `redsocial`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_publicacion_foto1`
    FOREIGN KEY (`foto_id_foto`)
    REFERENCES `redsocial`.`foto` (`id_foto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `redsocial`.`comentario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redsocial`.`comentario` (
                                                        `id_comentario` INT NOT NULL,
                                                        `texto` VARCHAR(300) NOT NULL,
    `publicacion_id_publicacion` INT NOT NULL,
    PRIMARY KEY (`id_comentario`),
    INDEX `fk_comentario_publicacion1_idx` (`publicacion_id_publicacion` ASC) VISIBLE,
    CONSTRAINT `fk_comentario_publicacion1`
    FOREIGN KEY (`publicacion_id_publicacion`)
    REFERENCES `redsocial`.`publicacion` (`id_publicacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
