SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema progetto
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema progetto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `progetto` DEFAULT CHARACTER SET utf8 ;
USE `progetto` ;

-- -----------------------------------------------------
-- Table `progetto`.`prodotti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `progetto`.`prodotti` (
  `id` VARCHAR(50) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `descrizione` VARCHAR(200) NULL DEFAULT NULL,
  `prezzo` DECIMAL(6,2) NOT NULL,
  `img` VARCHAR(150) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `colore` VARCHAR(45) NULL DEFAULT NULL,
  `data_inserimento` VARCHAR(15) NOT NULL,
  `genere` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `progetto`.`utenti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `progetto`.`utenti` (
  `username` VARCHAR(10) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `indirizzo` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `cognome` VARCHAR(45) NOT NULL,
  `ruolo` VARCHAR(45) NULL DEFAULT 'utente',
  `uuid` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `progetto`.`carrello`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `progetto`.`carrello` (
  `entry` INT(11) NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(45) NOT NULL,
  `id_prod` VARCHAR(45) NOT NULL,
  `data` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`entry`),
  INDEX `user_idx` (`user` ASC),
  INDEX `prodotto_idx` (`id_prod` ASC),
  CONSTRAINT `prodotto`
    FOREIGN KEY (`id_prod`)
    REFERENCES `progetto`.`prodotti` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user`
    FOREIGN KEY (`user`)
    REFERENCES `progetto`.`utenti` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 764
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;