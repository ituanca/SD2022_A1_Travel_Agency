-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema assignment1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema assignment1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `assignment1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `assignment1` ;

-- -----------------------------------------------------
-- Table `assignment1`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment1`.`user` (
  `id` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `firstName` VARCHAR(255) NULL DEFAULT NULL,
  `lastName` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_jreodf78a7pl5qidfh43axdfb` (`username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment1`.`destination`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment1`.`destination` (
  `id` VARCHAR(255) NOT NULL,
  `destination` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment1`.`package`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment1`.`package` (
  `id` VARCHAR(255) NOT NULL,
  `endDate` DATETIME NULL DEFAULT NULL,
  `extraDetails` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `numberOfBookings` INT NULL DEFAULT NULL,
  `price` VARCHAR(255) NULL DEFAULT NULL,
  `startDate` DATETIME NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `destinationId` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKcdyhx1i3g8jhx9ocovyg4ld9d` (`destinationId` ASC) VISIBLE,
  CONSTRAINT `FKcdyhx1i3g8jhx9ocovyg4ld9d`
    FOREIGN KEY (`destinationId`)
    REFERENCES `assignment1`.`destination` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment1`.`booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment1`.`booking` (
  `id` VARCHAR(255) NOT NULL,
  `packageId` VARCHAR(255) NULL DEFAULT NULL,
  `userId` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK5vs1tddbsd90l089txyo0va9h` (`packageId` ASC) VISIBLE,
  INDEX `FK54ugfxfinotylxwks5yw12htl` (`userId` ASC) VISIBLE,
  CONSTRAINT `FK54ugfxfinotylxwks5yw12htl`
    FOREIGN KEY (`userId`)
    REFERENCES `assignment1`.`user` (`id`),
  CONSTRAINT `FK5vs1tddbsd90l089txyo0va9h`
    FOREIGN KEY (`packageId`)
    REFERENCES `assignment1`.`package` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment1`.`travelagency`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment1`.`travelagency` (
  `id` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
