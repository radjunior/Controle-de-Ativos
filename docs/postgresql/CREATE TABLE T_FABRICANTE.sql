-- Table: public.t_fabricante

-- DROP TABLE IF EXISTS public.t_fabricante;

CREATE TABLE IF NOT EXISTS public.t_fabricante
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 0 MAXVALUE 999999999 CACHE 1 ),
    nome character varying(40) COLLATE pg_catalog."default" NOT NULL,
    modelo character varying(40) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT t_fabricante_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.t_fabricante
    OWNER to postgres;