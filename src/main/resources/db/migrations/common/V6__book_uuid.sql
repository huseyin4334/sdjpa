create table book_uuid(
    id binary(16) not null,
    isbn varchar(255),
    publisher varchar(255),
    title varchar(255),
    authorId bigint,
    primary key (id)
) engine=InnoDB;