-- Table: public.t_rc_manut_externa

-- DROP TABLE IF EXISTS public.t_rc_manut_externa;

CREATE TABLE IF NOT EXISTS public.t_rc_manut_externa
(
    req_compra integer NOT NULL,
    sit_memorizacao character varying(1) COLLATE pg_catalog."default",
    val_total numeric(10,2),
    id_manut_equipamento integer,
    CONSTRAINT t_rc_manut_externa_pkey PRIMARY KEY (req_compra),
    CONSTRAINT fk_manut_equipamento FOREIGN KEY (id_manut_equipamento)
        REFERENCES public.t_manut_equipamento (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.t_rc_manut_externa
    OWNER to postgres;

COMMENT ON COLUMN public.t_rc_manut_externa.sit_memorizacao
    IS 'S = Memorizada (Ainda não tem proposta)
N = Desmemorizada (Já tem proposta)';