create table message
(
    message_id integer default nextval('message_id_seq'::regclass) not null
        constraint message_pk
            primary key,
    content    varchar,
    date       timestamp,
    chat       integer
        constraint message_chat_id_fk
            references chat
);

alter table message
    owner to postgres;

create unique index message_id_uindex
    on message (message_id);

