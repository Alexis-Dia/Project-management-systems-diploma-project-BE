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
/*PASSWORD - 12345678*/
INSERT INTO USERS (id, last_name, first_name, patronymic, birthday, login, password, money, role, status)
VALUES (1,
        'Alexey',
        'Ivanov',
        'Nikolaevich',
        '2022-05-16 01:31:00.000000',
        'ivanov@gmail.com',
        '$2a$10$OOCcNwK5pLjah/ApkbvZfOsD0tzW37T6hbRY2PdbTCGAf0f1qiWT.',
        0,
        'USER',
        'FREE'
);