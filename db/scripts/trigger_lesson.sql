create table products (
                          id serial primary key,
                          name varchar(50),
                          producer varchar(50),
                          count integer default 0,
                          price integer
);

create trigger discount_trigger
    after insert
    on products
    for each row
execute procedure discount();

create or replace function discount()
    returns trigger as
$$
BEGIN
    update products
    set price = price - price * 0.2
    where count <= 5 AND id = new.id;
    return NEW;
END;
$$
    LANGUAGE 'plpgsql';

insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 3, 50);

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
execute procedure tax();

create or replace function tax()
    returns trigger as
$$
BEGIN
    update products
    set price = price - price * 0.2
    where id = (select id from inserted) and count <= 5;
    return new;
END;
$$
    LANGUAGE 'plpgsql';

drop trigger discount_trigger on products;

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
execute procedure tax();

create or replace function tax()
    returns trigger as
$$
BEGIN
    update products
    set price = price - price * 0.2
    where id = (select id from inserted) and count <= 5;
    return new;
END;
$$
    LANGUAGE 'plpgsql';