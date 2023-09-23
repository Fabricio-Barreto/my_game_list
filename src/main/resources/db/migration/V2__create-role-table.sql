CREATE TABLE IF NOT EXISTS public.tb_role
(
    role_id uuid NOT NULL,
    role_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tb_role_pkey PRIMARY KEY (role_id),
    CONSTRAINT tb_role_role_name_key UNIQUE (role_name),
    CONSTRAINT tb_role_role_name_check CHECK (role_name::text = ANY (ARRAY['ROLE_ADMIN'::character varying, 'ROLE_USER'::character varying]::text[]))
)