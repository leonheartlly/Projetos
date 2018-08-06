$(document).ready(function(){
	

//    $('.collapsible').collapsible();
    
    $("#secretaria-form").submit(function(event){
		alert('funfou');
		event.preventDefault();
	});
		
});

function findSecretaria(id){
	$.ajax({
		url: "/prefeitura/obterSecretaria/",
		type: "POST", 
//		//contentType: "application/json",
		dataType: 'json',
		data:JSON.stringify(id),
		cache:false,
		 beforeSend: function(xhr) {
		        xhr.setRequestHeader("Accept", "application/json");
		        xhr.setRequestHeader("Content-Type", "application/json");
		    },
	    success: function(result){
	    	$("#secretaria-desccricao").removeClass("hide");
	    	
	    	$("#secretaria-titulo").html(result.secretaria);
	    	$("#secretaria-secretario").html(result.secretario);
	    	$("#secretaria-missao").html(result.missao);
	    	$("#secretaria-telefone").html(result.telefone);
	    	$("#secretaria-email").html(result.email);
	    	$("#secretaria-inicio").html(result.horarioAbertura);
	    	$("#secretaria-fim").html(result.horarioFechamento);
	    	
//	    	if($.isEmptyObject(result)){
//	    	}else{
//		    	$.each(result, function(key, value){
//		    		$('#detailBody').append('<tr>');
//		    		$('#detailBody').append('<td class="td center-align">'+value.nome+'</td>');
//		    		$('#detailBody').append('<td class="td center-align">'+value.exercicio+'</td>');
//		    		$('#detailBody').append('<td class="td center-align">'+value.mes+'</td>');
//		    		$('#detailBody').append('<td class="td center-align">'+value.resumo+'</td>');
//		    		$('#detailBody').append('<td class="td center-align">'+value.orgao.orgao+'</td>');
//		    		$('#detailBody').append('<td class="td center-align" attach=/img/'+value.arquivo+' onclick="displayPDF($(this).attr('+"'attach'"+'),'+"'legislacao'"+')"> <i class="small material-icons green-text text-darken-4 pointer download" >cloud_download</i></td></tr>');
//		    	});
//	    	}
	    }, 
	    error: function(xhr, status, error){
	    	alert(error);
	    }
	    });
}
		
