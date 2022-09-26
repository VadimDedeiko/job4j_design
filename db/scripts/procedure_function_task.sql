create or replace procedure delete_id_products(i_id integer)
    language 'plpgsql'
as
$$
BEGIN
    DELETE FROM products WHERE ID = i_id;
END
$$;

call delete_id_products(2);

create or replace procedure delete_zero_count()
    language 'plpgsql'
as
$$
BEGIN
    delete from products WHERE count = 0;
END;
$$;

call delete_zero_count();

call insert_data('product_10', 'producer_10', 0, 50);

call insert_data('product_3', 'producer_3', 8, 115);

create or replace function f_delete_id_products(i_id integer)
    returns void
    language 'plpgsql'
as
$$
begin
    DELETE FROM products WHERE ID = i_id;
end;
$$;

select f_delete_id_products(3);

create or replace function f_delete_zero_count()
    returns void
    language 'plpgsql'
as
$$
begin
    delete from products WHERE count = 0;
end;
$$;

insert into products (name, producer, count, price) VALUES ('Product100', 'Product100', 0, 100);

select f_delete_zero_count();