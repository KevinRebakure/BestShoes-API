-- Orders table
create table orders
(
    id           bigint generated always as identity
        constraint orders_pk
            primary key,
    placed_on    date           not null,
    user_id      bigint         not null
        constraint orders_users_fk
            references users,
    total_amount decimal(8, 2)  not null
);

-- Order_items table
create table order_items
(
    id         bigint generated always as identity
        constraint order_items_pk
            primary key,
    product_id bigint not null
        constraint order_items_products_fk
            references products,
    quantity   bigint not null,
    order_id   bigint not null
        constraint order_items_orders_fk
            references orders
);