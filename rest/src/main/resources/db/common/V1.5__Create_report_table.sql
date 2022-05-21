CREATE SEQUENCE SEQ_REPORT_ID START 10 INCREMENT 1;
CREATE TABLE IF NOT EXISTS REPORT (
    id                                  BIGINT NOT NULL PRIMARY KEY,
    name                                VARCHAR (100) NOT NULL,
    comment                             VARCHAR(500),
    create_date                         TIMESTAMP NOT NULL,
    task_id                             BIGINT, /*WE CAN'T USE HERE 'NOT NULL'-CONSTRAINT*/
    FOREIGN KEY (task_id)               REFERENCES task
);

INSERT INTO PROJECT(id, name, comment, create_date, hours, priority, budget, status) VALUES(1, 'Medical project', 'Created by admin', '2021-12-17 16:57:31.886321', 1000.0, 10, 50000.0, 'NEW');
INSERT INTO PROJECT(id, name, comment, create_date, hours, priority, budget, status) VALUES(2, 'Car auction project', 'Created by admin', '2021-12-18 16:57:31.886321', 1000.0, 10, 50000.0, 'NEW');
INSERT INTO PROJECT(id, name, comment, create_date, hours, priority, budget, status) VALUES(3, 'House renting project', 'Created by admin', '2021-12-19 16:57:31.886321', 1000.0, 10, 50000.0, 'NEW');

INSERT INTO TASK(id, name, comment, create_date, project_id, status) VALUES(1, 'Creating user-table at the database', 'Created by admin', '2021-12-17 17:57:31.886321', 1, 'NEW');
INSERT INTO TASK(id, name, comment, create_date, project_id, status) VALUES(2, 'Creating name-column at the user-table', 'Created by admin', '2021-12-18 17:57:31.886321', 1, 'NEW');
INSERT INTO TASK(id, name, comment, create_date, project_id, status) VALUES(3, 'Creating rest-api for new users', 'Created by admin', '2021-12-19 18:57:31.886321', 1, 'IN_PROGRESS');
INSERT INTO TASK(id, name, comment, create_date, project_id, status) VALUES(4, 'Change color of header', 'Created by admin', '2021-12-20 19:57:31.886321', 1, 'ON_HOLD');
INSERT INTO TASK(id, name, comment, create_date, project_id, status) VALUES(5, 'Refactoring of code', 'Created by admin', '2021-12-21 19:57:31.886321', 1, 'FINISHED');

INSERT INTO PROJECT_TO_USER(id, project_id, user_id) VALUES(1, 1, 1);
INSERT INTO PROJECT_TO_USER(id, project_id, user_id) VALUES(2, 1, 2);

INSERT INTO REPORT(id, name, comment, create_date, task_id) VALUES(1, 'Created repository-layer', 'Created by Ivan Ivanov', '2021-12-17 17:57:31.886321', 3);
INSERT INTO REPORT(id, name, comment, create_date, task_id) VALUES(2, 'Created service-layer', 'Created by Ivan Ivanov', '2021-12-17 18:57:31.886321', 3);