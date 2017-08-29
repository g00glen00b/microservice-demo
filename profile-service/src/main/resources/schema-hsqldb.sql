CREATE TABLE IF NOT EXISTS profile (
  username          VARCHAR(32) NOT NULL PRIMARY KEY,
  email             VARCHAR(128) UNIQUE NOT NULL,
  firstname         VARCHAR(16),
  lastname          VARCHAR(16),
  bio               VARCHAR(256));

CREATE TABLE IF NOT EXISTS profile_avatar (
  id                INTEGER IDENTITY PRIMARY KEY,
  username          VARCHAR(32) UNIQUE NOT NULL,
  avatar            BINARY LARGE OBJECT(1M),
  mime              VARCHAR(16) NOT NULL);

ALTER TABLE profile_avatar
  ADD FOREIGN KEY (username)
  REFERENCES profile(username);