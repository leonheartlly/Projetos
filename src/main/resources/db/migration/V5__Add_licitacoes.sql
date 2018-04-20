INSERT INTO `prefeitura`.`fornecedores` (`id`, `nome`, `razao_social`, `tipo_fornecedor`, `cpf`, `cnpj`)
VALUES ( 1, 'João Dória',
'Proparoxitona Foundations', 'J', null, '123.456.456/0001-01');

ALTER TABLE `prefeitura`.`licitacao` ADD column `id_situacao` int not null after `id_fornecedor`;
ALTER TABLE `prefeitura`.`licitacao` 
ADD CONSTRAINT `situacao`
  FOREIGN KEY (`id_situacao`)
  REFERENCES `prefeitura`.`situacao` (`id`);

INSERT INTO `prefeitura`.`licitacao` (`id`, `processo`, `edital`, `data_abertura`, `data_homolog`, `data_adjudicacao`, `id_modalidade`, `objeto`, `valor`, `id_orgao`, `flag_anexo`, `id_fornecedor`, `id_situacao`)
VALUES
(1 , 'PROCESSO FMS N° 003/2018', 'PREGÃO PRESENCIAL N° 001/2018', STR_TO_DATE('05/03/2018', '%d/%m/%Y'), STR_TO_DATE('16/02/2018', '%d/%m/%Y'), STR_TO_DATE('16/02/2018', '%d/%m/%Y'), 8,
'O Ordenador de Despesas do EM/6ª RPM, no uso das atribuições que lhe competem, torna público que devido a problemas administrativos, o Pregão Eletrônico 01/2018, Processo nº 1251852 01/2018, Unidade de Compra 125 1852, aquisição de ração para os cães do canil do 8º BPM/6ª RPM, está sendo prorrogado, informaremos em breve a nova data do certame. Outras informações à respeito poderão ser obtidas através do email: almox.caa6@gmail.com ou através dos telefones (035) 3829.3222/ 3829-3229.',
'630.500,00', 2, true, 1, 3);

INSERT INTO `prefeitura`.`licitacao` (`id`, `processo`, `edital`, `data_abertura`, `data_homolog`, `data_adjudicacao`, `id_modalidade`, `objeto`, `valor`, `id_orgao`, `flag_anexo`, `id_fornecedor`, `id_situacao`)
VALUES
(2 , 'PROCESSO FMDE N° 004/2018', 'PREGÃO PRESENCIAL N° 004/2018', STR_TO_DATE('05/03/2018', '%d/%m/%Y'), STR_TO_DATE('16/02/2018', '%d/%m/%Y'), STR_TO_DATE('16/02/2018', '%d/%m/%Y'), 8,
'Locação de veículos para prestação de serviços no transporte de alunos que residem na zona rural para o Colégio Estadual Bernardo Sayão por um período estimado de 180 dias.',
'156.779,99', 2, true, 1, 3);


INSERT INTO `prefeitura`.`anexo`(`id`, `id_licitacao`, `tipo_anexo`, `caminho`) VALUES (1, 1, 1, '/pdf/contratos.pdf');
INSERT INTO `prefeitura`.`anexo`(`id`, `id_licitacao`, `tipo_anexo`, `caminho`) VALUES (2, 1, 2, '/pdf/contratos.pdf');
INSERT INTO `prefeitura`.`anexo`(`id`, `id_licitacao`, `tipo_anexo`, `caminho`) VALUES (3, 1, 3, '/pdf/contratos.pdf');
INSERT INTO `prefeitura`.`anexo`(`id`, `id_licitacao`, `tipo_anexo`, `caminho`) VALUES (4, 2, 1, '/pdf/contratos.pdf');
INSERT INTO `prefeitura`.`anexo`(`id`, `id_licitacao`, `tipo_anexo`, `caminho`) VALUES (5, 2, 2, '/pdf/contratos.pdf');
INSERT INTO `prefeitura`.`anexo`(`id`, `id_licitacao`, `tipo_anexo`, `caminho`) VALUES (6, 2, 3, '/pdf/contratos.pdf');
