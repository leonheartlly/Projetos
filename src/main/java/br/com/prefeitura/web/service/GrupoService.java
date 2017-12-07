package br.com.prefeitura.web.service;

import br.com.prefeitura.web.entity.GrupoEntity;
import br.com.prefeitura.web.model.GrupoModel;
import br.com.prefeitura.web.repository.IGrupoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GrupoService {

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
		
		gruposEntity.forEach(grupo ->{
			gruposModel.add(new GrupoModel(grupo.getCodigo(), grupo.getDescription()));
		});
		
		return gruposModel;
	}
	
	
}
