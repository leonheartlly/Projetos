package br.com.prefeitura.web.domain.jdbc;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.prefeitura.web.model.Licitacao;

@Repository
public class JDBCRepositoryConfig {

	/**
	 * Query 'AND'.
	 */
	protected static final String AND = "AND";

	/**
	 * Query 'WHERE'
	 */
	protected static final String WHERE = "WHERE";

	/**
	 * JDBC Template.
	 */
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	
	/**
	 * Cria a cláusula WHERE do select de acordo com os filtros selecionados
	 * pelo usuário.
	 * 
	 * @param objeto
	 *            Trecho de texto do objeto.
	 * @param dataInicial
	 *            data inicial desejada.
	 * @param dataFinal
	 *            data final desejada.
	 * @param idOrgao
	 *            orgão escolhido.
	 * @param idFornecedor
	 *            fornecedor relacionado à licitação.
	 * @param idModalidade
	 *            modalidade da licitação.
	 * @return Query montada de acordo com o filtro selecionado.
	 */
	protected String createDynamicQuery(Licitacao licitacao, List<Object> selectedFilters) {

		StringBuilder whereQuery = new StringBuilder(WHERE);

		if (StringUtils.isNotBlank(licitacao.getObjeto().trim())) {
			whereQuery.append(" li.objeto LIKE CONCAT('%',?,'%') ");
			selectedFilters.add(licitacao.getObjeto());
		}

		if (StringUtils.isNotBlank(licitacao.getDataInicialVO()) && StringUtils.isNotBlank(licitacao.getDataFinalVO())) {
			checkQuery(whereQuery);
			whereQuery.append(" li.data_abertura BETWEEN ? AND ? ");
			selectedFilters.add(licitacao.getDataInicialVO());
			selectedFilters.add(licitacao.getDataFinalVO());
		} else if (StringUtils.isNotBlank(licitacao.getDataInicialVO()) && StringUtils.isBlank(licitacao.getDataFinalVO())) {
			checkQuery(whereQuery);
			whereQuery.append(" li.data_abertura > ? ");
			selectedFilters.add(licitacao.getDataInicialVO());
		} else if (StringUtils.isNotBlank(licitacao.getDataFinalVO()) && StringUtils.isBlank(licitacao.getDataInicialVO())) {
			checkQuery(whereQuery);
			whereQuery.append(" li.data_abertura < ? ");
			selectedFilters.add(licitacao.getDataFinalVO());
		}

		if (licitacao.getOrgaoVO() != null && licitacao.getOrgaoVO() > 0 && NumberUtils.isDigits(licitacao.getOrgaoVO().toString())) {
			checkQuery(whereQuery);
			whereQuery.append(" li.id_orgao = ? ");
			selectedFilters.add(licitacao.getOrgaoVO());
		}

		if (StringUtils.isNotBlank(licitacao.getFornecedorVO()) && NumberUtils.isDigits(licitacao.getFornecedorVO())) {
			checkQuery(whereQuery);
			whereQuery.append(" li.id_fornecedor = ?");
			selectedFilters.add(licitacao.getFornecedorVO());
		}

		if (licitacao.getModalidadeVO() != null && licitacao.getModalidadeVO() > 0 && NumberUtils.isDigits(licitacao.getModalidadeVO().toString())) {
			checkQuery(whereQuery);
			if (licitacao.getModalidadeVO() > 1) {
				whereQuery.append(" li.id_modalidade = ?");
				selectedFilters.add(licitacao.getModalidadeVO());
			} else {
				whereQuery.append(" li.id_modalidade > ?");
				selectedFilters.add(licitacao.getModalidadeVO());
			}
		}

		return whereQuery.toString();
	}
	
	/**
	 * Adiciona o 'AND' à query quando necessário.
	 * 
	 * @param whereQuery
	 *            query dinamica.
	 */
	protected void checkQuery(StringBuilder whereQuery) {
		if (whereQuery.length() > 5) {
			whereQuery.append(AND);
		}
	}
}
