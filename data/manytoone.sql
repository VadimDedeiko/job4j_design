create table factory(
id serial primary key,
		car text
);

create table car(
id serial primary key,
	names varchar(10),
	position_id int references factory(id)
);