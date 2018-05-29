

$(document).ready(function() {
	$("#legislacao-form").submit(function(event){
		var datas = { idOrgao: "1", tipo : "1"};
		event.preventDefault();
		
		var val = findFormValues();
		$.ajax({
			url: "/editais/filtrarLegislacao", 
			type: 'POST', 
			contentType: "application/json",
			dataType: 'json',
			data: JSON.stringify({"idOrgao":"1", "tipo":"1"}), 
			cache:false,
		    success: function(result){
		    	$("#results").replaceWith(result);
		    }, 
		    error: function(xhr, status, error){
		    	
//		    	alert("ERRO " + xhr.responseText);
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
	$("#UserOption").val(value);
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

function findTableData(){
	
	
//	$.ajaxSetup({
//		cache: false
//	});
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

