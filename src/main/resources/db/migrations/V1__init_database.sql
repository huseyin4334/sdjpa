/*
    That's default file for hibernate initialization query file.

    book_seq equals hibernate_sequence in previous hibernate versions.
    Because if you have heavy usage with tables. In this time, hibernate_sequence table can be locked. Therefore, performance will be decrease.
    Therefore, Every table have own sequence table for performance.
*/

drop table if exist book;
drop table if exist book_seq

create table book(
    id bigint not null,
    isbn varchar(255),
    publisher varchar(255),
    title varchar(255),
    primary key (id)
) engine=InnoDB;

create table book_seq (
    next_val bigint
) engine=InnoDB;

insert into book_seq values(1);

