package br.com.prefeitura.web.model;

import lombok.Data;

@Data
public class Anexo {

	private Long id;
	private Long idLicitacao;
	private Long tipoAnexo;
	private String caminhoArquivo;
	
	/**
	 * Construtor com parametros.
	 * @param id ID do anexo.
	 * @param idLicitacao ID da licitação ao qual o anexo está ligado.
	 * @param tipoAnexo o tipo do anexo (Edital, Aviso, Retificação de aviso).
	 * @param caminhoArquivo caminho ao qual o anexo está ligado.
	 */
	public Anexo(Long id, Long idLicitacao, Long tipoAnexo, String caminhoArquivo) {
		super();
		this.id = id;
		this.idLicitacao = idLicitacao;
		this.tipoAnexo = tipoAnexo;
		this.caminhoArquivo = caminhoArquivo;
	}
	
	
}
