create table cars (
                      id serial primary key,
                      brand varchar(255),
                      model varchar(255),
                      price integer
);

insert into cars (brand, model, price) VALUES ('Audi', 'A4', 50000);
insert into cars (brand, model, price) VALUES ('Audi', 'RS7', 115000);
insert into cars (brand, model, price) VALUES ('Lexus', 'IS250', 60000);
insert into cars (brand, model, price) VALUES ('Honda', 'CR-V', 62000);
insert into cars (brand, model, price) VALUES ('Mercedes-Benz', 'E300', 72000);

begin transaction;

insert into cars (brand, model, price) VALUES ('Lada', 'XRay', 60000);

commit transaction;

select * from cars;

begin transaction;

delete from cars;

drop table products;

rollback transaction;

select * from cars;

begin transaction;

insert into cars (brand, model, price) VALUES ('Cherry', 'Amulet', 20000);

savepoint first_savepoint;

delete from cars where price = 60000;
update cars set price = 100000 where brand = 'Audi';

select * from cars;

rollback to first_savepoint;

commit transaction;

