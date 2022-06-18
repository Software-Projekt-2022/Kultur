create table book
(
    book_id      integer default nextval('book_id_seq'::regclass) not null
        constraint book_pk_2
            primary key
        constraint book_pk
            unique,
    title        varchar,
    author       varchar,
    description  varchar,
    status       integer
        constraint book_borrowstatus_id_fk
            references "borrowStatus",
    publisher    varchar,
    category     varchar,
    language     varchar,
    release_date timestamp
);

alter table book
    owner to postgres;

INSERT INTO public.book (book_id, title, author, description, status, publisher, category, language, release_date) VALUES (1, 'Es', 'Steven King', 'Irgendwas mit einem Clown und einem Roten Ballon', 1, 'King Stories', 'Horror', 'DE', '1982-05-30 00:00:00.000000');
INSERT INTO public.book (book_id, title, author, description, status, publisher, category, language, release_date) VALUES (2, 'Es', 'Steven King', 'Der Clown kommt zurück', 1, 'King Stories', 'Horror', 'DE', '2009-05-30 00:00:00.000000');
INSERT INTO public.book (book_id, title, author, description, status, publisher, category, language, release_date) VALUES (3, 'Die Fünf Freunde', 'Steve Jobs', 'Die Fünf Freunde für alle Fälle', 4, 'Magic Castle', 'Kinderbuch', 'DE', '2019-05-30 11:59:19.000000');
INSERT INTO public.book (book_id, title, author, description, status, publisher, category, language, release_date) VALUES (4, '???', 'Martin Luther', 'Irgendwas mit Glauben', 3, 'Gott', 'Glauben', 'DE', '1230-05-30 12:00:01.000000 BC');
