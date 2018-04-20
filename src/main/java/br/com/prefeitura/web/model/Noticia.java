package br.com.prefeitura.web.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class Noticia {

	private Long id;
	private String noticia;
	private String dataNoticia;
	private Autor autor;
	private Categoria categoria;
	private String titulo;
	private List<ImagemNoticia> imagens;
	
	/**
	 * Construtor com parametros
	 * @param id
	 * @param noticia
	 * @param dataNoticia
	 * @param autor
	 * @param categoria
	 * @param titulo
	 * @param imagens
	 */
	public Noticia(Long id, String noticia, String dataNoticia, Autor autor, Categoria categoria, String titulo,
			List<ImagemNoticia> imagens) {
		super();
		this.id = id;
		this.noticia = noticia;
		this.dataNoticia = dataNoticia;
		this.autor = autor;
		this.categoria = categoria;
		this.titulo = titulo;
		this.imagens = imagens;
	}
	
	/**
	 * 
	 * @param id
	 * @param noticia
	 * @param dataNoticia
	 * @param autor
	 * @param categoria
	 * @param titulo
	 * @param imagens
	 * @return
	 */
	@Builder(builderMethodName = "noticiaBuilder")
	public static Noticia newNoticia(Long id, String noticia, String dataNoticia, Autor autor, Categoria categoria, String titulo,
			List<ImagemNoticia> imagens) {
		return new Noticia(id, noticia, dataNoticia, autor, categoria, titulo, imagens);
	}
}
