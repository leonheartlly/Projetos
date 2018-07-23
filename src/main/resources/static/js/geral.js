//
//function teste2(menuId) {
//	
//	if(menuId ==''){
//		
//		$(".header-options-box").addClass("inactive");
//		$("div:has(active)").removeClass('active');
//		return;
//	}
//	
//	$("#" + menuId).removeClass("inactive");
//	$("#" + menuId).addClass("active");
//	
////	if($(".header-options-box:contains('active')").){
////		
////	}
//	
//	removeUnneededClass(menuId);
//	$(".ar-box-display").css('display', 'block');
//}

/**
 * Renova a CSS de links selecionados.
 * @param boxId id da caixa atual
 * @param linkId id do link
 * @returns
 */
function showBlock(boxId, linkId) {

	$(".ar-box-vline").removeClass("ar-box-is-selected");
	$(".has-second-tier").removeClass("ar-box-active");
	$("#" + linkId).addClass("ar-box-active");
	$("#" + boxId).addClass("ar-box-is-selected");

}

/**
 * Itera através das DIV's adiciona a classe inativo.
 * @param actualID id usado atualmente.
 * @returns
 */
function removeUnneededClass(actualID){
	
	$('div > .header-options-box').each(function(){
//		alert($(this).attr("id"));
		var id = $(this).attr('id');
		if(id != actualID){
			$("#" + id).removeClass('active');
			$("#" + id).addClass('inactive');
		}
	});
}

$(document).ready(function() {

	/**
	 * Fecha a caixa de submenu ao retirar o mouse de cima.
	 */
//	$("#menuBox").mouseleave(function() {
//
//		$(this).fadeOut("fast", function() {
//			
//		});
//		// $(this).hide();
//	});
	
	 $('.dropdown-button').dropdown({
		 hover: true,
		 belowOrigin: true,
		 constrainWidth: false,
		 stopPropagation: false
	 });
	 

});

/**
 * Adicona o efeito de mouseover da tag A à tag h5
 * @param tag imagem atual
 * @returns
 */
function addTagEffect(tag){
	tag.children('div').children('h5').addClass("news-hover-conf");
}

function removeTagEffect(tag){
	tag.children('div').children('h5').removeClass("news-hover-conf");
}

/**
 * Google maps.
 * @returns
 */
function initMap(selected) {
	
	if(selected == undefined){
		selected = 0;
	}
	
    var locations = [
    					{lat: -12.9308834, lng: -49.8268863}, //prefeitura
    					{lat: -12.927618, lng: -49.833797}, //policia
    					 {lat: -12.931642, lng: -49.8284274} //hospital
    				];
    
    
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 17,
      center: locations[selected]
    });
   
    for(var i = 0; i < locations.length; i++){

    	
    	var marker = new google.maps.Marker({
    		
    		
    		position: locations[i],
    		map: map
    	});
	}
    
}

/**
 * Adiciona o efeito hover no menu header quando está no dropdown.
 * @param id id da tag A.
 */
function addDropDownHoverEffect(id){
	$('#'+id).addClass('hoverText');
}

/**
 * Remove o efeito hover no header quando sai do dropdown.
 * @param id id da tag A.
 */
function removeDropDownHoverEffect(id){
	$('#'+id).removeClass('hoverText');
}

function showButtonText(tag){
	tag.children('p').removeClass("hide");
	tag.children('img').addClass("hide");
}

function hideButtonText(tag){
	tag.children('p').addClass("hide");
	tag.children('img').removeClass("hide");
}

/**
 * Moves page to top.
 * @returns
 */
function moveToTop() {
	$("html, body").animate({
		scrollTop : 0
	}, "slow");
}

function hideBtn(id){
	$('#'+id).addClass('hide');
}

window.onscroll = function(ev) {
	
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight && (window.innerHeight + window.scrollY) > 1500) {
    	
        $("#pageup").removeClass("hide");
    }
};
