ALTER TABLE t_usuario ADD CONSTRAINT uk_id UNIQUE (id); -- garantindo a unicidade dos valores da coluna

ALTER TABLE t_aplicacao ADD COLUMN id_usuario integer;
ALTER TABLE t_aplicacao ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES t_usuario (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID;

ALTER TABLE t_calibra_equipamento ADD COLUMN id_usuario integer;
ALTER TABLE t_calibra_equipamento ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES t_usuario (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID;

ALTER TABLE t_empresa ADD COLUMN id_usuario integer;
ALTER TABLE t_empresa ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES t_usuario (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID;

ALTER TABLE t_equipamento ADD COLUMN id_usuario integer;
ALTER TABLE t_equipamento ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES t_usuario (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID;

ALTER TABLE t_fabricante ADD COLUMN id_usuario integer;
ALTER TABLE t_fabricante ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES t_usuario (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID;

ALTER TABLE t_manut_equipamento ADD COLUMN id_usuario integer;
ALTER TABLE t_manut_equipamento ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES t_usuario (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID;

ALTER TABLE t_proposta_manut ADD COLUMN id_usuario integer;
ALTER TABLE t_proposta_manut ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES t_usuario (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID;

ALTER TABLE t_rc_manut_externa ADD COLUMN id_usuario integer;
ALTER TABLE t_rc_manut_externa ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES t_usuario (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID;