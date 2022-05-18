CREATE SEQUENCE SEQ_TASK_ID START 10 INCREMENT 1;
CREATE TABLE IF NOT EXISTS TASK (
    id                                  BIGINT NOT NULL PRIMARY KEY,
    name                                VARCHAR (100) NOT NULL,
    comment                             VARCHAR(500),
    create_date                         TIMESTAMP NOT NULL,
    project_id                          BIGINT, /*WE CAN'T USE HERE 'NOT NULL'-CONSTRAINT*/
    FOREIGN KEY (project_id)            REFERENCES project,
    status                              VARCHAR (50)
);
