CREATE TABLE user
(
    id          UUID NOT NULL UNIQUE,
    name        VARCHAR(30) NOT NULL,
    surname     VARCHAR(30) NOT NULL,
    age         INTEGER NOT NULL,
    address     VARCHAR(50) NOT NULL,
    email       VARCHAR(255) NOT NULL UNIQUE ,
    CONSTRAINT pk_user PRIMARY KEY (id)
);