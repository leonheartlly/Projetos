

$(document).ready(function() {
	
//	var csrf_token = $('meta[name="csrf-token"]').attr('content');
//	alert(csrf_token);
//	var token = $('#csrfToken').val();
//	var header = $('#csrfHeader').val();

	
	$("#legislacao-form").submit(function(event){
		var datas = { idOrgao: "1", tipo : "1"};
		var idOrgao = $("#selectedOrgao").children(":selected").attr("value");
		var idTipo = $("#selectedType").attr("value");
		
		alert(idTipo);
		
		event.preventDefault();
		
		var val = findFormValues();
		$.ajax({
			url: "/editais/filtrarLegislacao/", ///editais/filtrarLegislacao
			type: "POST", 
			//contentType: "application/json",
			dataType: 'json',
			data:JSON.stringify({"idOrgao":"1", "tipo":"1"}),
			cache:false,
			 beforeSend: function(xhr) {
			        xhr.setRequestHeader("Accept", "application/json");
			        xhr.setRequestHeader("Content-Type", "application/json");
//			        xhr.setRequestHeader(header, token);
			    },
		    success: function(result){
//		    	
		    	$.each(result, function(key, value){
		    		$('#detailBody').append('<tr>');
		    		$('#detailBody').append('<td class="td center-align">'+value.nome+'</td>');
		    		$('#detailBody').append('<td class="td center-align">'+value.exercicio+'</td>');
		    		$('#detailBody').append('<td class="td center-align">'+value.mes+'</td>');
		    		$('#detailBody').append('<td class="td center-align">'+value.resumo+'</td>');
		    		$('#detailBody').append('<td class="td center-align">'+value.orgao.orgao+'</td>');
		    		$('#detailBody').append('<td class="td center-align" attach=/img/'+value.arquivo+' onclick="displayPDF($(this).attr('+"'attach'"+'),'+"'legislacao'"+')"> <i class="small material-icons green-text text-darken-4 pointer download" >cloud_download</i></td></tr>');
//		    		$('#detailBody').append('<td class="td center-align">'+value.nome+'</td>'+
//		    				'<td class="td center-align">'+value.exercicio+'</td>'+
//		    				'<td class="td center-align">'+value.mes+'</td>'+
//		    				'<td class="td center-align">'+value.resumo+'</td>'+
//		    				'<td class="td center-align">'+value.orgao.orgao+'</td>'+
//		    				'<td class="td center-align" attach=/img/'+value.arquivo+' onclick="displayPDF($(this).attr('+"'attach'"+'),'+"'legislacao'"+')"> <i class="small material-icons green-text text-darken-4 pointer download" >cloud_download</i></td>');
		    		//$('#detailBody').html('<td class="td center-align">'+value.exercicio+'</td>');
		    	});
		    }, 
		    error: function(xhr, status, error){
		    	
		    	alert("ERRO " + xhr.responseText);
		    }
		    });
	});
});


function displaySearch(code){
	var option = $("#UserOption").val();
	
	setValue(code);
	
	if($("#legislacao-form").css('display') == "none"){
		
		$("#legislacao-form").show('slow');
	}else{
		
		$("#legislacao-form").hide('slow');
		if(option != code){
			$("#legislacao-form").show('slow');
		}
	}
}

function setValue(value){
	$("#selectedType").val(value);
}

function changeTitle(title){
	$("#formTitle").text(title);
}

function findFormValues(){
	var values = {};
	values["tipo"] = $("#UserOption").val();
	values["orgao"] = $("select[name=idOrgao]").val();
	return values;
}

function displayPDF(file, titulo) {
	var PDFwindow = window.open(file, '_blankPage', 'fullscreen=yes');
	PDFwindow.document.title = titulo;

	return false;
}

function findTableData(){
	
	var data = $('legislacao-form').serialize();
    $.ajax({url: "/editais/filtrarLegislacao", type: 'GET', data: val, 
    success: function(result){
    	$("#results").html(result);
    }, 
    error: function(xhr, status, error){
    	
//    	alert("ERRO " + xhr.responseText);
    }
    });
}

