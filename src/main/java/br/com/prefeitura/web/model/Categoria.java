package br.com.prefeitura.web.model;

import lombok.Builder;
import lombok.Data;

@Data
public class Categoria {

	private Long id;
	private String descricao;
	private String categoria;
	
	public Categoria(Long id, String descricao, String categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.categoria = categoria;
	}

	public Categoria() {
		super();
	}
	
	@Builder(builderMethodName = "categoriaBuilder")
	public static Categoria newCategoria(Long id, String descricao, String categoria) {
		return new Categoria(id, descricao, categoria);
	}
}
