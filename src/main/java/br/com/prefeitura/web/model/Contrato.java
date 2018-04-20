package br.com.prefeitura.web.model;

import lombok.Builder;
import lombok.Data;

@Data
public class Contrato {

	private Long id;
	private String numero;
	private String dataAssinatura;
	private String dataVigencia;
	private String dataPublicacao;
	private String valor;
	private String objeto;
	private Fornecedor fornecedor;
	private Orgao orgao;
	
	
	private String dataInicialVO;
	private String dataFinalVO;
	private Long orgaoVO;
	private String cnpjVO;
	private String fornecedorVO;
	
	
	public Contrato(Long id, String numero, String dataAssinatura, String dataVigencia, String dataPublicacao,
			String valor, String objeto, Fornecedor fornecedor, Orgao orgao) {
		this.id = id;
		this.numero = numero;
		this.dataAssinatura = dataAssinatura;
		this.dataVigencia = dataVigencia;
		this.dataPublicacao = dataPublicacao;
		this.valor = valor;
		this.objeto = objeto;
		this.fornecedor = fornecedor;
		this.orgao = orgao;
	}
	
	public Contrato() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @param numero
	 * @param dataAssinatura
	 * @param dataVigencia
	 * @param dataPublicacao
	 * @param valor
	 * @param objeto
	 * @param fornecedor
	 * @param orgao
	 * @return
	 */
	@Builder(builderMethodName = "contratoBuilder")
	public static Contrato newContrato(Long id, String numero, String dataAssinatura, String dataVigencia, String dataPublicacao,
			String valor, String objeto, Fornecedor fornecedor, Orgao orgao) {
		Contrato contrato = new Contrato(id, numero, dataAssinatura, dataVigencia, dataPublicacao, valor, objeto, fornecedor, orgao);
		return contrato;
	}
}
