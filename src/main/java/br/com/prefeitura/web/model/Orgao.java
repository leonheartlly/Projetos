package br.com.prefeitura.web.model;

import lombok.Data;

@Data
public class Orgao {

	public Orgao(Long id, String orgao){
		this.id = id;
		this.orgao = orgao;
	}
	private Long id;
	private String orgao;
	
	
	public Orgao() {
		super();
	}
	
	
}
