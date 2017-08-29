CREATE TABLE IF NOT EXISTS profile (
  username          VARCHAR(32) NOT NULL,
  email             VARCHAR(128) UNIQUE NOT NULL,
  firstname         VARCHAR(16),
  lastname          VARCHAR(16),
  bio               VARCHAR(256),
  PRIMARY KEY(username)) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS profile_avatar (
  id                INT(11) NOT NULL AUTO_INCREMENT,
  username          VARCHAR(32) UNIQUE NOT NULL,
  avatar            MEDIUMBLOB,
  mime              VARCHAR(16) NOT NULL,
  PRIMARY KEY(id)) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE profile_avatar
  ADD FOREIGN KEY (username)
  REFERENCES profile(username);