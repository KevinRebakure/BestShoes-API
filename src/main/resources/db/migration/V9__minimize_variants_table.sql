-- Drop foreign key constraints FIRST
alter table variants
    drop constraint variants_brands_id_fk;

alter table variants
    drop constraint variants_colors_id_fk;

alter table variants
    drop constraint variants_colors_id_fk_2;

alter table variants
    drop constraint variants_materials_id_fk;

-- Rename columns
alter table variants
    rename column brand_id to brand;

alter table variants
    rename column color_id to color;

alter table variants
    rename column size_id to size;

alter table variants
    rename column material_id to material;

-- Change column types
alter table variants
    alter column brand type varchar(50) using brand::varchar(50);

alter table variants
    alter column color type varchar(50) using color::varchar(50);

alter table variants
    alter column size type varchar(50) using size::varchar(50);

alter table variants
    alter column material type varchar(50) using material::varchar(50);
