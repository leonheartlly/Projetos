package br.com.prefeitura.web.domain.jdbc;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCRepositoryConfig {

	private static final Logger LOGGER = Logger.getLogger(JDBCRepositoryConfig.class);

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
	protected String createDynamicQuery(String objeto, String dataInicial, String dataFinal, Long idOrgao,
			Long idFornecedor, Long idModalidade, List<Object> selectedFilters) {

		StringBuilder whereQuery = new StringBuilder(WHERE);

		if (StringUtils.isNotBlank(objeto.trim())) {
			whereQuery.append(" li.objeto LIKE CONCAT('%',?,'%') ");
			selectedFilters.add(objeto);
		}

		if (StringUtils.isNotBlank(dataInicial) && StringUtils.isNotBlank(dataFinal)) {
			checkQuery(whereQuery);
			whereQuery.append(" li.data_abertura BETWEEN ? AND ? ");
			selectedFilters.add(dataInicial);
			selectedFilters.add(dataFinal);
		} else if (StringUtils.isNotBlank(dataInicial) && StringUtils.isBlank(dataFinal)) {
			checkQuery(whereQuery);
			whereQuery.append(" li.data_abertura > ? ");
			selectedFilters.add(dataInicial);
		} else if (StringUtils.isNotBlank(dataFinal) && StringUtils.isBlank(dataInicial)) {
			checkQuery(whereQuery);
			whereQuery.append(" li.data_abertura < ? ");
			selectedFilters.add(dataFinal);
		}

		if (NumberUtils.isDigits(idOrgao.toString()) && idOrgao > 0) {
			checkQuery(whereQuery);
			whereQuery.append(" li.id_orgao = ? ");
			selectedFilters.add(idOrgao);
		}

		if (NumberUtils.isDigits(idFornecedor.toString()) && idFornecedor > 0) {
			checkQuery(whereQuery);
			whereQuery.append(" li.id_fornecedor = ?");
			selectedFilters.add(idFornecedor);
		}

		if (NumberUtils.isDigits(idModalidade.toString()) && idModalidade >= 1) {
			checkQuery(whereQuery);
			if (idModalidade > 1) {
				whereQuery.append(" li.id_modalidade = ?");
				selectedFilters.add(idModalidade);
			} else {
				whereQuery.append(" li.id_modalidade > ?");
				selectedFilters.add(idModalidade);
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
