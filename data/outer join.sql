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
insert into employees (name_emp, departments_id) values ('name5', 4);
insert into employees (name_emp, departments_id) values ('name6', null);

select * from departments as d left join employees as e on e.departments_id = d.id;
select * from departments as d right join employees as e on e.departments_id = d.id;
select * from departments as d full join employees as e on e.departments_id = d.id;
select * from departments as d cross join employees as e;


select * from departments as d right join employees as e on e.departments_id = d.id;
select d.id, d.name_dep, e.id, e.name_emp, e.departments_id  from employees as e
left join departments  as d on e.departments_id = d.id;

select * from departments as d  right join employees as e on e.departments_id = d.id
where d.id is null;

create table teens(
id serial primary key,
name varchar(50) unique,
gender varchar(50)
);

insert into teens (name, gender) values ('name1', 'M');
insert into teens (name, gender) values ('name2', 'F');
insert into teens (name, gender) values ('name3', 'M');
insert into teens (name, gender) values ('name4', 'F');

select t1.name, t1.gender, t2.name, t2.gender from teens as t1 cross join teens t2
where t1.gender<>t2.gender and t1.name<>t2.name;