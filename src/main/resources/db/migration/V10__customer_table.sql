drop table exists customer cascade;

create table customer
(
    id bigint not null auto_increment primary key,
    customer_name varchar(128),
    address varchar(128),
    city varchar(64),
    state varchar(128),
    zip_code varchar(32),
    phone varchar(128),
    email varchar(128),
    created_date timestamp,
    last_modified_date timestamp
) engine=InnoDB;

alter table order_header
    add column customer_id bigint;
alter table order_header
    add constraint customer_id_pk foreign key (customer_id) references customer(id);

alter table order_header drop column customer;

insert into customer (customer_name, address, city, state, zip_code, phone, email, created_date, last_modified_date)
values ('David', '123 Duval', 'Chicago', 'FL', '33040', '305.27456.65786', 'cheese@tested.com', now(), now());

update order_header set customer_id = (select id from customer limit 1);

