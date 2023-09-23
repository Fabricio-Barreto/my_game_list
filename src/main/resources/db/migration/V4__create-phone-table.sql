CREATE TABLE IF NOT EXISTS public.tb_phone
(
    is_main_phone boolean NOT NULL,
    phone_id uuid NOT NULL,
    user_id uuid,
    phone_number character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tb_phone_pkey PRIMARY KEY (phone_id),
    CONSTRAINT fkjjbcywxyw2amay8m6ow0t9yxj FOREIGN KEY (user_id)
        REFERENCES public.tb_user (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

