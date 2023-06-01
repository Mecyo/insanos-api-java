		
CREATE TABLE `cursoalgaworks`.`ocorrencia`(
`id` BIGINT NOT NULL auto_increment,
`data_registro` DATETIME NOT NULL,
`descricao` VARCHAR(60) NOT NULL,
`entrega_id` BIGINT NOT NULL,
PRIMARY KEY (id));

ALTER TABLE `cursoalgaworks`.`ocorrencia`
ADD CONSTRAINT fk_ocorrencia_entrega
FOREIGN KEY (entrega_id)
REFERENCES `cursoalgaworks`.`entrega` (id);



CREATE TABLE `cursoalgaworks`.`entrega_ocorrencias`(
`entrega_id` BIGINT NOT NULL,
`ocorrencia_id` BIGINT NOT NULL);

ALTER TABLE `cursoalgaworks`.`entrega_ocorrencias`
ADD CONSTRAINT fk_entrega_ocorrencias_entrega
FOREIGN KEY (entrega_id)
REFERENCES `cursoalgaworks`.`entrega` (id);

ALTER TABLE `cursoalgaworks`.`entrega_ocorrencias`
ADD CONSTRAINT fk_entrega_ocorrencias_ocorrencia
FOREIGN KEY (ocorrencia_id)
REFERENCES `cursoalgaworks`.`ocorrencia` (id);
