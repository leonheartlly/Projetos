/**
 * Its never been here.
 * @returns
 */
function openEye() {
	$('.the-eye').fadeToggle('slow');
	setTimeout(function() {
		window.history.back();
	}, 1000);
}

$(document).ready(function() {
	
	/**
	 * Retorna o usuário para a página anterior após 5 segundos.
	 */
	setTimeout(function() {
		window.history.back();
	}, 5000);
});