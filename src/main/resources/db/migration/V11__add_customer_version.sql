alter table customer
    add column version integer;

update customer set version = 0 where version is null;

