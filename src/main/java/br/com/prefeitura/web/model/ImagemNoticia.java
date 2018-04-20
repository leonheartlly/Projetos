package br.com.prefeitura.web.model;

import lombok.Builder;
import lombok.Data;

@Data
public class ImagemNoticia {

	private Long id;
	private String caminho;
	private String nome;
	private String titulo;
	private String descricao;
	private Long idNoticia;
	
	public ImagemNoticia(Long id, String caminho, String nome, String titulo, String descricao, Long idNoticia) {
		super();
		this.id = id;
		this.caminho = caminho;
		this.nome = nome;
		this.titulo = titulo;
		this.descricao = descricao;
		this.idNoticia = idNoticia;
	}
	
	@Builder(builderMethodName = "imageBuilder")
	public static ImagemNoticia newImage(Long id, String caminho, String nome, String titulo, String descricao, Long idNoticia) {
		return new ImagemNoticia(id, caminho, nome, titulo, descricao, idNoticia);
	}

	public ImagemNoticia() {
		super();
	}
	
	
}
