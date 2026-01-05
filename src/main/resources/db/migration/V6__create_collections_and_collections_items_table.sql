-- Collections table
create table collections
(
    id   bigint generated always as identity
        constraint collections_pk
            primary key,
    name varchar(255) not null
);

-- Collection_items table (junction table)
create table collection_items
(
    id            bigint generated always as identity
        constraint collection_items_pk
            primary key,
    product_id    bigint not null
        constraint collection_items_products_fk
            references products,
    collection_id bigint not null
        constraint collection_items_collections_fk
            references collections
);