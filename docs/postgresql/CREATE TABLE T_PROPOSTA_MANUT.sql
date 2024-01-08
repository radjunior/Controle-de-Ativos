-- Table: public.t_proposta_manut

-- DROP TABLE IF EXISTS public.t_proposta_manut;

CREATE TABLE IF NOT EXISTS public.t_proposta_manut
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 0 MAXVALUE 999999999 CACHE 1 ),
    id_equipamento integer NOT NULL,
    id_empresa integer NOT NULL,
    item integer,
    valor numeric(10,2),
    descricao character varying(40) COLLATE pg_catalog."default",
    id_manut_equipamento integer,
    CONSTRAINT t_proposta_manut_pkey PRIMARY KEY (id),
    CONSTRAINT fk_empresa FOREIGN KEY (id_empresa)
        REFERENCES public.t_empresa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_equipamento FOREIGN KEY (id_equipamento)
        REFERENCES public.t_equipamento (num_serie) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_manut_equipamento FOREIGN KEY (id_manut_equipamento)
        REFERENCES public.t_manut_equipamento (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.t_proposta_manut
    OWNER to postgres;