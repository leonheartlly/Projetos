package br.com.prefeitura.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.prefeitura.web.domain.entity.ProjetosEntity;
import br.com.prefeitura.web.model.Projetos;
import br.com.prefeitura.web.repository.ProjetosRepository;

@Service
public class ProjetosService extends ServiceHelper{
	
	private static final Logger LOGGER = Logger.getLogger(ProjetosService.class);

	@Autowired
	private ProjetosRepository projetosRepository;
	
	@Value("${caminho.capa.secretaria}")
	private String caminhoCapaSecretaria;
	
	/**
	 * Consulta projetos relacionados ao Id informado.
	 * @param id selecionado pelo usuário.
	 * @return projetos relacionados ao Id.
	 */
	public List<Projetos> findProjetosById(Long id) {

		List<Projetos> projetos = new ArrayList<>();

		try {
			List<ProjetosEntity> projetosEntity = this.projetosRepository.findByIdSecretaria(id);
			projetos = convertProjetosObject(projetosEntity);

			addFilePath(projetos);
			return projetos;
		} catch (DataAccessException dae) {
			LOGGER.error("[LOG-ERROR] " + ProjetosService.class.getSimpleName() + " ERRO DE ACESSO AOS DADOS. " + dae);
			throw dae;
		} catch (Exception e) {
			LOGGER.error("[LOG-ERROR] " + ProjetosService.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
			throw e;
		}

	}

	/**
	 * Adiciona caminho configurado para a obtenção dos projetos.
	 * @param projetos configurados com o caminho correto.
	 */
	private void addFilePath(List<Projetos> projetos ){
		
		projetos.forEach(projeto ->{
			projeto.setFotoCapa(caminhoCapaSecretaria + projeto.getFotoCapa());
		});
	}
	
}
