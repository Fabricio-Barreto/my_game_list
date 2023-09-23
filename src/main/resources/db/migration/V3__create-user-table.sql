CREATE TABLE IF NOT EXISTS public.tb_user
(
    location_id uuid,
    user_id uuid NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tb_user_pkey PRIMARY KEY (user_id),
    CONSTRAINT tb_user_email_key UNIQUE (email),
    CONSTRAINT tb_user_location_id_key UNIQUE (location_id),
    CONSTRAINT fk4qhn01b35rve9g9j0cl3u1r22 FOREIGN KEY (location_id)
        REFERENCES public.tb_location (location_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
