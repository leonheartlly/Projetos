package br.com.prefeitura.web.model;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Builder;
import lombok.Data;

/**
 * Modelo Licitação. 
 * @author Lucas.
 *
 */
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
	private Fornecedor fornecedor;
	private Situacao situacao;
	
	private String dataFinalVO;
	private String dataInicialVO;
	private String cnpjVO;
	private Long orgaoVO;
	private Long modalidadeVO;
	private String fornecedorVO;
	private List<Anexo> anexos;
	
	/**
	 * Construtor Padrão.
	 */
	public Licitacao() {
		super();
	}


	public Licitacao(Long id, String processo, String edital, String dataAbertura, String dataHomolog,
			String dataAdjudicacao, Modalidade modalidade, String objeto, String valor, Orgao orgao, boolean possuiAnexo,
			Fornecedor fornecedor, Situacao situacao) {
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
		this.situacao = situacao;
	}

	/**
	 * Cria uma instancia do objeto Licitação com o padrão Builder.
	 * @param id
	 * @param processo
	 * @param edital
	 * @param dataAbertura
	 * @param dataHomolog
	 * @param dataAdjudicacao
	 * @param modalidade
	 * @param objeto
	 * @param valor
	 * @param orgao
	 * @param possuiAnexo
	 * @param fornecedor
	 * @param situacao
	 * @return
	 */
	@Builder(builderMethodName = "licitacaoBuilder")
	public static Licitacao newLicitacao(Long id, String processo, String edital, String dataAbertura, String dataHomolog,
			String dataAdjudicacao, Modalidade modalidade, String objeto, String valor, Orgao orgao, boolean possuiAnexo,
			Fornecedor fornecedor, Situacao situacao){
		 Licitacao licitacao = new Licitacao(id, processo, edital, dataAbertura, dataHomolog, dataAdjudicacao, modalidade, objeto, valor, orgao, possuiAnexo, fornecedor, situacao);
		 return licitacao;
	}
	
	public boolean isLicitacaoEmpty(){
		
		if(modalidadeVO == null && orgaoVO == null && StringUtils.isEmpty(dataFinalVO) && StringUtils.isEmpty(dataInicialVO) && StringUtils.isEmpty(fornecedorVO) && StringUtils.isEmpty(cnpjVO) && StringUtils.isEmpty(objeto) ){
			return true;
		}
		return false;
	}
}
