function modalFeed( tag, noticia){
	
	var date = tag.children('div').children('span:first').text();
	var topic = tag.children('div').children('span:last').text();
	var title = tag.children('div').children('h5').text();
	var news = tag.children('div').children('h6').text();
	topic = topic.toUpperCase();
	$('.modal-title').text(title);
	$('.modal-subtitle').text(date + ' - ' + topic);
	$('.modal-text').text(news);
	
	alert(noticia.titulo);
	
}

$(document).ready(function() {
	$('.modal').modal();
	
    $('.negocio').lightGallery({
        thumbnail:true
    }); 


	        
});

function initTrem(id){
	
}