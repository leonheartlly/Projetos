
/**
 * Cria o grafico de pizza conforme dados.
 */
function createPieChart(id, val){
	
	var pie = JSON.parse(getPieData());
	
	var values = [pie[val].recipe, pie[val].cost];
	var labels = [pie[val].recipeLegend, pie[val].costLegend]
	
	var data = {
		datasets : [ {
			backgroundColor : [ pie[val].costColor, pie[val].recipeColor ],
			data : values
		} ],
		labels : labels
	};
	
	var options = createChartOptions(pie);
	
	
	createChart('pie', id, data, options);
}

/**
 * Cria o grafico de linhas referente a receitas.
 */
function createLineChart(id){
	
	
	var line = JSON.parse(getLineData());
	
	var label= new Array();
	var teste = new Array();
	var values = new Array();
	var lineColor = new Array();
	
	$.each(line, function(i, obj) {
		label.push(obj.month);
		values.push(obj.recipe );
		teste.push("Arrecadação: R$" + obj.date);
		lineColor.push("#82b1ff");
	});
	
	var data = {
		datasets : [ {
			data : values,
			label: "Arrecadação: R$",
			backgroundColor : "#b9f6ca",
			borderColor: "#0277bd",
			fill: false
		} ],
		labels : label
	};
	

	var options = createChartOptions(line);
//	,
//	elements: {
//		line: {
//			tension: 0
//		}
//	}
	createChart('line', id, data, options);
}

/**
 * Cria o grafico de linhas referente a receitas.
 */
function createBarChart(id){
	
	var bar = JSON.parse(getBarData());
	
	var label= new Array();
	var valuesFstOrgan = new Array();
	var valuesSecOrgan = new Array();
	var valuesTrdOrgan = new Array();
	var valuesFrtOrgan = new Array();
	
	$.each(bar, function(i, obj) {
		
		if($.inArray(obj.month, label) == -1){
			label.push(obj.month);
		}
		if(obj.idOrgan == 1){
			valuesFstOrgan.push(obj.payment);
		}else if(obj.idOrgan == 2){
			valuesSecOrgan.push(obj.payment);
		}else if(obj.idOrgan == 3){
			valuesTrdOrgan.push(obj.payment);
		}else if(obj.idOrgan == 4){
			valuesFrtOrgan.push(obj.payment);
		}
//		values.push(obj.payment);
	});
	
	var data = {
		datasets : [ {
			data : valuesFstOrgan,
			label: "Fundo Municipal de Saúde",
			backgroundColor : "#82b1ff",
		},
		{
			data : valuesSecOrgan,
			label: "Administração R$",
			backgroundColor : "#8e5ea2",
		},
		{
			data : valuesTrdOrgan,
			label: "Fundo Municipal de Assistência Social",
			backgroundColor : "#3cba9f",
		},
		{
			data : valuesFrtOrgan,
			label: "Fundo de teste",
			backgroundColor : "#e8c3b9",
		},],
		labels : label
	};
	
	var options = {
			legend : {
				labels: {
					usePointStyle: true,
				},
			},
			title : {
				display : false,
			}
		};

	createChart('bar', id, data, options);
}

/**
 * Legenda gráfico de barras.
 * @param colorName
 * @returns
 */
function createPointStyleConfig(colorName) {
	var config = createConfig(colorName);
	config.options.legend.labels.usePointStyle = true;
	config.options.title.text = 'Point Style Legend';
	return config;
}


/**
 * Cria opções de configuração em gráficos.
 * @param option
 * @returns
 */
function createChartOptions(option) {

	var options = {
		legend : {
			display : option.legend
		},
		title : {
			display : option.legend,
		}
	};
	
	return options;
}

/**
 * Cria o gráfico.
 * @param chartStyle tipo do gráfico.
 * @param id id do html.
 * @returns
 */
function createChart(chartStyle, id, data, options){
	
	var myPieChart = new Chart(document.getElementById(id), {
	    type: chartStyle,
	    data: data,
	    options: options
	});
}

$(document).ready(function() {

	createPieChart('pieChart', 0);
	createPieChart('pieChart2', 1);
	createLineChart('lineChart');
	createBarChart('barChart');

});
