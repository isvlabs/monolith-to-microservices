CREATE DATABASE IF NOT EXISTS idcardmanager;
GRANT ALL PRIVILEGES ON idcardmanager.* TO idcm@localhost IDENTIFIED BY 'idcm';

USE idcardmanager;

CREATE TABLE IF NOT EXISTS employees (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  surname VARCHAR(30),
  corporate_email VARCHAR(30),
  birth_date DATE,
  card_number INT(4) UNSIGNED NOT NULL,
  photo VARCHAR(2083),
  INDEX(corporate_email)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS cards (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  card_number VARCHAR(30),
  card_type VARCHAR(30),
  issue_date DATE,
  expiry_date DATE,
  INDEX(card_number)
) engine=InnoDB;
