package br.com.prefeitura.web.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prefeitura.web.domain.entity.GraphEntity;
import br.com.prefeitura.web.model.Grafico;
import br.com.prefeitura.web.repository.GraphRepository;
import br.com.prefeitura.web.utils.GraphEnum;
import br.com.prefeitura.web.utils.PortalPrefeituraUtils;

@Service
@Transactional
public class GraphService extends ServiceHelper {

	@Autowired
	private GraphRepository graphRepository;
	
	private static final Logger LOGGER = 
		      Logger.getLogger(GraphService.class); 
	
	/**
	 * Consulta dados para o gráfico de pizza. É esperado que se retorna apenas 2 meses para este gráfico.
	 * @return dados da consulta.
	 */
	 public List<Grafico> findPieChart(){
		 
		 List<Grafico> grafico = new ArrayList<>();
		 
		 try{
			 List<GraphEntity> graficoEntity = this.graphRepository.findGraphEntityByTipoOrderByDataDesc(GraphEnum.PIE.tipo(), new PageRequest(0, 2));
			 grafico = convertGraficosObject(graficoEntity);
			 convertDatas(grafico);
			 return grafico;
		 }catch(DataAccessException dae){
			 LOGGER.error("[LOG-ERROR] " + GraphService.class.getSimpleName()
						+ " ERRO DE ACESSO AOS DADOS. " + dae);
			 throw dae;
		 }catch(Exception e){
			 LOGGER.error("[LOG-ERROR] " + GraphService.class.getSimpleName()
						+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
			 throw e;
		 }
	 }
	 
	 /**
	  * Consulta dados par ao gráfico de linhas (arrecadação). Esperado uma quantidade máxima de 12 valores.
	  * @return dados da consulta.
	  */
	 public List<Grafico> findLineChart(){
		 
		 List<Grafico> grafico = new ArrayList<>();
		 try{
			 List<GraphEntity> graficoEntity = this.graphRepository.findGraphEntityTopByTipo(GraphEnum.LINE.tipo(), new PageRequest(0, 12));
			 grafico = convertGraficosObject(graficoEntity);
			 
			 convertDatas(grafico);
			 grafico = ordenarGraficoPorData(grafico);
			 return grafico;
		 }catch(DataAccessException dae){
			 LOGGER.error("[LOG-ERROR] " + GraphService.class.getSimpleName()
						+ " ERRO DE ACESSO AOS DADOS. " + dae);
			 throw dae;
		 }catch(Exception e){
			 LOGGER.error("[LOG-ERROR] " + GraphService.class.getSimpleName()
						+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
			 throw e;
		 }
	 }
	 
	 /**
	  * Obtém dados para o gráfico de barras (Folha de pagamentos). Esperado uma quantidade máxima de 12 valores.
	  * @return dados da consulta.
	  */
	 public List<Grafico> findBarChart(){
		 
		 List<Grafico> grafico = new ArrayList<>();
		 try{
			 List<GraphEntity> graficoEntity = this.graphRepository.findByTipoOrderByDataDesc(GraphEnum.BAR.tipo(), new PageRequest(0, 12));
			 grafico = convertGraficosObject(graficoEntity);
			 
			 convertDatas(grafico);
			 grafico = ordenarGraficoPorData(grafico);
			 return grafico;
		 }catch(DataAccessException dae){
			 LOGGER.error("[LOG-ERROR] " + GraphService.class.getSimpleName()
						+ " ERRO DE ACESSO AOS DADOS. " + dae);
			 throw dae;
		 }catch(Exception e){
			 LOGGER.error("[LOG-ERROR] " + GraphService.class.getSimpleName()
						+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
			 throw e;
		 }
	 }
	 
	 /**
	  * converte datas.
	  * @param graficos
	  */
	 private void convertDatas(List<Grafico> graficos){
		 graficos.forEach(grafico -> {
			 String convertido = PortalPrefeituraUtils.convertDateFromDB(grafico.getData());
			 String mes = PortalPrefeituraUtils.getFullMonthName(convertido);
			 
			 LocalDate date = LocalDate.parse(grafico.getData());
			 Month monthNumber = date.getMonth();
			 
			 grafico.setNumeroMes(monthNumber.getValue());
			 grafico.setMes(mes);
			 grafico.setYear(PortalPrefeituraUtils.getYear(convertido));
			 grafico.setData(convertido);
		 });
	 }
	 
	/**
	 * Ordena as noticias por data reversa.
	 * @param graficos lista de noticias.
	 * @return lista de noticias ordenadas.
	 */
	public List<Grafico> ordenarGraficoPorData(List<Grafico> graficos){
		
		if(!graficos.isEmpty()){
			
			graficos = graficos.stream().sorted(
					(news, nextNews) -> PortalPrefeituraUtils.getDateTimeMMM(news.getData())
					.compareTo(PortalPrefeituraUtils.getDateTimeMMM(nextNews.getData())))
					.collect(Collectors.toList());
		}
		return graficos;
	}
}
