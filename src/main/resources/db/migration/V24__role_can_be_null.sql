alter table users
    alter column role drop not null;

alter table users
    alter column role set default null;

