create table address
(
    address_id integer default nextval('address_id_seq'::regclass) not null
        constraint address_pk
            primary key,
    street     varchar,
    number     varchar,
    zip        integer,
    city       varchar,
    state      varchar,
    country    varchar
);

alter table address
    owner to postgres;

create unique index address_id_uindex
    on address (address_id);

INSERT INTO public.address (address_id, street, number, zip, city, state, country) VALUES (1, 'Am Sportplatz', '2', 32427, 'CyberCity', 'Metaverse', 'Internet');
