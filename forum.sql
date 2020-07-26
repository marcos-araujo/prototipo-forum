CREATE DATABASE IF NOT EXISTS FORUM;

DROP TABLE IF EXISTS FORUM.PALAVRA;

CREATE TABLE FORUM.PALAVRA(
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  PALAVRA varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS FORUM.THREAD;

CREATE TABLE FORUM.TOPICO(
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  TEXTO varchar(255) NOT NULL,
  ID_PAI bigint(20) NOT NULL DEFAULT '0',
  DATA date NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;