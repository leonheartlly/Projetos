package br.com.prefeitura.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	/***
	 * ESSE MÉTODO CARREGA A PÁGINA (index.html) DE LOGIN DA NOSSA APLICAÇÃO
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		
		return "index";
	}
	
	/***
	 * ESSE MÉTODO CARREGA A PÁGINA (index.html) DE LOGIN DA NOSSA APLICAÇÃO
	 * 
	 * @return
	 */
	@RequestMapping(value = "/acessoNegado2", method = RequestMethod.GET)
	public String index2() {
		return "acessoNegado2";
	}

	/***
	 * CARREGA À PÁGINA INICIAL DA APLICAÇÃO APÓS EFETUARMOS O LOGIN
	 * 
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {

		return "home";
	}
	
	/***
	 * CARREGA À PÁGINA NOTÍCIA
	 * 
	 * @return
	 */
	@RequestMapping(value = "/noticia", method = RequestMethod.GET)
	public String noticia() {

		return "noticia";
	}
	
	/***
	 * CARREGA À PÁGINA NOTÍCIA
	 * 
	 * @return
	 */
	@RequestMapping(value = "/licitacao", method = RequestMethod.GET)
	public String licitacao() {

		return "licitacao";
	}

	/***
	 * MOSTRA UM PÁGINA COM A MENSAGEM DE ACESSO NEGADO QUANDO O USUÁRIO NÃO
	 * TIVER PERMISSÃO DE ACESSAR UMA DETERMINADA FUNÇÃO DO SISTEMA
	 * 
	 * @return
	 */
	@RequestMapping(value = "/acessoNegado", method = RequestMethod.GET)
	public String acessoNegado() {

		return "acessoNegado";
	}

}