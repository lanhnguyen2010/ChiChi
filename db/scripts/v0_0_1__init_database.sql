DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  PROFILE_ID VARCHAR(100) NOT NULL,
  FIRST_NAME varchar(45) DEFAULT NULL,
  LAST_NAME varchar(45) DEFAULT NULL,
  FULL_NAME VARCHAR(100) DEFAULT NULL,
  EMAIL varchar(100) DEFAULT NULL,
  PICTURE_URL TEXT DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE job(
  ID BIGINT NOT NULL,
  TITLE VARCHAR(200) NOT NULL,
  DESCRIPTION TEXT DEFAULT NULL,
  TOTAL_HOURS BIGINT NOT NULL,
  START_TIME DATETIME DEFAULT NULL,
  DEAD_LINE DATETIME DEFAULT NULL,
  POSTED_BY BIGINT NOT NULL,
  POSTED_AT DATETIME DEFAULT NULL,
  LOGO BLOB DEFAULT NULL,
  RESOUCES TEXT DEFAULT NULL,
  PICKED_BY BIGINT DEFAULT NULL,
  PICKED_AT DATETIME DEFAULT NULL,
  REVIEW_BY BIGINT DEFAULT NULL,
  RESULT_HOURS BIGINT DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
