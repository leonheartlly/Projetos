package br.com.prefeitura.web.model;

import lombok.Builder;
import lombok.Data;

@Data
public class Legislacao {

	private Long id;
	private String nome;
	private Integer exercicio;
	private Integer mes;
	private String resumo;
	private Orgao orgao;
	private String arquivo;
	private Integer tipo;
	
	
	public Legislacao(Long id, String nome, Integer exercicio, Integer mes, String resumo, Orgao orgao, String arquivo,
			Integer tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.exercicio = exercicio;
		this.mes = mes;
		this.resumo = resumo;
		this.orgao = orgao;
		this.arquivo = arquivo;
		this.tipo = tipo;
	}

	public Legislacao() {
	}
	
	@Builder(builderMethodName = "legislacaoBuilder")
	public static Legislacao newLegislacao(Long id, String nome, Integer exercicio, Integer mes, String resumo, Orgao orgao, String arquivo,
			Integer tipo) {
		return new Legislacao(id, nome, exercicio, mes, resumo, orgao, arquivo, tipo);
	}
}
