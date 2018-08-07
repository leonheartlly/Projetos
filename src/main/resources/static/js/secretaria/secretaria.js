$(document).ready(function(){
	

		
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
	    	
	    	var projectHtml = '';
	    	var filesHtml = '';
	    	var endFile= '';
	    	
	    	if(!$.isEmptyObject(result.listaProjetos)){
	    		$.each(result.listaProjetos, function(key, value){
	    			projectHtml += '<li><div class="collapsible-header row mb0"><div class="col l1 xl1"><img class="circle" width="100" height="100" src="'+ value.fotoCapa + '"/></div><div class="ml20 col l11 xl11"><h5>' + value.titulo + '</h5><br/><span>'+value.descricao+'</span></div></div><div class="collapsible-body"><h5>Arquivos para Download</h5>';
	    			if(!$.isEmptyObject(result.listaArquivoProjeto)){
		    			$.each(result.listaArquivoProjeto, function(key, value){
		    				if(value.tipo == 1){ //PDF
		    					filesHtml += '<a class="waves-effect waves-light btn uppercase">'+ value.nomeArquivo +'</a> <span>PDF</span><br/>';
		    				}else if(value.tipo == 2){//DOC
		    					filesHtml += '<a class="waves-effect waves-light btn uppercase">'+ value.nomeArquivo +'</a> <span>DOC</span>';
		    				}else if(value.tipo == 3){//ZIP
		    					filesHtml += '<a class="waves-effect waves-light btn uppercase">'+ value.nomeArquivo +'</a> <span>ZIP</span>';
		    				}
				    	});
		    		}
	    			endFile += '</div></li>';
	    			projectHtml += filesHtml + endFile;
	    			filesHtml = '';
		    		endFile= '';
		    	});
	    		
	    		$('#secretaria-accordion').html(projectHtml);
	    	}else{
	    		projectHtml += '<li><div class="row"><div class="ml20 col l11 xl11 valign-wrapper center"><h5>Esta secretaria não possui nenhum projeto ativo no momento.</h5><br/></div></div></li>';
	    		$('#secretaria-accordion').html(projectHtml);
	    	}
	    	
	    }, 
	    error: function(xhr, status, error){
	    }
	    });
}
		
/**
 * Clears the accordion body.
 * @returns
 */
function refreshTable(){
	$("#secretaria-accordion").empty();
}
