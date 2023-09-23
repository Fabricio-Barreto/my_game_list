CREATE TABLE IF NOT EXISTS public.tb_location
(
    location_id uuid NOT NULL,
    cep character varying(255) COLLATE pg_catalog."default",
    city character varying(255) COLLATE pg_catalog."default" NOT NULL,
    country character varying(255) COLLATE pg_catalog."default" NOT NULL,
    state character varying(255) COLLATE pg_catalog."default" NOT NULL,
    zip_code character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT tb_location_pkey PRIMARY KEY (location_id)
)



