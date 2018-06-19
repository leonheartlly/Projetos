package br.com.prefeitura.web.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="legislacao", schema="araguacu")
@Entity
public class LegislacaoEntity implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="data")
	private String data;
	
	@Column(name="resumo")
	private String resumo;
	
	@ManyToOne
	@JoinColumn(name="id_orgao")
	private OrgaoEntity orgao;
	
	@Column(name="arquivo")
	private String arquivo;
	
	@Column(name="tipo")
	private Integer tipo;
}
