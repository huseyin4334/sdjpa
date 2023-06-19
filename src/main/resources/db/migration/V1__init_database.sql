drop table if exists book cascade;
drop table if exists author;

create table book(
    id bigint not null auto_increment primary key,
    isbn varchar(255),
    publisher varchar(255),
    title varchar(255),
    author_id bigint,
) engine=InnoDB;

create table author(
    id bigint not null auto_increment primary key,
    first_name varchar(255),
    last_name varchar(255),
) engine=InnoDB;


alter table book add constraint book_author_fk foreign key (author_id) references author (id);

insert into author (first_name, last_name) values ('Craig', 'Walls');

insert into book (isbn, publisher, title, author_id) values (
    '978-23534456', 'Simon & Schuster', 'Spring Book Action', (select id from author where first_name = 'Craig' and last_name = 'Walls')
)

insert into book (isbn, publisher, title, author_id) values (
    '978-23534534', 'Simon & Schuster', 'Spring Book Action 1', (select id from author where first_name = 'Craig' and last_name = 'Walls')
)

insert into book (isbn, publisher, title, author_id) values (
    '978-23534534', 'Simon & Schuster', 'Spring Book Action 2', (select id from author where first_name = 'Craig' and last_name = 'Walls')
)

insert into book (isbn, publisher, title, author_id) values (
    '978-23536786', 'Simon & Schuster', 'Spring Book Action 3', (select id from author where first_name = 'Craig' and last_name = 'Walls')
)

insert into author (first_name, last_name) values ('Eric', 'Evans');

insert into book (isbn, publisher, title, author_id) values (
    '978-24934534', 'Addison Wesley', 'Domain Driven Design', (select id from author where first_name = 'Eric' and last_name = 'Evans')
)

insert into book (isbn, publisher, title, author_id) values (
    '978-56897098', 'Addison Wesley', 'Domain Driven Design Last Edition', (select id from author where first_name = 'Eric' and last_name = 'Evans')
)

insert into author (first_name, last_name) values ('Robert', 'Martin');

insert into book (isbn, publisher, title, author_id) values (
    '978-45645787', 'Addison Wesley', 'Domain Driven Design', (select id from author where first_name = 'Robert' and last_name = 'Martin')
)