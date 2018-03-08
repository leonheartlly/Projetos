/* INSERTS DE USUÁRIOS */
insert into prefeitura.tb_usuario values(1, 'Lucas Ferreira', 'lferreira', '$2a$10$7mt.8OuITJGTsN2dVlsfxel.DpI6z6i4QKZaWzsJAjyLU3eDFf1qe', 1 );
insert into prefeitura.tb_usuario values(2, 'Lucas Ferreira', 'silva', '$2a$10$DvRDbDj.RLOm2mfgxY2xV.2YWORmxWSBCNGV02pElwjgUKEL2UYua', 1 );
insert into prefeitura.tb_usuario values(3, 'Lucas Ferreira', 'ferreira', '123', 1 );

/* INSERTS DE GRUPOS */
insert into prefeitura.tb_grupo values(1,'ADMINISTRADORES', 'Administrador');
insert into prefeitura.tb_grupo values(2, 'USUARIOS', 'Usuários Comuns');
insert into prefeitura.tb_grupo values(3, 'OPERADORES', 'Usuários Cadastramento');

/* INSERTS DE PERMISSOES */
insert into prefeitura.tb_permissao values (1, 'ROLE_CADASTRO_USUARIO', 'CADASTRO DE NOVOS USUARIOS');
insert into prefeitura.tb_permissao values (2, 'ROLE_CONSULTA_USUARIO', 'CONSULTA DE USUARIOS');
insert into prefeitura.tb_permissao values (3, 'ROLE_ADMIN', 'ADMINISTRAÇÃO COMPLETA DO SISTEMA');

