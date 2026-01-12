alter table order_items
    drop constraint order_items_products_fk;

alter table order_items
    add constraint order_items_products_fk
        foreign key (product_id) references products
            on delete cascade;

