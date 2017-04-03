CREATE TABLE IF NOT EXISTS article (
  id                 INTEGER IDENTITY PRIMARY KEY,
  title              VARCHAR(64) NOT NULL,
  text               VARCHAR(16384),
  username           VARCHAR(32) NOT NULL,
  slug               VARCHAR(64) NOT NULL UNIQUE,
  created            TIMESTAMP(2) DEFAULT current_timestamp);