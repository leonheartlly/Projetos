package br.com.prefeitura.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prefeitura.web.domain.entity.OrgaoEntity;
import br.com.prefeitura.web.model.Orgao;
import br.com.prefeitura.web.repository.IOrgaoRepository;

@Service
@Transactional
public class OrgaoService {

	private static final Logger LOGGER = 
		      Logger.getLogger(OrgaoService.class); 
	
	@Autowired
	private IOrgaoRepository iOrgaoRepository;
	

	/**
	 * Consulta grupos cadastrados.
	 * @return lista de grupos.
	 */
	@Transactional(readOnly = true)
	public List<Orgao> consultarOrgaos(){
		
		List<Orgao> orgaoSModel = new ArrayList<>();
		
		//consulta todos os grupos
		List<OrgaoEntity> orgaoEntity = this.iOrgaoRepository.findAll();
		
		if(ArrayUtils.isNotEmpty(orgaoEntity.toArray())){
			LOGGER.info("[LOG-INFO] " + OrgaoService.class.getSimpleName() + " - ORGAOS OBTIDOS.");
			orgaoEntity.forEach(orgao ->{
				orgaoSModel.add(new Orgao(orgao.getId(), orgao.getOrgao()));
			});
		}else{
			LOGGER.warn("[LOG-WARN]" + OrgaoService.class.getSimpleName() + " - NAO FOI POSSIVEL OBTER A LISTA DE ORGAOS CADASTRADOS.", new Exception());
		}
		
		return orgaoSModel;
	}
}
