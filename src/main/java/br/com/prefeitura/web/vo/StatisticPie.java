package br.com.prefeitura.web.vo;

import java.util.List;

import lombok.Data;

@Data
public class StatisticPie {

	private String costPercent;
	private String recipePercent;
	
	private String currentMonth;
	private String lastMonth;
	
	private List<PieChartVO> pies;
	
}
