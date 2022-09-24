create table students (
                          id serial primary key,
                          name varchar(50)
);

insert into students (name) values ('���� ������');
insert into students (name) values ('���� ������');
create table authors (
                         id serial primary key,
                         name varchar(50)
);

insert into authors (name) values ('��������� ������');
insert into authors (name) values ('������� ������');
create table books (
                       id serial primary key,
                       name varchar(200),
                       author_id integer references authors(id)
);

insert into books (name, author_id) values ('������� ������', 1);
insert into books (name, author_id) values ('����������� �����', 1);
insert into books (name, author_id) values ('����������', 1);
insert into books (name, author_id) values ('������� ����', 2);
insert into books (name, author_id) values ('���', 2);

create table orders (
                        id serial primary key,
                        active boolean default true,
                        book_id integer references books(id),
                        student_id integer references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);

create view popular_alexandr as
select b.name as books, a.name as author, count(s.name) as buy
from books as b
         left join authors a on b.author_id = a.id
         left join orders o on o.book_id = b.id
         left join students s on o.student_id = s.id
where a.name like '���������%'
group by (b.name, a.name)
ORDER BY buy DESC;

select * from popular_alexandr;