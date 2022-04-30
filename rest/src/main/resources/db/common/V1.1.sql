CREATE SEQUENCE SEQ_PROJECT_ID START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS project (
    id                                  BIGINT NOT NULL PRIMARY KEY,
    name                                VARCHAR (100) NOT NULL,
    comment                             VARCHAR(500),
    create_date                         TIMESTAMP NOT NULL
);

CREATE SEQUENCE SEQ_TASK_ID START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS task (
    id                                  BIGINT NOT NULL PRIMARY KEY,
    project_id                          BIGINT NOT NULL,
    FOREIGN KEY (project_id)            REFERENCES project,
    name                                VARCHAR (100) NOT NULL,
    comment                             VARCHAR(500),
    create_date                         TIMESTAMP NOT NULL
);