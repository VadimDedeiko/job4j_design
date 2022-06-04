create table tipen(
id serial primary key,
name varchar(50)
);

create table product(
id serial primary key,
name varchar(50),
type_id int references tipen(id),
expired_date date,
price float
);

insert into tipen (name) values ('Сыр');
insert into tipen (name) values ('Молоко');
insert into tipen (name) values ('Хлеб');

insert into product (name, type_id, expired_date, price) values ('Молоко', 2, '2022-06-05', 30);
insert into product (name, type_id, expired_date, price) values ('Молоко сгущенное', 2, '2022-06-05', 30);
insert into product (name, type_id, expired_date, price) values ('Молоко козье', 2, '2022-06-05', 30);
insert into product (name, type_id, expired_date, price) values ('Сыр', 1, '2022-06-05', 50);
insert into product (name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2022-06-07', 30);
insert into product (name, type_id, expired_date, price) values ('мороженое', 2, '2022-06-07', 30);
insert into product (name, type_id, expired_date, price) values ('Торт', 2, '2022-06-07', 300);
insert into product (name, type_id, expired_date, price) values ('Хлеб', 3, '2022-06-07', 40);

select * from product where name = 'Сыр';
select * from product where name like '%мороженое%';
select * from product where expired_date > current_date;

select name, max(price) from product;


select count(name) as Количество
from tipen inner join product as p on p.type_id = tipen.id;

select t.name, p.name
from tipen as t inner join product as p on p.type_id = t.id
where t.name = 'Сыр' and t.name = 'Молоко';

select t.name as Тип_продукта, count(t.name) as Количество
from product as p inner join tipen as t on p.type_id = t.id
group by t.name
having count(p.name) < 10;

select p.name, tipen.name
from product as p inner join tipen on p.type_id = tipen.id;