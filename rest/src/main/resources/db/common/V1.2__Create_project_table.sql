CREATE SEQUENCE SEQ_PROJECT_ID START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS PROJECT (
    id                                  BIGINT NOT NULL PRIMARY KEY,
    name                                VARCHAR (100) NOT NULL,
    comment                             VARCHAR(500),
    create_date                         TIMESTAMP NOT NULL
);
INSERT INTO PROJECT(id, name, comment, create_date) VALUES(1, 'Medical progect', 'Created by Joe Dow', '2021-12-17 16:57:31.886321');
