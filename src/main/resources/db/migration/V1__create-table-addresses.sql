create table addresses (
    id int primary key,
    email varchar(50) not null unique,
    personalString varchar(100) unique
);