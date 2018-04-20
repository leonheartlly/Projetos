package br.com.prefeitura.web.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="noticias", schema="prefeitura")
public class NoticiaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="noticia")
	private String noticia;
	
	@Column(name="data_noticia")
	private String dataNoticia;
	
	@ManyToOne
	@JoinColumn(name="id_autor")
	private AutorEntity autor;
	
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private CategoriaEntity categoria;
	
	@Column(name="titulo")
	private String titulo;
	
}
