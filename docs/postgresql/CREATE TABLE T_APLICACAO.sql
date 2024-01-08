-- Table: public.t_aplicacao

-- DROP TABLE IF EXISTS public.t_aplicacao;

CREATE TABLE IF NOT EXISTS public.t_aplicacao
(
    tag character varying(20) COLLATE pg_catalog."default" NOT NULL,
    descricao character varying(40) COLLATE pg_catalog."default" NOT NULL,
    id_equipamento integer NOT NULL,
    CONSTRAINT t_aplicacao_pkey PRIMARY KEY (tag),
    CONSTRAINT fk_equipamento FOREIGN KEY (id_equipamento)
        REFERENCES public.t_equipamento (num_serie) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.t_aplicacao
    OWNER to postgres;