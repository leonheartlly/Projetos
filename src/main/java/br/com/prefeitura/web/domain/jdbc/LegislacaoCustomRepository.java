package br.com.prefeitura.web.domain.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.prefeitura.web.domain.entity.LegislacaoEntity;
import br.com.prefeitura.web.domain.entity.OrgaoEntity;
import br.com.prefeitura.web.model.Legislacao;

@Repository
public class LegislacaoCustomRepository extends JDBCRepositoryConfig {

	private static final Logger LOGGER = Logger.getLogger(LegislacaoCustomRepository.class);

//	/**
//	 * Query 'AND'.
//	 */
//	private static final String AND = "AND";
//
//	/**
//	 * Query 'WHERE'
//	 */
//	private static final String WHERE = "WHERE";

//	/**
//	 * JDBC Template.
//	 */
//	@Autowired
//	private JdbcTemplate jdbcTemplate;

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
	public List<LegislacaoEntity> FindLegislacaoByFilters(Legislacao legislacao) throws Exception{

		String sql = "SELECT le.id, le.nome, le.data, le.resumo, le.id_orgao, le.arquivo, le.tipo, "
				+ "org.id, org.desc "  
				+ "FROM " + "legislacao le "
				+ "join orgaos org on le.id_orgao = org.id ";

		List<Object> selectedFilters = new ArrayList<>();

		sql += createDynamicQuery(legislacao, selectedFilters);

		try {
			List<LegislacaoEntity> licitacoes = this.jdbcTemplate.query(sql, selectedFilters.toArray(),
					new RowMapper<LegislacaoEntity>() {
						public LegislacaoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
							LegislacaoEntity legislacao = new LegislacaoEntity();
							legislacao.setId(rs.getLong(1));
							legislacao.setNome(rs.getString(2));
							legislacao.setData(rs.getString(3));
							legislacao.setResumo(rs.getString(4));
							legislacao.setArquivo(rs.getString(6));
							legislacao.setTipo(rs.getInt(7));

							OrgaoEntity orgao = new OrgaoEntity();
							orgao.setId(rs.getLong(8));
							orgao.setOrgao(rs.getString(9));
							legislacao.setOrgao(orgao);

							return legislacao;
						}
					});
			LOGGER.info("[LOG-INFO] " + LegislacaoCustomRepository.class.getSimpleName()
					+ " A CONSULTA COSTUMIZADA COM FILTROS FOI EFETUADA. " + licitacoes.size()
					+ " RESULTADOS OBTIDOS.");
			return licitacoes;
		} catch (DataAccessException dae) {
			LOGGER.error("[LOG-ERROR] " + LegislacaoCustomRepository.class.getSimpleName()
					+ " ERRO DE SQL AO EFETUAR A CONSULTA SOLICITADA. " + dae);
			throw dae;
		} catch (Exception ex) {
			LOGGER.error("[LOG-ERROR] " + LegislacaoCustomRepository.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA SOLICITADA. " + ex);
			throw ex;
		}
	}

	private String createDynamicQuery(Legislacao legislacao, List<Object> selectedFilters){
		
		StringBuilder whereQuery = new StringBuilder(WHERE);
		if(StringUtils.isNotBlank(legislacao.getResumo())){
			whereQuery.append(" le.nome LIKE CONCAT('%',?,'%') OR le.resumo LIKE CONCAT('%',?,'%') ");
			selectedFilters.add(legislacao.getResumo());
			selectedFilters.add(legislacao.getResumo());
		}
		
		if(StringUtils.isNotBlank(legislacao.getDataInicial()) && StringUtils.isNotBlank(legislacao.getDataFinal())){
			checkQuery(whereQuery);
			whereQuery.append(" le.data BETWEEN ? AND ? ");
			selectedFilters.add(legislacao.getDataInicial());
			selectedFilters.add(legislacao.getDataFinal());
		}else if(StringUtils.isNotBlank(legislacao.getDataInicial()) && StringUtils.isBlank(legislacao.getDataFinal())){
			checkQuery(whereQuery);
			whereQuery.append(" le.data > ? ");
			selectedFilters.add(legislacao.getDataInicial());
		}else if(StringUtils.isNotBlank(legislacao.getDataFinal()) && StringUtils.isBlank(legislacao.getDataInicial())){
			checkQuery(whereQuery);
			whereQuery.append(" le.data < ? ");
			selectedFilters.add(legislacao.getDataFinal());
		}
		
		
		if(legislacao.getIdOrgao() != null && NumberUtils.isDigits(legislacao.getIdOrgao().toString()) && legislacao.getIdOrgao() > 0){
			checkQuery(whereQuery);
			whereQuery.append(" le.id_orgao = ? ");
			selectedFilters.add(legislacao.getIdOrgao());
		}
		
		if(legislacao.getTipo() != null && NumberUtils.isDigits(legislacao.getTipo().toString()) && legislacao.getTipo() > 0){
			checkQuery(whereQuery);
			whereQuery.append(" le.tipo = ? ");
			selectedFilters.add(legislacao.getTipo());
		}
		
		return whereQuery.toString();
	}

	

}
