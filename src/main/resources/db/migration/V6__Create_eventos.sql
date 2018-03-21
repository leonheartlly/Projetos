CREATE TABLE `prefeitura`.`eventos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data` DATE NOT NULL COMMENT 'Data do evento.',
  `descricao` VARCHAR(500) NOT NULL,
  `local_evento` VARCHAR(100) NOT NULL,
  `titulo_evento` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
COMMENT = 'Eventos programados pela prefeitura de Araguaçu.';

ALTER TABLE `prefeitura`.`noticias` 
DROP FOREIGN KEY `categoria`;
ALTER TABLE `prefeitura`.`noticias` 
CHANGE COLUMN `noticia` `noticia` VARCHAR(5000) NOT NULL COMMENT 'corpo da notícia.' ,
CHANGE COLUMN `data_noticia` `data_noticia` DATE NOT NULL COMMENT 'Data de criação da noticia.' ,
CHANGE COLUMN `autor` `autor` VARCHAR(45) NOT NULL COMMENT 'Autor da noticia.' ,
CHANGE COLUMN `imagem` `imagem` VARCHAR(200) NULL DEFAULT NULL COMMENT 'Caminho da/s imagem/s relacionadas à noticia.' ,
CHANGE COLUMN `id_categoria` `id_categoria` INT(11) NOT NULL COMMENT 'Categoria em que se enquadra a noticia.' ,
ADD COLUMN `titulo` VARCHAR(45) NOT NULL COMMENT 'Título da notícia.' AFTER `id_categoria`;
ALTER TABLE `prefeitura`.`noticias` 
ADD CONSTRAINT `categoria`
  FOREIGN KEY (`id_categoria`)
  REFERENCES `prefeitura`.`categoria_noticias` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
