package br.com.prefeitura.web.service;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.prefeitura.web.utils.ManifestacaoEnum;
import br.com.prefeitura.web.vo.MailDelation;

@Service
public class MailService {

	private static final Logger LOGGER = Logger.getLogger(MailService.class);

	@Autowired
	private JavaMailSender mailSender;

	@Value("${annonimous.mail.from}")
	private String mailFrom;
	
	private final static String UTF8 = "UTF-8";
	private final static String TEXT_HTML = "text/html";
	
	private final static String TAG_OPEN_LABEL = "<label style='font-weight: bold; color: #9e9e9e;'>";
	private final static String TAG_CLOSE_LABEL = "</label>";
	private final static String TAG_BR = "<br/>";
	private final static String TAG_TABLE= "<table style='width:100%'>";
	private final static String TAG_OPEN_TR = "<tr>";
	private final static String TAG_CLOSE_TR = "</tr>";
	private final static String TAG_OPEN_TD = "<td style='color: #9e9e9e;'>";
	private final static String TAG_CLOSE_TD = "</td>";
	private final static String TAG_CLOSE_TABLE = "</table>";
	
	/**
	 * 
	 * @param delation
	 * @return
	 */
	public boolean sendEmail(MailDelation delation) {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, UTF8);
			
			String mailBody = createMailBody(delation);
					
			mimeMessage.setContent(mailBody, TEXT_HTML);
			helper.setTo("Contato@araguacu.to.gov.br");
			helper.setSubject(findManifestation(delation.getType()));
			helper.setFrom(mailFrom);
			mailSender.send(mimeMessage);
//			mailSender.send(message);
			return true;
		} catch (Exception e) {
			LOGGER.error("[LOG-ERROR] " + MailService.class.getSimpleName()
					+ " - SECRETARIA. sendEmail() EXCEPTION: " + e);
			return false;
		}
	}
	
	/**
	 * Montagem do corpo do email.
	 * @param delation
	 * @return email configurado em formato html.
	 */
	private String createMailBody(MailDelation delation){
		
		StringBuilder sb = new StringBuilder();
		sb.append("<div style='background-color: #388e3c; height: 200px;'>"
				+ "<h2 style='margin-left: 36%; margin-top: 0px; margin-bottom: 5px; color: white;'>Prefeitura de Araguacu</h2>"
				+ "<a href='http://araguacu.to.gov.br'><img style='margin-top: 30px; margin-left: 43%;' src='http://www.araguacu.to.gov.br/img/brasaoleft.png' /></a></div><br/><div style='margin-left: 20px;'>");
		sb.append(TAG_TABLE);
			sb.append(TAG_OPEN_TR);
				sb.append(TAG_OPEN_TD);
					sb.append(TAG_OPEN_LABEL + "Tipo Manifestacao: " + TAG_CLOSE_LABEL + (delation.isIdentity() == true ? "Anonima" : "Aberta").trim().toUpperCase());
				sb.append(TAG_CLOSE_TD);
		
//		sb.append("<div style='margin-left: 20px;'><label style='font-weight: bold; color: #9e9e9e;'>Tipo Manifestacao: </label>" + (delation.isIdentity() == true ? "Anonima" : "Aberta").trim().toUpperCase() + "<br/>");
		if(!delation.isIdentity()){
			if(delation.getResponse() >= 1 && delation.getResponse() < 4){
					sb.append(TAG_OPEN_TD);
						sb.append(TAG_OPEN_LABEL +  "Responder por: " + TAG_CLOSE_LABEL + delation.getResponse() + TAG_BR);
					sb.append(TAG_CLOSE_TD);
				sb.append(TAG_CLOSE_TR);
			}else{
				sb.append(TAG_CLOSE_TR);
			}
			
			if(StringUtils.isNotEmpty(delation.getName())){
				sb.append(TAG_OPEN_TR);
					sb.append(TAG_OPEN_TD);
						sb.append(TAG_OPEN_LABEL + "Nome: " + TAG_CLOSE_LABEL + delation.getName().trim().toUpperCase() + TAG_BR);
					sb.append(TAG_CLOSE_TD);
				sb.append(TAG_CLOSE_TR);
			}
			
			sb.append(TAG_OPEN_TR);
			if(StringUtils.isNotEmpty(delation.getEmail())){
					sb.append(TAG_OPEN_TD);
						sb.append(TAG_OPEN_LABEL +  "Email: " +  TAG_CLOSE_LABEL + delation.getEmail().trim().toUpperCase() + TAG_BR);
					sb.append(TAG_CLOSE_TD);
			}
			if(StringUtils.isNotEmpty(delation.getPhone())){
				sb.append(TAG_OPEN_TD);
					sb.append(TAG_OPEN_LABEL + " Fone: " +  TAG_CLOSE_LABEL + delation.getPhone() + TAG_BR);
				sb.append(TAG_CLOSE_TD);
			}
			sb.append(TAG_CLOSE_TR);
		}
		sb.append(TAG_CLOSE_TABLE);
		sb.append(TAG_BR + TAG_BR);
		
		sb.append(TAG_OPEN_LABEL + "Descricao:" + TAG_CLOSE_LABEL + TAG_BR);
		sb.append("<p style='margin-left: 10px;'>" + delation.getDescription() + "</p></div>");
		sb.append(TAG_BR + TAG_BR);
		sb.append("<p style='float:right; font-size: small;'>Email recebido atraves da Ouvidoria do <a href='http://localhost:8080/prefeitura/ouvidoria'>portal Araguacu</a>.</p>");
		return sb.toString();
	}
	
	/**
	 * Obtém o tipo de manifestação selecionada.
	 * @param tipo
	 * @return descrição da manifestação.
	 */
	private String findManifestation(short tipo){
		return ManifestacaoEnum.DENUNCIA.findManifestation(tipo).getDescricao();
	}

}
