CREATE TABLE `cursoalgaworks`.`clan` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `data_registro` DATETIME NOT NULL,
  PRIMARY KEY (`id`));
  
INSERT INTO cursoalgaworks.clan
(nome, data_registro)
VALUES('Insanos', now()),('Terroristas', now()),('Irmandade', now());

