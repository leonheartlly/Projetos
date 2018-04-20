package br.com.prefeitura.web.controller;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.prefeitura.web.model.Noticia;
import br.com.prefeitura.web.service.NoticiaService;

@Controller
@RequestMapping("/referencias")
public class NoticiaController {
	

	@Autowired
	private NoticiaService NoticiaService;
	
	
	private static final Logger LOGGER = 
		      Logger.getLogger(NoticiaController.class); 

	
	/**
	 * Busca geral de notícias.
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/noticias", method = RequestMethod.GET)
	public ModelAndView licitacao(Locale locale, Model model) {

		LOGGER.info("[LOG-INFO] "+ NoticiaController.class.getSimpleName()+" - NOTICIAS.");
		
		List<Noticia> noticias = NoticiaService.findNoticias();
		boolean isOldNews = noticias.size() > 5 ? true : false;
		
		model.addAttribute("noticias", noticias);
		model.addAttribute("oldNews", isOldNews);
		model.addAttribute("locale", locale);
		
		return new ModelAndView("noticia");
	}
	
	/***
	 * CHAMA PÁGINA DE LICITAÇÕES
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/paginaTeste", method = RequestMethod.GET)
	public ModelAndView paginaTeste(@RequestParam(value="imagem[]", required=true) String[] imagens, Locale locale, Model model) {

		LOGGER.info("[LOG-INFO] "+ NoticiaController.class.getSimpleName()+" - IMAGENS.");
		/* LISTA DE GRUPOS QUE VAMOS MOSTRAR NA PÁGINA */
//		model.addAttribute("noticias", NoticiaService.findNoticias());
//		model.addAttribute("imagens", modalidadeService.consultarModalidades());
//		model.addAttribute("anexos", anexoService.consultarAnexosPorLicitacao());
		model.addAttribute("locale", locale);
//		model.addAttribute("forneceor", new Fornecedor(1L, "", "", "", "", ""));
		
		/* OBJETO QUE VAMOS ATRIBUIR OS VALORES DOS CAMPOS */
//		model.addAttribute("licitacao", licicitacaoService.consultarLicitacoes());

		return new ModelAndView("paginaTeste");
	}
	


	
}
