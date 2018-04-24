package br.com.prefeitura.web.vo;

import lombok.Data;

@Data
public class PieChartVO {
	
	private String formatedRecipe;
	private String formatedCost;
	private String recipe;
	private String cost;
	private String recipePercent;
	private String costPercent;
	
	private String recipeColor;
	private String costColor;
	
	private boolean legend;
	private String recipeLegend;
	private String costLegend;
	
	private String month;
	private short year;

}
