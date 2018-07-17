CREATE DATABASE IF NOT EXISTS idcardmanager;
GRANT ALL PRIVILEGES ON idcardmanager.* TO idcm@localhost IDENTIFIED BY 'idcm';

USE idcardmanager;

CREATE TABLE IF NOT EXISTS employees (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  firstName VARCHAR(30),
  surname VARCHAR(30),
  corporateEmail VARCHAR(30),
  birthDate DATE,
  cardNumber INT(4) UNSIGNED NOT NULL,
  photo VARCHAR(2083),
  INDEX(corporate_email)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS cards (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  cardNumber VARCHAR(30),
  cardType VARCHAR(30),
  issueDate DATE,
  expiryDate DATE,
  INDEX(card_number)
) engine=InnoDB;
