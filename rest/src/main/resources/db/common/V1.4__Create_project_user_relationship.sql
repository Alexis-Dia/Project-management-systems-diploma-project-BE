-- ALTER TABLE USERS ADD COLUMN project_id BIGINT NOT NULL;
-- ALTER TABLE USERS ADD CONSTRAINT fk_project_id FOREIGN KEY (project_id) REFERENCES project;

-- ALTER TABLE PROJECT ADD COLUMN user_id BIGINT NOT NULL;
-- ALTER TABLE PROJECT ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users;

CREATE SEQUENCE SEQ_PROJECT_TO_USER_ID START 1 INCREMENT 1;
CREATE TABLE IF NOT EXISTS PROJECT_TO_USER (
    id                                  BIGINT,
    project_id                          BIGINT NOT NULL,
    FOREIGN KEY (project_id)            REFERENCES project,
    user_id                             BIGINT NOT NULL,
    FOREIGN KEY (user_id)               REFERENCES users,
    UNIQUE (project_id, user_id)
);

/*PASSWORD - 12345678*/
INSERT INTO USERS (id, last_name, first_name, patronymic, birthday, login, password, money, role, status)
VALUES (1,
        'Aleksey',
        'Alekseev',
        'Alekseevich',
        '2022-05-16 01:31:00.000000',
        'admin@gmail.com',
        '$2a$10$OOCcNwK5pLjah/ApkbvZfOsD0tzW37T6hbRY2PdbTCGAf0f1qiWT.',
        0,
        'ADMIN',
        'FREE'
       );
INSERT INTO USERS (id, last_name, first_name, patronymic, birthday, login, password, money, role, status)
VALUES (2,
        'Ivan',
        'Ivanov',
        'Ivanovich',
        '2022-05-16 01:31:00.000000',
        'ivanov@gmail.com',
        '$2a$10$OOCcNwK5pLjah/ApkbvZfOsD0tzW37T6hbRY2PdbTCGAf0f1qiWT.',
        0,
        'USER',
        'BUSY'
);
INSERT INTO USERS (id, last_name, first_name, patronymic, birthday, login, password, money, role, status)
VALUES (3,
        'Sergey',
        'Sergeyev',
        'Sergeyevich',
        '2022-05-16 01:31:00.000000',
        'sergeyev@gmail.com',
        '$2a$10$OOCcNwK5pLjah/ApkbvZfOsD0tzW37T6hbRY2PdbTCGAf0f1qiWT.',
        0,
        'USER',
        'FREE'
);

INSERT INTO USERS (id, last_name, first_name, patronymic, birthday, login, password, money, role, status)
VALUES (4,
        'Petr',
        'Petrov',
        'Petrovich',
        '2022-05-16 01:31:00.000000',
        'petrov@gmail.com',
        '$2a$10$OOCcNwK5pLjah/ApkbvZfOsD0tzW37T6hbRY2PdbTCGAf0f1qiWT.',
        0,
        'USER',
        'FREE'
);







