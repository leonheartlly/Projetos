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

  
  ALTER TABLE `prefeitura`.`fornecedores` 
ADD UNIQUE INDEX `cnpj_UNIQUE` (`cnpj` ASC);


ALTER TABLE `prefeitura`.`contratos` 
ADD COLUMN `objeto` VARCHAR(5000) NOT NULL AFTER `id_orgao`;

INSERT INTO `prefeitura`.`fornecedores` (`id`, `nome`, `razao_social`, `tipo_fornecedor`, `cpf`, `cnpj`)
VALUES ( 2, 'L.W. ASSIS BARROS EIRELI - ME',
'L.W. ASSIS BARROS EIRELI - ME', 'J', null, '23.456.456/0001-01');

INSERT INTO `prefeitura`.`fornecedores` (`id`, `nome`, `razao_social`, `tipo_fornecedor`, `cpf`, `cnpj`)
VALUES ( 3, 'JANILSON MARTINS DA SILVA',
null, 'F', '20630811318', null);

INSERT INTO prefeitura.contratos (`id`,`numero`,`data_assinatura`,`data_vigencia`,`data_publicacao`,`valor`,`id_fornecedor`,`id_orgao`,`objeto`)
VALUES (1 , 12 , STR_TO_DATE('16/01/2018', '%d/%m/%Y'), STR_TO_DATE('31/12/2018', '%d/%m/%Y'), STR_TO_DATE('16/01/2018', '%d/%m/%Y'), "252.000,00", 2, 1, "Despesa com prestação de serviços com locação de imóvel com quatro comodos e um 
banheiro devidamente instalado para dar suporte a paciente com doença cronica degenerativa. Conforme objeto do contrato");

INSERT INTO prefeitura.contratos (`id`,`numero`,`data_assinatura`,`data_vigencia`,`data_publicacao`,`valor`,`id_fornecedor`,`id_orgao`,`objeto`)
VALUES (2 , 13 , STR_TO_DATE('02/01/2018', '%d/%m/%Y'), STR_TO_DATE('31/12/2018', '%d/%m/%Y'), STR_TO_DATE('01/02/2018', '%d/%m/%Y'), "6.240,00", 3, 1, "Despesa com prestação de serviços com locação de imóvel com quatro comodos e um 
banheiro devidamente instalado para dar suporte a paciente com doença cronica degenerativa. Conforme objeto do contrato");