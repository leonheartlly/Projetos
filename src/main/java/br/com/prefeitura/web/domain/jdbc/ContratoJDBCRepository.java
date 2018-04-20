package br.com.prefeitura.web.domain.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.prefeitura.web.domain.entity.ContratoEntity;
import br.com.prefeitura.web.domain.entity.FornecedorEntity;
import br.com.prefeitura.web.domain.entity.OrgaoEntity;

@Repository
public class ContratoJDBCRepository{

	private static final Logger LOGGER = Logger.getLogger(ContratoJDBCRepository.class);
	
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
	 * Obtém o resultado de uma consulta costumizada de acordo com os filtros
	 * utilizados.
	 * 
	 * @param objeto
	 *            objeto informado.
	 * @param dataInicial
	 *            data inicial informada.
	 * @param dataFinal
	 *            data final informada.
	 * @param idOrgao
	 *            id do Orgão informado.
	 * @param idFornecedor
	 *            id do Fornecedor informado.
	 * @param idModalidade
	 *            id da Modalidade informada.
	 * @return lista de licitacoes encontradas.
	 */
	public List<ContratoEntity> findContratoByFilters(String dataInicial, String dataFinal,
			Long idOrgao, Long idFornecedor) throws Exception{

		String sql = "SELECT con.id, con.numero, con.data_assinatura, con.data_vigencia, con.data_publicacao, con.valor, "
				+ "fo.id, fo.nome, fo.razao_social, fo.tipo_fornecedor, fo.cpf, fo.cnpj, "
				+ "org.id, org.desc, " 
				+ "FROM " 
				+ "contratos con "
				+ "join fornecedores fo on li.id_fornecedor = fo.id "
				+ "join orgaos org on li.id_orgao = org.id ";

		List<Object> selectedFilters = new ArrayList<>();

		sql += createDynamicQuery(dataInicial, dataFinal, idOrgao, idFornecedor, selectedFilters);

		try {

			List<ContratoEntity> licitacoes = this.jdbcTemplate.query(sql, selectedFilters.toArray(),
					new RowMapper<ContratoEntity>() {
						public ContratoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
							ContratoEntity contrato = new ContratoEntity();
							contrato.setId(rs.getLong(1));
							contrato.setDataAssinatura(rs.getString(4));
							contrato.setDataVigencia(rs.getString(5));
							contrato.setDataPublicacao(rs.getString(6));
							contrato.setValor(rs.getString(8));

							FornecedorEntity fornecedor = new FornecedorEntity();
							fornecedor.setId(rs.getLong(12));
							fornecedor.setNome(rs.getString(13));
							fornecedor.setRazaoSocial(rs.getString(14));
							fornecedor.setTipoFornecedor(rs.getString(15));
							fornecedor.setCpf(rs.getString(16));
							fornecedor.setCnpj(rs.getString(17));
							contrato.setFornecedor(fornecedor);

							OrgaoEntity orgao = new OrgaoEntity();
							orgao.setId(rs.getLong(18));
							orgao.setOrgao(rs.getString(19));
							contrato.setOrgao(orgao);

							return contrato;
						}
					});
			
			return licitacoes;
			
		} catch (DataAccessException dae) {
			LOGGER.error("[LOG-ERROR] " + ContratoJDBCRepository.class.getSimpleName()
					+ " ERRO DE SQL AO EFETUAR A CONSULTA SOLICITADA. " + dae);
			throw dae;
		} catch (Exception ex) {
			LOGGER.error("[LOG-ERROR] " + ContratoJDBCRepository.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA SOLICITADA. " + ex);
			throw ex;
		}
	}
	
	
	protected String createDynamicQuery(String dataInicial, String dataFinal, Long idOrgao,
			Long idFornecedor, List<Object> selectedFilters) {

		StringBuilder whereQuery = new StringBuilder(WHERE);

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

		return whereQuery.toString();
	}
	
	/**
	 * Adiciona o 'AND' à query quando necessário.
	 * 
	 * @param whereQuery
	 *            query dinamica.
	 */
	private void checkQuery(StringBuilder whereQuery) {
		if (whereQuery.length() > 5) {
			whereQuery.append(AND);
		}
	}
}
