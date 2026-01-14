create table users
(
    id       bigint generated always as identity
        constraint users_pk
            primary key,
    name     varchar(255) not null,
    email    varchar(255) not null
        constraint unique_email
            unique,
    password varchar(255) not null,
    role     varchar(50) default null
);
