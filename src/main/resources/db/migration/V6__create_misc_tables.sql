-- Brands table
create table brands
(
    id   bigint generated always as identity
        constraint brands_pk
            primary key,
    name varchar(255) not null
);

-- Sizes table
create table sizes
(
    id         bigint generated always as identity
        constraint sizes_pk
            primary key,
    size_value int         not null,
    size_unit  varchar(5)  not null
);

-- Materials table
create table materials
(
    id   bigint generated always as identity
        constraint materials_pk
            primary key,
    name varchar(50) not null
);

-- Colors table
create table colors
(
    id   bigint generated always as identity
        constraint colors_pk
            primary key,
    name varchar(50) not null
);
