package br.com.prefeitura.web.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="anexo", schema="araguacu")
@Entity
public class AnexoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="id_licitacao")
	private Long idLicitacao;
	
	@Column(name="tipo_anexo")
	private Long tipoAnexo;
	
	@Column(name="caminho")
	private String caminhoArquivo;
}
