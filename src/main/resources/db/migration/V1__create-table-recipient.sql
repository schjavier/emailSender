create table recipient (
    id bigint auto_increment primary key,
    email varchar(50) not null unique,
    personal_string varchar(100) unique
);