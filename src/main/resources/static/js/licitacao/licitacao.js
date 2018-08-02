function displayDetails(id) {
	var nextElem = parseInt(id) + parseInt(1);
	if ($("#" + nextElem).hasClass('hide-box')) {
		$("#" + nextElem).removeClass('hide-box');

		nextElem = parseInt(nextElem) + parseInt(1)
		$("#" + nextElem).removeClass('hide-box');
	} else {
		$("#" + nextElem).addClass('hide-box');
		nextElem = parseInt(nextElem) + parseInt(1)
		$("#" + nextElem).addClass('hide-box');
	}
}

//function refresh() {
//	$("#objeto-licitacao").val("");
//	$("#licitacao-form")[0].reset();
//}

function displayPDF(file, titulo) {
	var PDFwindow = window.open(file, '_blankPage', 'fullscreen=yes');
	PDFwindow.document.title = titulo;

	return false;
}

$(document).ready(
		function() {

			$('select').material_select();
			
			

			$('.datepicker')
					.pickadate(
							{
								selectMonths : true, // Creates a dropdown to
														// control month
								monthsFull : [ 'Janeiro', 'Fevereiro', 'Março',
										'Abril', 'Maio', 'Junho', 'Julho',
										'Agosto', 'Setembro', 'Outubro',
										'Novembro', 'Dezembro' ],
								format : 'dd/mm/yyyy',
								weekdaysLetter : [ 'D', 'S', 'T', 'Q', 'Q',
										'S', 'S' ],
								weekdaysShort : [ 'Dom', 'Seg', 'Ter', 'Qua',
										'Qui', 'Sex', 'Sab' ],
								selectYears : 15, // Creates a dropdown of 15
													// years to control year,
								today : 'Hoje',
								clear : 'Limpar',
								close : 'Ok',
								closeOnSelect : false
							// Close upon selecting a date,
							}
							);

			$('#detailBody >tr').each(function(index) {

				$(this).attr('id', index);
			});

		});

/**
 * Create a CSV file from the results being showed.
 * @returns csv file.
 */
function createCSVFile(){
    var html = document.querySelector("table").outerHTML;
    exportTableToCsv(html, "licitacao.csv");
}

/**
 * 
 * @returns
 */
function createDocFile(){
	
	var content = document.querySelector('.docFile').outerHTML;
	var converted = htmlDocx.asBlob(content, {orientation: 'landscape'});
	saveAs(converted, 'licitacao.docx');
}

/**
 * Create a PDF file from the filtered table.
 * @param licitacao table.
 * @returns
 */
function createPDFFile(licitacao){
	
	var columns = ["N° Processo", "Edital", "Dt. Abertura", "Modalidade", "Situação", "Valor", "objeto"];
	var pdfData = [];
	licitacao.forEach(function (value, i){
		pdfData.push( [value.processo, value.edital, value.dataAbertura, value.modalidade.modalidade, value.situacao.descricao, value.valor, value.objeto]);
	});
	
	var doc = new jsPDF('l');
    doc.setFontSize(8);
    doc.autoTable(columns, pdfData, {
    	 
    	styles: {overflow: 'linebreak'},
    	columnStyles: {
    		0: {columnWidth: 25},
    		1: {columnWidth: 40},
    		2: {columnWidth: 25},
    		3: {columnWidth: 22},
    		4: {columnWidth: 22},
    		5: {columnWidth: 22}
    	}
    });
    doc.output("dataurlnewwindow");
}


