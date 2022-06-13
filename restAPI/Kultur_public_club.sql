create table club
(
    club_id     integer default nextval('club_id_seq'::regclass) not null
        constraint club_pk
            primary key,
    name        varchar,
    description varchar,
    place       integer
        constraint club_coordinates_coordinates_id_fk
            references coordinates,
    logo_path   varchar,
    cost        varchar,
    category    varchar,
    chat        integer
        constraint club_chat_chat_id_fk
            references chat,
    meetings    integer,
    address     integer
        constraint club_address_address_id_fk
            references address,
    contact     integer
        constraint club_contact_contact_id_fk
            references contact
);

alter table club
    owner to postgres;

create unique index club_id_uindex
    on club (club_id);

INSERT INTO public.club (club_id, name, description, place, logo_path, cost, category, chat, meetings, address, contact) VALUES (1, 'Fußball FC', 'Wir spielen euch schwindelig', 1, '/path/to/logo.png', '15€/Monat', 'Sport', null, null, null, null);
INSERT INTO public.club (club_id, name, description, place, logo_path, cost, category, chat, meetings, address, contact) VALUES (2, '1. FC CyberCity', 'Holt euch den Ball und kickt los', 2, null, '12€/Monat', 'Sport', null, null, null, null);
INSERT INTO public.club (club_id, name, description, place, logo_path, cost, category, chat, meetings, address, contact) VALUES (3, 'Schachmeister', 'Für alle, die einen würdigen Gegner suchen', 3, null, '5€/Monat', 'Brettspiele', null, null, null, null);
