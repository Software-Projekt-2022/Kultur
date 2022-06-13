create table coordinates
(
    coordinates_id integer default nextval('coordinates_id_seq'::regclass) not null
        constraint coordinates_pk
            primary key,
    longitude      double precision,
    latitude       double precision
);

alter table coordinates
    owner to postgres;

create unique index coordinates_id_uindex
    on coordinates (coordinates_id);

INSERT INTO public.coordinates (coordinates_id, longitude, latitude) VALUES (1, 52.25644, 8.91491);
INSERT INTO public.coordinates (coordinates_id, longitude, latitude) VALUES (2, 52.321, 9.003);
INSERT INTO public.coordinates (coordinates_id, longitude, latitude) VALUES (3, 52.572, 8.81221);
