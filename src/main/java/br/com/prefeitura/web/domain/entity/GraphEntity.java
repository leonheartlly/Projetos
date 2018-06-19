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
@Table(name="grafico", schema="araguacu")
@Entity
public class GraphEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="receita")
	private Double receita;
	
	@Column(name="despesa")
	private Double despesa;
	
	@Column(name="data")
	private String data;
	
	@Column(name="tipo")
	private Integer tipo;
	
	@ManyToOne
	@JoinColumn(name="id_orgao")
	private OrgaoEntity orgao;
	
}
