package br.com.prefeitura.web.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="imagem_noticia", schema="prefeitura")
public class ImagemNoticiaEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="caminho_imagem")
	private String caminho;
	
	@Column(name="nome_imagem")
	private String nome;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="id_noticia")
	private Long idNoticia;

}
