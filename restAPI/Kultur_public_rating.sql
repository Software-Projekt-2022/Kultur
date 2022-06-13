create table rating
(
    rating_id integer default nextval('rating_id_seq'::regclass) not null
        constraint rating_pk
            primary key,
    stars     integer,
    comment   varchar
);

alter table rating
    owner to postgres;

create unique index rating_id_uindex
    on rating (rating_id);

INSERT INTO public.rating (rating_id, stars, comment) VALUES (1, 5, '10/10 would play again!');
INSERT INTO public.rating (rating_id, stars, comment) VALUES (2, 3, 'Funktioniert wie erwartet! TOP!');
INSERT INTO public.rating (rating_id, stars, comment) VALUES (3, 1, 'Ich hatte eigentlich was anders bestellt');
INSERT INTO public.rating (rating_id, stars, comment) VALUES (4, 1, 'Ich wollte Schach spielen und bin jetzt im Fußballverein?!? ');
