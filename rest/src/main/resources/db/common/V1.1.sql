CREATE SEQUENCE SEQ_PROJECT_ID START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS PROJECT (
    id                                  BIGINT NOT NULL PRIMARY KEY,
    name                                VARCHAR (100) NOT NULL,
    comment                             VARCHAR(500),
    create_date                         TIMESTAMP NOT NULL
);
INSERT INTO PROJECT(id, name, comment, create_date) VALUES(1, 'Medical progect', 'Created by Joe Dow', '2021-12-17 16:57:31.886321');

CREATE SEQUENCE SEQ_TASK_ID START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS TASK (
    id                                  BIGINT NOT NULL PRIMARY KEY,
    project_id                          BIGINT NOT NULL,
    FOREIGN KEY (project_id)            REFERENCES project,
    name                                VARCHAR (100) NOT NULL,
    comment                             VARCHAR(500),
    create_date                         TIMESTAMP NOT NULL
);
INSERT INTO TASK(id, project_id, name, comment, create_date) VALUES(1, 1, 'Task 1', 'Created by Joe Dow', '2021-12-17 17:57:31.886321');
INSERT INTO TASK(id, project_id, name, comment, create_date) VALUES(2, 1, 'Task 2', 'Created by Joe Dow', '2021-12-17 18:57:31.886321');

CREATE SEQUENCE SEQ_USER_ID START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS users (
    id                                  BIGINT NOT NULL PRIMARY KEY,
    last_name                           VARCHAR (100) NOT NULL,
    first_name                          VARCHAR(100),
    patronymic                          VARCHAR(100),
    birthday                            TIMESTAMP NOT NULL,
    login                               VARCHAR(100) NOT NULL,
    password                            VARCHAR(500) NOT NULL,
    money                               float4,
    role                                VARCHAR(100),
    status                         VARCHAR(100)
);