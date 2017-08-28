CREATE TABLE IF NOT EXISTS state (
  id                 INTEGER IDENTITY PRIMARY KEY,
  email              VARCHAR(128) NOT NULL UNIQUE,
  username           VARCHAR(32) NOT NULL UNIQUE,
  profile            BIT NOT NULL,
  uaa                BIT NOT NULL,
  created            TIMESTAMP(2) DEFAULT current_timestamp);
