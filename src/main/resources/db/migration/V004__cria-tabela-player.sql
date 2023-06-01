CREATE TABLE cursoalgaworks.player (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(60) NOT NULL,
  email VARCHAR(255) NOT NULL,
  telefone VARCHAR(20) NOT NULL,
  nickname VARCHAR(50) NOT NULL,
  clan INT NOT NULL,
  nivel INT NOT NULL,
  PRIMARY KEY (id));
