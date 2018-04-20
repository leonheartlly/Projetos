CREATE TABLE `prefeitura`.`calendario_eventos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(80) NOT NULL COMMENT 'Titulo do evento.',
  `data_inicio` DATE NOT NULL,
  `data_fim` DATE NOT NULL,
  `horario_inicio` TIME NOT NULL COMMENT 'Horário em que o evento irá se iniciar.',
  `horario_fim` TIME NOT NULL COMMENT 'Horário de término do evento.',
  `local` VARCHAR(120) NOT NULL COMMENT 'Local onde irá ocorrer o evento',
  `descricao` VARCHAR(300) NOT NULL COMMENT 'Descrição do evento.',
  PRIMARY KEY (`id`))
COMMENT = 'Calendário de eventos de Araguaçu.';

ALTER TABLE `prefeitura`.`calendario_eventos` 
CHANGE COLUMN `local` `rua` VARCHAR(120) NOT NULL COMMENT 'Local onde irá ocorrer o evento' ,
ADD COLUMN `bairro` VARCHAR(80) NOT NULL AFTER `rua`,
ADD COLUMN `numero` VARCHAR(10) NOT NULL AFTER `bairro`;

ALTER TABLE `prefeitura`.`calendario_eventos` 
CHANGE COLUMN `descricao` `descricao` VARCHAR(500) NOT NULL COMMENT 'Descrição do evento.' ;

ALTER TABLE `prefeitura`.`calendario_eventos` 
ADD COLUMN `imagem` VARCHAR(80) NOT NULL COMMENT 'Apenas o nome da imagem usada na divulgação do evento.' AFTER `descricao`;



INSERT INTO `prefeitura`.`calendario_eventos`
(`id`, `titulo`, `data_inicio`, `data_fim`, `horario_inicio`, `horario_fim`, `rua`, `bairro`, `numero`, `descricao`)
VALUES
(1, "Aniversário Lucas", STR_TO_DATE('22/01/2018', '%d/%m/%Y'), STR_TO_DATE('22/01/2018', '%d/%m/%Y'), '10:00:00', '00:00:00', "Segismundo Pereira", "Santa Mônica", "1590", "Aniversário do melhor desenvolvedor em ascenção presente no Brasil e no cenário mundial. Para participar, leve 5kg de carne de primeira e 2kg de carvão. Ao entrar no evento será cobrado uma taxa de R$ 50.");


INSERT INTO `prefeitura`.`calendario_eventos`
(`id`, `titulo`, `data_inicio`, `data_fim`, `horario_inicio`, `horario_fim`, `rua`, `bairro`, `numero`, `descricao`)
VALUES
(2, "Feiras de Matemática e Ciências", STR_TO_DATE('07/08/2017', '%d/%m/%Y'), STR_TO_DATE('07/08/2017', '%d/%m/%Y'), '08:00:00', '17:00:00', "Evaristo Costa", "Amistad", "51", "A Secretaria de Educação de Florianópolis realizará a III Feira Municipal de Matemática, III Feira Municipal de Ciências e IV Feira Regional de Matemática. A intenção é fomentar, divulgar e socializar experiências, pesquisas e atividades sobre os conhecimentos dessas áreas.
Elas ocorrerão no dia 7 de agosto, na Escola Básica Municipal Herondina Medeiros Zeferino, em Ingleses, das 8h às 17h.
Mais informações podem ser encontradas no SITE do evento.");

INSERT INTO `prefeitura`.`calendario_eventos` (`id`, `titulo`, `data_inicio`, `data_fim`, `horario_inicio`, `horario_fim`, `rua`, `bairro`, `numero`, `descricao`) VALUES ('3', 'Apresentacao Indios', '2017-08-07', '2017-08-07', '08:00:00', '17:00:00', 'Evaristo Costa', 'Amistad', '51', 'Os indios negros da tribo grande pau farão uma apresentação de sua cultura recém inventada.');
