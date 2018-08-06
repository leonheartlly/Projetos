package br.com.prefeitura.web.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
public class ArquivoProjeto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private short tipo;
	private String arquivo;
	private Long idProjeto;
	private String nomeArquivo;
	
	public ArquivoProjeto(Long id, short tipo, String arquivo, Long idProjeto, String nomeArquivo) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.arquivo = arquivo;
		this.idProjeto = idProjeto;
		this.nomeArquivo = nomeArquivo;
	}

	@Builder(builderMethodName="arquivoProjetoBuilder")
	public static ArquivoProjeto newArquivoProjeto(Long id, short tipo, String arquivo, Long idProjeto, String nomeArquivo) {
		return new ArquivoProjeto (id, tipo, arquivo, idProjeto, nomeArquivo);
	}
}
