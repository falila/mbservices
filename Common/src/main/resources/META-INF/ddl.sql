/**
 * Author:  rapha
 * Created: 9-Sep-2018
 */

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema guard
-- -----------------------------------------------------
-- DROP SCHEMA IF EXISTS `guard` ;

-- clean up 
 
DROP table  DELIVERY;
DROP table  PRODUCT;
DROP table  DELIVERYBOY;
DROP table  ORDERED;
DROP table  CLIENT;
DROP table  USER;

-- -----------------------------------------------------
-- Schema guard
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `guard` DEFAULT CHARACTER SET utf8 ;
USE `guard` ;

-- -----------------------------------------------------
-- Table `guard`.``
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `guard`.`` (
  `idUSER` BIGINT(20) NOT NULL,
  PRIMARY KEY (`idUSER`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `guard`.`CLIENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `guard`.`CLIENT` (
  `CLIENTID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `REFERENCE` VARCHAR(99) NULL,
  `TYPE` VARCHAR(45) NULL COMMENT 'RESTAURENT, RETAILERS,ONLINE STORE',
  `ADDRESS` VARCHAR(99) NULL,
  `LOCATIONCODE` VARCHAR(45) NULL,
  PRIMARY KEY (`CLIENTID`))
ENGINE = InnoDB;

CREATE INDEX `CLIENT_ID_INDEX` ON `guard`.`CLIENT` (`CLIENTID` ASC);

CREATE INDEX `CLIENT_TYPE_INDEX` ON `guard`.`CLIENT` (`TYPE` ASC);

CREATE INDEX `CLIENT_REFERENCE_INDEX` ON `guard`.`CLIENT` (`REFERENCE` ASC);

CREATE INDEX `CLIENT_ADDRESS_INDEX` ON `guard`.`CLIENT` (`ADDRESS` ASC);


-- -----------------------------------------------------
-- Table `guard`.`ORDERED`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `guard`.`ORDERED` (
  `ORDEREDID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `REFERENCE` VARCHAR(99) NULL,
  `WHENMADE` DATETIME NULL,
  `WAYISSUED` VARCHAR(45) NULL COMMENT 'defines the media used in placing the ORDERED (phone,app,api,in store...).',
  `PAYMENTBY` VARCHAR(45) NULL,
  `TOTALPRICE` DECIMAL NULL,
  `FROMS` VARCHAR(99) NULL,
  `DEST` VARCHAR(99) NULL,
  `CLIENTID` BIGINT(20) NOT NULL,
  `ESTIMATE_DELIV_TIME` DATETIME NULL,
  `INFODETAIL` VARCHAR(100) NULL COMMENT 'extra information',
  `ESTIMATEDISTANCE` VARCHAR(5) NULL,
  `ESTIMATETIME` VARCHAR(8) NULL,
  `PHONE` VARCHAR(15) NULL,
  `OWNERNAME` VARCHAR(45) NULL,
  PRIMARY KEY (`ORDEREDID`),
  CONSTRAINT `CLIENTID`
    FOREIGN KEY (`CLIENTID`)
    REFERENCES `guard`.`CLIENT` (`CLIENTID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `CLIENTID_idx` ON `guard`.`ORDERED` (`CLIENTID` ASC);


-- -----------------------------------------------------
-- Table `guard`.`DELIVERYBOY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `guard`.`DELIVERYBOY` (
  `ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `LEVEL` INT NULL COMMENT 'describes the level of a driver boy \n\n1 , 2 , 3 , 4\n',
  `RATING` INT NULL COMMENT 'A driver score',
  `STATUS` INT NULL COMMENT '1 , 2, 3, 4',
  `DRIVERLICENCENUMBER` VARCHAR(45) NULL,
  `ENGINEPLATENUMBER` VARCHAR(45) NULL,
  `ENGINEMODEL` VARCHAR(45) NULL,
  `ENGINETYPE` VARCHAR(45) NULL,
  `LICENCEISSUEDATE` DATETIME NULL,
  `LICENCEEXPDATE` DATETIME NULL,
  `REFERENCE` VARCHAR(99) NULL,
  `ADDRESS` VARCHAR(99) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

CREATE INDEX `DELIVERYBOY_INDEX` ON `guard`.`DELIVERYBOY` (`ID` ASC);

CREATE INDEX `RATING_INDEX` ON `guard`.`DELIVERYBOY` (`RATING` ASC);

CREATE INDEX `STATUS_INDEX` ON `guard`.`DELIVERYBOY` (`STATUS` ASC);

CREATE INDEX `LEVEL_INDEX` ON `guard`.`DELIVERYBOY` (`LEVEL` ASC);


-- -----------------------------------------------------
-- Table `guard`.`DELIVERY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `guard`.`DELIVERY` (
  `DELIVERYID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `WHENASSIGNED` DATETIME NULL,
  `WHENDELIVERED` DATETIME NULL,
  `DELIVEREDBY` BIGINT(20) NULL,
  `ORDEREDID` BIGINT(20) NULL,
  `LONG` DOUBLE NULL,
  `LAT` DOUBLE NULL,
  `STATUS` INT NULL COMMENT '0 - delivered\n1-  assigned\n2-  inprogress\n3- canceled\n4- hold\n5- preparation',
  `RATING` INT NULL COMMENT '0-5',
  `COMMENT` VARCHAR(125) NULL,
  PRIMARY KEY (`DELIVERYID`),
  CONSTRAINT `ORDERED_ID_FK`
    FOREIGN KEY (`ORDEREDID`)
    REFERENCES `guard`.`ORDERED` (`ORDEREDID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `DELIVERYBOY_ID_FK`
    FOREIGN KEY (`DELIVEREDBY`)
    REFERENCES `guard`.`DELIVERYBOY` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `ORDERED_ID_FK_idx` ON `guard`.`DELIVERY` (`ORDEREDID` ASC);

CREATE INDEX `DELIVERYBOY_ID_FK_idx` ON `guard`.`DELIVERY` (`DELIVEREDBY` ASC);


-- -----------------------------------------------------
-- Table `guard`.`CLIENTFEE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `guard`.`CLIENTFEE` (
  `FEEID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `CODE` INT NULL COMMENT '1 - driver level 1\n2- driver level 2\n3 - driver level 3\n\n4 - client level 1\n5- client level 2\n6- client level 3\n\n',
  `DESCRIPTION` VARCHAR(45) NULL,
  `VALUE1` DECIMAL NULL,
  `VALUE2` DECIMAL NULL,
  `VALUE3` DECIMAL NULL,
  PRIMARY KEY (`FEEID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `guard`.`PRODUCT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `guard`.`PRODUCT` (
  `PRODUCT_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `PNAME` VARCHAR(45) NULL,
  `PRICE` DECIMAL NULL,
  `DESCRIPTION` VARCHAR(300) NULL,
  `CATEGORY` VARCHAR(45) NULL,
  `QUANTITY` INT NULL COMMENT 'It is really important to mark that we need to keep it simple ,so each order can have zero or many products .\nMoreover, when we receive a order that does not have any product (restaurant request delivery) we still able to process it.\nFurethermore,whenever we get a order from retailer or online store they might send us their orders details ( products etc..) ,and our system should be able to process it without any problems.',
  `ORDERED_ORDEREDID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`PRODUCT_ID`, `ORDERED_ORDEREDID`),
  CONSTRAINT `fk_PRODUCT_ORDERED1`
    FOREIGN KEY (`ORDERED_ORDEREDID`)
    REFERENCES `guard`.`ORDERED` (`ORDEREDID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `PRODUCT_ID_INDEX` ON `guard`.`PRODUCT` (`PRODUCT_ID` ASC);

CREATE INDEX `fk_PRODUCT_ORDERED1_idx` ON `guard`.`PRODUCT` (`ORDERED_ORDEREDID` ASC);


-- -----------------------------------------------------
-- Table `guard`.`BOYSFEE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `guard`.`BOYSFEE` (
  `BOYSFEE` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `CODE` INT NULL,
  `DESCRIPTION` VARCHAR(45) NULL,
  `VALUE1` DECIMAL NULL,
  `VALUE2` DECIMAL NULL,
  `VALUE3` DECIMAL NULL,
  PRIMARY KEY (`BOYSFEE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `guard`.`authority`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `guard`.`authority` ;

CREATE TABLE IF NOT EXISTS `guard`.`authority` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `guard`.`authority_seq`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `guard`.`authority_seq` ;

CREATE TABLE IF NOT EXISTS `guard`.`authority_seq` (
  `next_val` BIGINT(20) NULL DEFAULT NULL)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `guard`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `guard`.`user` ;

CREATE TABLE IF NOT EXISTS `guard`.`user` (
  `id` BIGINT(20) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `enabled` BIT(1) NOT NULL,
  `firstname` VARCHAR(50) NOT NULL,
  `lastpasswordresetdate` DATETIME NOT NULL,
  `lastname` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `UK_sb8bbouer5wak8vyiiy4pf2bx` ON `guard`.`user` (`username` ASC);


-- -----------------------------------------------------
-- Table `guard`.`user_authority`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `guard`.`user_authority` ;

CREATE TABLE IF NOT EXISTS `guard`.`user_authority` (
  `user_id` BIGINT(20) NOT NULL,
  `authority_id` BIGINT(20) NOT NULL)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `FKgvxjs381k6f48d5d2yi11uh89` ON `guard`.`user_authority` (`authority_id` ASC);

CREATE INDEX `FKpqlsjpkybgos9w2svcri7j8xy` ON `guard`.`user_authority` (`user_id` ASC);


-- -----------------------------------------------------
-- Table `guard`.`user_seq`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `guard`.`user_seq` ;

CREATE TABLE IF NOT EXISTS `guard`.`user_seq` (
  `next_val` BIGINT(20) NULL DEFAULT NULL)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
