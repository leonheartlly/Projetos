package br.com.prefeitura.web.utils;

public enum LegislacaoEnum {

	PPA("PLANO PLURIANUAL - PPA", 1),
	LDO("LEI DIRETRIZES ORÇAMENTÁRIAS - LDO", 2),
	LOA("LEI ORÇAMENTÁRIA ANUAL - LOA", 3),
	QDD("QUADRO DETALHADO DE DESPESA - QDD", 4),
	EOR("EXECUÇÃO ORÇAMENTARIA - EOR", 5),
	FIN("FINANCEIRO", 6),
	PAT("PATRIMONIAL", 7),
	REC("RECEITAS", 8),
	ARC("ARRECADADAS", 9),
	PCT("PRESTACAO DE CONTAS", 10),
	RREO("REL. RESUMIDO EXEC. ORÇAMENTÁRIA - RREO", 11),
	RGF("REL. GESTÃO FISCAL - RGF", 12),
	DEFAULT("Default", 13);
	
	/**
	 * Descrição da legislação.
	 */
	private String descricao;
	
	/**
	 * Código da legislação.
	 */
	private int tipo;

	/**
	 * Construtor.
	 * @param descricao
	 * @param tipo
	 */
	private LegislacaoEnum(String descricao, int tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}
	
	/**
	 * Obtém a descrição da legislação.
	 * @return
	 */
	public String grafico(){
		return descricao;
	}
	
	/**
	 * Obtém o código da legislação.
	 * @return
	 */
	public int tipo(){
		return tipo;
	}

	/**
	 * Retorna a legislação/descrição de acordo com o valor informado.
	 * @param Value código da legislação.
	 * @return enum.
	 */
	public LegislacaoEnum findLegislacao(int graphValue) {
        switch (graphValue) {
            case 1:
                return PPA;
            case 2:
                return LDO;
            case 3:
                return LOA;
            case 4:
                return QDD;
            case 5:
                return EOR;
            case 6:
                return FIN;
            case 7:
                return PAT;
            case 8:
                return REC;
            case 9:
                return ARC;
            case 10:
                return PCT;
            case 11:
                return RREO;
            case 12:
            	return RGF;
            default:
                return DEFAULT;
        }
    }
}
