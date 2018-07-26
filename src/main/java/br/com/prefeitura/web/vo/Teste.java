package br.com.prefeitura.web.vo;

import lombok.Data;

@Data
public class Teste {

	private int idOrgao;
	private int tipo;
	
	public Teste() {
	}

	public Teste(int idOrgao, int tipo) {
		super();
		this.idOrgao = idOrgao;
		this.tipo = tipo;
	}
	
	
}
