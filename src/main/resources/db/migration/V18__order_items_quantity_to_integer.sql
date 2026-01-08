alter table order_items
    alter column quantity type int using quantity::int;

