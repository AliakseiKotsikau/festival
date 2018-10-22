-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema FestivalDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema FestivalDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `FestivalDB` DEFAULT CHARACTER SET utf8 ;
USE `FestivalDB` ;

-- -----------------------------------------------------
-- Table `FestivalDB`.`Logins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalDB`.`Logins` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `permission` ENUM('Guest', 'User', 'Admin') NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalDB`.`Participants`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalDB`.`Participants` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `FestivalDB`.`Logins` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalDB`.`Festivals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalDB`.`Festivals` (
  `festival_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(155) NOT NULL,
  `date` DATE NOT NULL,
  `place` VARCHAR(105) NOT NULL,
  `seating` INT NOT NULL,
  PRIMARY KEY (`festival_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalDB`.`Performers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalDB`.`Performers` (
  `performer_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`performer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalDB`.`Fetivals_participants`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalDB`.`Fetivals_participants` (
  `participant_id` INT UNSIGNED NOT NULL,
  `festival_id` INT UNSIGNED NOT NULL,
  INDEX `participant ID_idx` (`participant_id` ASC) VISIBLE,
  INDEX `fk_festival_id_idx` (`festival_id` ASC) VISIBLE,
  PRIMARY KEY (`participant_id`, `festival_id`),
  CONSTRAINT `fk_festival_part_id`
    FOREIGN KEY (`festival_id`)
    REFERENCES `FestivalDB`.`Festivals` (`festival_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participant_id`
    FOREIGN KEY (`participant_id`)
    REFERENCES `FestivalDB`.`Participants` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalDB`.`Festivals_performers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalDB`.`Festivals_performers` (
  `festival_id` INT UNSIGNED NOT NULL,
  `performer_id` INT UNSIGNED NOT NULL,
  INDEX `performer ID_idx` (`performer_id` ASC) VISIBLE,
  PRIMARY KEY (`festival_id`, `performer_id`),
  CONSTRAINT `fk_festival_perf_id`
    FOREIGN KEY (`festival_id`)
    REFERENCES `FestivalDB`.`Festivals` (`festival_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_performer_id`
    FOREIGN KEY (`performer_id`)
    REFERENCES `FestivalDB`.`Performers` (`performer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
