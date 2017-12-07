package br.com.prefeitura.web.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="tb_grupo", schema="prefeitura")
@Entity
public class GrupoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_grupo")
	private Long codigo;
	
	@Column(name="ds_nome")
	private String name;
	
	@Column(name="ds_descricao")
	private String description;

	@ManyToMany
	@JoinTable(name="TB_USUARIO_X_GRUPO", joinColumns=@JoinColumn(name="id_grupo", referencedColumnName="id_grupo"), 
	inverseJoinColumns=@JoinColumn(name="id_usuario", referencedColumnName="id_usuario"))
	private List<UsuarioEntity> usuarios;
	
	@ManyToMany
	@JoinTable(name="TB_PERMISSAO_X_GRUPO", joinColumns=@JoinColumn(name="id_grupo",referencedColumnName="id_grupo"),
			inverseJoinColumns=@JoinColumn(name="id_permissao", referencedColumnName="id_permissao"))
	private List<PermissaoEntity> permissoes;
	
}
