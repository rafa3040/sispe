SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `sispe` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `sispe` ;

-- -----------------------------------------------------
-- Table `sispe`.`persona`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sispe`.`persona` (
  `numero_identificacion` BIGINT NOT NULL ,
  `nombre_persona` VARCHAR(50) NOT NULL ,
  `apellido_persona` VARCHAR(50) ,
  `tipo_documento` ENUM('CC','CE') NOT NULL ,
  `fecha_nacimiento` DATE ,
  `telefono` BIGINT ,
  `correo_electronico` VARCHAR(50) ,
  `profesion` VARCHAR(100) ,
  `especializacion` VARCHAR(100) ,
  PRIMARY KEY (`numero_identificacion`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sispe`.`experiencia`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sispe`.`experiencia` (
  `numero_experiencia` INT NOT NULL AUTO_INCREMENT ,
  `fecha_inicio` DATE NOT NULL ,
  `fecha_final` DATE NOT NULL ,
  `numero_identificacion` BIGINT NOT NULL ,
  PRIMARY KEY (`numero_experiencia`) ,
  INDEX `fk_experiencia_persona_idx` (`numero_identificacion` ASC) ,
  CONSTRAINT `fk_experiencia_persona`
    FOREIGN KEY (`numero_identificacion` )
    REFERENCES `sispe`.`persona` (`numero_identificacion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sispe`.`usuario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sispe`.`usuario` (
  `id_usuario` INT NOT NULL ,
  `nombre_usuario` VARCHAR(45) NOT NULL ,
  `contrasena_usuario` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id_usuario`, `nombre_usuario`) )
ENGINE = InnoDB;

USE `sispe` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
