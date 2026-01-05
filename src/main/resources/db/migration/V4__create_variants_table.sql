create table variants
(
    id                 bigint generated always as identity
        constraint variants_pk
            primary key,
    brand_id           bigint            not null
        constraint variants_brands_id_fk
            references brands,
    color_id           bigint            not null
        constraint variants_colors_id_fk
            references colors,
    size_id            bigint            not null
        constraint variants_colors_id_fk_2
            references colors,
    material_id        bigint            not null
        constraint variants_materials_id_fk
            references materials,
    price              decimal(8, 2)     not null,
    quantity           integer default 0 not null,
    stock_keeping_unit varchar(255)      not null
        constraint unique_sku
            unique
);

