DROP TABLE IF EXISTS TB_USER;

CREATE TABLE TB_USER (
                         `USER_ID` VARCHAR2(20) PRIMARY KEY,
                         `USER_NM` VARCHAR2(20) NOT NULL,
                         `ACV` VARCHAR2(10)
);

DROP TABLE IF EXISTS TB_ORG;

CREATE TABLE TB_ORG (
                         `ORG_CD` VARCHAR2(20) PRIMARY KEY,
                         `ORG_NM` VARCHAR2(20) NOT NULL,
                         `ACV` VARCHAR2(10),
                         `PREN_ORG_CD` VARCHAR2(20)
);

DROP TABLE IF EXISTS TB_ORG_USERS;

CREATE TABLE TB_ORG_USERS (
                         `ORG_CD` VARCHAR2(20),
                         `USER_ID` VARCHAR2(20)
);