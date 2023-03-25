drop table if exists products;
create table if not exists products(
    ean varchar(15) primary key,
    name varchar(255),
    brand varchar(255)
);

DROP TABLE IF EXISTS profiles;
CREATE TABLE IF NOT EXISTS profiles
(
    email      VARCHAR(20) NOT NULL PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name  VARCHAR(20) NOT NULL
);

INSERT INTO profiles (email, first_name, last_name)
VALUES ('johndoe@gmail.com', 'John', 'Doe');
INSERT INTO profiles (email, first_name, last_name)
VALUES ('janedoe@gmail.com', 'Jane', 'Doe');
