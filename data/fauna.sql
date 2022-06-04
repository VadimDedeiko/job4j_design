create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('fish', 25, '1981-11-02');
insert into fauna(name, avg_age, discovery_date) values ('whale', 12000, null);
insert into fauna(name, avg_age, discovery_date) values ('seaweed', 23000, '1938-09-02');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';