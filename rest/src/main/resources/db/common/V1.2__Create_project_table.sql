CREATE SEQUENCE SEQ_PROJECT_ID START 10 INCREMENT 1;
CREATE TABLE IF NOT EXISTS PROJECT (
    id                                  BIGINT NOT NULL PRIMARY KEY,
    name                                VARCHAR (100) NOT NULL,
    comment                             VARCHAR(500),
    create_date                         TIMESTAMP NOT NULL,
    hours                               FLOAT4,
    priority                            INT NOT NULL,
    budget                              FLOAT4,
    status                              VARCHAR (50)
);
