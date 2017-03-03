CREATE TABLE IF NOT EXISTS article (
  id                 INTEGER IDENTITY PRIMARY KEY,
  title              VARCHAR(32) NOT NULL,
  text               VARCHAR(4096),
  header             INTEGER,
  user               INTEGER,
  slug               VARCHAR(16) NOT NULL UNIQUE,
  created            TIMESTAMP(2) DEFAULT current_timestamp);