alter table categories
    drop constraint categories_categories_id_fk;

alter table categories
    add constraint categories_categories_id_fk
        foreign key (parent_id) references categories
            on delete cascade;

