$(document).ready(function() {
	createPieChart('pieChart', 0);
	
	 $('.negocio').lightGallery({
	        thumbnail:true
	    }); 
//	alert($(window).height());
//	alert($(window).width());
});

/**
 * Vai para a próxima posição da lista de eventos.
 * @param id evento atual
 * @param eventList lista de eventos encontrados.
 */
function viewNext(id, eventList) {
	var size = eventList.length - 1;
	for (i = 0; i < eventList.length; i++) {
		if (eventList[i].id == id) {
			if (i == size) {
				hideEvent(eventList[i].id);
				displayEvent(eventList[0].id);
			} else {
				hideEvent(eventList[i].id);
				displayEvent(eventList[i + 1].id);
			}
		}
	}
}

/**
 * Volta para a posição anterior da lista de eventos.
 * @param id evento atual.
 * @param eventList eventos encontrados.
 */
function viewPrev(id, eventList) {
	
	var size = eventList.length - 1;
	if(eventList[0].id == id){
		hideEvent(id);
		displayEvent(eventList[size].id);
		return;
	}
	
	for (i = size; i > 0; i--) {
		if (eventList[i].id == id) {
				hideEvent(eventList[i].id);
				displayEvent(eventList[i -1].id);
			
		}
	}
}

/**
 * Esconde um card de evento.
 * @param id evento a ser escondido.
 */
function hideEvent(id) {
	$('#event' + id).addClass("hide");
}

/**
 * Mostra um card de evento.
 * @param id evento a ser mostrado.
 */
function displayEvent(id) {
	$('#event' + id).removeClass("hide");
}


/**
 * Cria o grafico de pizza conforme dados.
 */
function createPieChart(id, val){
	
	var pie = JSON.parse(getPieData());
	
	var values = [pie[val].receita, pie[val].despesa];
	var labels = ["Receita", "Despesa"]
	
	var data = {
		datasets : [ {
			backgroundColor : [ "#4caf50", "#ff5252" ],
			data : values
		} ],
		labels : labels
	};
	
	var options = {
			legend : {
				display : true,
				position: 'bottom',
				labels: {
					usePointStyle: true,
				},
			},
			title : {
				display : false,
			}
		};
	
	//var options = createChartOptions(pie);
	
	
	createChart('pie', id, data, options);
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


function findLineData(){
    $.ajax({url: "/estatisticas/lineChart", 
    success: function(result){
    	createLineChart('lineChart', result);
    }, 
    error: function(){
    	alert("fail");
    }
    });
}


function findBarData(){
    $.ajax({url: "/estatisticas/barChart", 
    success: function(result){
    	createBarChart('barChart', result);
    }, 
    error: function(){
    	alert("fail");
    }
    });
}

function createLineChart(id, data){
	
	var line = JSON.parse(data);
	
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

	createChart('line', id, data, options);
}

function createBarChart(id, data){
	
	var bar = JSON.parse(data);
	
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