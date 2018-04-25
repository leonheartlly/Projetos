CREATE TABLE `prefeitura`.`legislacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(70) NOT NULL,
  `exercicio` INT NOT NULL,
  `mes` INT NOT NULL,
  `resumo` VARCHAR(80) NOT NULL,
  `id_orgao` INT NOT NULL,
  `arquivo` VARCHAR(80) NOT NULL COMMENT 'Apenas o nome do arquivo com sua extensão.',
  `tipo` INT NOT NULL COMMENT 'tipo da consulta ao qual o arquivo se refere.',
  PRIMARY KEY (`id`))
COMMENT = 'Tab';


ALTER TABLE `prefeitura`.`legislacao` 
ADD INDEX `orgao_idxx` (`id_orgao` ASC);
ALTER TABLE `prefeitura`.`legislacao` 
ADD CONSTRAINT `lesgislacao_orgao`
  FOREIGN KEY (`id_orgao`)
  REFERENCES `prefeitura`.`orgaos` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
