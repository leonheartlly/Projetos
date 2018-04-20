$(document).ready(function() {

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