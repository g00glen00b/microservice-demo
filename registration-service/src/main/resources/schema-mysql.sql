CREATE TABLE state (
  id          INT(11) NOT NULL AUTO_INCREMENT,
  email       VARCHAR(128) UNIQUE NOT NULL,
  username    VARCHAR(32) UNIQUE NOT NULL,
  profile     BIT(1) NOT NULL,
  uaa         BIT(1) NOT NULL,
  created     DATETIME DEFAULT current_timestamp,
  PRIMARY KEY(id)) ENGINE=InnoDB DEFAULT CHARSET=latin1;