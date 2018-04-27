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
  
  ALTER TABLE `prefeitura`.`legislacao` 
DROP COLUMN `exercicio`,
CHANGE COLUMN `mes` `data` DATE NOT NULL ;

INSERT INTO `prefeitura`.`legislacao` (`id`, `nome`, `data`, `resumo`, `id_orgao`, `arquivo`, `tipo`) VALUES ('1', 'LOJ 2018', STR_TO_DATE('05/01/2018', '%d/%m/%Y'), 'LOJ 2018', '1', 'ara.jpeg', '1');
INSERT INTO `prefeitura`.`legislacao` (`id`, `nome`, `data`, `resumo`, `id_orgao`, `arquivo`, `tipo`) VALUES ('2', 'LOF 2018', STR_TO_DATE('05/02/2018', '%d/%m/%Y'), 'LOF 2018', '1', 'ara.jpeg', '1');
INSERT INTO `prefeitura`.`legislacao` (`id`, `nome`, `data`, `resumo`, `id_orgao`, `arquivo`, `tipo`) VALUES ('3', 'LOM 2018', STR_TO_DATE('05/03/2018', '%d/%m/%Y'), 'LOM 2018', '1', 'ara.jpeg', '1');
