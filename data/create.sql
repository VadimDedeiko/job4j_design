create table category(
id serial primary key,
condition varchar(50)
);

create table state(
id serial primary key,
condition varchar(10)
);

create table role(
id serial primary key,
dancer bool
);

create table rules(
id serial primary key,
waltz varchar(30)
);

create table users(
	id serial primary key,
	names varchar(30),
	role_id int  references role(id)
);

create table item(
id serial primary key,
request varchar(30),
category_id int references category(id),
users_id int references users(id),
state_id references state(id)
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

create table role_rules(
id serial primary key,
role_id int references role(id),
rules_id int references rules(id)
);