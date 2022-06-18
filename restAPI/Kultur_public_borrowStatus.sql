create table "borrowStatus"
(
    status_id integer default nextval('"borrowStatus_id_seq"'::regclass) not null
        constraint borrowstatus_pk
            primary key,
    status    varchar
);

alter table "borrowStatus"
    owner to postgres;

create unique index borrowstatus_id_uindex
    on "borrowStatus" (status_id);

INSERT INTO public."borrowStatus" (status_id, status) VALUES (0, 'Borrowed');
INSERT INTO public."borrowStatus" (status_id, status) VALUES (1, 'Returned');
INSERT INTO public."borrowStatus" (status_id, status) VALUES (2, 'Overdue');
INSERT INTO public."borrowStatus" (status_id, status) VALUES (3, 'Lost');
INSERT INTO public."borrowStatus" (status_id, status) VALUES (4, 'Ordered');
