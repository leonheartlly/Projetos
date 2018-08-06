package br.com.prefeitura.web.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="arquivo_projeto", schema="araguacu")
public class ArquivoProjetoEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="tipo")
	private short tipo;
	
	@Column(name="arquivo")
	private String arquivo;
	
	@Column(name="id_projeto")
	private Long idProjeto;
	
	@Column(name="nome_arquivo")
	private String nomeArquivo;

}
