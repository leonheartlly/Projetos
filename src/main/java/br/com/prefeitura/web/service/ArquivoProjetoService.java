package br.com.prefeitura.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.prefeitura.web.domain.entity.ArquivoProjetoEntity;
import br.com.prefeitura.web.model.ArquivoProjeto;
import br.com.prefeitura.web.repository.ArquivoProjetoRepository;

@Service
public class ArquivoProjetoService extends ServiceHelper{

	private static final Logger LOGGER = 
		      Logger.getLogger(ArquivoProjetoService.class); 
	
	@Autowired
	private ArquivoProjetoRepository arquivoProjetoRepository;
	
	/**
	 * Consulta arquivos para download dos projetos.
	 * 
	 * @return Lista de arquivos para download dos projetos desenvolvidos pelas secretarias.
	 */
	public List<ArquivoProjeto> findArquivosById(Long id) {

		List<ArquivoProjeto> arquivos = new ArrayList<>();

		try {
			List<ArquivoProjetoEntity> arquivosEntity = this.arquivoProjetoRepository.findByIdProjeto(id);
			arquivos = convertArquivoProjetoObject(arquivosEntity);

			return arquivos;
		} catch (DataAccessException dae) {
			LOGGER.error("[LOG-ERROR] " + NoticiaService.class.getSimpleName() + " ERRO DE ACESSO AOS DADOS. " + dae);
			throw dae;
		} catch (Exception e) {
			LOGGER.error("[LOG-ERROR] " + NoticiaService.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
			throw e;
		}

	}
	
}
