create table caliber(
id serial primary key,
caliber_kind varchar(30)
);

create table weapon(
id serial primary key,
name_weapon varchar(30),
caliber_id int references caliber(id)
);

insert into caliber(caliber_kind) values ('7,62');
insert into caliber(caliber_kind) values ('5.7');
insert into caliber(caliber_kind) values ('9');

insert into weapon(name_weapon, caliber_id) values ('kalashnikov', 1);
insert into weapon(name_weapon, caliber_id) values ('pistol', 2);
insert into weapon(name_weapon, caliber_id) values ('revolver', 2);
insert into weapon(name_weapon, caliber_id) values ('machine gun', 3);
insert into weapon(name_weapon, caliber_id) values ('Barrett REC7', 3);


select * from caliber inner join weapon on caliber.id = weapon.caliber_id;

select w.name_weapon, c.caliber_kind
from caliber as c join weapon as w on c.id = w.caliber_id;

select c.caliber_kind as Вид, w.name_weapon as Имя
from caliber as c join weapon as w on c.id = w.caliber_id;