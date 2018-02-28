function modalFeed( tag){
	
	var date = tag.children('div').children('span:first').text();
	var topic = tag.children('div').children('span:last').text();
	var title = tag.children('div').children('h5').text();
	topic = topic.toUpperCase();
	$('.modal-title').text(title);
	$('.modal-subtitle').text(date + ' - ' + topic);

	
}