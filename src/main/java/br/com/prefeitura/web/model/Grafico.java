package br.com.prefeitura.web.model;

import br.com.prefeitura.web.utils.GraphEnum;
import lombok.Builder;
import lombok.Data;

@Data
public class Grafico {

	private Long id;
	private Double receita;
	private Double despesa;
	private String data;
	private GraphEnum tipoGrafico;
	private Orgao orgao;
	
	private String mes;
	private int numeroMes;
	private short year;
	
	public Grafico(Long id, Double receita, Double despesa, String data, GraphEnum tipoGrafico, Orgao orgao) {
		super();
		this.id = id;
		this.receita = receita;
		this.despesa = despesa;
		this.data = data;
		this.tipoGrafico = tipoGrafico;
		this.orgao = orgao;
	}
	
	
	
	public Grafico() {
	}

	@Builder(builderMethodName = "graphBuilder")
	public static Grafico newGraph(Long id, Double receita, Double despesa, String data, GraphEnum tipoGrafico, Orgao orgao) {
		return new Grafico(id, receita, despesa, data, tipoGrafico, orgao);
	}
}
