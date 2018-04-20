package br.com.prefeitura.web.model;

import lombok.Data;

@Data
public class Grupo {

	public Grupo(Long codigo, String description) {
		this.codigo = codigo;
		this.descricao = description;
	}
	private Long codigo;
	private String nome;
	private String descricao;
	private Boolean checked;
}
