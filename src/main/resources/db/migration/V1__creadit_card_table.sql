/*
    That's default file for hibernate initialization query file.

    book_seq equals hibernate_sequence in previous hibernate versions.
    Because if you have heavy usage with tables. In this time, hibernate_sequence table can be locked. Therefore, performance will be decrease.
    Therefore, Every table have own sequence table for performance.
*/

drop table if exist credit_card;
create table credit_card(
    id bigint not null auto_increment,
    credit_card_number varchar(20),
    cvv varchar(4),
    expiration_date varchar(7),
    primary key (id)
) engine=InnoDB;

