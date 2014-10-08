SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `SISPE` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `SISPE` ;

-- -----------------------------------------------------
-- Table `SISPE`.`PERSONA`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `SISPE`.`PERSONA` (
  `numero_identificacion` INT NOT NULL ,
  `nombre_persona` VARCHAR(45) NOT NULL ,
  `apellido_persona` VARCHAR(45) NOT NULL ,
  `tipo_documento` ENUM('CC','CE') NOT NULL ,
  `fecha_nacimiento` DATE NOT NULL ,
  `telefono` INT NOT NULL ,
  `correo_electronico` VARCHAR(45) NOT NULL ,
  `profesion` VARCHAR(45) NOT NULL ,
  `especializacion` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`numero_identificacion`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SISPE`.`EXPERIENCIA`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `SISPE`.`EXPERIENCIA` (
  `numero_EXPERIENCIA` INT NOT NULL ,
  `fecha_inicio` DATE NOT NULL ,
  `fecha_final` DATE NOT NULL ,
  `PERSONA_numero_identificacion` INT NULL ,
  PRIMARY KEY (`numero_EXPERIENCIA`) ,
  INDEX `fk_EXPERIENCIA_PERSONA_idx` (`PERSONA_numero_identificacion` ASC) ,
  CONSTRAINT `fk_EXPERIENCIA_PERSONA`
    FOREIGN KEY (`PERSONA_numero_identificacion` )
    REFERENCES `SISPE`.`PERSONA` (`numero_identificacion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SISPE`.`USUARIO`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `SISPE`.`USUARIO` (
  `id_usuario` INT NOT NULL ,
  `nombre_usuario` VARCHAR(45) NOT NULL ,
  `contrasena_usuario` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id_usuario`, `nombre_usuario`) )
ENGINE = InnoDB;

USE `SISPE` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
