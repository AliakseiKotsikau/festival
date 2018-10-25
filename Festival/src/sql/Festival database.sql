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
CREATE TABLE IF NOT EXISTS `FestivalDB`.`Login` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `permission` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalDB`.`Participants`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalDB`.`Participant` (
`participant_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL ,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  PRIMARY KEY (`participant_id`),
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `FestivalDB`.`Login` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalDB`.`Festivals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalDB`.`Festival` (
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
CREATE TABLE IF NOT EXISTS `FestivalDB`.`Performer` (
  `performer_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`performer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalDB`.`Fetivals_participants`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalDB`.`Festival_participant` (
  `participant_id` INT UNSIGNED NOT NULL,
  `festival_id` INT UNSIGNED NOT NULL,
  INDEX `participant ID_idx` (`participant_id` ASC) VISIBLE,
  INDEX `fk_festival_id_idx` (`festival_id` ASC) VISIBLE,
  PRIMARY KEY (`participant_id`, `festival_id`),
  CONSTRAINT `fk_festival_part_id`
    FOREIGN KEY (`festival_id`)
    REFERENCES `FestivalDB`.`Festival` (`festival_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participant_id`
    FOREIGN KEY (`participant_id`)
    REFERENCES `FestivalDB`.`Participant` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FestivalDB`.`Festivals_performers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FestivalDB`.`Festival_performer` (
  `festival_id` INT UNSIGNED NOT NULL,
  `performer_id` INT UNSIGNED NOT NULL,
  INDEX `performer ID_idx` (`performer_id` ASC) VISIBLE,
  PRIMARY KEY (`festival_id`, `performer_id`),
  CONSTRAINT `fk_festival_perf_id`
    FOREIGN KEY (`festival_id`)
    REFERENCES `FestivalDB`.`Festival` (`festival_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_performer_id`
    FOREIGN KEY (`performer_id`)
    REFERENCES `FestivalDB`.`Performer` (`performer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
