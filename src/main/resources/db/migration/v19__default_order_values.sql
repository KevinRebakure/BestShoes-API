alter table orders
    alter column placed_on set default current_date;

alter table orders
    alter column total_amount set default 0;

