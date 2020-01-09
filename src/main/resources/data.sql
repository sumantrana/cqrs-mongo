DROP TABLE IF EXISTS TB_USER;

CREATE TABLE TB_USER (
                         `USER_ID` VARCHAR2(20),
                         `USER_NM` VARCHAR2(20) NOT NULL,
                         `ACV` VARCHAR2(10),
                         PRIMARY KEY (USER_ID)
);

DROP TABLE IF EXISTS TB_ORG;

CREATE TABLE TB_ORG (
                         `ORG_CD` VARCHAR2(20),
                         `ORG_NM` VARCHAR2(20) NOT NULL,
                         `ACV` VARCHAR2(10),
                         `PREN_ORG_CD` VARCHAR2(20)
                         PRIMARY KEY (ORG_CD)
);

DROP TABLE IF EXISTS TB_ORG_USERS;

CREATE TABLE TB_ORG_USERS (
                         `ID` NUMBER IDENTITY PRIMARY KEY,
                         `ORG_CD` VARCHAR2(20),
                         `USER_ID` VARCHAR2(20) NOT NULL,
);