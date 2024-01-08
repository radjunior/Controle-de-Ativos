-- Table: public.t_manut_equipamento

-- DROP TABLE IF EXISTS public.t_manut_equipamento;

CREATE TABLE IF NOT EXISTS public.t_manut_equipamento
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 0 MAXVALUE 999999999 CACHE 1 ),
    data_envio date NOT NULL,
    data_retorno date NOT NULL,
    tipo character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT t_manut_equipamento_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.t_manut_equipamento
    OWNER to postgres;