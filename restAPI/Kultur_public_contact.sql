create table contact
(
    contact_id  integer default nextval('contact_id_seq'::regclass) not null
        constraint contact_pk
            primary key,
    name        varchar,
    phone       varchar,
    website     varchar,
    address     integer
        constraint contact_address_id_fk
            references address,
    coordinates integer
        constraint contact_coordinates_id_fk
            references coordinates,
    image       varchar,
    hours       varchar,
    category    varchar,
    description integer
);

alter table contact
    owner to postgres;

create unique index contact_id_uindex
    on contact (contact_id);

INSERT INTO public.contact (contact_id, name, phone, website, address, coordinates, image, hours, category, description) VALUES (1, 'Michael Müller', '0123456789', 'http://kultur.cyber-city.systems/club/1', 1, 1, null, null, 'Sport', null);
