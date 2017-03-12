CREATE TABLE IF NOT EXISTS profile (
  username          VARCHAR(32) NOT NULL IDENTITY PRIMARY KEY,
  firstname         VARCHAR(16) NOT NULL,
  lastname          VARCHAR(16) NOT NULL,
  bio               VARCHAR(256) NOT NULL,
  email             VARCHAR(64));

CREATE TABLE IF NOT EXISTS profile_avatar (
  id                INTEGER IDENTITY PRIMARY KEY,
  username          VARCHAR(32) UNIQUE NOT NULL,
  avatar            BINARY LARGE OBJECT(1M),
  mime              VARCHAR(16) NOT NULL);

ALTER TABLE profile_avatar
  ADD FOREIGN KEY (username)
  REFERENCES profile(username);