drop table exists order_header cascade;

create table order_line
(
    id bigint not null auto_increment primary key,
    order_header_id bigint,
    quantity int,
    created_date timestamp,
    last_modified_date timestamp,
    constraint order_header_pk foreign key (order_header_id) references order_header(id)
) engine = InnoDB;