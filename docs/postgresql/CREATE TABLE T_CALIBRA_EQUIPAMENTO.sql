-- Table: public.t_calibra_equipamento

-- DROP TABLE IF EXISTS public.t_calibra_equipamento;

CREATE TABLE IF NOT EXISTS public.t_calibra_equipamento
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 0 MAXVALUE 999999999 CACHE 1 ),
    id_equipamento integer NOT NULL,
    id_empresa integer NOT NULL,
    data_calibracao date,
    situacao character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT t_calibra_equipamento_pkey PRIMARY KEY (id),
    CONSTRAINT fk_empresa FOREIGN KEY (id_empresa)
        REFERENCES public.t_empresa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_equipamento FOREIGN KEY (id_equipamento)
        REFERENCES public.t_equipamento (num_serie) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.t_calibra_equipamento
    OWNER to postgres;