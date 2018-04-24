package br.com.prefeitura.web.controller;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.prefeitura.web.model.Grafico;
import br.com.prefeitura.web.service.GraphService;
import br.com.prefeitura.web.vo.BarChartVO;
import br.com.prefeitura.web.vo.LineChartVO;
import br.com.prefeitura.web.vo.PieChartVO;
import br.com.prefeitura.web.vo.StatisticPie;

@Controller
@RequestMapping("/estatisticas")
public class GraficoController {
	

	@Autowired
	private GraphService graphService;
	
	
	private static final Logger LOGGER = 
		      Logger.getLogger(GraficoController.class); 

	@Value ("${pie.color.recipe}")
	private String recipeColor;
	
	@Value ("${pie.color.cost}")
	private String costColor;
	
	@Value ("${pie.recipe.text}")
	private String recipeText;
	
	@Value ("${pie.cost.text}")
	private String costText;
	
	/**
	 * Busca geral de graficos.
	 * @param locale
	 * @param model
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/graficos", method = RequestMethod.GET)
	public ModelAndView licitacao(Locale locale, Model model) throws JsonProcessingException {

		LOGGER.info("[LOG-INFO] "+ GraficoController.class.getSimpleName()+" - GRAFICOS.");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		List<Grafico> pies = graphService.findPieChart();
		StatisticPie statistics = findPieStatisticsAndDatas(pies);
		
		List<Grafico> lineCharts = graphService.findLineChart();
		List<LineChartVO> lineVOCharts =  secureLineVO(lineCharts);
		
		List<Grafico> bars = graphService.findBarChart();
		List<BarChartVO> barVoCharts = secureBarVO(bars);
		
		model.addAttribute("pieChart", objectMapper.writeValueAsString(statistics.getPies()));
		model.addAttribute("pieStatistics", statistics);
		
		model.addAttribute("lineChart", objectMapper.writeValueAsString(lineVOCharts));
		model.addAttribute("barChart", objectMapper.writeValueAsString(barVoCharts));
		model.addAttribute("locale", locale);
		
		return new ModelAndView("graficos");
	}
	
	/**
	 * Gera dados de visão para o gráfico de pizza.
	 * @param graficos lista de gráficos.
	 * @return VO.
	 */
	private List<PieChartVO> securePieVO(List<Grafico> graficos) {

		List<PieChartVO> pies = new ArrayList<>();
		graficos.forEach(grafico -> {
			PieChartVO pie = new PieChartVO();
			pie.setCost(grafico.getDespesa().toString());
			pie.setRecipe(grafico.getReceita().toString());
			pie.setFormatedCost(formatMoneyValue(grafico.getDespesa()));
			pie.setFormatedRecipe(formatMoneyValue(grafico.getReceita()));
			pie.setCostPercent(findTotalPercent(grafico.getDespesa(), grafico.getReceita()));
			pie.setRecipePercent(findTotalPercent(grafico.getReceita(), grafico.getDespesa()));
			pie.setCostColor(costColor);
			pie.setRecipeColor(recipeColor);
			pie.setLegend(false);
			pie.setCostLegend(costText);
			pie.setRecipeLegend(recipeText);
			pie.setYear(grafico.getYear());
			pie.setMonth(grafico.getMes());
			pies.add(pie);
		});
		return pies;
	}


	/**
	 * Formata um valor para Reais.
	 */
	private String formatMoneyValue(Double value) {
		NumberFormat format =  new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
		String currency = format.format(value);
		return currency;
	}
	
	/**
	 * Gera dados estatísticos para o gráfico de pizza.
	 * @param graficos lista de gráficos.
	 * @return StatisticVO
	 */
	private StatisticPie findPieStatisticsAndDatas(List<Grafico> graficos){
		List<PieChartVO> pieVOCharts = securePieVO(graficos);
		StatisticPie statistics = new StatisticPie();
		
		statistics.setCostPercent(findCostPercentual(graficos.get(0).getDespesa(), graficos.get(1).getDespesa()));
		statistics.setRecipePercent(findCostPercentual(graficos.get(0).getReceita(), graficos.get(1).getReceita()));
		statistics.setCurrentMonth(graficos.get(0).getMes());
		statistics.setLastMonth(graficos.get(1).getMes());
		
		statistics.setPies(pieVOCharts);
		return statistics;
	}
	
	/**
	 * Obtém o percentual de uso de gastos e receita em relação ao mes anterior.
	 * @param value
	 * @param value2
	 * @return
	 */
	private static String findCostPercentual(Double value, Double value2){
		DecimalFormat df = new DecimalFormat("##.00");
		Double total = (value * 100) / value2;
		return df.format(total);
	}
	
	/**
	 * Obtém o percentual de gastos de um mês.
	 * @param recipe receita.
	 * @param cost despesas.
	 * @return percentual.
	 */
	private static String findTotalPercent(Double recipe, Double cost){
		Double total = (recipe * 100) / (recipe + cost);
		DecimalFormat df = new DecimalFormat("##.00");
		return df.format(total);
	}
	
	/**
	 * Converte o objeto gráfico para um objeto VO do gráfico de linhas.
	 * @param lines lista de gráficos.
	 * @return Lista de VO do gráfico de linhas.
	 */
	private List<LineChartVO> secureLineVO(List<Grafico> lines){
		List<LineChartVO> liveVo = new ArrayList<>();
		
		for(Grafico grafico : lines){
			LineChartVO line = new LineChartVO();
			line.setMonth(grafico.getMes());
			line.setMonthNumber(grafico.getNumeroMes());
			line.setRecipe(String.valueOf(grafico.getReceita()));
			line.setYear(grafico.getYear());
			line.setDate(grafico.getData());
			liveVo.add(line);
		}
		return liveVo;
	}
	
	/**
	 * Converte o objeto gráfico para um objeto VO de gráfico de barras.
	 * @param bars lista de gráficos.
	 * @return lista de VO de gráfico de barras.
	 */
	private List<BarChartVO> secureBarVO(List<Grafico> bars){
		
		List<BarChartVO> barsVO = new ArrayList<>();
		
		bars.forEach(bar ->{
			BarChartVO barVO = new BarChartVO();
			barVO.setOrgan(bar.getOrgao().getOrgao());
			barVO.setPayment(String.valueOf(bar.getReceita()));
			barVO.setYear(bar.getYear());
			barVO.setMonth(bar.getMes());
			barVO.setIdOrgan(bar.getOrgao().getId());
			barsVO.add(barVO);
		});
		
		return barsVO;
	}
}
