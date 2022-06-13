create table chat
(
    chat_id integer default nextval('chat_id_seq'::regclass) not null
        constraint chat_pk
            primary key,
    name    varchar
);

alter table chat
    owner to postgres;

create unique index chat_id_uindex
    on chat (chat_id);

