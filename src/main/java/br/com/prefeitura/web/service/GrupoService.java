package br.com.prefeitura.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prefeitura.web.entity.GrupoEntity;
import br.com.prefeitura.web.model.GrupoModel;
import br.com.prefeitura.web.repository.IGrupoRepository;

@Service
@Transactional
public class GrupoService {

	private static final Logger LOGGER = 
		      Logger.getLogger(GrupoService.class); 
	
	@Autowired
	private IGrupoRepository iGrupoRepository;
	
	/**
	 * Consulta grupos cadastrados.
	 * @return lista de grupos.
	 */
	@Transactional(readOnly = true)
	public List<GrupoModel> consultarGrupos(){
		
		List<GrupoModel> gruposModel = new ArrayList<>();
		
		//consulta todos os grupos
		List<GrupoEntity> gruposEntity = this.iGrupoRepository.findAll();
		
		if(ArrayUtils.isNotEmpty(gruposEntity.toArray())){
			LOGGER.info("[LOG-INFO] " + GrupoService.class.getSimpleName() + " - GRUPOS OBTIDOS.");
			gruposEntity.forEach(grupo ->{
				gruposModel.add(new GrupoModel(grupo.getCodigo(), grupo.getDescription()));
			});
		}else{
			LOGGER.warn("[LOG-WARN]" + GrupoService.class.getSimpleName() + " - NAO FOI POSSIVEL OBTER A LISTA DE GRUPOS CADASTRADOS.", new Exception());
		}
		
		return gruposModel;
	}
	
	
}
