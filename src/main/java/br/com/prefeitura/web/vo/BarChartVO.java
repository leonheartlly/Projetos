package br.com.prefeitura.web.vo;

import lombok.Data;

@Data
public class BarChartVO {

//	private List<String> months;
	private String payment;
	private String organ;
	private short year;
	private String month;
	private Long idOrgan;
	
	
}
