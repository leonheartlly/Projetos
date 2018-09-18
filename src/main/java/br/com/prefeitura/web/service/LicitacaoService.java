package br.com.prefeitura.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.prefeitura.web.domain.entity.LicitacaoEntity;
import br.com.prefeitura.web.domain.jdbc.LicitacaoCustomRepository;
import br.com.prefeitura.web.model.Fornecedor;
import br.com.prefeitura.web.model.Licitacao;
import br.com.prefeitura.web.repository.FornecedorRepository;
import br.com.prefeitura.web.repository.LicitacaoRepository;
import br.com.prefeitura.web.repository.OrgaoRepository;
import br.com.prefeitura.web.utils.PortalPrefeituraUtils;

@Service
public class LicitacaoService extends ServiceHelper{

	@Autowired
	private OrgaoRepository orgaoRepository;
	
	@Autowired
	private LicitacaoRepository licitacaoRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	private LicitacaoCustomRepository licitacaoCustom;
	
	
	private static final Logger LOGGER = 
		      Logger.getLogger(LicitacaoService.class); 
	
	/**
	 * Consulta usuarios cadastrados.
	 * @return
	 */
	 public List<Licitacao> consultarLicitacoes(){
		 
		 List<Licitacao> licitacoes = new ArrayList<>();
		 
		 try{
			 List<LicitacaoEntity> licitacoesEntity = this.licitacaoRepository.findAll();
			 licitacoes = convertLicitacaoObject(licitacoesEntity);
			 formatDate(licitacoes);
			 return licitacoes;
		 }catch(DataAccessException dae){
			 LOGGER.error("[LOG-ERROR] " + LicitacaoService.class.getSimpleName()
						+ " ERRO DE ACESSO AOS DADOS. " + dae);
			 throw dae;
		 }catch(Exception e){
			 LOGGER.error("[LOG-ERROR] " + LicitacaoService.class.getSimpleName()
						+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
			 throw e;
		 }
		 
	 }
	 
	 /**
	  * Pesquisa licitações através dos dados informados no filtro.
	  * @param licitacao
	  * @return
	  */
	public List<Licitacao> pesquisarLicitacoes(Licitacao licitacao) {

		List<Licitacao> licitacoes = new ArrayList<>();

		analiseFiltrosAplicados(licitacao);

		List<LicitacaoEntity> licitacaoEntity;
		try {
			licitacaoEntity = licitacaoCustom.FindLicitacaoByFilters(licitacao);
			
			licitacoes = convertLicitacaoObject(licitacaoEntity);
			formatDate(licitacoes);
		} catch (Exception e) {
			LOGGER.error("[LOG-ERROR] " + LicitacaoService.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
		}
		 
		 return licitacoes;
	 }

	 /**
	  * Efetua análises e validações dos campos enviados para consulta através do filtro de licitações.
	  * @param licitacao Objeto licitação.
	  */
	private void analiseFiltrosAplicados(Licitacao licitacao) {
		
		 Fornecedor fornecedor = findFornecedor(licitacao.getCnpjVO(), licitacao.getFornecedorVO());
		 licitacao.setFornecedor(fornecedor);
		 
		 String dataInicialFormatada = PortalPrefeituraUtils.converDateDBFormat(licitacao.getDataInicialVO());
		 String dataFinalFormatada = PortalPrefeituraUtils.converDateDBFormat(licitacao.getDataFinalVO());
		 
		 if(StringUtils.isNotBlank(dataInicialFormatada)){
			 licitacao.setDataInicialVO(dataInicialFormatada);
		 }
//		 else{
//			 licitacao.setDataInicialVO(this.dataInicial);
//			 LOGGER.info("[LOG-WARN] "+ LicitacaoService.class.getSimpleName() +" O CAMPO DATA INICIAL NÃO FOI PREENCHIDO OU NÃO ESTÁ NO PADRÃO NECESSÁRIO. " + this.dataInicial);
//		 }
		 
		 if(StringUtils.isNotBlank(dataFinalFormatada)){
			 licitacao.setDataFinalVO(dataFinalFormatada);
		 }
//		 else{
//			 licitacao.setDataFinalVO(this.dataFinal);
//			 LOGGER.info("[LOG-WARN] "+ LicitacaoService.class.getSimpleName() +" O CAMPO DATA FINAL NÃO FOI PREENCHIDO OU NÃO ESTÁ NO PADRÃO NECESSÁRIO. " + this.dataFinal);
//		 }
//		 
		 if(StringUtils.isNotBlank(licitacao.getObjeto())){
			 boolean isValid = PortalPrefeituraUtils.objectIsValid(licitacao.getObjeto());
			 if(!isValid){
				 licitacao.setObjeto("");
				 LOGGER.info("[LOG-WARN] "+ LicitacaoService.class.getSimpleName() +" O CAMPO OBJETO NÃO ESTÁ NO PADRÃO NECESSÁRIO OU ESTÁ USANDO UM PADRÃO DE BUSCA COM UM CÓDIGO QUE OFERECE RISCO À BASE DE DADOS. " + licitacao.getObjeto());
			 }
		 }
	}
	
	/**
	 * Converte datas do formato da base de dados para o formato padrão.
	 * @param licitacoes lista de licitações.
	 */
	private void formatDate(List<Licitacao> licitacoes){
		licitacoes.forEach(licitacao ->{
			licitacao.setDataAbertura(PortalPrefeituraUtils.convertDateFromDB(licitacao.getDataAbertura()));
			licitacao.setDataHomolog(PortalPrefeituraUtils.convertDateFromDB(licitacao.getDataHomolog()));
			licitacao.setDataAdjudicacao(PortalPrefeituraUtils.convertDateFromDB(licitacao.getDataAdjudicacao()));
		});
	}
	
}
