create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

--Первый триггер с функцией начисления налога для таблицы "products"
--срабатывает после вставки данных, действует на запрос (statement уровень)
create or replace function tax()
    returns trigger as
$$
begin
    update products
    set price = price + price * 0.2
    where id = (select id from inserted);
    return new;
end;
$$
    LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert
    on products
    referencing new table as inserted
    for each statement
execute procedure tax();

--Второй триггер с функцией начисления налога для таблицы "products"
--срабатывает до вставки данных, действует на запись (row уровень)
create or replace function tax_befo()
    returns trigger as
$$
BEGIN
    NEW.price = NEW.price + NEW.price * 0.2;
    return NEW;
END;
$$
    LANGUAGE 'plpgsql';

create trigger tax_befo_insert
    before insert
    on products
    for each row
execute procedure tax_befo();

--Третий триггер с функцией занесения данных в таблицу "history_of_price" при вставке данных в таблицу "products"
--срабатывает после вставки данных, действует на запись (row уровень)
create or replace function history_after()
    returns trigger as
$$
BEGIN
    insert into history_of_price(name, price, date)
    values(NEW.name, NEW.price, current_date);
    return NEW;
END;
$$
    LANGUAGE 'plpgsql';

create trigger history_trigger
    after insert
    on products
    for each row
execute procedure history_after();