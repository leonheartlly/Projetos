package br.com.prefeitura.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prefeitura.web.domain.entity.ModalidadeEntity;
import br.com.prefeitura.web.model.Modalidade;
import br.com.prefeitura.web.repository.IModalidadeRespository;

@Service
@Transactional
public class ModalidadeService {


	private static final Logger LOGGER = 
		      Logger.getLogger(ModalidadeService.class); 
	
	@Autowired
	private IModalidadeRespository iModalidadeRespository;
	

	/**
	 * Consulta grupos cadastrados.
	 * @return lista de grupos.
	 */
	@Transactional(readOnly = true)
	public List<Modalidade> consultarModalidades(){
		
		List<Modalidade> modalidadesModel = new ArrayList<>();
		
		//consulta todos os grupos
		List<ModalidadeEntity> modalidadeEntity = this.iModalidadeRespository.findAll();
		
		if(ArrayUtils.isNotEmpty(modalidadeEntity.toArray())){
			LOGGER.info("[LOG-INFO] " + OrgaoService.class.getSimpleName() + " - MODALIADES OBTIDAS.");
			modalidadeEntity.forEach(modalidade ->{
				modalidadesModel.add(new Modalidade(modalidade.getId(), modalidade.getDescricao()));
			});
		}else{
			LOGGER.warn("[LOG-WARN]" + OrgaoService.class.getSimpleName() + " - NAO FOI POSSIVEL OBTER A LISTA DE MODALIDADES CADASTRADAS.", new Exception());
		}
		
		return modalidadesModel;
	}
}
