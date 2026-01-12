alter table order_items
    drop constraint order_items_orders_fk;

alter table order_items
    add constraint order_items_orders_fk
        foreign key (order_id) references orders
            on delete set null;

