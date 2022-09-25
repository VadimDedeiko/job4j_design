create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

--������ ������� � �������� ���������� ������ ��� ������� "products"
--����������� ����� ������� ������, ��������� �� ������ (statement �������)
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

--������ ������� � �������� ���������� ������ ��� ������� "products"
--����������� �� ������� ������, ��������� �� ������ (row �������)
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

--������ ������� � �������� ��������� ������ � ������� "history_of_price" ��� ������� ������ � ������� "products"
--����������� ����� ������� ������, ��������� �� ������ (row �������)
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