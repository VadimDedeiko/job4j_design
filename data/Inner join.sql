create table weapon(
id serial primary key,
name_weapon varchar(30)
);

create table variety(
id serial primary key,
variety_kind varchar(30),
weapon_id int references weapon(id)
);
insert into weapon(name_weapon) values ('kalashnikov');
insert into weapon(name_weapon) values ('pistol');
insert into weapon(name_weapon) values ('machine gun');
insert into variety(variety_kind, weapon_id) values ('30 bullets', 1);
insert into variety(variety_kind, weapon_id) values ('10 bullets', 2);
insert into variety(variety_kind, weapon_id) values ('100 bullets', 3);
insert into variety(variety_kind) values ('50 bullets');

select from variety inner join weapon on variety.weapon_id = weapon.id;

select n.name_weapon, v.variety_kind
from variety as v join weapon as n on v.weapon_id = n.id;

select v.variety_kind as Вид, w.name_weapon as Имя
from variety as v join weapon as w on v.weapon_id = w.id;