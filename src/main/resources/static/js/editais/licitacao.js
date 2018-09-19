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

function displayPDF(file, titulo) {
	var PDFwindow = window.open(file, '_blankPage', 'fullscreen=yes');
	PDFwindow.document.title = titulo;

	return false;
}



$(document).ready(function() {
	$('.modal').modal();
	$('.tooltipped').tooltip({delay: 10});
	$("#cnpj-licitacao").mask("99.999.999/9999-99");
	
	
	
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
							});

	$('#detailBody >tr').each(function(index) {

		$(this).attr('id', index);
	});
	
$("#licitacao-form").submit(function(event){
	
		var idOrgao = $("#orgao-selector").attr("selected-Id");
		var initDate= $("#data-inicial").val();
		var finalDate= $("#data-final").val();
		var cnpj= $("#cnpj-licitacao").val();
		var fornecedor= $("#fornecedor-licitacao").val();
		var idModalidade= $("#modalidade-selector").attr("selected-Id");
		var objeto= $("#objeto-licitacao").val();
		
		
		
		if(validate(objeto, idOrgao, initDate, finalDate, cnpj, fornecedor, idModalidade)){
		var datas = { orgaoVO: idOrgao, dataInicialVO : initDate, dataFinalVO: finalDate, cnpjVO: cnpj, fornecedorVO: fornecedor, modalidadeVO: idModalidade, objeto: objeto};
		
		showLoading();
		$("#licitacao-accordion").empty();
		event.preventDefault();
		hideNotFound();
		
		$.ajax({
			url: "/editais/filtrarLicitacao/",
			type: "POST", 
			dataType: 'json',
			data:JSON.stringify(datas),
			cache:false,
			beforeSend: function(xhr) {
			        xhr.setRequestHeader("Accept", "application/json");
			        xhr.setRequestHeader("Content-Type", "application/json");
			   },
		    success: function(result){
		    	
		    	
		    	if($.isEmptyObject(result)){
		    		showNotFound();
		    	}else{
		    		
		    		var generatedCode = '<li ><div class="collapsible-header row mb0"><div class=" col l12"><div class="col l1"><h6 class="bold green-text text-darken-3 center">Nº Processo</h6></div><div class="col l2"><h6 class="bold green-text text-darken-3 center">Edital</h6></div><div class="col l7"><h6 class="bold green-text text-darken-3 center">Objeto</h6></div><div class="col l1"><h6 class="bold green-text text-darken-3 center">Situação</h6></div><div class="col l1"><h6 class="bold green-text text-darken-3 center">Valor</h6></div></div></div></li>';
			    	$.each(result, function(key, value){
			    		var downloadBtnCode = '';
			    		generatedCode += '<li> <div class="collapsible-header"> <div class="row resize">' +
			    			'<label class="col l1 xl1 p0 center">' + value.processo + '</label>' +
			    			'<label class="col l2 xl2 center ">' + value.edital + '</label>' +
			    			'<label class="col l7 xl7 left line-resize ">' + value.objeto + '</label>' + 
			    			'<label class="col l1 xl1 center">' + value.situacao.descricao + '</label>'+ 
			    			'<label class="col l1 xl1 center">R$' + value.valor + '</label></div></div>';
			    		generatedCode += '<div class="collapsible-body p0"><div class="z-depth-3"><h6 class="hidden-tr-title m0 p20 bold">DETALHES DA LICITAÇÃO </h6> <div class="divider ml15 mr15"></div><div class="p20"><div class="row"><div class="col l4">' +
			    			'<label class="bold grey-text text-darken-3 ">SITUAÇÃO: </label> <label class="bold"> &nbsp' + value.situacao.descricao + '</label></div>'+
			    			'<div class="col l4"><label class="bold grey-text text-darken-3 ">Nº PROCESSO: </label> <label class="bold"> &nbsp' + value.processo + '</label></div>' +
			    			'<div class="col l4"><label class="bold grey-text text-darken-3 ">Nº EDITAL: </label> <label class="bold "> &nbsp' + value.edital + '</label></div></div>';
			    		generatedCode += '<div class="row"><div class="col l4"><label class="bold grey-text text-darken-3 ">DATA ABERTURA: </label> <label class="bold"> &nbsp' + value.dataAbertura + '</label>' +
			    			'</div><div class="col l4"><label class="bold grey-text text-darken-3 ">DATA HOMOLOGAÇÃO: </label> <label class="bold"> &nbsp' + value.dataHomolog + '</label></div>' +
			    			'<div class="col l4"><label class="bold grey-text text-darken-3 ">DATA ADJUDICAÇÃO: </label> <label class="bold"> &nbsp' + value.dataAdjudicacao + '</label></div></div>';
			    		generatedCode += '<div class="row"><div class="col l4"><label class="bold grey-text text-darken-3 ">ORGÃO: </label> <label class="bold"> &nbsp' + value.orgao.orgao + '</label>' + 
			    			'</div><div class="col l4"><label class="bold grey-text text-darken-3 ">MODALIDADE LICITAÇÃO: </label> <label class="bold"> &nbsp' + value.modalidade.modalidade + '</label></div>'+ 
			    			'<div class="col l4"><label class="bold grey-text text-darken-3 ">VALOR: </label> <label class="bold">&nbsp R$ &nbsp' + value.valor + '</label></div></div>';
			    		generatedCode += '<div class="row mb5"><div class="col l12"><label class="bold grey-text text-darken-3 ">OBJETO: </label>' +
		    				'<label class="bold red-text text-darken-4"> &nbsp' + value.objeto + '</label></div>';
			    		if(value.anexos.length > 0){
			    			downloadBtnCode += '<div class="fixed"><div class="fixed-action-btn horizontal pt20 click-to-toggle"><a class="btn-floating btn-large green tooltipped pulse" data-position="bottom" data-tooltip="ANEXOS" id="btn-attach"><i class="large material-icons">file_download</i></a><ul class="pt20">';
			    		
				    		$.each(value.anexos, function (chave, valor){
				    				if(valor.tipoAnexo == 1){
				    					downloadBtnCode += '<li class="btn-floating waves-effect waves-light tooltipped blue center" data-position="bottom" data-delay="10" data-tooltip="EDITAL" onclick="displayPDF(\'' +  valor.caminhoArquivo + '\', \'arquivo\')" ><a  ><i class="material-icons white-text">insert_drive_file</i></a></li>';
				    				}
				    				if(valor.tipoAnexo == 2){
				    					downloadBtnCode += '<li class="btn-floating waves-effect waves-light tooltipped yellow darken-1 center" data-position="bottom" data-delay="10" data-tooltip="AVISO" onclick="displayPDF(\'' +  valor.caminhoArquivo + '\', \'arquivo\')" ><a><i class="material-icons white-text">new_releases</i></a> </li>';
				    				}
					    			if(valor.tipoAnexo == 3){
					    				downloadBtnCode += '<li class="btn-floating waves-effect waves-light tooltipped red center"  data-position="bottom" data-delay="10" data-tooltip="CANCELAMENTO" onclick="displayPDF(\'' +  valor.caminhoArquivo + '\', \'arquivo\')"><a><i class="material-icons white-text">cancel</i></a></li>';
					    			}
					    			if(valor.tipoAnexo >= 4 || valor.tipoAnexo < 1){
					    				downloadBtnCode += '<li class="btn-floating waves-effect waves-light tooltipped center" data-position="bottom" data-delay="10" data-tooltip="OUTROS" onclick="displayPDF(\'' +  valor.caminhoArquivo + '\', \'arquivo\')"> <a><i class="material-icons white-text">flag</i></a></li>';
					    			}
					    			if(chave == value.anexos.length){
					    				downloadBtnCode += '</ul></div></div>';
					    			}
				    		});
				    		generatedCode += downloadBtnCode;
			    		}
			    		
			    		generatedCode += '</div></div></div></div></li>';

			    		
			    	});
			    	$('#licitacao-accordion').append(generatedCode);
		    	}
			    $("#loading").modal('close');
			    callTooltips();
		    }, 
		    error: function(xhr, status, error){
		    	$("#loading").modal('close');
		    	showNotFound();
		    }
		  });
		}else{
			//cancela o submit do usuário
			return false;
		}
	});
	
});

/**
 * Validate every possible input field.
 * 
 * @returns true or false
 */
function validate(objeto, idOrgao, initDate, finalDate, cnpj, fornecedor, idModalidade){

	Materialize.Toast.removeAll();
	var $toastContent = '';
	var isValid = true;
	
	if(objeto != "" && objeto.trim().length > 0 && objeto.trim().length < 10){
		$toastContent =  $('<span class="valign-wrapper red-text"><i class="small material-icons prefix red-text pr5">warning</i>A Pesquisa por Objeto de Licitação deve ter mais de 10 caracteres.</span>');
		Materialize.toast($toastContent, 10000, 'grey lighten-5 rounded');
		isValid = false;
	}
	$toastContent = '';
	if(idOrgao < 0 || idOrgao > 4){
		$toastContent =  $('<span class="valign-wrapper red-text"><i class="small material-icons prefix red-text pr5">warning</i>O orgão selecionado é inválido. Tente novamente mais tarde.</span>');
		Materialize.toast($toastContent, 10000, 'grey lighten-5 rounded');
		isValid = false;
	}
	$toastContent = '';
	if(idModalidade < 0 || idModalidade > 20){
		$toastContent =  $('<span class="valign-wrapper red-text"><i class="small material-icons prefix red-text pr5">warning</i>A modalidade selecionada é inválida. Tente novamente mais tarde.</span>');
		Materialize.toast($toastContent, 10000, 'grey lighten-5 rounded');
		isValid = false;
	}
	$toastContent = '';
	if(cnpj != ""){
		var testCNPJ = cnpj.replace('.', '');
		testCNPJ = testCNPJ.replace('-', '');
		testCNPJ = testCNPJ.replace('/', '');
		if(testCNPJ.length > 0 && testCNPJ.length <= 14){
			$toastContent =  $('<span class="valign-wrapper red-text"><i class="small material-icons prefix red-text pr5">warning</i>O CNPJ deve conter 14 dígitos.</span>');
			Materialize.toast($toastContent, 10000, 'grey lighten-5 rounded');
			isValid = false;
		}
	}	
	
	$toastContent = '';
	if(objeto === "" && idOrgao ==="" && idModalidade ==="" && fornecedor ==="" && initDate ==="" && finalDate === "" && cnpj === ""){
		$toastContent =  $('<span class="valign-wrapper red-text"><i class="small material-icons prefix red-text pr5">warning</i>Para efetuar uma pesquisa, preecha pelo menos um filtro.</span>');
		Materialize.toast($toastContent, 10000, 'grey lighten-5 rounded');
		isValid = false;
	}
	return isValid;
}

/**
 * Ativa os tooltips.
 * @returns
 */
function callTooltips(){
	$(".tooltipped").tooltip({delay: 10});
}

/**
 * Create a CSV file from the results being showed.
 * @returns csv file.
 */
function createCSVFile(){
    var html = document.querySelector("table").outerHTML;
    exportTableToCsv(html, "licitacao.csv");
}

function showLoading(){
	$('#loading').modal({dismissible:false});
	$('#loading').modal('open');
	$("#loading").removeClass('hide');
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


