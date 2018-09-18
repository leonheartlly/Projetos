$(document).ready(function() {
	
//	var csrf_token = $('meta[name="csrf-token"]').attr('content');
//	var token = $('#csrfToken').val();
//	var header = $('#csrfHeader').val();
	
	$("#legislacao-form").submit(function(event){
		
		var idOrgao = $("#customSelect").attr("selected-Id");
		var idTipo = $("#selectedType").attr("value");
		var nameValue= $("#nome-legislacao").val();
		var initDate= $("#data-inicial").val();
		var finalDate= $("#data-final").val();
		
		var datas = { idOrgao: idOrgao, tipo : idTipo, resumo: nameValue, dataInicial: initDate, dataFinal: finalDate};
		
		event.preventDefault();
		
		var val = findFormValues();
		$.ajax({
			url: "/editais/filtrarLegislacao/",
			type: "POST", 
			dataType: 'json',
			data:JSON.stringify(datas),
			cache:false,
			 beforeSend: function(xhr) {
			        xhr.setRequestHeader("Accept", "application/json");
			        xhr.setRequestHeader("Content-Type", "application/json");
//			        xhr.setRequestHeader(header, token);
			    },
		    success: function(result){
		    	if($.isEmptyObject(result)){
		    		showNotFound();
		    	}else{
			    	$.each(result, function(key, value){
			    		$('#detailBody').append('<tr>');
			    		$('#detailBody').append('<td class="td center-align">'+value.nome+'</td>');
			    		$('#detailBody').append('<td class="td center-align">'+value.exercicio+'</td>');
			    		$('#detailBody').append('<td class="td center-align">'+value.mes+'</td>');
			    		$('#detailBody').append('<td class="td center-align">'+value.resumo+'</td>');
			    		$('#detailBody').append('<td class="td center-align">'+value.orgao.orgao+'</td>');
			    		$('#detailBody').append('<td class="td center-align" attach=/img/'+value.arquivo+' onclick="displayPDF($(this).attr('+"'attach'"+'),'+"'legislacao'"+')"> <i class="small material-icons green-text text-darken-4 pointer download" >cloud_download</i></td></tr>');
			    	});
		    	}
		    }, 
		    error: function(xhr, status, error){
		    	showHideNotFound();
		    }
		    });
	});
	
});

function refreshTable(){
	
	$("#detailBody").empty();
}

function openList(){
	$("#select1").removeClass('hidden');
}

function chooseElement(text, value){
	$("#customSelect").val(text);
	$("#customSelect").attr('selected-Id', value);
	$("#select1").addClass('hidden');
}

function hideElement(){
	$("#select1").addClass('hidden');
}

function removeMaterializeSelect(){
	$('.select-dropdown').addClass('none');
}


function displaySearch(code) {
	var option = $("#UserOption").val();

	setValue(code);

	if ($("#legislacao-form").css('display') == "none") {

		$("#legislacao-form").show('slow');
	} else {

		$("#legislacao-form").hide('slow');
		if (option != code) {
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


