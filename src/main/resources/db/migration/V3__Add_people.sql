/* VINCULA PERMISSOES PARA GRUPOS*/
insert into prefeitura.tb_permissao_x_grupo values (1, 3);
insert into prefeitura.tb_permissao_x_grupo values (2, 2); 
insert into prefeitura.tb_permissao_x_grupo values (3, 1); 

/* VINCULA O USUÁRIO AO GRUPO DE ADMINISTRADORES */
insert into prefeitura.tb_usuario_x_grupo values (1, 1);
insert into prefeitura.TB_USUARIO_X_GRUPO values (2, 2); 
insert into prefeitura.TB_USUARIO_X_GRUPO values (3, 1); 

insert into PREFEITURA.TB_USUARIO values(null, 'Administrador', 'admin', '$2a$10$7mt.8OuITJGTsN2dVlsfxel.DpI6z6i4QKZaWzsJAjyLU3eDFf1qe', 0 );

