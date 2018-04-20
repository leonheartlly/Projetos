package br.com.prefeitura.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.prefeitura.web.domain.entity.ContratoEntity;
import br.com.prefeitura.web.domain.jdbc.ContratoJDBCRepository;
import br.com.prefeitura.web.model.Contrato;
import br.com.prefeitura.web.model.Fornecedor;
import br.com.prefeitura.web.repository.ContratoRepository;
import br.com.prefeitura.web.utils.PortalPrefeituraUtils;

@Service
public class ContratoService extends ServiceHelper {

	@Autowired
	private ContratoRepository contratoRepository;

	private static final Logger LOGGER = Logger.getLogger(ContratoService.class);

	@Autowired
	private ContratoJDBCRepository contratoJdbc;

	/**
	 * Consulta usuarios cadastrados.
	 * 
	 * @return
	 */
	public List<Contrato> consultarTodosContratos() {

		List<Contrato> contratos = new ArrayList<>();

		try {
			List<ContratoEntity> licitacoesEntity = this.contratoRepository.findAll();

			contratos = convertContratoObject(licitacoesEntity);
			
			formatDate(contratos);
			
			return contratos;
		} catch (DataAccessException dae) {
			LOGGER.error("[LOG-ERROR] " + ContratoService.class.getSimpleName() + " ERRO DE ACESSO AOS DADOS. " + dae);
			throw dae;
		} catch (Exception e) {
			LOGGER.error("[LOG-ERROR] " + ContratoService.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
			throw e;
		}

	}

	/**
	 * Pesquisa licitações através dos dados informados no filtro.
	 * 
	 * @param contrato
	 * @return
	 */
	public List<Contrato> pesquisarLicitacoes(Contrato contrato) {

		List<Contrato> contratos = new ArrayList<>();

		checkAppliedFilters(contrato);

		List<ContratoEntity> contratoEntity;
		try {
			contratoEntity = contratoJdbc.findContratoByFilters(contrato.getDataInicialVO(), contrato.getDataFinalVO(),
					contrato.getOrgaoVO(), contrato.getFornecedor().getId());

			contratos = convertContratoObject(contratoEntity);
			formatDate(contratos);
		} catch (Exception e) {
			LOGGER.error("[LOG-ERROR] " + ContratoService.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
		}

		return contratos;
	}

	/**
	 * Efetua análises e validações dos campos enviados para consulta através do
	 * filtro de licitações.
	 * 
	 * @param contrato
	 *            Objeto licitação.
	 */
	private void checkAppliedFilters(Contrato contrato) {

		Fornecedor fornecedor = findFornecedor(contrato.getCnpjVO(), contrato.getFornecedorVO());
		contrato.setFornecedor(fornecedor);

		String dataInicialFormatada = PortalPrefeituraUtils.converDateDBFormat(contrato.getDataInicialVO());
		String dataFinalFormatada = PortalPrefeituraUtils.converDateDBFormat(contrato.getDataFinalVO());

		if (StringUtils.isNotBlank(dataInicialFormatada)) {
			contrato.setDataInicialVO(dataInicialFormatada);
		}

		if (StringUtils.isNotBlank(dataFinalFormatada)) {
			contrato.setDataFinalVO(dataFinalFormatada);
		}
	}

	/**
	 * Converte uma data do formato da base de dados para o formato padrão.
	 * @param contratos lista de contratos.
	 */
	private void formatDate(List<Contrato> contratos){
		contratos.forEach(contrato ->{
			contrato.setDataAssinatura(PortalPrefeituraUtils.convertDateFromDB(contrato.getDataAssinatura()));
			contrato.setDataPublicacao(PortalPrefeituraUtils.convertDateFromDB(contrato.getDataPublicacao()));
			contrato.setDataVigencia(PortalPrefeituraUtils.convertDateFromDB(contrato.getDataVigencia()));
		});
	}
}
