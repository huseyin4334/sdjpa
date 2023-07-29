drop table exists product cascade;

create table product
(
    id bigint not null auto_increment primary key,
    name varchar(255)
) engine=InnoDB;

insert into product (name) values ('PRODUCT-1'),('PRODUCT-2'),('PRODUCT-3'),('PRODUCT-4'),('PRODUCT-5')