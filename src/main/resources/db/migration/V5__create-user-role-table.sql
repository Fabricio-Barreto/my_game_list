CREATE TABLE IF NOT EXISTS public.tb_users_roles
(
    role_id uuid NOT NULL,
    user_id uuid NOT NULL,
    CONSTRAINT fk6p4o2kxbq23rthm174k19xo2h FOREIGN KEY (role_id)
        REFERENCES public.tb_role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk85qorv8qojsxvl1nv56vckxmj FOREIGN KEY (user_id)
        REFERENCES public.tb_user (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)