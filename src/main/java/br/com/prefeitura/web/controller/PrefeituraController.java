package br.com.prefeitura.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.prefeitura.web.model.ArquivoProjeto;
import br.com.prefeitura.web.model.Projetos;
import br.com.prefeitura.web.model.Secretaria;
import br.com.prefeitura.web.service.ArquivoProjetoService;
import br.com.prefeitura.web.service.ProjetosService;
import br.com.prefeitura.web.service.SecretariaService;

@Controller
@RequestMapping("/prefeitura")
@Secured("permitAll")
public class PrefeituraController {

	private static final Logger LOGGER = 
		      Logger.getLogger(PrefeituraController.class); 
	
	@Autowired
	private SecretariaService secretariaService;
	
	@Autowired
	private ProjetosService projetosService;
	
	@Autowired
	private ArquivoProjetoService arquivoProjetoService;
	
	/**
	 * 
	 * @param id
	 * @param locale
	 * @param model
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/obterSecretaria", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody Secretaria obterSecretaria(@RequestBody Long id, Locale locale, Model model) throws JsonProcessingException { 
		
		LOGGER.info("[LOG-INFO] "+ PrefeituraController.class.getSimpleName()+" - SECRETARIA.");
		Secretaria secretaria = new Secretaria();
		List<Projetos> projetos = new ArrayList<>();
		List<ArquivoProjeto> arquivos = new ArrayList<>();
		try{
			if(id != null && id > 0 && id <= 15){
				secretaria = secretariaService.findSecretariaById(id);
				projetos = projetosService.findProjetosById(id);
				arquivos = arquivoProjetoService.findArquivosById(id);
				
				secretaria.setListaProjetos(projetos);
				secretaria.setListaArquivoProjeto(arquivos);
			}
		}catch(NullPointerException np){
			LOGGER.error("[LOG-ERROR] "+ PrefeituraController.class.getSimpleName()+" - SECRETARIA. obterSecretaria() NULLPOINTEREXCEPTION: " + np);
		}catch(Exception e){
			LOGGER.error("[LOG-ERROR] "+ PrefeituraController.class.getSimpleName()+" - SECRETARIA. obterSecretaria() EXCEPTION: " + e);
		}
		
		model.addAttribute("secretaria", secretaria);
		
		return secretaria;
	}
	
}
