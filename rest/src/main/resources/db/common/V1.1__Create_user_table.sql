CREATE SEQUENCE SEQ_USER_ID START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS users (
    id                                  BIGINT NOT NULL PRIMARY KEY,
    last_name                           VARCHAR (100) NOT NULL,
    first_name                          VARCHAR(100),
    patronymic                          VARCHAR(100),
    birthday                            TIMESTAMP NOT NULL,
    login                               VARCHAR(100) NOT NULL,
    password                            VARCHAR(500) NOT NULL,
    money                               FLOAT4,
    role                                VARCHAR(100),
    status                              VARCHAR(100)
);
