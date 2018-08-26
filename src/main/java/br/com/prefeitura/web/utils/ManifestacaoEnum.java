package br.com.prefeitura.web.utils;

/**
 * 
 * @author Lucas
 *
 */
public enum ManifestacaoEnum {

	SUGESTAO("Sugestão", 1),
	RECLAMACAO("Reclamação", 2),
	DENUNCIA("Denúncia", 3),
	ELOGIO("Elogio", 4);
	
	/**
	 * Descrição da manifestação.
	 */
	private String descricao;
	
	private int tipo;

	
	private ManifestacaoEnum(String descricao, int tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
	/**
	 * Obtém a descrição da manifestação solicitada.
	 * @return
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Obtém a descrição da manifestação solicitada.
	 * @return
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * Retorna a manifestação/descrição de acordo com o valor informado.
	 * @param código da manifestação.
	 * @return enum manifestação.
	 */
	public ManifestacaoEnum findManifestation(int tipo) {
        switch (tipo) {
            case 1:
                return SUGESTAO;
            case 2:
                return RECLAMACAO;
            case 3:
                return DENUNCIA;
            case 4:
                return ELOGIO;
            default:
            	return null;
        }
    }
	
}
