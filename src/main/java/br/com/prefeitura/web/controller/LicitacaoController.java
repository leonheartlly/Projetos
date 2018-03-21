package br.com.prefeitura.web.controller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.prefeitura.web.service.AnexoService;
import br.com.prefeitura.web.service.LicitacaoService;
import br.com.prefeitura.web.service.ModalidadeService;
import br.com.prefeitura.web.service.OrgaoService;

@Controller
@RequestMapping("/editais")
public class LicitacaoController {
	

	@Autowired
	private OrgaoService orgaoService;
	
	@Autowired
	private ModalidadeService modalidadeService;
	
	@Autowired
	private LicitacaoService licicitacaoService;
	
	@Autowired
	private AnexoService anexoService;
	
	private static final Logger LOGGER = 
		      Logger.getLogger(LicitacaoController.class); 

	
	/***
	 * CHAMA PÁGINA DE LICITAÇÕES
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/licitacao", method = RequestMethod.GET)
	public ModelAndView licitacao(Locale locale, Model model) {

		LOGGER.info("[LOG-INFO] "+ UsuarioController.class.getSimpleName()+" - LICITACOES.");
		/* LISTA DE GRUPOS QUE VAMOS MOSTRAR NA PÁGINA */
		model.addAttribute("orgaos", orgaoService.consultarOrgaos());
		model.addAttribute("modalidades", modalidadeService.consultarModalidades());
		model.addAttribute("anexos", anexoService.consultarAnexosPorLicitacao());
		model.addAttribute("locale", locale);
		
		/* OBJETO QUE VAMOS ATRIBUIR OS VALORES DOS CAMPOS */
		model.addAttribute("licitacao", licicitacaoService.consultarLicitacoes());

		return new ModelAndView("licitacao");
	}

}
