CREATE TABLE IF NOT EXISTS article (
  id                 INT(11) NOT NULL AUTO_INCREMENT,
  title              VARCHAR(64) NOT NULL,
  text               VARCHAR(16384),
  username           VARCHAR(32) NOT NULL,
  slug               VARCHAR(64) NOT NULL UNIQUE,
  created            DATETIME DEFAULT current_timestamp,
  PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=latin1;