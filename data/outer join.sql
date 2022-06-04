create table departments(
id serial primary key,
name_dep varchar(50)
);

create table employees(
id serial primary key,
name_emp varchar(50),
departments_id int references departments(id)
);

insert into departments (name_dep) values ('department1');
insert into departments (name_dep) values ('department2');
insert into departments (name_dep) values ('department3');
insert into departments (name_dep) values ('department4');

insert into employees (name_emp, departments_id) values ('name1', 1);
insert into employees (name_emp, departments_id) values ('name2', 2);
insert into employees (name_emp, departments_id) values ('name3', 3);
insert into employees (name_emp, departments_id) values ('name4', null);
insert into employees (name_emp, departments_id) values ('name5', 5);
insert into employees (name_emp, departments_id) values ('name6', null);

select * from departments as d left join employees as e on e.employees_id = d.id;
select * from departments as d right join employees as e on e.employees_id = d.id;
select * from departments as d full join employees as e on e.employees_id = d.id;
select * from departments as d cross join employees as e on e.employees_id = d.id;

select * from departments as d left join employees as e on e.employees_id = d.id;
select * from employees as d right join departments as e on e.employees_id = d.id;

create table teens(
id serial primary key,
name varchar(50),
gender varchar(50)
);

insert into teens (name, gender) values ('name1', 'M');
insert into teens (name, gender) values ('name2', 'F');
insert into teens (name, gender) values ('name3', 'M');
insert into teens (name, gender) values ('name4', 'F');

select t1.name as a, t2.gender as b
from teens as t1 cross join teens t2;