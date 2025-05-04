CREATE TABLE company
(
    id   SERIAL PRIMARY KEY,
    name varchar(64) not null unique
);

create table company_locales
(
    company_id  int references company (id),
    lang        varchar(2),
    description varchar(255) not null,
    primary key (company_id, lang)
);

create table users
(
    id         bigserial primary key,
    username   varchar(64) not null unique,
    birth_date date,
    firstname  varchar(64),
    lastname   varchar(64),
    role       varchar(32),
    company_id int references company (id),
    image varchar(128)
);

create table payment
(
    id          bigserial primary key,
    amount      int    not null,
    receiver_id bigint not null references users (id)
);

create table chat
(
    id   bigserial primary key,
    name varchar(64) not null unique
);

create table users_chat
(
    id      bigserial primary key,
    user_id bigint not null references users (id),
    chat_id bigint not null references chat (id),
    unique (user_id, chat_id)
);