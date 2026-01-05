create table products
(
    id          bigint generated always as identity
        constraint products_pk
            primary key,
    category_id integer       not null
        constraint products_categories_id_fk
            references categories
            on delete cascade,
    variant_id  bigint        not null
        constraint products_variants_id_fk
            references variants
            on delete cascade,
    name        varchar(255)  not null,
    description varchar(255),
    base_price  decimal(8, 2) not null
);

