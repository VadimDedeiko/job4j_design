CREATE TABLE gender
(
    id  SERIAL PRIMARY KEY,
    sex varchar(25)
);

CREATE TABLE person
(
    id        SERIAL PRIMARY KEY,
    name      varchar(255),
    address   varchar(500),
    gender_id int references gender (id)
);

CREATE TABLE movies
(
    id   SERIAL PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE delivery
(
    id         SERIAL PRIMARY KEY,
    movies_id  integer references movies (id),
    person_id  integer references person (id)
);

INSERT INTO gender (sex) values ('Мужской'), ('Женский');

INSERT INTO person (name, address, gender_id)
VALUES ('Ольга Егорова', '1-й Казанский перулок, д. 14', 2),
       ('Иванов Сергей', 'ул. Центральная, д. 40, кв. 74', 1),
       ('Иванов Сергей', 'ул. Ленина, д. 7, кв. 130', 1);

INSERT INTO movies (name)
VALUES ('Пираты Карибского моря'),
       ('Матрица: Революция'),
       ('Человек, который изменил все'),
       ('Интерстеллар');

INSERT INTO delivery (person_id, movies_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 2);

SELECT * from delivery;

