-- Table: public.t_usuario

-- DROP TABLE IF EXISTS public.t_usuario;

CREATE TABLE IF NOT EXISTS public.t_usuario
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 999999999 CACHE 1 ),
    nome_completo character varying(80) COLLATE pg_catalog."default" NOT NULL,
    login_usuario character varying(15) COLLATE pg_catalog."default" NOT NULL,
    login_senha character varying(25) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id, login_usuario)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.t_usuario
    OWNER to postgres;