create table variants
(
    id                 bigint generated always as identity,
    brand              varchar(50),
    color              varchar(50),
    size               varchar(50),
    material           varchar(50),
    price              decimal(8, 2)     not null,
    quantity           integer default 0 not null,
    stock_keeping_unit varchar(255)      not null
        constraint unique_sku
            unique,
    constraint variants_pk
        primary key (id, brand, size, material, color),
    constraint variants_id_unique
        unique (id)
);

create table products
(
    id          bigint generated always as identity
        constraint products_pk
            primary key,
    category_id integer       not null,
    variant_id  bigint        not null,
    name        varchar(255)  not null,
    description varchar(255),
    base_price  decimal(8, 2) not null,
    constraint products_variants_id_fk
        foreign key (variant_id) references variants (id)
            on delete cascade
);
