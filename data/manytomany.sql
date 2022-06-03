create table car(
id serial primary key,
	name varchar(10),
	position_id int references factory(id)
);

create table car_power(
id serial primary key,
	number int
);

create table car_power_car(
	id serial primary key,
	car_power_id references car_power(id)
	car_id references car(id)
);