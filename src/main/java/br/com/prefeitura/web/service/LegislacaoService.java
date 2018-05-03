package br.com.prefeitura.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.prefeitura.web.domain.entity.LegislacaoEntity;
import br.com.prefeitura.web.domain.jdbc.LegislacaoCustomRepository;
import br.com.prefeitura.web.model.Legislacao;
import br.com.prefeitura.web.model.Orgao;
import br.com.prefeitura.web.repository.LegislacaoRepository;

@Service
public class LegislacaoService extends ServiceHelper{

//	@Autowired
//	private OrgaoRepository orgaoRepository;
//	
	@Autowired
	private LegislacaoRepository legislacaoRepository;
	
	@Autowired
	private LegislacaoCustomRepository legislacaoCustomRepository;
	
	private static final Logger LOGGER = 
		      Logger.getLogger(LegislacaoService.class); 
	
	/**
	 * Consulta usuarios cadastrados.
	 * @return
	 * @throws Exception 
	 */
	 public List<Legislacao> findLegislacaoByFormFilters(Legislacao legislacao) {
		 
		 List<Legislacao> legislacoes = new ArrayList<>();
		 
		 if(ObjectUtils.isEmpty(legislacao.getOrgao())){
			 legislacao.setOrgao(new Orgao());
		 }
		 
		 try{
			List<LegislacaoEntity> legislacoesEntity = this.legislacaoCustomRepository.FindLegislacaoByFilters(
					legislacao.getNome(), legislacao.getDataInicial(), legislacao.getDataFinal(),
					legislacao.getIdOrgao(), legislacao.getTipo());
			
			 legislacoes = convertLegislacaoObject(legislacoesEntity);
		 }catch(Exception e){
			 LOGGER.error("[LOG-ERROR] " + LegislacaoService.class.getSimpleName()
						+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
		 }
		 return legislacoes;
	 }
	 
	 /**
		 * Consulta usuarios cadastrados.
		 * @return
		 * @throws Exception 
		 */
		 public List<Legislacao> findAll() {
			 
			 List<Legislacao> legislacoes = new ArrayList<>();
			 
			 
			 try{
				List<LegislacaoEntity> legislacoesEntity = this.legislacaoRepository.findAll();
				
				 legislacoes = convertLegislacaoObject(legislacoesEntity);
			 }catch(Exception e){
				 LOGGER.error("[LOG-ERROR] " + LegislacaoService.class.getSimpleName()
							+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
			 }
			 return legislacoes;
		 }
}
