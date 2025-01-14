CREATE TABLE task
(
    id          UUID NOT NULL,
    name        VARCHAR(30),
    description VARCHAR(254),
    done        BOOLEAN,
    category    VARCHAR(255),
    priority    VARCHAR(255),
    CONSTRAINT pk_task PRIMARY KEY (id)
);