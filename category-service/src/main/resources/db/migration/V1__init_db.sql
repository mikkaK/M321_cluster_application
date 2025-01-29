CREATE TABLE category
(
    id          UUID NOT NULL,
    name        VARCHAR(30),
    description VARCHAR(254),
    user_id     UUID NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);