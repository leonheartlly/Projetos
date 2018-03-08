/*CRIA TABELA DE GRUPOS*/
CREATE TABLE PREFEITURA.tb_grupo (
  `id_grupo` int(11) NOT NULL AUTO_INCREMENT,
  `ds_nome` varchar(50) NOT NULL,
  `ds_descricao` varchar(200) NOT NULL,
  PRIMARY KEY (`id_grupo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*CRIA TABELA DE PERMISSÕES*/
CREATE TABLE PREFEITURA.tb_permissao (
  `id_permissao` int(11) NOT NULL AUTO_INCREMENT,
  `ds_permissao` varchar(50) NOT NULL,
  `ds_descricao` varchar(200) NOT NULL,
  PRIMARY KEY (`id_permissao`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*CRIA TABELA DE USUÁRIOS*/
CREATE TABLE PREFEITURA.tb_usuario (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `ds_nome` varchar(60) NOT NULL,
  `ds_login` varchar(60) NOT NULL,
  `ds_senha` varchar(400) NOT NULL,
  `fl_ativo` decimal(1,0) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*CRIA TABELA DE USUÁRIO X GRUPO*/
CREATE TABLE PREFEITURA.tb_usuario_x_grupo (
  `id_usuario` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_grupo`),
  KEY `id_grupo` (`id_grupo`),
  CONSTRAINT `tb_usuario_x_grupo_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`),
  CONSTRAINT `tb_usuario_x_grupo_ibfk_2` FOREIGN KEY (`id_grupo`) REFERENCES `tb_grupo` (`id_grupo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*CRIA TABELA DE PERMISSÃO X GRUPO*/
CREATE TABLE PREFEITURA.tb_permissao_x_grupo (
  `id_permissao` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  PRIMARY KEY (`id_permissao`,`id_grupo`),
  KEY `FK_GRUP_1` (`id_grupo`),
  CONSTRAINT `FK_GRUP_1` FOREIGN KEY (`id_grupo`) REFERENCES `tb_grupo` (`id_grupo`),
  CONSTRAINT `FK_PERM_1` FOREIGN KEY (`id_permissao`) REFERENCES `tb_permissao` (`id_permissao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* CRIA TABELA DE  FORNECEDORES*/
CREATE TABLE PREFEITURA.fornecedores (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `razao_social` varchar(100) DEFAULT NULL,
  `tipo_fornecedor` char(1) NOT NULL COMMENT 'F para pessoa fisica\nJ para pessoa juridica',
  `cpf` varchar(17) DEFAULT NULL,
  `cnpj` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Fornecedores cadastrados.';

/* CRIA TABELA DE MODALIDADE LICITAÇÃO */
CREATE TABLE PREFEITURA.modalidade_licitacao (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Modalidades de licitações possíveis.';

/* CRIA TABELA DE ORGÃOS */
CREATE TABLE PREFEITURA.orgaos (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Orgãos Públicos.';

/* CRIA TABELA DE CATEGORIA-NOTICIAS */
CREATE TABLE PREFEITURA.categoria_noticias (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Categorias possíveis para notícias.	';

/* CRIA TABELA DE SITUACAO */
CREATE TABLE PREFEITURA.situacao (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Situações de licitações e contratos.';

/* CRIA TABELA DE CONTRATOS */
CREATE TABLE PREFEITURA.contratos (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(11) NOT NULL,
  `data_assinatura` date NOT NULL,
  `data_vigencia` date NOT NULL,
  `data_publicacao` date NOT NULL,
  `valor` varchar(20) NOT NULL,
  `id_fornecedor` int(11) NOT NULL,
  `id_orgao` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fornecedor_idx` (`id_fornecedor`),
  KEY `orgao_contrato_idx` (`id_orgao`),
  CONSTRAINT `fornecedor_contratado` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `orgao_contrato` FOREIGN KEY (`id_orgao`) REFERENCES `orgaos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* CRIA TABELA DE NOTICIAS */
CREATE TABLE PREFEITURA.noticias (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `noticia` varchar(5000) NOT NULL,
  `data_noticia` date NOT NULL,
  `autor` varchar(45) NOT NULL,
  `imagem` varchar(200) DEFAULT NULL,
  `id_categoria` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `categoria_idx` (`id_categoria`),
  CONSTRAINT `categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria_noticias` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tabela de noticias do portal da prefeitura de araguaçu.';

/* CRIA TABELA DE LICITACAO */
CREATE TABLE PREFEITURA.licitacao (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `processo` varchar(45) NOT NULL,
  `edital` varchar(45) NOT NULL,
  `data_abertura` date NOT NULL,
  `data_homolog` date DEFAULT NULL,
  `data_adjudicacao` date DEFAULT NULL,
  `id_modalidade` int(11) NOT NULL,
  `objeto` varchar(5000) NOT NULL,
  `valor` varchar(45) NOT NULL,
  `id_orgao` int(11) NOT NULL,
  `flag_anexo` tinyint(4) NOT NULL,
  `id_fornecedor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `modalidade_idx` (`id_modalidade`),
  KEY `orgao_idx` (`id_orgao`),
  KEY `fornecedor_idx` (`id_fornecedor`),
  CONSTRAINT `fornecedor` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `modalidade` FOREIGN KEY (`id_modalidade`) REFERENCES `modalidade_licitacao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `orgao` FOREIGN KEY (`id_orgao`) REFERENCES `orgaos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Licitações disponibilizadas pela prefeitura de Araguaçu.';

/* CRIA TABELA DE ANEXO */
CREATE TABLE PREFEITURA.anexo (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_licitacao` int(11) DEFAULT NULL COMMENT 'Licitação ao qual o anexo está vinculado.\nO campo pode ser nulo já que nem todos os anexos precisam ser de licitação.',
  `tipo_anexo` int(11) DEFAULT NULL COMMENT 'Tipo de anexo referente à licitação, pode ser Edital, Aviso, Retificação de aviso.',
  `caminho` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `licitacao_idx` (`id_licitacao`),
  CONSTRAINT `licitacao` FOREIGN KEY (`id_licitacao`) REFERENCES `licitacao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='caminho dos anexos do site.';
