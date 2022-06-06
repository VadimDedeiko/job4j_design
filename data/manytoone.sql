create table factory(
id serial primary key,
		car text
);

create table clothes(
id serial primary key,
	names varchar(10),
	factory_id int references factory(id)
);