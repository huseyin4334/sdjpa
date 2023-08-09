drop table if exist credit_card_pan;
create table credit_card_pan(
    id bigint not null auto_increment,
    credit_card_number varchar(30),
    primary key (id)
) engine=InnoDB;