package br.com.prefeitura.web.controller;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.prefeitura.web.model.CalendarioEventos;
import br.com.prefeitura.web.service.CalendarioEventosService;

@Controller
public class HomeController {

	private static final Logger LOGGER = 
		      Logger.getLogger(HomeController.class); 
	
	@Autowired
	private CalendarioEventosService calendarioEventosService;
	
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

}
