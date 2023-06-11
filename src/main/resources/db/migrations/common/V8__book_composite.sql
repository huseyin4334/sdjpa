create table book_composite(
    isbn varchar(255),
    publisher varchar(255),
    title varchar(255),
    authorId bigint,
    primary key (title, isbn)
) engine=InnoDB;