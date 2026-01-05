alter table order_items
    drop constraint order_items_orders_fk;

alter table order_items
    add constraint order_items_orders_fk
        foreign key (order_id) references orders
            on delete cascade;

alter table collection_items
    drop constraint collection_items_collections_fk;

alter table collection_items
    add constraint collection_items_collections_fk
        foreign key (collection_id) references collections
            on delete cascade;

