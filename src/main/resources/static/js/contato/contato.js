
$(document).ready(function() {
	

	        
});

function hoverEffect(id){
	if($("#"+id).text() == "EM BREVE"){
		$("#"+id).addClass("redHover");
	}else{
		$("#"+id).addClass("numberHover");
	}
	
}



function removeHover(id){
	if($("#"+id).text() == "EM BREVE"){
		$("#"+id).removeClass("redHover");
	}else{
		$("#"+id).removeClass("numberHover");
	}
	
}
