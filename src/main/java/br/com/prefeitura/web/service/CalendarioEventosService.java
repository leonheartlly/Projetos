package br.com.prefeitura.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.prefeitura.web.domain.entity.CalendarioEventosEntity;
import br.com.prefeitura.web.model.CalendarioEventos;
import br.com.prefeitura.web.repository.CalendarioEventosRepository;
import br.com.prefeitura.web.utils.PortalPrefeituraUtils;

@Service
public class CalendarioEventosService extends ServiceHelper{

	
	private static final Logger LOGGER = Logger.getLogger(CalendarioEventosService.class);
	
	@Autowired
	private CalendarioEventosRepository calendarioEventosRepository;
	
	/**
	 * 
	 * @return
	 */
	public List<CalendarioEventos> findallEvents(){
		
		List<CalendarioEventos> eventos = new ArrayList<>();

		try {
			List<CalendarioEventosEntity> noticiasEntity = this.calendarioEventosRepository.findAll();
			eventos = convertEventsObject(noticiasEntity);

			convertDateFormat(eventos);
			eventos = reorderEvents(eventos);
			
			return eventos;
		} catch (DataAccessException dae) {
			LOGGER.error("[LOG-ERROR] " + NoticiaService.class.getSimpleName() + " ERRO DE ACESSO AOS DADOS. " + dae);
			throw dae;
		} catch (Exception e) {
			LOGGER.error("[LOG-ERROR] " + NoticiaService.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
			throw e;
		}
	}

	/**
	 * Converte datas das noticias para o formato padrão.
	 * @param eventos noticias encontradas na base.
	 */
	private void convertDateFormat(List<CalendarioEventos> eventos){
		
		eventos.forEach(evento -> {
			String init = PortalPrefeituraUtils.convertDateFromDB(evento.getDataInicio());
			evento.setMes(PortalPrefeituraUtils.getMonth(init).toUpperCase());
			evento.setDia(PortalPrefeituraUtils.getDay(init));
			evento.setAno(PortalPrefeituraUtils.getYear(init));
			
			String dateFormat = PortalPrefeituraUtils.convertDateFormatMMM(init);
			
			evento.setDataInicio(dateFormat);
			
			String end = PortalPrefeituraUtils.convertDateFromDB(evento.getDataFim());
			dateFormat = PortalPrefeituraUtils.convertDateFormatMMM(end);
			evento.setDataFim(dateFormat);
		});
	}
	
	/**
	 * 
	 * @param eventos
	 * @return
	 */
	private List<CalendarioEventos> reorderEvents(List<CalendarioEventos> eventos) {
		
		if(!eventos.isEmpty() && eventos.size() > 1){
					
			eventos = eventos.stream().sorted(
					(event, nextEvent) -> PortalPrefeituraUtils.getDateTime(event.getDataInicio())
					.compareTo(PortalPrefeituraUtils.getDateTime(nextEvent.getDataInicio())))
					.collect(Collectors.toList());
			Collections.reverse(eventos);
		}
		return eventos;
	}
	
}
