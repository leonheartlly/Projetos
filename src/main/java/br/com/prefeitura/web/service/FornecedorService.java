package br.com.prefeitura.web.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prefeitura.web.domain.entity.FornecedorEntity;
import br.com.prefeitura.web.model.Fornecedor;
import br.com.prefeitura.web.repository.FornecedorRepository;

@Service
@Transactional
public class FornecedorService {
	
	private static final Logger LOGGER = 
		      Logger.getLogger(FornecedorService.class); 
	
	/**
	 * Interface de fornecedores
	 */
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	/**
	 * Consulta grupos cadastrados.
	 * @return lista de grupos.
	 */
	@Transactional(readOnly = true)
	public Fornecedor consultarFornecedorPorCnpj(String cnpj){
		
		Fornecedor fornecedor = new Fornecedor();
		
		FornecedorEntity fornecedorEntity = this.fornecedorRepository.findByCnpj(cnpj);
		
		try{
			if(fornecedorEntity != null){
				LOGGER.info("[LOG-INFO] " + FornecedorService.class.getSimpleName() + " - FORNECEDOR OBTIDO.");
				
				convertObject(fornecedor, fornecedorEntity);
			}
			
		}catch(NullPointerException np){
			LOGGER.info("[LOG-WARN] " + FornecedorService.class.getSimpleName() + " - NÃO FOI ENCONTRADO UM FORNECEDOR COM ESTE CNPJ. ", np);
		}catch(Exception e){
			LOGGER.info("[LOG-WARN] " + FornecedorService.class.getSimpleName() + " - ERRO INESPERADO AO BUSCAR UM FORNECEDOR. ", e);
		}
		
		return fornecedor;
	}

	/**
	 * Consulta fornecedores cadastrados através do nome.
	 * @return Fornecedor.
	 */
	@Transactional(readOnly = true)
	public Fornecedor consultarFornecedorPorNome(String nome){
		
		Fornecedor fornecedor = new Fornecedor();
		
		FornecedorEntity fornecedorEntity = this.fornecedorRepository.findByNome(nome);
		
		try{
			if(fornecedorEntity != null){
				LOGGER.info("[LOG-INFO] " + FornecedorService.class.getSimpleName() + " - FORNECEDOR OBTIDO.");
				
				convertObject(fornecedor, fornecedorEntity);
			}
			
		}catch(NullPointerException np){
			LOGGER.info("[LOG-WARN] " + FornecedorService.class.getSimpleName() + " - NÃO FOI ENCONTRADO UM FORNECEDOR COM ESTE NOME. ", np);
		}catch(Exception e){
			LOGGER.info("[LOG-WARN] " + FornecedorService.class.getSimpleName() + " - ERRO INESPERADO AO BUSCAR UM FORNECEDOR. ", e);
		}
		
		return fornecedor;
	}

	/**
	 * Converte uma entidade para um model.
	 * @param fornecedor Fornecedor Model.
	 * @param fornecedorEntity Entidade fornecedor
	 */
	private void convertObject(Fornecedor fornecedor, FornecedorEntity fornecedorEntity) {
		fornecedor.setId(fornecedorEntity.getId());
		fornecedor.setRazaoSocial(fornecedorEntity.getRazaoSocial());
		fornecedor.setNome(fornecedorEntity.getNome());
		fornecedor.setTipoFornecedor(fornecedorEntity.getTipoFornecedor());
		fornecedor.setCnpj(fornecedorEntity.getCnpj());
		fornecedor.setCpf(fornecedorEntity.getCpf());
	}
}
