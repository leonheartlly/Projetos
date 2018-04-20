package br.com.prefeitura.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.github.rkpunjal.sqlsafe.SqlSafeUtil;

public class PortalPrefeituraUtils {

	private static final Logger LOGGER = 
			Logger.getLogger(PortalPrefeituraUtils.class); 
	/**
	 * Constante de formato de data.
	 */
	private static final String DATE_FORMAT_DATABASE = "yyyy-MM-dd";
	
	/**
	 * Constante de formato de data.
	 */
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	
	/**
	 * Constante de formato de data
	 */
	private static final String DATE_FORMAT_MONTH = "dd MMM YYYY";
	
	/**
	 * Confirma se a data de entrada está no padrao yyyy-MM-dd. Sem letras e sem números inválidos.
	 */
	private static final String REGEX_DB_FORMAT_CHECK = "[1-2]\\d\\d[0-9]-[0-1]\\d-[0-3]\\d";
	
	/**
	 * Confirma se a data de saida está no padrão dd/MM/yyyy. 
	 */
	private static final String REGEX_DATE_FORMAT_CHECK = "[0-3]\\d\\/[0-1]\\d\\/[1-2]\\d\\d[0-9]";
	
	/**
	 * Verifica se o CNPJ está no formato correto.
	 */
	private static final String REGEX_CNPJ_CHECK = "\\d{2}.\\d{3}.\\d{3}\\/\\d{4}-\\d{2}";
	
	
	/**
	 * Formata uma data do formato padrão para o formato usado na base.
	 * @param data data a ser formatada.
	 * @return data no formato 'yyyy-MM-dd'.
	 */
	public static String converDateDBFormat(String data){
		
		if(StringUtils.isNotBlank(data) && data.matches(REGEX_DB_FORMAT_CHECK)){
			DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
			LocalDate date = LocalDate.parse(data, dtFormatter);
			return date.toString();
		}else{
			LOGGER.info("[LOG-WARN] " + PortalPrefeituraUtils.class.getSimpleName() + " A DATA INFORMADA ESTÁ EM BRANCO OU EM UM FORMATO INVÁLIDO. ");
		}
		
		return null;
	}
	
	/**
	 * Formata uma data vinda da base para o formato padrão do site.
	 * @param data data a ser formatada.
	 * @return data no formato 'dd/MM/yyyy'.
	 */
	public static String convertDateFromDB(String data){
		
		if(StringUtils.isNotBlank(data) && data.matches(REGEX_DB_FORMAT_CHECK)){
			DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_DATABASE);
			LocalDate date = LocalDate.parse(data, dtFormatter);
			
			dtFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
			return date.format(dtFormatter);
		}else{
			LOGGER.info("[LOG-WARN] " + PortalPrefeituraUtils.class.getSimpleName() + " A DATA INFORMADA ESTÁ EM BRANCO OU EM UM FORMATO INVÁLIDO. ");
		}
		return null;
	}
	
	/**
	 * Formata uma data vinda da base para o formato padrão do site.
	 * @param data data a ser formatada.
	 * @return data no formato 'dd/MM/yyyy'.
	 */
	public static String convertDateFormatMMM(String data){
		
		try{
			
			if(StringUtils.isNotBlank(data) && data.matches(REGEX_DATE_FORMAT_CHECK)){
				SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
				SimpleDateFormat desiredFormat = new SimpleDateFormat(DATE_FORMAT_MONTH);
				desiredFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
				Date date = formatter.parse(data);
				
				return desiredFormat.format(date).toUpperCase();
			}else{
				LOGGER.info("[LOG-WARN] " + PortalPrefeituraUtils.class.getSimpleName() + " A DATA INFORMADA ESTÁ EM BRANCO OU EM UM FORMATO INVÁLIDO. ");
			}
		}catch(Exception e){
			LOGGER.error("[LOG-WARN] " + PortalPrefeituraUtils.class.getSimpleName() + " ERRO INESPERADO AO CONVERTER A DATA. " + e);
		}
		return null;
	}

	/**
	 * Obtem o objeto Date da data obtida no banco.
	 * @param date data do banco.
	 * @return objeto Date.
	 */
	public static Date getDateTime(String date) {
		try {
			if(StringUtils.isNotBlank(date)){
				Date data;
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
				data = formatter.parse(date);
				return data;
			}
		} catch (ParseException e) {
			LOGGER.warn("[LOG-WARN] - NÃO FOI POSSÍVEL OBTER A DATA DAS MENSAGENS. " + e);
		}
		return null;
	}
	
	/**
	 * Obtém o mes de um ano.
	 * @param date data do do evento.
	 * @return mes do evento.
	 */
	public static String getMonth(String date) {
		try {
			if(StringUtils.isNotBlank(date)){
				
				DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
				LocalDate data = LocalDate.parse(date, dtFormatter);
				dtFormatter = DateTimeFormatter.ofPattern("MMM");
				
				return dtFormatter.format(data);
			}
		} catch(Exception e){
			LOGGER.warn("[LOG-WARN] - ERRO AO BUSCAR O MÊS. " + e);
		}
		return null;
	}
	
	/**
	 * Obtém o dia da data informada.
	 * @param date data informada.
	 * @return dia do mes.
	 */
	public static byte getDay(String date) {
		try {
			if(StringUtils.isNotBlank(date)){
				
				DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
				LocalDate data = LocalDate.parse(date, dtFormatter);
				return ((Integer)data.getDayOfMonth()).byteValue();
			}
		} catch(Exception e){
			LOGGER.warn("[LOG-WARN] - ERRO AO BUSCAR O MÊS. " + e);
		}
		return 0;
	}
	
	/**
	 * Obtém o ano da data informada.
	 * @param date data informada.
	 * @return ano informado.
	 */
	public static short getYear(String date) {
		try {
			if(StringUtils.isNotBlank(date)){
				
				DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
				LocalDate data = LocalDate.parse(date, dtFormatter);
				return ((Integer)data.getYear()).shortValue();
			}
		} catch(Exception e){
			LOGGER.warn("[LOG-WARN] - ERRO AO BUSCAR O MÊS. " + e);
		}
		return 0;
	}
	
	/**
	 * Verifica se o CNPJ informado está no padrão correto.
	 * @param cnpj cnpj informado.
	 * @return boolean
	 */
	public static boolean cnpjFormatIsValid(String cnpj){
		
		if(cnpj.matches(REGEX_CNPJ_CHECK)){
			
			return true;
		}else{
			LOGGER.info("[LOG-WARN] " + PortalPrefeituraUtils.class.getSimpleName() + " O CNPJ INFORMADO NÃO ESTÁ NO PADRÃO CORRETO. ");
		}
		
		return false;
	}
	
	/**
	 * Efetua validação do campo Objeto.
	 * @param objeto
	 * @return
	 */
	public static boolean objectIsValid(String objeto){
		
		if(StringUtils.isNotBlank(objeto) && objeto.length() > 3 && SqlSafeUtil.isSqlInjectionSafe(objeto)){
			
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
//		String data = converDateDBFormat("2018-10-10");
		String data2 = getMonth("10/10/2018");
		
		byte data = getDay("10/10/2018");
		
		short data3 = getYear("10/10/2018");
		
		System.out.println(data2);
		System.out.println(data);
		System.out.println(data3);
		
//		boolean cnpj = cnpjFormatIsValid("14.218.835/0001-27");
		
//		Licitacao lici = new Licitacao();
//		lici.setObjeto("select adf from abc");
//		boolean test = SqlSafeUtil.isSqlInjectionSafe("Os valores são");
//		System.out.println(test);
//		teste(lici);
		
//		System.out.println("delete um homem".contains(String.matches("(select)|(delete)|(update)")));
		
//		System.out.println(cnpj);
		
//		System.out.println(data + " " + data.matches("[1-2]\\d\\d[0-9]-[0-1]\\d-[0-3]\\d"));
		
//		convertDateFromDB("");
	}
	
}
