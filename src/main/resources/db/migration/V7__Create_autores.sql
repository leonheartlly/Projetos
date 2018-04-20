CREATE TABLE `prefeitura`.`autor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
COMMENT = 'Tabela de autores de notícias para a Prefeitura de Araguaçu.';


ALTER TABLE `prefeitura`.`noticias` 
CHANGE COLUMN `autor` `id_autor` INT NOT NULL COMMENT 'Autor da noticia.' ;

ALTER TABLE `prefeitura`.`noticias` 
ADD INDEX `id_autor_idx` (`id_autor` ASC);
ALTER TABLE `prefeitura`.`noticias` 
ADD CONSTRAINT `id_autor`
  FOREIGN KEY (`id_autor`)
  REFERENCES `prefeitura`.`autor` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  
  CREATE TABLE `prefeitura`.`imagem_noticia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `caminho_imagem` VARCHAR(125) NOT NULL,
  `nome_imagem` VARCHAR(100) NOT NULL,
  `formato_imagem` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
COMMENT = 'Imagens relacionadas às noticias.';

ALTER TABLE `prefeitura`.`imagem_noticia` 
ADD COLUMN `id_noticia` INT NOT NULL AFTER `descricao`;

ALTER TABLE `prefeitura`.`imagem_noticia` 
ADD INDEX `id_noticia_idx` (`id_noticia` ASC);
ALTER TABLE `prefeitura`.`imagem_noticia` 
ADD CONSTRAINT `id_noticia`
  FOREIGN KEY (`id_noticia`)
  REFERENCES `prefeitura`.`noticias` (`id`);

ALTER TABLE `prefeitura`.`noticias` 
CHANGE COLUMN `titulo` `titulo` VARCHAR(100) NOT NULL COMMENT 'Título da notícia.' ;


INSERT INTO `prefeitura`.`categoria_noticias` (`id`, `descricao`, `categoria`)
VALUES (1 , "Nesta categoria se enquadra notícias científicas e de tecnologia em geral", "Ciência e Tecnologia");

INSERT INTO `prefeitura`.`categoria_noticias` (`id`, `descricao`, `categoria`)
VALUES (2 , "Notícias sobre eventos locais e costumes", "Cultura e Entretenimento");

INSERT INTO `prefeitura`.`categoria_noticias` (`id`, `descricao`, `categoria`)
VALUES (3 , "Desastres naturais e acidentes", "Desastres e acidentes");

INSERT INTO `prefeitura`.`categoria_noticias` (`id`, `descricao`, `categoria`)
VALUES (4 , "Direito dos cidadãos e leis", "Direito");

INSERT INTO `prefeitura`.`categoria_noticias` (`id`, `descricao`, `categoria`)
VALUES (5 , "Economias e negócios em geral", "Economia e Negócios");

INSERT INTO `prefeitura`.`categoria_noticias` (`id`, `descricao`, `categoria`)
VALUES (6 , "Educação", "Educação");

INSERT INTO `prefeitura`.`categoria_noticias` (`id`, `descricao`, `categoria`)
VALUES (7 , "Feiras e eventos livres", "Eventos");

INSERT INTO `prefeitura`.`categoria_noticias` (`id`, `descricao`, `categoria`)
VALUES (8 , "Saúde e assuntos de saúde pública", "Saúde");

INSERT INTO `prefeitura`.`categoria_noticias` (`id`, `descricao`, `categoria`)
VALUES (9 , "Religião e religiosidades", "Religião");

INSERT INTO `prefeitura`.`categoria_noticias` (`id`, `descricao`, `categoria`)
 VALUES (10 , "Transporte e meios de locomoção", "Transporte");
 
INSERT INTO `prefeitura`.`categoria_noticias` (`id`, `descricao`, `categoria`)
VALUES (11 , "Sociedade e assuntos da região", "Sociedade");

INSERT INTO `prefeitura`.`categoria_noticias` (`id`, `descricao`, `categoria`)
VALUES (12 , "Segurança publica", "Segurança");

 
INSERT INTO `prefeitura`.`autor` (`id`, `nome`, `cargo`)
VALUES
(1, "Lucas Ferreira", "Jornalista");

INSERT INTO `prefeitura`.`autor` (`id`, `nome`, `cargo`)
VALUES
(2, "Cleanto Macário", "Profissional de TI");

INSERT INTO `prefeitura`.`autor` (`id`, `nome`, `cargo`)
VALUES
(3, "José das Couves", "Youtuber");


ALTER TABLE `prefeitura`.`imagem_noticia` 
CHANGE COLUMN `formato_imagem` `titulo` VARCHAR(65) NOT NULL ;

ALTER TABLE `prefeitura`.`noticias` 
DROP COLUMN `imagem`;


INSERT INTO `prefeitura`.`noticias` (`id`, `noticia`, `data_noticia`, `id_autor`, `id_categoria`, `titulo`)
VALUES
(1, "A Dança da Chuva, no entanto, era diferente, dependendo da tribo e do local. A Tribo Hopi, dos Estados Unidos, colocavam cobras na boca e sussurravam orações, acreditando que as preces seriam levadas pelos animais aos deuses-serpentes. Além do líder, dois outros índios também dançavam no ritual, em torno de um círculo.

Os Índios Zuni, também dos Estados Unidos, cantavam e dançavam em zigue-zague. Homens e mulheres usavam pulseiras e máscaras, com o peito adornado de pelas e colares.

Alguns povos eslavos também realizavam a dança. As moças jovens usavam máscaras de folhas e gravelhos, e dançavam em frente às casas do seus povoados. Os donos das casas costumavam jogar água sobre as garotas, a fim de pedir chuvas.

Os não-índios e/ou praticantes de religiões 'pagãs' que desejam realizar uma Dança da Chuva, aconselha-se vestir-se de roupas feitas com elementos da natureza, como folhas e galhos de árvores, peles, penas, terra, pedras etc. Deve-se girar em sentido horário, em um campo aberto, com seu próprio canto de chuva, que deve ser rítmico e fácil (ou procure a tribo indígena mais próxima).", STR_TO_DATE('20/01/2018', '%d/%m/%Y'), 1, 1, "Titulo da primeira materia do site");


INSERT INTO `prefeitura`.`noticias` (`id`, `noticia`, `data_noticia`, `id_autor`, `id_categoria`, `titulo`)
VALUES
(2, "Em 2017, as despesas com auxílios- doença por acidente de trabalho no Ceará somaram quase R$ 19 milhões, isso, somente com gastos previdenciários. Os dados são do Ministério Público do Trabalho (MTP). Ao todo foram 4.406 afastamentos no estado, uma redução de 15,5% comparado ao ano anterior. Segundo os pesquisadores, esse número reflete à queda no número de empregados dos últimos anos.

“A ação coordenada do MTP, Superintendência Regional do Trabalho no Ceará e outros órgãos que atuam nessa temática vem buscando a diminuição desses números”, correlata Géorgia Maria Silveira, procurado do MTP. 
Segundo representando do setor produtivo, há também uma parcela de acidentes que ocorre devido negligência por parte dos funcionários que se recuam a utilizar EPIs ou seguir procedimentos de segurança específicos.

Setores mais atingidos 

O levantamento feito pelo MTP apontou que as áreas mais afetadas são as de fabricação de calçados, atendimento hospitalar, construção civil e, especialmente, a indústria de produtos alimentícios.

De 2012 à 2017, o Ceará ficou em décimo segundo num ranking nacional de acidentes de trabalho, registrando 52.612 CATs.

Municípios 

O maior número de emissões de Comunicações de Acidentes veio foi registrado em Fortaleza. Em seguida aparecem as cidades de Maracanaú, Sobral, Caucaia, Horizonte, Juazeiro do Norte, Eusébio e São Gonçalo do Amarante.

Nos seis anos de estudos e análises, foram registrados cerca de 34 mil auxílios- doença em todo o Estado, gerando, nestes cinco anos, um prejuízo de 260 milhões de reais.", STR_TO_DATE('06/04/2018', '%d/%m/%Y'), 2, 5, "Afastamento de trabalhadores gera gasto de R$ 18,6 mi no Ceará");

INSERT INTO `prefeitura`.`imagem_noticia`(`id`, `caminho_imagem`, `nome_imagem`, `titulo`, `descricao`,`id_noticia`)
VALUES (1,"\\img\\" , "news.jpg", "Trabalhadores afetados", "Trabalhadores que faziam derrubada de mata foram gravemente afetados", 2);


INSERT INTO `prefeitura`.`noticias` (`id`, `noticia`, `data_noticia`, `id_autor`, `id_categoria`, `titulo`)
VALUES
(3, "A pena do dono de um posto de combustível que funcionava sem licença ambiental em Araguaçu, na região sul do Tocantins, foi revertida em uma doação para o presídio da cidade. José Sabino de Souza foi condenado porque estabelecimento não tinha a documentação do Ibama. Ele chegou a ser notificado da irregularidade, mas não resolveu o problema.

O caso foi para a Justiça após o empresário desobedecer uma ordem judicial para realizar algumas adequações. A Justiça decidiu que ele era culpado, mas na hora de estabelecer a sentença o Ministério Público Estadual e a Defensoria Pública sugeriram uma pena alternativa.

Ele foi condenado a arcar com os custos de uma reforma na área do banho de sol dos pesos da Cadeia Pública de Araguaçu. O poder judiciário estima que a construção de uma cobertura com grades no local vai custar pouco mais de R$ 7 mil.

A defesa de Souza disse que está satisfeita com o resultado 'A lei prevê este tipo de acordo, pela lei do juizado penal e não é a primeira vez que acontece na comarca', ressaltou a advogada do empresário, Anna Paula Fernanda Lemos.
", STR_TO_DATE('02/04/2018', '%d/%m/%Y'), 1, 4, "Dono de posto que não tinha licença tem pena revertida em doação para presídio");

INSERT INTO `prefeitura`.`imagem_noticia`
(`id`, `caminho_imagem`, `nome_imagem`, `titulo`, `descricao`, `id_noticia`)
VALUES(2, '\\img\\', "ara.jpeg", "Agricultor", "Agricultor ensina seus filhos sua profissão", 2);


INSERT INTO `prefeitura`.`noticias` (`id`, `noticia`, `data_noticia`, `id_autor`, `id_categoria`, `titulo`)
VALUES
(4, "A pena do dono de um posto de combustível que funcionava sem licença ambiental em Araguaçu, na região sul do Tocantins, foi revertida em uma doação para o presídio da cidade. José Sabino de Souza foi condenado porque estabelecimento não tinha a documentação do Ibama. Ele chegou a ser notificado da irregularidade, mas não resolveu o problema.

O caso foi para a Justiça após o empresário desobedecer uma ordem judicial para realizar algumas adequações. A Justiça decidiu que ele era culpado, mas na hora de estabelecer a sentença o Ministério Público Estadual e a Defensoria Pública sugeriram uma pena alternativa.

Ele foi condenado a arcar com os custos de uma reforma na área do banho de sol dos pesos da Cadeia Pública de Araguaçu. O poder judiciário estima que a construção de uma cobertura com grades no local vai custar pouco mais de R$ 7 mil.

A defesa de Souza disse que está satisfeita com o resultado 'A lei prevê este tipo de acordo, pela lei do juizado penal e não é a primeira vez que acontece na comarca', ressaltou a advogada do empresário, Anna Paula Fernanda Lemos.
", STR_TO_DATE('02/04/2017', '%d/%m/%Y'), 1, 4, "Bolsomito desde do pedestal e come asfalto");

INSERT INTO `prefeitura`.`imagem_noticia`
(`id`, `caminho_imagem`, `nome_imagem`, `titulo`, `descricao`, `id_noticia`)
VALUES(3, '\\img\\', "ara.jpeg", "Agricultor", "Agricultor ensina seus filhos sua profissão", 4);

INSERT INTO `prefeitura`.`noticias` (`id`, `noticia`, `data_noticia`, `id_autor`, `id_categoria`, `titulo`)
VALUES
(5, "A pena do dono de um posto de combustível que funcionava sem licença ambiental em Araguaçu, na região sul do Tocantins, foi revertida em uma doação para o presídio da cidade. José Sabino de Souza foi condenado porque estabelecimento não tinha a documentação do Ibama. Ele chegou a ser notificado da irregularidade, mas não resolveu o problema.

O caso foi para a Justiça após o empresário desobedecer uma ordem judicial para realizar algumas adequações. A Justiça decidiu que ele era culpado, mas na hora de estabelecer a sentença o Ministério Público Estadual e a Defensoria Pública sugeriram uma pena alternativa.

Ele foi condenado a arcar com os custos de uma reforma na área do banho de sol dos pesos da Cadeia Pública de Araguaçu. O poder judiciário estima que a construção de uma cobertura com grades no local vai custar pouco mais de R$ 7 mil.

A defesa de Souza disse que está satisfeita com o resultado 'A lei prevê este tipo de acordo, pela lei do juizado penal e não é a primeira vez que acontece na comarca', ressaltou a advogada do empresário, Anna Paula Fernanda Lemos.
", STR_TO_DATE('28/02/2018', '%d/%m/%Y'), 1, 4, "Homem atira em mulher e o tiro acerta a si próprio");

INSERT INTO `prefeitura`.`imagem_noticia`
(`id`, `caminho_imagem`, `nome_imagem`, `titulo`, `descricao`, `id_noticia`)
VALUES(4, '\\img\\', "ara.jpeg", "Agricultor", "Agricultor ensina seus filhos sua profissão", 5);

INSERT INTO `prefeitura`.`noticias` (`id`, `noticia`, `data_noticia`, `id_autor`, `id_categoria`, `titulo`)
VALUES
(6, "A pena do dono de um posto de combustível que funcionava sem licença ambiental em Araguaçu, na região sul do Tocantins, foi revertida em uma doação para o presídio da cidade. José Sabino de Souza foi condenado porque estabelecimento não tinha a documentação do Ibama. Ele chegou a ser notificado da irregularidade, mas não resolveu o problema.

O caso foi para a Justiça após o empresário desobedecer uma ordem judicial para realizar algumas adequações. A Justiça decidiu que ele era culpado, mas na hora de estabelecer a sentença o Ministério Público Estadual e a Defensoria Pública sugeriram uma pena alternativa.

Ele foi condenado a arcar com os custos de uma reforma na área do banho de sol dos pesos da Cadeia Pública de Araguaçu. O poder judiciário estima que a construção de uma cobertura com grades no local vai custar pouco mais de R$ 7 mil.

A defesa de Souza disse que está satisfeita com o resultado 'A lei prevê este tipo de acordo, pela lei do juizado penal e não é a primeira vez que acontece na comarca', ressaltou a advogada do empresário, Anna Paula Fernanda Lemos.
", STR_TO_DATE('28/03/2018', '%d/%m/%Y'), 1, 4, "Mulher da um beijo gostoso e esquece quem é");

INSERT INTO `prefeitura`.`imagem_noticia`
(`id`, `caminho_imagem`, `nome_imagem`, `titulo`, `descricao`, `id_noticia`)
VALUES(5, '\\img\\', "ara.jpeg", "Agricultor", "Agricultor ensina seus filhos sua profissão", 6);

INSERT INTO `prefeitura`.`noticias` (`id`, `noticia`, `data_noticia`, `id_autor`, `id_categoria`, `titulo`)
VALUES
(7, "A pena do dono de um posto de combustível que funcionava sem licença ambiental em Araguaçu, na região sul do Tocantins, foi revertida em uma doação para o presídio da cidade. José Sabino de Souza foi condenado porque estabelecimento não tinha a documentação do Ibama. Ele chegou a ser notificado da irregularidade, mas não resolveu o problema.

O caso foi para a Justiça após o empresário desobedecer uma ordem judicial para realizar algumas adequações. A Justiça decidiu que ele era culpado, mas na hora de estabelecer a sentença o Ministério Público Estadual e a Defensoria Pública sugeriram uma pena alternativa.

Ele foi condenado a arcar com os custos de uma reforma na área do banho de sol dos pesos da Cadeia Pública de Araguaçu. O poder judiciário estima que a construção de uma cobertura com grades no local vai custar pouco mais de R$ 7 mil.

A defesa de Souza disse que está satisfeita com o resultado 'A lei prevê este tipo de acordo, pela lei do juizado penal e não é a primeira vez que acontece na comarca', ressaltou a advogada do empresário, Anna Paula Fernanda Lemos.
", STR_TO_DATE('22/01/2018', '%d/%m/%Y'), 1, 4, "Lucas faz aniversário e está um arraso");

INSERT INTO `prefeitura`.`imagem_noticia`
(`id`, `caminho_imagem`, `nome_imagem`, `titulo`, `descricao`, `id_noticia`)
VALUES(6, '\\img\\', "ara.jpeg", "Agricultor", "Agricultor ensina seus filhos sua profissão", 7);

INSERT INTO `prefeitura`.`imagem_noticia`
(`id`, `caminho_imagem`, `nome_imagem`, `titulo`, `descricao`, `id_noticia`)
VALUES(7, '\\img\\', "ara.jpeg", "Agricultor", "Agricultor ensina seus filhos sua profissão", 1);

INSERT INTO `prefeitura`.`imagem_noticia`
(`id`, `caminho_imagem`, `nome_imagem`, `titulo`, `descricao`, `id_noticia`)
VALUES(8, '\\img\\', "ara.jpeg", "Agricultor", "Agricultor ensina seus filhos sua profissão", 3);


