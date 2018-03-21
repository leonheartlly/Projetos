function displayDetails(id){
	var nextElem = parseInt(id) + parseInt(1);
	if($("#"+nextElem).hasClass('hide-box')){
		$("#"+ nextElem).removeClass('hide-box');
		
		nextElem = parseInt(nextElem) + parseInt(1)
		$("#" + nextElem).removeClass('hide-box');
	}
	else{
		$("#"+nextElem).addClass('hide-box');
		nextElem = parseInt(nextElem) + parseInt(1)
		$("#"+nextElem).addClass('hide-box');
	}
}

function displayPDF(file, titulo){
	var PDFwindow = window.open(file, '_blankPage', 'fullscreen=yes');
	//$(newPage.document).find('html').append('<head><title>'+titulo+'</title></head>');
	PDFwindow.document.title=titulo;
	
	return false;
}

function teste(){
//	alert($('select[name=selector]').val());
	return 1;
}

$(document).ready(function() {
	
	
	$('select').material_select();
    
    
    
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        monthsFull: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
        format: 'dd/mm/yyyy',
        weekdaysLetter: [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
        weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
        selectYears: 15, // Creates a dropdown of 15 years to control year,
        today: 'Today',
        clear: 'Clear',
        close: 'Ok',
        closeOnSelect: false // Close upon selecting a date,
      });
    
    $('#detailBody >tr').each(function(index){
    	
    		$(this).attr('id', index);
    });
    
    	
});

