package br.com.prefeitura.web.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.prefeitura.web.domain.entity.OuvidoriaEntity;
import br.com.prefeitura.web.model.Ouvidoria;
import br.com.prefeitura.web.repository.OuvidoriaRepository;

@Service
public class OuvidoriaService {

	private static final Logger LOGGER = Logger.getLogger(OuvidoriaService.class);

	@Autowired
	private OuvidoriaRepository ouvidoriaRepository;
	
	/**
	 * Consulta usuarios cadastrados.
	 * 
	 * @return lista de noticias.
	 */
	public Ouvidoria countTypes() {

		Ouvidoria ouvidoria = new Ouvidoria();
		
		try {
			ouvidoria.setTotalSugestions(this.ouvidoriaRepository.findCountGroupByTipo(1));
			ouvidoria.setTotalComplaint(this.ouvidoriaRepository.findCountGroupByTipo(2));
			ouvidoria.setTotalDelation(this.ouvidoriaRepository.findCountGroupByTipo(3));
			ouvidoria.setTotalCompliment(this.ouvidoriaRepository.findCountGroupByTipo(4));

			return ouvidoria;
		} catch (DataAccessException dae) {
			LOGGER.error("[LOG-ERROR] " + OuvidoriaService.class.getSimpleName() + " ERRO DE ACESSO AOS DADOS. " + dae);
			throw dae;
		} catch (Exception e) {
			LOGGER.error("[LOG-ERROR] " + OuvidoriaService.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
			throw e;
		}
	}
	
	/**
	 * Insere um valor na base de dados.
	 * @param type tipo da manifestação.
	 * @return ins
	 */
	public boolean addTypeStatistic(short type){
		
		OuvidoriaEntity entity = new OuvidoriaEntity();
		entity.setTipo(type);
		boolean isSuccessful = true;
		
		try{
			this.ouvidoriaRepository.save(entity);
		}catch(Exception e){
			isSuccessful = false;
			LOGGER.error("[LOG-ERROR] " + OuvidoriaService.class.getSimpleName()
					+ " ERRO INESPERADO AO INSERIR UM VALOR NA TABELA. addTypeStatistic() " + e);
		}
		return isSuccessful;
	}
	
	
	
}
