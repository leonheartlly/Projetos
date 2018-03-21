package br.com.prefeitura.web.domain.model;

import lombok.Data;

@Data
public class Licitacao {

	private Long id;
	private String processo;
	private String edital;
	private String dataAbertura;
	private String dataHomolog;
	private String dataAdjudicacao;
	private Modalidade modalidade;
	private String objeto;
	private String valor;
	private Orgao orgao;
	private boolean possuiAnexo;
	private String fornecedor;
	private Situacao situacao;
	
	
	public Licitacao(Long id, String processo, String edital, String dataAbertura, String dataHomolog,
			String dataAdjudicacao, Modalidade modalidade, String objeto, String valor, Orgao orgao, boolean possuiAnexo,
			String fornecedor, Situacao situacaoModel) {
		super();
		this.id = id;
		this.processo = processo;
		this.edital = edital;
		this.dataAbertura = dataAbertura;
		this.dataHomolog = dataHomolog;
		this.dataAdjudicacao = dataAdjudicacao;
		this.modalidade = modalidade;
		this.objeto = objeto;
		this.valor = valor;
		this.orgao = orgao;
		this.possuiAnexo = possuiAnexo;
		this.fornecedor = fornecedor;
		this.situacao = situacaoModel;
	}
	


	
}
