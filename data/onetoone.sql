create table car(
id serial primary key,
	name varchar(10),
	position_id int references factory(id)
);

create motor_serial_number(
	id serial primary key,
	serial int references car(id) unique,
);