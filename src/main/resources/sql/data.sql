insert into company(name)
values ('Google'),
       ('Meta'),
       ('Amazon');

insert into company_locales(company_id, lang, description)
values ((select id from company where name = 'Google'), 'en', 'Google description'),
       ((select id from company where name ='Google'), 'ru', 'Google описание'),
       ((select id from company where name = 'Meta'), 'en', 'Meta description'),
       ((select id from company where name = 'Meta'), 'ru', 'Meta описание'),
       ((select id from company where name = 'Amazon'), 'en', 'Amazon description'),
       ((select id from company where name = 'Amazon'), 'ru', 'Amazon описание');

insert into users(username, birth_date, firstname, lastname, role, company_id)
values ('max@gmail.com', '2002-06-07', 'Max', 'Maximov', 'ADMIN', 1),
       ('ivan@gmail.com', '2001-03-02', 'Ivan', 'Ivanov', 'USER', 1),
       ('petr@gmail.com', '1990-01-08', 'Petr', 'Petrov', 'ADMIN', 2),
       ('katya@gmail.com', '2003-05-08', 'Katya', 'Egorova', 'USER', 2),
       ('ilya@gmail.com', '1995-10-23', 'Ilya', 'Rudenko', 'ADMIN', 3),
       ('mihail@gmail.com', '1999-01-29', 'Mihail', 'Mihailov', 'USER', 3),
       ('evgen@gmail.com', '2001-03-20', 'Evgen', 'Evgenov', 'ADMIN', 1),
       ('alex@gmail.com', '1998-08-08', 'Alex', 'Alexov', 'USER', 2),
       ('egor@gmail.com', '1996-06-26', 'Egor', 'Egorov', 'USER', 1),
       ('kolya@gmail.com', '1994-04-24', 'Kolya', 'Suvorov', 'USER', 3);

insert into payment(amount, receiver_id)
values (23200, 1),
       (26500, 2),
       (24100, 3),
       (19500, 4),
       (18200, 5),
       (15650, 6),
       (29000, 7),
       (27500, 8),
       (17500, 9),
       (20000, 10);

insert into chat(name)
values ('Chatterbox'),
       ('Administration'),
       ('Public');

insert into users_chat(user_id, chat_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (1, 2),
       (3, 2),
       (5, 2),
       (7, 2),
       (8, 3),
       (7, 3),
       (9, 3),
       (10, 3);