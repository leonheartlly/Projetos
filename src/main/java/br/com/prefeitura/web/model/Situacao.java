package br.com.prefeitura.web.domain.model;

import lombok.Data;

@Data
public class Situacao {

	private Long id;
	
	private String descricao;
	
	public Situacao(Long id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}
}
