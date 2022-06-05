create table body(
id serial primary key,
name varchar(50)
);

create table motor(
id serial primary key,
name varchar(50)
);

create table transmission(
id serial primary key,
name varchar(50)
);


create table car(
id serial primary key,
name varchar(50),
body_id int references body(id),
motor_id int references motor(id),
transmission_id int references transmission(id)
);

insert into body (name)
 values ('body1'),('body2'), ('body3');
insert into motor (name)
 values ('motor1'), ('motor2'), ('motor3');
insert into transmission (name)
values ('transmission1'), ('transmission2'), ('transmission3');

insert into car (name, body_id, motor_id, transmission_id)
values ('car1', 1, 1, 1), ('car2', 2, 2, 2), ('car3', 1, 1, null);

--¬ывести список всех машин и все прив€занные к ним детали.
select k.name as c, v.name as b, motor.name as e, transmission.name as t
from
car as k left join body as v on k.body_id = v.id
left join motor on k.motor_id = motor.id
left join transmission on k.transmission_id = transmission.id;

--¬ывести отдельно детали, которые не используютс€ в машине, кузова, двигатели, коробки передач.
select body.name from
body left join car on body.id = car.body_id where car.id is null
union
select motor.name from motor left join car on motor.id = car.body_id where car.id is null
union
select transmission.name from transmission left join car on transmission.id = car.body_id
where car.id is null;