DROP SCHEMA IF EXISTS `weathersaurus`;
CREATE SCHEMA IF NOT EXISTS `weathersaurus` DEFAULT CHARACTER SET latin1 ;
USE `weathersaurus` ;

-- -----------------------------------------------------
-- Table `weathersaurus`.`users`
-- -----------------------------------------------------

CREATE TABLE  `weathersaurus`.`users` (
  `USERID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` VARCHAR(45) NULL DEFAULT NULL,
  `LASTNAME` VARCHAR(45) NULL DEFAULT NULL,
  `EMAILADDRESS` VARCHAR(64) NULL DEFAULT NULL,
  `GENDER` VARCHAR(20) NULL DEFAULT NULL,
  `AGE` BIGINT(20) NULL DEFAULT NULL,
  `STATE` VARCHAR(64) NULL DEFAULT NULL,
  `USERNAME` VARCHAR(20)  NULL DEFAULT NULL,
  `PASSWORD` VARCHAR(64) NULL DEFAULT NULL,
  PRIMARY KEY (`USERID`));

-- -----------------------------------------------------
-- Table `weathersaurus`.`weatherdaily`
-- -----------------------------------------------------
CREATE TABLE `weathersaurus`.`weatherdaily` (
  `LOCATION_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` VARCHAR(20) NOT NULL,
  `TEMPERATURE` FLOAT NOT NULL,
  `HUMIDITY` FLOAT NOT NULL,
  `WINDSPEED` FLOAT NOT NULL,
  `DAY` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`LOCATION_ID`));



