drop table if exists products;
create table if not exists products(
    ean varchar(15) primary key,
    name varchar(255),
    brand varchar(255)
);
INSERT INTO  products (ean, name, brand)
VALUES (4935531465706,'product 1', 'brand 1');
INSERT INTO  products (ean, name, brand)
VALUES (4935531465707,'product 2', 'brand 2');
INSERT INTO  products (ean, name, brand)
VALUES (4935531465708,'product 3', 'brand 3');
INSERT INTO  products (ean, name, brand)
VALUES (4935531465709,'product 4', 'brand 4');
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
