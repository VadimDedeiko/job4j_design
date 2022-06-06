create table car(
id serial primary key,
names varchar(10)
);

create table car_power(
id serial primary key,
number varchar(10)
);

create table car_power_car(
	id serial primary key,
	car_power_id int references car_power(id),
	car_id int references car(id)
);