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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.prefeitura.web.model.ArquivoProjeto;
import br.com.prefeitura.web.model.Ouvidoria;
import br.com.prefeitura.web.model.Projetos;
import br.com.prefeitura.web.model.Secretaria;
import br.com.prefeitura.web.service.ArquivoProjetoService;
import br.com.prefeitura.web.service.MailService;
import br.com.prefeitura.web.service.OuvidoriaService;
import br.com.prefeitura.web.service.ProjetosService;
import br.com.prefeitura.web.service.SecretariaService;
import br.com.prefeitura.web.vo.MailDelation;

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
	
	@Autowired
	private MailService mail;
	
	@Autowired 
	private OuvidoriaService ouvidoriaService;
	
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
	
	/**
	 * 
	 * @param delation
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/ouvidoria", method = RequestMethod.GET)
    public ModelAndView obterOuvidoria(Locale locale, Model model) {
        
        Ouvidoria ouvidoria = new Ouvidoria();
        
        try{
        	//consulta novamente as estatísticas apenas se o insert tiver sucesso.
        	ouvidoria = ouvidoriaService.countTypes();
        }catch(Exception e){
        	LOGGER.error("[LOG-ERROR] " + PrefeituraController.class.getSimpleName() + " - OUVIDORIA. obterOuvidoria EXCEPTION: " + e);
        }
        model.addAttribute("graficoOuvidoria", ouvidoria);

        return new ModelAndView("ouvidoria");
    }
	
	/**
	 * Envia email para um orgão destinatário e obtem um gráfico a partir do envio.
	 * @param delation
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/mail", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Ouvidoria sendMail(@RequestBody MailDelation delation,Locale locale, Model model) {
        
//		boolean mailSend = true;
        boolean mailSend = mail.sendEmail(delation);
        Ouvidoria ouvidoria = new Ouvidoria();
        
        if(mailSend){
	        try{
	        	mailSend = ouvidoriaService.addTypeStatistic(delation.getType());
//	        	consulta novamente as estatísticas apenas se o insert tiver sucesso.
	        	if(mailSend){ 
	        		ouvidoria = ouvidoriaService.countTypes();
	        	}
	        }catch(Exception e){
	        	LOGGER.error("[LOG-ERROR] " + PrefeituraController.class.getSimpleName() + " - OUVIDORIA. sendMail() EXCEPTION: " + e);
	        }
        }

        return ouvidoria;
    }
	
}
