package br.com.prefeitura.web.domain.model;

import lombok.Data;

@Data
public class Modalidade {

	public Modalidade(Long id, String modalidade){
		this.id = id;
		this.modalidade = modalidade;
	}
	
	private Long id;
	private String modalidade;
}
