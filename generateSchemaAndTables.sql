-- MySQL Script generated by MySQL Workbench
-- pon, 17 wrz 2018, 20:11:02
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Warsztaty_W_6
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Warsztaty_W_6
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Warsztaty_W_6` DEFAULT CHARACTER SET utf8 ;
USE `Warsztaty_W_6` ;

-- -----------------------------------------------------
-- Table `Warsztaty_W_6`.`app_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warsztaty_W_6`.`app_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `enabled` BIT(1) NULL DEFAULT NULL,
  `password` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `username` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_1j9d9a06i600gd43uu3km82jw` (`email` ASC),
  UNIQUE INDEX `UK_3k4cplvh82srueuttfkwnylq0` (`username` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


-- -----------------------------------------------------
-- Table `Warsztaty_W_6`.`app_messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warsztaty_W_6`.`app_messages` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `isNewMessage` BIT(1) NULL DEFAULT NULL,
  `text` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `receiver_id` BIGINT(20) NULL DEFAULT NULL,
  `sender_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKtgi0bblx6g4304il69tol4h7t` (`receiver_id` ASC),
  INDEX `FKhdqea8u5uw2bh2ydxscxd5hus` (`sender_id` ASC),
  CONSTRAINT `FKhdqea8u5uw2bh2ydxscxd5hus`
    FOREIGN KEY (`sender_id`)
    REFERENCES `Warsztaty_W_6`.`app_user` (`id`),
  CONSTRAINT `FKtgi0bblx6g4304il69tol4h7t`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `Warsztaty_W_6`.`app_user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


-- -----------------------------------------------------
-- Table `Warsztaty_W_6`.`tweets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warsztaty_W_6`.`tweets` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `created` DATETIME NULL DEFAULT NULL,
  `text` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `user_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK12sd5xsle5n3ds1m00abx628f` (`user_id` ASC),
  CONSTRAINT `FK12sd5xsle5n3ds1m00abx628f`
    FOREIGN KEY (`user_id`)
    REFERENCES `Warsztaty_W_6`.`app_user` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


-- -----------------------------------------------------
-- Table `Warsztaty_W_6`.`tweet_comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warsztaty_W_6`.`tweet_comments` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `created` DATETIME NULL DEFAULT NULL,
  `text` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `tweet_id` BIGINT(20) NULL DEFAULT NULL,
  `user_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK67pft7ltmwhjoqyn90e5axyky` (`tweet_id` ASC),
  INDEX `FKq111w7b1369g2ioonv67ttnnp` (`user_id` ASC),
  CONSTRAINT `FK67pft7ltmwhjoqyn90e5axyky`
    FOREIGN KEY (`tweet_id`)
    REFERENCES `Warsztaty_W_6`.`tweets` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `FKq111w7b1369g2ioonv67ttnnp`
    FOREIGN KEY (`user_id`)
    REFERENCES `Warsztaty_W_6`.`app_user` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

