-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema pizza_paradise
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pizza_paradise
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pizza_paradise` DEFAULT CHARACTER SET utf8mb3 ;
USE `pizza_paradise` ;

-- -----------------------------------------------------
-- Table `pizza_paradise`.`bruger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizza_paradise`.`bruger` (
  `bruger_id` INT NOT NULL AUTO_INCREMENT,
  `bruger_password` VARCHAR(255) NULL DEFAULT NULL,
  `bruger_email` VARCHAR(255) NULL DEFAULT NULL,
  `bruger_bonuspoint` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`bruger_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pizza_paradise`.`bestilling`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizza_paradise`.`bestilling` (
  `bestilling_id` INT NOT NULL AUTO_INCREMENT,
  `fk_bruger_id` INT NULL DEFAULT NULL,
  `bestilling_dato_tid` TIMESTAMP NOT NULL,
  `bestilling_pris` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`bestilling_id`),
  INDEX `bruger_id_idx` (`fk_bruger_id` ASC) VISIBLE,
  CONSTRAINT `bruger_id`
    FOREIGN KEY (`fk_bruger_id`)
    REFERENCES `pizza_paradise`.`bruger` (`bruger_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pizza_paradise`.`pizza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizza_paradise`.`pizza` (
  `pizza_id` INT NOT NULL AUTO_INCREMENT,
  `pizza_name` VARCHAR(255) NULL DEFAULT NULL,
  `pizza_beskrivelse` VARCHAR(255) NULL DEFAULT NULL,
  `fk_topping` INT NULL DEFAULT NULL,
  `pizza_pris` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`pizza_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pizza_paradise`.`topping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizza_paradise`.`topping` (
  `topping_id` INT NOT NULL AUTO_INCREMENT,
  `topping_navn` VARCHAR(255) NULL DEFAULT NULL,
  `topping_pris` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`topping_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pizza_paradise`.`bestilling_har_pizza_og_topping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizza_paradise`.`bestilling_har_pizza_og_topping` (
  `bestilling_pizza_topping_id` INT NOT NULL AUTO_INCREMENT,
  `fk_pizza` INT NULL DEFAULT NULL,
  `fk_bestilling` INT NULL DEFAULT NULL,
  `fk_topping` INT NULL DEFAULT NULL,
  PRIMARY KEY (`bestilling_pizza_topping_id`),
  INDEX `bestilling_fk_idx` (`fk_bestilling` ASC) VISIBLE,
  INDEX `pizza_fk_idx` (`fk_pizza` ASC) VISIBLE,
  INDEX `topping_fk_idx` (`fk_topping` ASC) VISIBLE,
  CONSTRAINT `bestilling_fk`
    FOREIGN KEY (`fk_bestilling`)
    REFERENCES `pizza_paradise`.`bestilling` (`bestilling_id`),
  CONSTRAINT `pizza_fk`
    FOREIGN KEY (`fk_pizza`)
    REFERENCES `pizza_paradise`.`pizza` (`pizza_id`),
  CONSTRAINT `topping_fk`
    FOREIGN KEY (`fk_topping`)
    REFERENCES `pizza_paradise`.`topping` (`topping_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
