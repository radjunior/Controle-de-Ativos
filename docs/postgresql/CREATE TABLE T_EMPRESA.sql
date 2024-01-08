-- Table: public.t_empresa

-- DROP TABLE IF EXISTS public.t_empresa;

CREATE TABLE IF NOT EXISTS public.t_empresa
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 0 MAXVALUE 999999999 CACHE 1 ),
    nome character varying(40) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT t_empresa_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.t_empresa
    OWNER to postgres;