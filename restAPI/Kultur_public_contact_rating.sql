create table contact_rating
(
    contact_rating_id integer default nextval('contact_rating_id_seq'::regclass) not null
        constraint contact_rating_pk
            primary key,
    contact           integer
        constraint contact_rating_contact_id_fk
            references contact,
    rating            integer
        constraint contact_rating_rating_id_fk
            references rating
);

alter table contact_rating
    owner to postgres;

create unique index contact_rating_id_uindex
    on contact_rating (contact_rating_id);

INSERT INTO public.contact_rating (contact_rating_id, contact, rating) VALUES (3, 1, 1);
INSERT INTO public.contact_rating (contact_rating_id, contact, rating) VALUES (4, 1, 2);
INSERT INTO public.contact_rating (contact_rating_id, contact, rating) VALUES (5, 1, 4);
