package br.com.prefeitura.web.model;

import lombok.Builder;
import lombok.Data;

@Data
public class Legislacao {

	private Long id;
	private String nome;
	private String data;
	private String resumo;
	private Orgao orgao;
	private String arquivo;
	private Integer tipo;
	
	private Long idOrgao;
	private Integer exercicio;
	private Integer mes;
	private String dataInicial;
	private String dataFinal;
	
	public Legislacao(Long id, String nome, String data, String resumo, Orgao orgao, String arquivo,
			Integer tipo, Integer exercicio, Integer mes) {
		super();
		this.id = id;
		this.nome = nome;
		this.exercicio = exercicio;
		this.mes = mes;
		this.resumo = resumo;
		this.orgao = orgao;
		this.arquivo = arquivo;
		this.tipo = tipo;
		this.data = data;
	}

	public Legislacao() {
	}
	
	@Builder(builderMethodName = "legislacaoBuilder")
	public static Legislacao newLegislacao(Long id, String nome, String data , String resumo, Orgao orgao, String arquivo,
			Integer tipo, Integer exercicio, Integer mes) {
		return new Legislacao(id, nome, data, resumo, orgao, arquivo, tipo, exercicio, mes);
	}
}
