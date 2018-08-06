package br.com.prefeitura.web.controller;

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

import br.com.prefeitura.web.model.Secretaria;
import br.com.prefeitura.web.service.SecretariaService;

@Controller
@RequestMapping("/prefeitura")
@Secured("permitAll")
public class PrefeituraController {

	private static final Logger LOGGER = 
		      Logger.getLogger(PrefeituraController.class); 
	
	@Autowired
	private SecretariaService secretariaService;
	
	/**
	 * Busca geral de notícias.
	 * @param locale
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ModelAndView home(Locale locale, Model model) {
//
//		LOGGER.info("[LOG-INFO] "+ HomeController.class.getSimpleName()+" - HOME.");
//		
////		List<Noticia> noticias = NoticiaService.findNoticias();
////		boolean isOldNews = noticias.size() > 5 ? true : false;
//		List<CalendarioEventos> eventos = this.calendarioEventosService.findallEvents();
////		
//		model.addAttribute("calendario", eventos);
////		model.addAttribute("oldNews", isOldNews);
////		model.addAttribute("locale", locale);
//		
//		return new ModelAndView("home");
//	}

	/**
	 * Busca geral do menu legislação.
	 * @param locale
	 * @param model
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/obterSecretaria", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody Secretaria obterSecretaria(@RequestBody Long id, Locale locale, Model model) throws JsonProcessingException { 
		
		LOGGER.info("[LOG-INFO] "+ PrefeituraController.class.getSimpleName()+" - SECRETARIA.");
		Secretaria secretaria = new Secretaria();
		try{
			if(id != null && id > 0 && id <= 15){
				secretaria = secretariaService.findSecretariaById(id);
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
