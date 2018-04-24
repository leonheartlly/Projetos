package br.com.prefeitura.web.utils;

public enum GraphEnum {

	PIE("Receita X Despesa", 1),
	BAR("Folha de Pagamento por Mes", 2),
	LINE("Arrecadação por Mês", 3),
	ESIC("Estatísticas E-SIC", 4),
	DEFAULT("NONE", 5);
	
	/**
	 * Descrição do gráfico.
	 */
	private String descricao;
	
	/**
	 * Código do gráfico.
	 */
	private int tipo;

	/**
	 * Construtor.
	 * @param descricao
	 * @param tipo
	 */
	private GraphEnum(String descricao, int tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
	/**
	 * Obtém a descrição do gráfico.
	 * @return
	 */
	public String grafico(){
		return descricao;
	}
	
	/**
	 * Obtém o código do gráfico.
	 * @return
	 */
	public int tipo(){
		return tipo;
	}

	/**
	 * Retorna o gráfico/descrição de acordo com o valor informado.
	 * @param graphValue código do gráfico.
	 * @return enum graph.
	 */
	public GraphEnum findGraph(int graphValue) {
        switch (graphValue) {
            case 1:
                return PIE;
            case 2:
                return BAR;
            case 3:
                return LINE;
            case 4:
                return ESIC;
            case 5:
            	return DEFAULT;
            default:
                return null;
        }
    }
}
