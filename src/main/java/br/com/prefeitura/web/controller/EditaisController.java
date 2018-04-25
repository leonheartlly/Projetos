package br.com.prefeitura.web.controller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.prefeitura.web.model.Fornecedor;
import br.com.prefeitura.web.model.Licitacao;
import br.com.prefeitura.web.service.AnexoService;
import br.com.prefeitura.web.service.ContratoService;
import br.com.prefeitura.web.service.LicitacaoService;
import br.com.prefeitura.web.service.ModalidadeService;
import br.com.prefeitura.web.service.OrgaoService;

@Controller
@RequestMapping("/editais")
public class EditaisController {
	

	@Autowired
	private OrgaoService orgaoService;
	
	@Autowired
	private ModalidadeService modalidadeService;
	
	@Autowired
	private LicitacaoService licicitacaoService;
	
	@Autowired
	private AnexoService anexoService;
	
	@Autowired
	private ContratoService contratoService;
	
	private static final Logger LOGGER = 
		      Logger.getLogger(EditaisController.class); 

	
	/***
	 * CHAMA PÁGINA DE LICITAÇÕES
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/licitacao", method = RequestMethod.GET)
	public ModelAndView licitacao(Locale locale, Model model) {

		LOGGER.info("[LOG-INFO] "+ EditaisController.class.getSimpleName()+" - LICITACOES.");
		/* LISTA DE GRUPOS QUE VAMOS MOSTRAR NA PÁGINA */
		model.addAttribute("orgaos", orgaoService.consultarOrgaos());
		model.addAttribute("modalidades", modalidadeService.consultarModalidades());
		model.addAttribute("anexos", anexoService.consultarAnexosPorLicitacao());
		model.addAttribute("locale", locale);
		model.addAttribute("forneceor", new Fornecedor(1L, "", "", "", "", ""));
		
		/* OBJETO QUE VAMOS ATRIBUIR OS VALORES DOS CAMPOS */
		model.addAttribute("licitacao", licicitacaoService.consultarLicitacoes());

		return new ModelAndView("licitacao");
	}
	
	
	

	@RequestMapping(value="/pesquisaLicitacao", method= RequestMethod.POST)
	public ModelAndView licitacaoForm(@ModelAttribute Licitacao licitacao, final BindingResult result, Model model, RedirectAttributes redirectAttributes){
//		if (result.hasErrors()) {
//			return null;
//		}
//		System.out.println(licitacao.getObjeto());
		ModelAndView modelAndView = new ModelAndView("/licitacao");
		
		/* OBJETO QUE VAMOS ATRIBUIR OS VALORES DOS CAMPOS */
		model.addAttribute("licitacao", licicitacaoService.pesquisarLicitacoes(licitacao));
		model.addAttribute("orgaos", orgaoService.consultarOrgaos());
		model.addAttribute("modalidades", modalidadeService.consultarModalidades());
		model.addAttribute("anexos", anexoService.consultarAnexosPorLicitacao());

		/*
		 * PASSANDO O ATRIBUTO PARA O ModelAndView QUE VAI REALIZAR O
		 * REDIRECIONAMENTO COM A MENSAGEM DE SUCESSO
		 */
		redirectAttributes.addFlashAttribute("msg_resultado", "Registro salvo com sucesso!");
		
		LOGGER.info("[LOG-INFO] "+ licitacao+" - LICITACOES.");
		return  modelAndView;
		
	}
	
	/***
	 * CHAMA PÁGINA DE LICITAÇÕES
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/contratos", method = RequestMethod.GET)
	public ModelAndView contratos(Locale locale, Model model) {

		/* LISTA DE GRUPOS QUE VAMOS MOSTRAR NA PÁGINA */
		model.addAttribute("orgaos", orgaoService.consultarOrgaos());
		model.addAttribute("locale", locale);
		model.addAttribute("forneceor", new Fornecedor(1L, "", "", "", "", ""));
		
		/* OBJETO QUE VAMOS ATRIBUIR OS VALORES DOS CAMPOS */
		model.addAttribute("contrato", contratoService.consultarTodosContratos());

		return new ModelAndView("contratos");
	}
	
	@RequestMapping(value="/pesquisaContrato", method= RequestMethod.POST)
	public ModelAndView searchContratoByFilter(@ModelAttribute Licitacao licitacao, final BindingResult result, Model model, RedirectAttributes redirectAttributes){
//		if (result.hasErrors()) {
//			return null;
//		}
//		System.out.println(licitacao.getObjeto());
		ModelAndView modelAndView = new ModelAndView("/contrato");
		
		/* OBJETO QUE VAMOS ATRIBUIR OS VALORES DOS CAMPOS */
		model.addAttribute("licitacao", licicitacaoService.pesquisarLicitacoes(licitacao));
		model.addAttribute("orgaos", orgaoService.consultarOrgaos());
		model.addAttribute("modalidades", modalidadeService.consultarModalidades());
		model.addAttribute("anexos", anexoService.consultarAnexosPorLicitacao());

		/*
		 * PASSANDO O ATRIBUTO PARA O ModelAndView QUE VAI REALIZAR O
		 * REDIRECIONAMENTO COM A MENSAGEM DE SUCESSO
		 */
		redirectAttributes.addFlashAttribute("msg_resultado", "Registro salvo com sucesso!");
		
		LOGGER.info("[LOG-INFO] "+ licitacao+" - LICITACOES.");
		return  modelAndView;
		
	}
	
}