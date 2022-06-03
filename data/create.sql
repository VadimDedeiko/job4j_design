create table item(
id serial primary key,
request varchar(30)
);

create table comments(
id serial primary key,
comment varchar(30),
item_id int references item(id)
);

create table attachs(
id serial primary key,
file varchar(50),
item_id int references item(id)
);

create table category(
id serial primary key,
condition varchar(50),
item_id int references item(id)
);

create table state(
id serial primary key,
condition varchar(10),
item_id int references item(id)
);

create table role(
id serial primary key,
dancer bool
);

create table users(
	id serial primary key,
	names varchar(30),
	users_id int  references role(id),
	users_item_id int references item(id)
);

create table rules(
id serial primary key,
waltz varchar(30)
);

create table role_rules(
id serial primary key,
role_id int references role(id),
rules_id int references rules(id)
);