insert into category(condition) values ('Category#1');
insert into state(condition) values ('first');
insert into state(condition) values ('second');
insert into state(condition) values ('third');

insert into role(dancer) values (true);
insert into role(dancer) values (false);

insert into rules(waltz) values ('waltz');
insert into rules(waltz) values ('breakdance');

insert into category(condition) values ('Junior');
insert into category(condition) values ('Middle');
insert into category(condition) values ('Strong');
insert into users(names, role_id) values ('Ivan', 1);
insert into users(names, role_id) values ('Roman', 1);
insert into users(names, role_id) values ('Kirill', 1);
insert into item(request, category_id, users_id, state_id) values ('Request#1', 1, 1, 1);
insert into item(request, category_id, users_id, state_id) values ('Request#2', 1, 1, 2);
insert into item(request, category_id, users_id, state_id) values ('Request#3', 1, 2, 3);

insert into role_rules(role_id, rules_id) values (1, 1);
insert into role_rules(role_id, rules_id) values (1, 1);
insert into role_rules(role_id, rules_id) values (1, 3);
insert into role_rules(role_id, rules_id) values (1, 1);

insert into comments(comment, item_id) values ('Nice dance', 1);
insert into comments(comment, item_id) values ('You can dance', 2);
insert into comments(comment, item_id) values ('You can sing', 3);

insert into attachs(file, item_id) values ('dance.avi', 1);
insert into attachs(file, item_id) values ('sing.avi', 2);