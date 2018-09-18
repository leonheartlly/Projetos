package br.com.prefeitura.web.model;

import lombok.Builder;
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
	
	/**
	 * Builder anexos.
	 * @param id
	 * @param idLicitacao licitação
	 * @param tipoAnexo tipo do anexo.
	 * @param caminhoArquivo caminho do arquivo com o nome.
	 * @return novo Anexo.
	 */
	@Builder(builderMethodName = "anexoBuilder")
	public static Anexo newAnexo(Long id, Long idLicitacao, Long tipoAnexo, String caminhoArquivo) {
		return new Anexo(id, idLicitacao, tipoAnexo, caminhoArquivo);
	}
}
