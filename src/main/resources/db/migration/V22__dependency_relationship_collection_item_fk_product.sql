alter table collection_items
    drop constraint collection_items_products_fk;

alter table collection_items
    add constraint collection_items_products_fk
        foreign key (product_id) references products
            on delete cascade;

