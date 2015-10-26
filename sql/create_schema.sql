CREATE USER admin@localhost identified BY 'admin';
GRANT usage ON *.* TO admin@localhost identified BY 'admin';
CREATE DATABASE IF NOT EXISTS contact;
GRANT ALL privileges ON contact.* TO admin@localhost;
USE contact;
CREATE TABLE IF NOT EXISTS `contact`.`Person` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `person_name` VARCHAR(45) NOT NULL,
  `person_surname` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone_number` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`person_id`))
ENGINE = InnoDB;
