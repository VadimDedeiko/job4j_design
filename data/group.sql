create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('smartphone', 20000);
insert into devices(name, price) values ('TV', 30000);
insert into devices(name, price) values ('PC', 60000);
insert into devices(name, price) values ('laptop', 55000);

insert into people(name) values ('Сергей'), ('Иван'), ('Татьяна'), ('Александр');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (3, 3);
insert into devices_people(device_id, people_id) values (4, 3);
insert into devices_people(device_id, people_id) values (4, 4);



select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as dp
inner join
devices as d
inner join
people as p
on dp.people_id = d.id
group by p.name;

select p.name, avg(d.price)
from devices_people as dp
inner join
devices as d
inner join
people as p
on dp.people_id = d.id
group by p.name
having avg(d.price) > 5000;