-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE person
(
    id         INT NOT NULL AUTO_INCREMENT,
    name       VARCHAR(250),
    birth_date LONG(250),
    email      VARCHAR(250),
    phone      VARCHAR(20)
);

CREATE TABLE user
(
    id       INT NOT NULL AUTO_INCREMENT,
    name     VARCHAR(250) UNIQUE,
    password VARCHAR(250)
);

-- insert some students
insert into person (name, birth_date, email, phone)
VALUES ('Alexander Grinevsky', -2820873600000, 'not yet invented', 'unknown');

insert into person (name, birth_date, email, phone)
VALUES ('Alex Grin', -2820873600000, 'not yet invented', 'unknown');

-- insert users
insert into user (name, password)
VALUES ('admin', 'qwe123');

insert into user (name, password)
VALUES ('student', 'qwe123');

insert into user (name, password)
VALUES ('qwe', 'qwe');