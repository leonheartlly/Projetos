package br.com.prefeitura.web.model;

import lombok.Data;

@Data
public class GrupoModel {

	public GrupoModel(Long codigo, String description) {
		this.codigo = codigo;
		this.descricao = description;
	}
	private Long codigo;
	private String nome;
	private String descricao;
	private Boolean checked;
}
