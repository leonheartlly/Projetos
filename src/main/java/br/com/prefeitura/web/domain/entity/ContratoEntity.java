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
@Table(name="contratos", schema="araguacu")
@Entity
public class ContratoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="numero")
	private String numero;
	
	@Column(name="data_assinatura")
	private String dataAssinatura;
	
	@Column(name="data_vigencia")
	private String dataVigencia;
	
	@Column(name="data_publicacao")
	private String dataPublicacao;
	
	@Column(name="valor")
	private String valor;
	
	@Column(name="objeto")
	private String objeto;
	
	@ManyToOne
	@JoinColumn(name="id_fornecedor")
	private FornecedorEntity fornecedor;
	
	@ManyToOne
	@JoinColumn(name="id_orgao")
	private OrgaoEntity orgao;
	
}
