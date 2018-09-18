package br.com.prefeitura.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prefeitura.web.domain.entity.AnexoEntity;
import br.com.prefeitura.web.model.Anexo;
import br.com.prefeitura.web.model.Licitacao;
import br.com.prefeitura.web.repository.AnexoRepository;

@Service
@Transactional
public class AnexoService extends ServiceHelper {

	private static final Logger LOGGER = 
		      Logger.getLogger(AnexoService.class); 
	
	@Autowired
	private AnexoRepository anexoRepository;
	

	/**
	 * Consulta anexos existentes em uma licitação.
	 * @return lista de grupos.
	 */
	@Transactional(readOnly = true)
	public List<Anexo> consultarAnexosPorLicitacao(){
		
		List<Anexo> anexos = new ArrayList<>();
		
		//consulta todos os grupos
		List<AnexoEntity> anexoEntity = this.anexoRepository.findAll();
		
		if(ArrayUtils.isNotEmpty(anexoEntity.toArray())){
			LOGGER.info("[LOG-INFO] " + AnexoService.class.getSimpleName() + " - ANEXOS OBTIDOS.");
			anexoEntity.forEach(anexo ->{
				anexos.add(new Anexo(anexo.getId(), anexo.getIdLicitacao(), anexo.getTipoAnexo(), anexo.getCaminhoArquivo()));
			});
		}else{
			LOGGER.warn("[LOG-WARN]" + OrgaoService.class.getSimpleName() + " - NAO FOI POSSIVEL OBTER A LISTA DE ORGAOS CADASTRADOS.", new Exception());
		}
		
		return anexos;
	}
	
	/**
	 * 
	 * @param licitacoes
	 * @return
	 */
	public List<Licitacao> consultarAnexosPorLicitacao(List<Licitacao> licitacoes){
		
		licitacoes.forEach(licitacao -> {
			List<AnexoEntity> anexosEntity = this.anexoRepository.findByIdLicitacao(licitacao.getId());
			List<Anexo> anexos = convertAnexosObject(anexosEntity);
			licitacao.setAnexos(anexos);
		});
		
		return licitacoes;
	}
}

