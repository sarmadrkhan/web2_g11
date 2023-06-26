drop table if exists messages;
drop table if exists tickets;
drop table if exists products;
drop table if exists profiles;

create table if not exists products
(
    ean   varchar(15) primary key,
    name  varchar(255),
    brand varchar(255)
);

DROP TABLE IF EXISTS profiles;
CREATE TABLE IF NOT EXISTS profiles
(
    email      VARCHAR(20) NOT NULL PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name  VARCHAR(20) NOT NULL
);


create table if not exists TICKETS
(
    tid              serial primary key,
    uid              varchar(20) not null,
    ean              varchar(15) not null,
    eid              varchar(20) not null,
    title            text        not null,
    id_chat          integer,
    description      text        not null,
    date_open        timestamp   not null,
    date_last_update timestamp   not null,
    priority         integer     not null,
    status           text        not null,
    foreign key (uid) references profiles (email),
    foreign key (ean) references products (ean),
    foreign key (eid) references profiles (email)
);

DROP TABLE IF EXISTS Messages;
CREATE TABLE IF NOT EXISTS Messages
(
    mid       serial primary key,
    id_chat   integer     not null,
    id_sender varchar(20) not null,
    timestamp timestamp   not null,
    content   text        not null,
    foreign key (id_sender) references profiles (email)
);

INSERT INTO products (ean, name, brand)
VALUES ('1234567898765', 'Product 1', 'Brand 1'),
       ('1234567898766', 'Product 2', 'Brand 1');

INSERT INTO profiles (email, first_name, last_name)
VALUES ('johndoe@gmail.com', 'John', 'Doe'),
       ('janedoe@gmail.com', 'Jane', 'Doe');

