CREATE TABLE `prefeitura`.`grafico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `receita` FLOAT NOT NULL,
  `despesa` FLOAT NULL,
  `data` DATE NOT NULL,
  `tipo` VARCHAR(45) NOT NULL COMMENT 'Gráfico ao qual os dados estarão relacionados. \nos valores devem ser \'pie\', \'line\', \'block\' ou \'esic\'.',
  `id_orgao` INT NULL COMMENT 'Para o gráfico por secretaria.',
  PRIMARY KEY (`id`),
  INDEX `id_orgao_idx` (`id_orgao` ASC),
  CONSTRAINT `id_orgao`
    FOREIGN KEY (`id_orgao`)
    REFERENCES `prefeitura`.`orgaos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Sustenta os gráficos estatísticos do site.';


ALTER TABLE `prefeitura`.`grafico` 
CHANGE COLUMN `tipo` `tipo` INT NOT NULL COMMENT 'Gráfico ao qual os dados estarão relacionados. \n' ;

ALTER TABLE `prefeitura`.`grafico` 
CHANGE COLUMN `receita` `receita` BIGINT(15) NOT NULL ,
CHANGE COLUMN `despesa` `despesa` BIGINT(15) NULL DEFAULT NULL ;

INSERT INTO `prefeitura`.`grafico`
(`id`, `receita`, `despesa`, `data`, `tipo`, `id_orgao`)
VALUES
(1, 1432709000, 1191669400, STR_TO_DATE('05/03/2018', '%d/%m/%Y'), 1, null);

INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`) VALUES ('2', '1432709000', '191669400', '2017-03-07', '1');




INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`) VALUES ('3', '1432709000', NULL, '2017-01-07', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`) VALUES ('4', '432709000', NULL, '2017-02-07', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`) VALUES ('5', '932709000', NULL, '2017-03-07', '3');


INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `data`, `tipo`) VALUES ('6', '812709000', '2017-04-07', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `data`, `tipo`) VALUES ('7', '1332709000', '2017-05-07', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `data`, `tipo`) VALUES ('8', '432709000', '2017-06-07', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `data`, `tipo`) VALUES ('9', '852709000', '2017-07-07', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `data`, `tipo`) VALUES ('10', '1132709000', '2017-08-07', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `data`, `tipo`) VALUES ('11', '532709000', '2017-09-07', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `data`, `tipo`) VALUES ('12', '432709000', '2017-10-07', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `data`, `tipo`) VALUES ('13', '2709000', '2017-11-07', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `data`, `tipo`) VALUES ('14', '932709000', '2017-12-07', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `data`, `tipo`) VALUES ('15', '832709000', '2016-12-07', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`) VALUES ('16', '732709000', '251669400', '2018-02-07', '1');


INSERT INTO `prefeitura`.`orgaos` (`id`, `desc`) VALUES ('4', 'Orgão Eleitoral Gratuito');

INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`, `id_orgao`) VALUES ('17', '622709000', '251669400', '2018-01-07', '2', '2');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`, `id_orgao`) VALUES ('18', '732709000', '211669400', '2018-01-07', '2', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`, `id_orgao`) VALUES ('19', '709000', '21669400', '2018-01-07', '2', '4');
UPDATE `prefeitura`.`grafico` SET `despesa`='11669400', `data`='2018-01-07', `tipo`='2', `id_orgao`='1' WHERE `id`='16';
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`, `id_orgao`) VALUES ('20', '62709000', '121669400', '2018-02-07', '2', '1');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`, `id_orgao`) VALUES ('21', '452709000', '71669400', '2018-02-07', '2', '2');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`, `id_orgao`) VALUES ('22', '432709000', '35669400', '2018-02-07', '2', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`, `id_orgao`) VALUES ('23', '102709000', '81669400', '2018-02-07', '2', '4');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`, `id_orgao`) VALUES ('24', '5709000', '91669400', '2018-03-07', '2', '1');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`, `id_orgao`) VALUES ('25', '2709000', '669400', '2018-03-07', '2', '2');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`, `id_orgao`) VALUES ('26', '251709000', '6569400', '2018-03-07', '2', '3');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`, `id_orgao`) VALUES ('27', '88709000', '88669400', '2018-03-07', '2', '4');
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`) VALUES ('28', '9000', '400', '2018-02-07', '1');

UPDATE `prefeitura`.`grafico` SET `tipo`='1', `id_orgao`=NULL WHERE `id`='16';
INSERT INTO `prefeitura`.`grafico` (`id`, `receita`, `despesa`, `data`, `tipo`, `id_orgao`) VALUES ('29', '322709000', '121669400', '2018-01-07', '2', '1');

ALTER TABLE `prefeitura`.`grafico` 
CHANGE COLUMN `receita` `receita` DOUBLE NOT NULL ,
CHANGE COLUMN `despesa` `despesa` DOUBLE NULL DEFAULT NULL ;

UPDATE `prefeitura`.`grafico` SET `receita`='14327090.00' WHERE `id`='1';
UPDATE `prefeitura`.`grafico` SET `receita`='14327090.00' WHERE `id`='2';
UPDATE `prefeitura`.`grafico` SET `receita`='14327090.00' WHERE `id`='3';
UPDATE `prefeitura`.`grafico` SET `receita`='4327090.00' WHERE `id`='4';
UPDATE `prefeitura`.`grafico` SET `receita`='9327090.00' WHERE `id`='5';
UPDATE `prefeitura`.`grafico` SET `receita`='8127090.00' WHERE `id`='6';
UPDATE `prefeitura`.`grafico` SET `receita`='13327090.00' WHERE `id`='7';
UPDATE `prefeitura`.`grafico` SET `receita`='4327090.00' WHERE `id`='8';
UPDATE `prefeitura`.`grafico` SET `receita`='8527090.00' WHERE `id`='9';
UPDATE `prefeitura`.`grafico` SET `receita`='11327090.00' WHERE `id`='10';
UPDATE `prefeitura`.`grafico` SET `receita`='5327090.00' WHERE `id`='11';
UPDATE `prefeitura`.`grafico` SET `receita`='4327090.00' WHERE `id`='12';
UPDATE `prefeitura`.`grafico` SET `receita`='27090.00' WHERE `id`='13';
UPDATE `prefeitura`.`grafico` SET `receita`='9327090.00' WHERE `id`='14';
UPDATE `prefeitura`.`grafico` SET `receita`='8327090.00' WHERE `id`='15';
UPDATE `prefeitura`.`grafico` SET `receita`='7327090.00' WHERE `id`='16';
UPDATE `prefeitura`.`grafico` SET `receita`='6227090.00' WHERE `id`='17';
UPDATE `prefeitura`.`grafico` SET `receita`='7327090.00' WHERE `id`='18';
UPDATE `prefeitura`.`grafico` SET `receita`='709000.45' WHERE `id`='19';
UPDATE `prefeitura`.`grafico` SET `receita`='627090.00' WHERE `id`='20';
UPDATE `prefeitura`.`grafico` SET `receita`='4527090.00' WHERE `id`='21';
UPDATE `prefeitura`.`grafico` SET `receita`='4327090.00' WHERE `id`='22';
UPDATE `prefeitura`.`grafico` SET `receita`='1027090.00' WHERE `id`='23';
UPDATE `prefeitura`.`grafico` SET `receita`='27090.00' WHERE `id`='25';
UPDATE `prefeitura`.`grafico` SET `receita`='2517090.00' WHERE `id`='26';
UPDATE `prefeitura`.`grafico` SET `receita`='887090.00' WHERE `id`='27';
UPDATE `prefeitura`.`grafico` SET `receita`='90.00' WHERE `id`='28';
UPDATE `prefeitura`.`grafico` SET `receita`='3227090.00' WHERE `id`='29';
UPDATE `prefeitura`.`grafico` SET `receita`='2270.90', `despesa`='1694.00' WHERE `id`='1';
