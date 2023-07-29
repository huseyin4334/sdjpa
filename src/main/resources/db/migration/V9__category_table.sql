drop table exists category cascade;
drop table exists product_category cascade;

create table category
(
    id bigint not null auto_increment primary key,
    name varchar(255),
    created_date timestamp,
    last_modified_date timestamp,
) engine=InnoDB;

create table product_category
(
    product_id bigint not null,
    category_id bigint not null,
    primary key (product_id, category_id),
    constraint pc_product_id_pk foreign key (product_id) references product(id),
    constraint pc_category_id_pk foreign key (category_id) references category(id)
) engine=InnoDB;

insert into category (name, created_date, last_modified_date)
values ('CAT-1', now(), now()), ('CAT-2', now(), now()), ('CAT-3', now(), now()), ('CAT-4', now(), now()), ('CAT-5', now(), now()), ('CAT-6', now(), now());

insert into product_category (product_id, category_id)
select p.id, c.id from product p, category c where p.name = 'PRODUCT-1' and c.name = 'CAT-2';

insert into product_category (product_id, category_id)
select p.id, c.id from product p, category c where p.name = 'PRODUCT-1' and c.name = 'CAT-3';

insert into product_category (product_id, category_id)
select p.id, c.id from product p, category c where p.name = 'PRODUCT-2' and c.name = 'CAT-1';

insert into product_category (product_id, category_id)
select p.id, c.id from product p, category c where p.name = 'PRODUCT-3' and c.name = 'CAT-4';

insert into product_category (product_id, category_id)
select p.id, c.id from product p, category c where p.name = 'PRODUCT-5' and c.name = 'CAT-6';