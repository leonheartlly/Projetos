package br.com.prefeitura.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.prefeitura.web.model.Fornecedor;
import br.com.prefeitura.web.model.Grafico;
import br.com.prefeitura.web.model.Legislacao;
import br.com.prefeitura.web.model.Licitacao;
import br.com.prefeitura.web.service.AnexoService;
import br.com.prefeitura.web.service.ContratoService;
import br.com.prefeitura.web.service.LegislacaoService;
import br.com.prefeitura.web.service.LicitacaoService;
import br.com.prefeitura.web.service.ModalidadeService;
import br.com.prefeitura.web.service.OrgaoService;
import br.com.prefeitura.web.vo.BarChartVO;
import br.com.prefeitura.web.vo.Teste;

@Controller
@RequestMapping("/editais")
@Secured("permitAll")
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
	
	@Autowired
	private LegislacaoService legislacaoService;
	
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

	/**
	 * Busca geral do menu legislação.
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/legislacao", method = RequestMethod.GET)
	public ModelAndView legislacao(@ModelAttribute Legislacao legislacao, Locale locale, Model model) {

		LOGGER.info("[LOG-INFO] "+ EditaisController.class.getSimpleName()+" - LEGISLAÇÃO.");
		
		if(!ObjectUtils.isEmpty(legislacao)){
//			List<Legislacao> legislacoes = this.legislacaoService.findLegislacaoByFormFilters(legislacao);
//			model.addAttribute("legislacao", legislacoes);
			
		}
		
		model.addAttribute("orgaos", orgaoService.consultarOrgaos());
		
		return new ModelAndView("legislacao");
	}
	
	/**
	 * Busca geral do menu legislação.
	 * @param locale
	 * @param model
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/filtrarLegislacao", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody List<Legislacao> pesquisarPorFiltro(@RequestBody Legislacao legislacao, Locale locale, Model model) throws JsonProcessingException { 
		
		LOGGER.info("[LOG-INFO] "+ EditaisController.class.getSimpleName()+" - LEGISLAÇÃO.");
		
		List<Legislacao> legislacoes = new ArrayList<>();
		try{
			if(legislacao.getIdOrgao() == null && legislacao.getResumo().equals("") 
					&& legislacao.getDataInicial().equals("") && legislacao.getDataFinal().equals("") && legislacao.getTipo() != null && legislacao.getTipo() > 0){
				legislacoes = this.legislacaoService.findAll();
			}else{
				legislacoes = this.legislacaoService.findLegislacaoByFormFilters(legislacao);
			}
		}catch(NullPointerException np){
			LOGGER.error("[LOG-ERROR] "+ EditaisController.class.getSimpleName()+" - LEGISLAÇÃO. PesquisarPorFiltro() NULLPOINTEREXCEPTION: " + np);
		}catch(Exception e){
			LOGGER.error("[LOG-ERROR] "+ EditaisController.class.getSimpleName()+" - LEGISLAÇÃO. PesquisarPorFiltro() EXCEPTION: " + e);
		}
		
		model.addAttribute("legislacoes", legislacoes);
		
		return legislacoes;
	}
}
