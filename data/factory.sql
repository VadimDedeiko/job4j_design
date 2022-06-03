create table factory(
id serial primary key,
	car text,
	color text,
	motor varchar(10)
);
insert into factory(
	car, color, motor) values('Toyota', 'Gray', '1ND-TV');
update factory set car = 'Mercedes';
update factory set motor = 'V6';
select * from factory;
delete from factory;