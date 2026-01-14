create table collections
(
    id   bigint generated always as identity
        constraint collections_pk
            primary key,
    name varchar(255) not null
        constraint unique_collection_name
            unique
);

create table collection_items
(
    id            bigint generated always as identity
        constraint collection_items_pk
            primary key,
    product_id    bigint not null,
    collection_id bigint not null,
    constraint collection_items_products_fk
        foreign key (product_id) references products
            on delete cascade,
    constraint collection_items_collections_fk
        foreign key (collection_id) references collections
            on delete cascade
);
