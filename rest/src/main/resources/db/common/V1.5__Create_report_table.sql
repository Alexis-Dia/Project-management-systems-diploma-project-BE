CREATE SEQUENCE SEQ_REPORT_ID START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS REPORT (
    id                                  BIGINT NOT NULL PRIMARY KEY,
    name                                VARCHAR (100) NOT NULL,
    comment                             VARCHAR(500),
    create_date                         TIMESTAMP NOT NULL,
    task_id                             BIGINT NOT NULL,
    FOREIGN KEY (task_id)               REFERENCES task
);

INSERT INTO REPORT(id, name, comment, create_date, task_id) VALUES(1, 'Report 1', 'Created by Joe B', '2021-12-17 17:57:31.886321', 1);
INSERT INTO REPORT(id, name, comment, create_date, task_id) VALUES(2, 'Report 2', 'Created by Joe B', '2021-12-17 18:57:31.886321', 1);