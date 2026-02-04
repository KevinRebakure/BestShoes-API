alter table orders
    add status varchar(10) default 'PENDING' not null;

