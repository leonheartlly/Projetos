package br.com.prefeitura.web.vo;

import lombok.Data;

@Data
public class LicitacaoVO {

	private String dataFinal;
	private String dataInicial;
	private String orgao;
	private String modalidade;
	private String fornecedor;
	private String cnpj;
	private String objeto;
}
