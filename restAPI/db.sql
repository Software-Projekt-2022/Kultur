create sequence "Club_id_seq"
    as integer;

alter sequence "Club_id_seq" owner to postgres;

create sequence "Event_id_seq"
    as integer;

alter sequence "Event_id_seq" owner to postgres;

create sequence "Chat_id_seq"
    as integer;

alter sequence "Chat_id_seq" owner to postgres;

create table chat
(
    id   integer default nextval('"Chat_id_seq"'::regclass) not null
        constraint chat_pk
            primary key,
    name varchar
);

alter table chat
    owner to postgres;

alter sequence "Chat_id_seq" owned by chat.id;

create table event
(
    id          integer default nextval('"Event_id_seq"'::regclass) not null
        constraint event_pk
            primary key,
    place       varchar,
    date        timestamp,
    chat        integer
        constraint event_chat_id_fk
            references chat
            on update cascade on delete set null,
    name        varchar                                             not null,
    description varchar,
    category    varchar,
    max_people  integer
);

alter table event
    owner to postgres;

alter sequence "Event_id_seq" owned by event.id;

create table club
(
    id          integer default nextval('"Club_id_seq"'::regclass) not null
        constraint club_pk
            primary key,
    name        varchar                                            not null,
    description varchar,
    place       varchar,
    logo_path   varchar,
    cost        varchar,
    category    varchar,
    chat        integer
        constraint club_chat_fk
            references chat
            on update cascade on delete set null,
    meetings    integer
        constraint club_event_fk
            references event
            on update cascade on delete set null
);

alter table club
    owner to postgres;

alter sequence "Club_id_seq" owned by club.id;

create table message
(
    id      serial
        constraint message_pk
            primary key,
    content varchar   not null,
    date    timestamp not null,
    chat    integer   not null
        constraint message_chat_id_fk
            references chat
);

alter table message
    owner to postgres;

create table "borrowStatus"
(
    id     serial
        constraint borrowstatus_pk
            primary key,
    status varchar not null
);

alter table "borrowStatus"
    owner to postgres;

create table book
(
    id            serial
        constraint book_pk
            primary key,
    title         varchar not null,
    author        varchar,
    "releaseDate" timestamp,
    description   varchar,
    status        integer
        constraint book_borrowstatus_id_fk
            references "borrowStatus",
    publisher     varchar,
    category      varchar,
    language      varchar,
    release_date  timestamp
);

alter table book
    owner to postgres;

