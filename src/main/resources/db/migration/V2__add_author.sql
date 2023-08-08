drop table if exist author;
drop table if exist author_seq;


create table author(
    id bigint identity not null,
    isbn varchar(255),
    publisher varchar(255),
    title varchar(255),
    authorId bigint,
    primary key (id)
) engine=InnoDB;

create table author_seq (
    next_val bigint
) engine=InnoDB;

insert into author_seq values(1);

