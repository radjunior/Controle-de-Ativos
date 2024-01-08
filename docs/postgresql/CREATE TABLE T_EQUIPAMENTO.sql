-- Table: public.t_equipamento

-- DROP TABLE IF EXISTS public.t_equipamento;

CREATE TABLE IF NOT EXISTS public.t_equipamento
(
    num_serie integer NOT NULL,
    codigo integer,
    id_fabricante integer,
    situacao character varying(30) COLLATE pg_catalog."default",
    CONSTRAINT t_equipamento_pkey PRIMARY KEY (num_serie),
    CONSTRAINT fk_fabricante FOREIGN KEY (id_fabricante)
        REFERENCES public.t_fabricante (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.t_equipamento
    OWNER to postgres;