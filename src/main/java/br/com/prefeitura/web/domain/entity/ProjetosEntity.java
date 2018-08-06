package br.com.prefeitura.web.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="projetos", schema="araguacu")
public class ProjetosEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="foto_capa")
	private String fotoCapa;
	
	@Column(name="id_secretaria")
	private Long idSecretaria;

}
