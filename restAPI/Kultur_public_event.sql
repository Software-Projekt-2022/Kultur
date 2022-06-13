create table event
(
    event_id    integer not null
        constraint event_pk
            primary key,
    place       integer
        constraint event_coordinates_id_fk
            references coordinates,
    date        timestamp,
    chat        integer
        constraint event_chat_id_fk
            references chat,
    name        varchar,
    description varchar,
    category    varchar,
    max_people  integer
);

alter table event
    owner to postgres;

create unique index event_id_uindex
    on event (event_id);

