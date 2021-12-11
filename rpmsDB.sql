CREATE DATABASE RPMSdb;

CREATE TABLE `RPMSdb`.`Renters` (
  `rID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `notify` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`rID`)
);
    
CREATE TABLE `RPMSdb`.`Managers` (
  `mID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`mID`)
);

CREATE TABLE `RPMSdb`.`Landlords` (
  `lID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`lID`)
);

CREATE TABLE `RPMSdb`.`PropertyFee` (
  `pfID` INT NOT NULL AUTO_INCREMENT,
  `period` INT NOT NULL,
  `fee` DOUBLE NOT NULL,
  PRIMARY KEY (`pfID`)
);

CREATE TABLE `RPMSdb`.`Search_Criteria` (
  `cID` INT NOT NULL AUTO_INCREMENT,
  `p_type` VARCHAR(45),
  `bath_min` INT,
  `bath_max` INT,
  `bed_min` INT,
  `bed_max` INT,
  `furnished` VARCHAR(45),
  `city_q` VARCHAR(45),
  `price_min` DOUBLE,
  `price_max` DOUBLE,
  `renter` INT NOT NULL,
  PRIMARY KEY (`cID`),
  INDEX `search_criteria_ibfk_1_idx` (`renter` ASC) VISIBLE,
  CONSTRAINT `search_criteria_ibfk_1`
    FOREIGN KEY (`renter`)
    REFERENCES `RPMSdb`.`Renters` (`rID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `RPMSdb`.`Properties` (
  `pID` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `p_type` VARCHAR(45) NOT NULL,
  `bathrooms` INT NOT NULL,
  `bedrooms` INT NOT NULL,
  `furnished` VARCHAR(45) NOT NULL,
  `city_quadrant` VARCHAR(3) NOT NULL,
  `price` DOUBLE NOT NULL,
  `state_of_listing` VARCHAR(45),
  `landlord` INT NOT NULL,
  PRIMARY KEY (`pID`),
  INDEX `properties_ibfk_1_idx` (`landlord` ASC) VISIBLE,
  CONSTRAINT `properties_ibfk_1`
    FOREIGN KEY (`landlord`)
    REFERENCES `RPMSdb`.`Landlords` (`lID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `RPMSdb`.`Emails` (
  `eID` INT NOT NULL AUTO_INCREMENT,
  `landlord`INT NOT NULL,
  `subject` VARCHAR(45) NOT NULL,
  `body` VARCHAR(45) NOT NULL,
  `property` INT NOT NULL,
  PRIMARY KEY (`eID`),
  INDEX `emails_ibfk_1_idx` (`landlord` ASC) VISIBLE,
  INDEX `emails_ibfk_2_idx` (`property` ASC) VISIBLE,
  CONSTRAINT `emails_ibfk_1`
    FOREIGN KEY (`landlord`)
    REFERENCES `RPMSdb`.`Landlords` (`lID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `emails_ibfk_2`
    FOREIGN KEY (`property`)
    REFERENCES `RPMSdb`.`Properties` (`pID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);