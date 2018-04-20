package br.com.prefeitura.web.model;

import lombok.Builder;
import lombok.Data;

@Data
public class Autor {

	private Long id;
	private String nome;
	private String cargo;
	
	public Autor(Long id, String nome, String cargo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
	}

	public Autor() {
		super();
	}
	
	@Builder(builderMethodName = "autorBuilder")
	public static Autor newAutor(Long id, String nome, String cargo) {
		return new Autor(id, nome, cargo);
	}
}
