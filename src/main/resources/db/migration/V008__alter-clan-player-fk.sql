ALTER TABLE cursoalgaworks.player CHANGE clan clan_id BIGINT NOT NULL;
ALTER TABLE cursoalgaworks.player MODIFY COLUMN clan_id BIGINT NOT NULL;

ALTER TABLE cursoalgaworks.player ADD CONSTRAINT fk_player_clan FOREIGN KEY (clan_id) REFERENCES cursoalgaworks.clan(id);

ALTER TABLE cursoalgaworks.player ADD cliente_id BIGINT NOT NULL;
ALTER TABLE cursoalgaworks.player ADD CONSTRAINT fk_player_cliente FOREIGN KEY (cliente_id) REFERENCES cursoalgaworks.cliente(id);

