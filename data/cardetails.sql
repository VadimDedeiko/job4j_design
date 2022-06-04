create table car(
id serial primary key,
name varchar(50)
);

create table details(
id serial primary key,
body bool,
motor bool,
transmission bool,
car_id int references car(id)
);

insert into car (name) values ('car1');
insert into car (name) values ('car2');
insert into car (name) values ('car3');
insert into car (name) values ('car4');

insert into details (body, motor, transmission, car_id) values (true,true,true,1);
insert into details (body, motor, transmission, car_id) values (true,true,true,2);
insert into details (body, motor, transmission, car_id) values (true,true,true,3);
insert into details (body, motor, transmission, car_id) values (true,true,true,4);
insert into details (body, motor, transmission, car_id) values (true,true,null,1);
insert into details (body, motor, transmission, car_id) values (true,null,true,1);

select * from details as d left outer join car as c on d.car_id = c.id;

select * from details as d 
left outer join car as c on d.car_id = c.id where d.body is null;
select * from details as d 
left outer join car as c on d.car_id = c.id where d.motor is null;
select * from details as d 
left outer join car as c on d.car_id = c.id where d.transmission is null;
select * from details as d 
left outer join car as c on d.car_id = c.id where d.car_id is null;