-- MySQL Workbench Synchronization
-- Generated: 2023-11-07 22:35
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: josue

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER TABLE `redsocial`.`foto`
DROP FOREIGN KEY `foto_ibfk_1`;

ALTER TABLE `redsocial`.`foto`
DROP COLUMN `publicacion_id_publicacion`,
DROP INDEX `publicacion_id_publicacion` ;
;

ALTER TABLE `redsocial`.`publicacion`
    ADD COLUMN `foto_id_foto` VARCHAR(50) NOT NULL AFTER `descripcion`,
ADD COLUMN `like` INT(11) NULL DEFAULT NULL AFTER `foto_id_foto`,
ADD INDEX `fk_publicacion_foto1_idx` (`foto_id_foto` ASC) VISIBLE;
;

ALTER TABLE `redsocial`.`usuario`
    ADD COLUMN `foto_id_foto` VARCHAR(50) NOT NULL AFTER `requiere_cambio`,
ADD INDEX `fk_usuario_foto1_idx` (`foto_id_foto` ASC) VISIBLE;
;

ALTER TABLE `redsocial`.`publicacion`
    ADD CONSTRAINT `fk_publicacion_foto1`
        FOREIGN KEY (`foto_id_foto`)
            REFERENCES `redsocial`.`foto` (`id_foto`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `redsocial`.`usuario`
    ADD CONSTRAINT `fk_usuario_foto1`
        FOREIGN KEY (`foto_id_foto`)
            REFERENCES `redsocial`.`foto` (`id_foto`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
