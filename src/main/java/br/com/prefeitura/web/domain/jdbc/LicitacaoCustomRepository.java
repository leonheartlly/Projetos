package br.com.prefeitura.web.domain.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.prefeitura.web.domain.entity.FornecedorEntity;
import br.com.prefeitura.web.domain.entity.LicitacaoEntity;
import br.com.prefeitura.web.domain.entity.ModalidadeEntity;
import br.com.prefeitura.web.domain.entity.OrgaoEntity;
import br.com.prefeitura.web.domain.entity.SituacaoEntity;

@Repository
public class LicitacaoCustomRepository extends JDBCRepositoryConfig {

	private static final Logger LOGGER = Logger.getLogger(LicitacaoCustomRepository.class);

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
	public List<LicitacaoEntity> FindLicitacaoByFilters(String objeto, String dataInicial, String dataFinal,
			Long idOrgao, Long idFornecedor, Long idModalidade) throws Exception{

		String sql = "SELECT li.id, li.processo, li.edital, li.data_abertura, li.data_homolog, li.data_adjudicacao, li.objeto, li.valor, li.flag_anexo, "
				+ "mo.id, mo.desc, " + "fo.id, fo.nome, fo.razao_social, fo.tipo_fornecedor, fo.cpf, fo.cnpj, "
				+ "org.id, org.desc, " + "sit.id, sit.desc " + "FROM " + "licitacao li "
				+ "join fornecedores fo on li.id_fornecedor = fo.id "
				+ "join modalidade_licitacao mo on li.id_modalidade = mo.id "
				+ "join orgaos org on li.id_orgao = org.id " + "join situacao sit on li.id_situacao = sit.id ";

		List<Object> selectedFilters = new ArrayList<>();

		sql += createDynamicQuery(objeto, dataInicial, dataFinal, idOrgao, idFornecedor, idModalidade, selectedFilters);

		try {

			List<LicitacaoEntity> licitacoes = this.jdbcTemplate.query(sql, selectedFilters.toArray(),
					new RowMapper<LicitacaoEntity>() {
						public LicitacaoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
							LicitacaoEntity licitacao = new LicitacaoEntity();
							licitacao.setId(rs.getLong(1));
							licitacao.setProcesso(rs.getString(2));
							licitacao.setEdital(rs.getString(3));
							licitacao.setDataAbertura(rs.getString(4));
							licitacao.setDataHomolog(rs.getString(5));
							licitacao.setDataAdjudicacao(rs.getString(6));
							licitacao.setObjeto(rs.getString(7));
							licitacao.setValor(rs.getString(8));
							licitacao.setPossuiAnexo(rs.getBoolean(9));

							ModalidadeEntity modalidade = new ModalidadeEntity();
							modalidade.setId(rs.getLong(10));
							modalidade.setDescricao(rs.getString(11));
							licitacao.setModalidade(modalidade);

							FornecedorEntity fornecedor = new FornecedorEntity();
							fornecedor.setId(rs.getLong(12));
							fornecedor.setNome(rs.getString(13));
							fornecedor.setRazaoSocial(rs.getString(14));
							fornecedor.setTipoFornecedor(rs.getString(15));
							fornecedor.setCpf(rs.getString(16));
							fornecedor.setCnpj(rs.getString(17));
							licitacao.setFornecedor(fornecedor);

							OrgaoEntity orgao = new OrgaoEntity();
							orgao.setId(rs.getLong(18));
							orgao.setOrgao(rs.getString(19));
							licitacao.setOrgao(orgao);

							SituacaoEntity situacao = new SituacaoEntity();
							situacao.setId(rs.getLong(20));
							situacao.setDescricao(rs.getString(21));
							licitacao.setSituacao(situacao);

							return licitacao;
						}
					});
			LOGGER.info("[LOG-INFO] " + LicitacaoCustomRepository.class.getSimpleName()
					+ " A CONSULTA COSTUMIZADA COM FILTROS FOI EFETUADA. " + licitacoes.size()
					+ " RESULTADOS OBTIDOS.");
			return licitacoes;
		} catch (DataAccessException dae) {
			LOGGER.error("[LOG-ERROR] " + LicitacaoCustomRepository.class.getSimpleName()
					+ " ERRO DE SQL AO EFETUAR A CONSULTA SOLICITADA. " + dae);
			throw dae;
		} catch (Exception ex) {
			LOGGER.error("[LOG-ERROR] " + LicitacaoCustomRepository.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA SOLICITADA. " + ex);
			throw ex;
		}
	}

	

	

}
