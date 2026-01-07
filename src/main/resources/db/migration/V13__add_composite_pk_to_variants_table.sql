alter table variants
    drop constraint variants_pk;

alter table variants
    add constraint variants_pk
        primary key (id, brand, size, material, color);

