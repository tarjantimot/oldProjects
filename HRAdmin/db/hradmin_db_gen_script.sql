-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hradmin
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hradmin
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hradmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci ;
USE `hradmin` ;

-- -----------------------------------------------------
-- Table `hradmin`.`job`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hradmin`.`job` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `job_title` VARCHAR(45) NOT NULL,
  `job_description` VARCHAR(3000) NULL DEFAULT NULL,
  `path` VARCHAR(255) NULL DEFAULT NULL,
  `min_salary` DOUBLE NOT NULL,
  `max_salary` DOUBLE NOT NULL,
  `worker_num` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_hungarian_ci;


-- -----------------------------------------------------
-- Table `hradmin`.`pdata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hradmin`.`pdata` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tax_num` VARCHAR(15) NOT NULL,
  `identification_num` VARCHAR(20) NOT NULL,
  `insurrance_num` VARCHAR(15) NOT NULL,
  `personal_num` VARCHAR(15) NOT NULL,
  `other` VARCHAR(45) NULL DEFAULT NULL,
  `other_type` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_hungarian_ci;


-- -----------------------------------------------------
-- Table `hradmin`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hradmin`.`employee` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birth_place` VARCHAR(85) NOT NULL,
  `birth_date` DATE NOT NULL,
  `status` VARCHAR(25) NOT NULL,
  `status_last_modified` DATE NOT NULL,
  `pdata_id` INT(11) NOT NULL,
  `job_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `employee_pdata_fk_idx` (`pdata_id` ASC) VISIBLE,
  INDEX `employee_job_fk_idx` (`job_id` ASC) VISIBLE,
  CONSTRAINT `employee_job_fk`
    FOREIGN KEY (`job_id`)
    REFERENCES `hradmin`.`job` (`id`),
  CONSTRAINT `employee_pdata_fk`
    FOREIGN KEY (`pdata_id`)
    REFERENCES `hradmin`.`pdata` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_hungarian_ci;


-- -----------------------------------------------------
-- Table `hradmin`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hradmin`.`address` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  `city` VARCHAR(85) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `street_number` VARCHAR(10) NOT NULL,
  `floor` INT(11) NULL DEFAULT NULL,
  `door` INT(11) NULL DEFAULT NULL,
  `employee_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `address_employee_idx` (`employee_id` ASC) VISIBLE,
  CONSTRAINT `address_employee`
    FOREIGN KEY (`employee_id`)
    REFERENCES `hradmin`.`employee` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_hungarian_ci;


-- -----------------------------------------------------
-- Table `hradmin`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hradmin`.`contact` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `c_type` VARCHAR(45) NOT NULL,
  `c_value` VARCHAR(45) NOT NULL,
  `employee_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `contact_employee_fk_idx` (`employee_id` ASC) VISIBLE,
  CONSTRAINT `contact_employee_fk`
    FOREIGN KEY (`employee_id`)
    REFERENCES `hradmin`.`employee` (`id`)
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_hungarian_ci;


-- -----------------------------------------------------
-- Table `hradmin`.`contract`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hradmin`.`contract` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `creation_date` DATE NOT NULL,
  `expiry_date` DATE NOT NULL,
  `path` VARCHAR(255) NOT NULL,
  `employee_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `contract_employee_fk_idx` (`employee_id` ASC) VISIBLE,
  CONSTRAINT `contract_employee_fk`
    FOREIGN KEY (`employee_id`)
    REFERENCES `hradmin`.`employee` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_hungarian_ci;


-- -----------------------------------------------------
-- Table `hradmin`.`salary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hradmin`.`salary` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `start_date` DATE NOT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  `amount` DOUBLE NOT NULL,
  `employee_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `salary_employee_fk_idx` (`employee_id` ASC) VISIBLE,
  CONSTRAINT `salary_employee_fk`
    FOREIGN KEY (`employee_id`)
    REFERENCES `hradmin`.`employee` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_hungarian_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
