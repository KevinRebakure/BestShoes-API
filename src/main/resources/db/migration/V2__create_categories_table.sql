create table categories
(
    id        integer generated always as identity
        constraint categories_pk
            primary key,
    name      varchar(255) not null,
    parent_id integer
        constraint categories_categories_id_fk
            references categories
            on delete cascade
);

alter table products
    add constraint products_categories_id_fk
        foreign key (category_id) references categories (id)
            on delete cascade;
