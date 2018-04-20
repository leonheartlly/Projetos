package br.com.prefeitura.web.model;

import lombok.Builder;
import lombok.Data;

@Data
public class CalendarioEventos {

	private Long id;
	private String titulo;
	private String dataInicio;
	private String dataFim;
	private String horarioInicio;
	private String horarioFim;
	private String rua;
	private String bairro;
	private String numero;
	private String descricao;
	private String imagem;
	
	private byte dia;
	private String mes;
	private short ano;
	
	
	public CalendarioEventos(Long id, String titulo, String dataInicio, String dataFim, String horarioInicio,
			String horarioFim, String rua, String bairro, String numero, String descricao, String imagem) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.horarioInicio = horarioInicio;
		this.horarioFim = horarioFim;
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.descricao = descricao;
		this.imagem = imagem;
	}


	public CalendarioEventos() {
		super();
	}
	
	@Builder(builderMethodName = "calendarBuilder")
	public static CalendarioEventos newCalendarioEventos(Long id, String titulo, String dataInicio, String dataFim, String horarioInicio,
			String horarioFim, String rua, String bairro, String numero, String descricao, String imagem) {
		return new CalendarioEventos(id, titulo, dataInicio, dataFim, horarioInicio,
				 horarioFim, rua, bairro, numero, descricao, imagem);
	}
	
}
