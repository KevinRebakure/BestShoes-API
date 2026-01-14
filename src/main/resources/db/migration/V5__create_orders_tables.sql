create table orders
(
    id           bigint generated always as identity
        constraint orders_pk
            primary key,
    placed_on    date default current_date not null,
    user_id      bigint                    not null
        constraint orders_users_fk
            references users,
    total_amount decimal(8, 2) default 0   not null
);

create table order_items
(
    id         bigint generated always as identity
        constraint order_items_pk
            primary key,
    product_id bigint not null,
    quantity   int    not null,
    order_id   bigint not null,
    constraint order_items_products_fk
        foreign key (product_id) references products
            on delete cascade,
    constraint order_items_orders_fk
        foreign key (order_id) references orders
            on delete set null
);
