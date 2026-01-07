alter table products
    add constraint products_variants_id_fk
        foreign key (variant_id) references variants (id);

