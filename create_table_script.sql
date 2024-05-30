-- Table: public.concerts

-- DROP TABLE IF EXISTS public.concerts;

CREATE TABLE IF NOT EXISTS public.concerts
(
    id uuid NOT NULL,
    concert_name character varying COLLATE pg_catalog."default" NOT NULL,
    status character varying COLLATE pg_catalog."default" NOT NULL,
    stock integer NOT NULL,
    start_time timestamp with time zone NOT NULL,
    end_time timestamp with time zone,
    CONSTRAINT concerts_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.concerts
    OWNER to postgres;

-- Table: public.concert_bookings

-- DROP TABLE IF EXISTS public.concert_bookings;

CREATE TABLE IF NOT EXISTS public.concert_bookings
(
    id uuid NOT NULL,
    concert_id uuid NOT NULL,
    created_at timestamp with time zone NOT NULL,
    email character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT concert_bookings_pkey PRIMARY KEY (id),
    CONSTRAINT fk_concert_id FOREIGN KEY (concert_id)
        REFERENCES public.concerts (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.concert_bookings
    OWNER to postgres;