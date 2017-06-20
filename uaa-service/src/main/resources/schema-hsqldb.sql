CREATE TABLE user (
  id          INTEGER IDENTITY PRIMARY KEY,
  username    VARCHAR(64) UNIQUE NOT NULL,
  password    VARCHAR(128) NOT NULL,
  enabled     BIT NOT NULL);

CREATE TABLE role (
  id          INTEGER IDENTITY PRIMARY KEY,
  username    VARCHAR(64) NOT NULL,
  role        VARCHAR(64) NOT NULL);

ALTER TABLE role
  ADD FOREIGN KEY (username)
  REFERENCES user(username);