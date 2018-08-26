$(document).ready(function() {
	
//	var graph = JSON.parse(getOuvidoriaData());
	createBarChart(getOuvidoriaData());
	
	$("#ouvidoria-form").submit(function(event){
		removeErrors();
		var isValid = pageRules();
		  
		if(isValid){
			sendEmail();
		}else{
			createAdviceTag(false);
		}
		
		event.preventDefault();
	});
	
	
	setPhoneMask();
	
});

$(window).load(function() {
//	var grafico = getData();
//	createBarChart(grafico);
});


function removeErrors(){
	$('label').each(function (){
		if($(this).hasClass('red-text')){
			$(this).removeClass('red-text');
		}
		
	});
	$('input').each(function (){
		if($(this).hasClass('select-error')){
			$(this).removeClass('select-error');
		}
		if($(this).hasClass('invalid')){
			$(this).removeClass('invalid');
		}
	});
	
	$("#description").removeClass('invalid');
}

function createAdviceTag(valid){
	
	var $toastContent = '';
	
	var mensagem = '';
	if(!valid){
		$toastContent =  $('<span class="valign-wrapper red-text"><i class="small material-icons prefix red-text pr5">warning</i>Os campos sinalizados são de preenchimento obrigatório.</span>');
		//mensagem = ;
	}else{
		$toastContent =  $('<span class="valign-wrapper green-text"><i class="small material-icons prefix green-text pr5">done</i>Manifestação Recebida.</span>');
//		mensagem = 'Manifestação Recebida.';
	}
	
	  Materialize.Toast.removeAll();
		 //= $('<span class="valign-wrapper red-text"><i class="small material-icons prefix red-text pr5">warning</i>' + mensagem + '</span>');
		Materialize.toast($toastContent, 10000, 'grey lighten-5 rounded');
}

/**
 * Pare rules based on user choosen options.
 */
function pageRules(){
	
	var isValid = true;

	if($("#description").val() == ""){
		$("#description").addClass("invalid");
		$("#description-tag label").addClass("red-text");
		isValid = false;
	}
	if($("#manifestacao-selector").attr('selected-Id') == ""){
		$("#manifestacao-selector").addClass("select-error");
		$("#manifest-tag label").addClass("red-text");
		isValid = false;
	}
	
	if(!$("#annoni").prop('checked')){
		if($("#email-ouvidoria").hasClass('obrigatorio') && $("#email-ouvidoria").val() == ""){
			$("#email-ouvidoria").addClass("invalid");
			$("#email-tag label").addClass("red-text");
			isValid = false;
		}
		if($("#fone-ouvidoria").hasClass('obrigatorio') && $("#fone-ouvidoria").val() == ""){
			$("#fone-ouvidoria").addClass('invalid');
			$("#phone-tag label").addClass("red-text");
			isValid = false;
		}
		if($("#resposta").attr("selected-Id") == ""){
			$("#resposta").addClass("select-error");
			$("#ouvidoria-response label").addClass("red-text");
			isValid = false;
		}
	}
	
	return isValid;
}

/**
 * hide/show fields if its annonimous contact.
 * @returns
 */
function annonimousContact(){
	if($("#annoni").prop('checked')){
		$("#ouvidoria-identify").addClass("hide");
		$("#ouvidoria-response").addClass("hide");
		
		$("#resposta").attr("selected-Id", "");
		$("#resposta").val("Obter resposta");
	}else{
		$("#ouvidoria-identify").removeClass("hide");
		$("#ouvidoria-response").removeClass("hide");
	}
}

/**
 * Turn to obligatory fields based on user option.
 * @param id choosen option id.
 * @returns
 */
function fonteResposta(id){
	$('input').each(function (){
		if($(this).hasClass('obrigatorio')){
			$(this).removeClass('obrigatorio');
		}
	});
	
	if(id == 'resposta1'){
		$("#email-ouvidoria").addClass("obrigatorio");
		$("#fone-ouvidoria").addClass("obrigatorio");
	}else if(id == 'resposta2'){
		$("#email-ouvidoria").addClass("obrigatorio");
	}else if(id == 'resposta3'){
		$("#fone-ouvidoria").addClass("obrigatorio");
	}
}

function sendEmail(){
	
	var name = $("#nome-ouvidoria").val();
	var email= $("#email-ouvidoria").val();
	var phone = $("#fone-ouvidoria").val();
	var identity = $("#annoni").prop('checked');
	var description = $("#description").val();
	var type = $("#manifestacao-selector").attr('selected-Id');
	var response = $("#resposta").attr('selected-Id');
	
	
	var datas = { name: name, identity : identity, description: description, type: type, email: email, phone: phone, response: response };
	$.ajax({
		url: "../prefeitura/mail/",
		type: "POST", 
		//contentType: "application/json",
		dataType: 'json',
		data:JSON.stringify(datas),
		cache:false,
		 beforeSend: function(xhr) {
		        xhr.setRequestHeader("Accept", "application/json");
		        xhr.setRequestHeader("Content-Type", "application/json");
//		        xhr.setRequestHeader(header, token);
		    },
	    success: function(values){
	    	createAdviceTag(true);
	    	createBarChart(values);
	    }, 
	    error: function(xhr, status, error){
//	    	showHideNotFound();
	    }
	    });
	
	return 
}

/**
 * Phone mask.
 * @returns
 */
function setPhoneMask(){
	var SPMaskBehavior = function (val) {
	    return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
	  },
	  spOptions = {
	    onKeyPress: function(val, e, field, options) {
	        field.mask(SPMaskBehavior.apply({}, arguments), options);
	      }
	  };

	  $('#fone-ouvidoria').mask(SPMaskBehavior, spOptions);
}

function createBarChart(result){
	
		$('#barChart').empty();
	
		var label = ["Sugestão", "Reclamação", "Denúncia", "Elogio"];
		var totalSugestions = result.totalSugestions;
		var totalComplaint = result.totalComplaint;
		var totalDelation = result.totalDelation;
		var totalCompliment = result.totalCompliment;
		
		
		new Chart(document.getElementById("barChart"),{
			type: 'bar',
		 data : {
				labels: ["Sugestão", "Reclamação", "Denúncia", "Elogio"],
		
			datasets : [ 
				{
					label: "",
					backgroundColor : ["#82b1ff",  "#8e5ea2", "#3cba9f", "#e8c3b9"],
					data : [totalSugestions, totalComplaint, totalDelation, totalCompliment]
				}
			]
		},
		 options: {
		      legend: { display: false },
		      title: {
		        display: true
		      },
		      scales: {
		    	  yAxes: [{
		    		  display:true,
		    		  ticks:{
		    			  suggestedMin: 0
		    		  }
		    	  }]
		      }
		    }
		});
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
	    data: data
	});
}
