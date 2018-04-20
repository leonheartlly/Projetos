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
@Table(name="licitacao", schema="prefeitura")
@Entity
public class LicitacaoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="processo")
	private String processo;
	
	@Column(name="edital")
	private String edital;
	
	@Column(name="data_abertura")
	private String dataAbertura;
	
	@Column(name="data_homolog")
	private String dataHomolog;
	
	@Column(name="data_adjudicacao")
	private String dataAdjudicacao;
	
	@ManyToOne
	@JoinColumn(name="id_modalidade")
	private ModalidadeEntity modalidade;
	
	@Column(name="objeto")
	private String objeto;
	
	@Column(name="valor")
	private String valor;
	
	@ManyToOne
	@JoinColumn(name="id_orgao")
//	@JoinTable(name="orgaos", joinColumns=@JoinColumn(name="id_orgao",referencedColumnName="id"))
	private OrgaoEntity orgao;
	
	@Column(name="flag_anexo")
	private boolean possuiAnexo;
	
//	@ManyToOne
//	@JoinTable(name="fornecedores", joinColumns=@JoinColumn(name="id_fornecedor",referencedColumnName="id" ))
//	inverseJoinColumns=@JoinColumn(name="id_permissao", referencedColumnName="id_permissao"))
	@ManyToOne
	@JoinColumn(name="id_fornecedor")
//	@Column(name="id_fornecedor")
	private FornecedorEntity fornecedor;
	
	@ManyToOne
	@JoinColumn(name="id_situacao")
	private SituacaoEntity situacao;
	
}
