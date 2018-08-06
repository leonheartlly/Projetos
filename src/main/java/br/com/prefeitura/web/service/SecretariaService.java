package br.com.prefeitura.web.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prefeitura.web.domain.entity.SecretariaEntity;
import br.com.prefeitura.web.model.Secretaria;
import br.com.prefeitura.web.repository.SecretariaRepository;

@Service
public class SecretariaService extends ServiceHelper{

	private static final Logger LOGGER = 
		      Logger.getLogger(SecretariaService.class); 
	
	@Autowired
	private SecretariaRepository secretariaRepository;
	
	/**
	 * Consulta usuarios cadastrados.
	 * @return
	 * @throws Exception 
	 */
	 public Secretaria findSecretariaById(Long id) {
		 
		 Secretaria secretaria = new Secretaria();
		 try{
			SecretariaEntity secretariaEntity = this.secretariaRepository.findOne(id);
			secretaria = convertSecretariaObject(secretariaEntity);
		 }catch(NullPointerException np){
			 LOGGER.error("[LOG-ERROR] " + SecretariaService.class.getSimpleName() + " ERRO INESPERADO AO EFETUAR A CONSULTA. " + np);
		 }catch(Exception e){
			 LOGGER.error("[LOG-ERROR] " + SecretariaService.class.getSimpleName() + " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
		 }
		 return secretaria;
	 }
	
}
