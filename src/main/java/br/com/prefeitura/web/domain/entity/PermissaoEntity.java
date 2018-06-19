package br.com.prefeitura.web.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Data
@Entity
@Table(name="tb_permissao", schema="araguacu")
public class PermissaoEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_permissao")
	private Long codigo;
	
	@Column(name="ds_permissao")
	private String permissao;
	
	@Column(name="ds_descricao")
	private String descricao;
	
	@ManyToMany(mappedBy = "permissoes")
	private List<GrupoEntity> grupos;
	

}
