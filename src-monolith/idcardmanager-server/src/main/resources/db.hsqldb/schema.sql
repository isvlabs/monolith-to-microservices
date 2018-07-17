DROP TABLE cards IF EXISTS;
DROP TABLE employees IF EXISTS;

CREATE TABLE employees (
  id         INTEGER IDENTITY PRIMARY KEY,
  firstName VARCHAR(30),
  surname  VARCHAR(30),
  corporateEmail VARCHAR(30),
  birthDate DATE,
  cardNumber INTEGER NOT NULL,
  photo VARCHAR(2083)
);
CREATE INDEX employees_corporate_email ON cards (corporate_email);

CREATE TABLE cards (
  id         INTEGER IDENTITY PRIMARY KEY,
  cardNumber VARCHAR(30),
  cardType  VARCHAR(30),
  issueDate DATE,
  expiryDate DATE,
);
CREATE INDEX cards_last_name ON cards (last_name);
