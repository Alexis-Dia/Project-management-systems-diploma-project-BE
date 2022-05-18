CREATE SEQUENCE SEQ_REPORT_ID START 10 INCREMENT 1;
CREATE TABLE IF NOT EXISTS REPORT (
    id                                  BIGINT NOT NULL PRIMARY KEY,
    name                                VARCHAR (100) NOT NULL,
    comment                             VARCHAR(500),
    create_date                         TIMESTAMP NOT NULL,
    task_id                             BIGINT, /*WE CAN'T USE HERE 'NOT NULL'-CONSTRAINT*/
    FOREIGN KEY (task_id)               REFERENCES task
);

INSERT INTO PROJECT(id, name, comment, create_date, hours, priority, budget, status) VALUES(1, 'Medical project', 'Created by Joe Dow', '2021-12-17 16:57:31.886321', 1000.0, 10, 50000.0, 'NEW');
INSERT INTO PROJECT(id, name, comment, create_date, hours, priority, budget, status) VALUES(2, 'Car auction project', 'Created by Joe Dow', '2021-12-17 16:57:31.886321', 1000.0, 10, 50000.0, 'NEW');
INSERT INTO PROJECT(id, name, comment, create_date, hours, priority, budget, status) VALUES(3, 'House renting project', 'Created by Joe Dow', '2021-12-17 16:57:31.886321', 1000.0, 10, 50000.0, 'NEW');

INSERT INTO TASK(id, name, comment, create_date, project_id, status) VALUES(1, 'Task 1', 'Created by Joe Dow', '2021-12-17 17:57:31.886321', 1, 'New');
INSERT INTO TASK(id, name, comment, create_date, project_id, status) VALUES(2, 'Task 2', 'Created by Joe Dow', '2021-12-17 17:57:31.886321', 1, 'New');
INSERT INTO TASK(id, name, comment, create_date, project_id, status) VALUES(3, 'Task 2', 'Created by Joe Dow', '2021-12-17 18:57:31.886321', 1, 'In progress');
INSERT INTO TASK(id, name, comment, create_date, project_id, status) VALUES(4, 'Task 3', 'Created by Joe Dow', '2021-12-17 19:57:31.886321', 1, 'On hold');
INSERT INTO TASK(id, name, comment, create_date, project_id, status) VALUES(5, 'Task 1', 'Created by Joe Dow', '2021-12-17 19:57:31.886321', 1, 'Finished');

INSERT INTO PROJECT_TO_USER(id, project_id, user_id) VALUES(1, 1, 1);
INSERT INTO PROJECT_TO_USER(id, project_id, user_id) VALUES(2, 1, 2);

INSERT INTO REPORT(id, name, comment, create_date, task_id) VALUES(1, 'Report 1', 'Created by Joe B', '2021-12-17 17:57:31.886321', 3);
INSERT INTO REPORT(id, name, comment, create_date, task_id) VALUES(2, 'Report 2', 'Created by Joe B', '2021-12-17 18:57:31.886321', 3);