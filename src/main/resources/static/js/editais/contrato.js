/**
 * Create a CSV file from the results being showed.
 * @returns csv file.
 */
function createCSVFile(){
    var html = document.querySelector("#contratoTable").outerHTML;
    exportTableToCsv(html, "contrato.csv");
}

/**
 * 
 * @returns
 */
function createDocFile(){
	
	var content = document.querySelector('.docFile').outerHTML;
	var converted = htmlDocx.asBlob(content, {orientation: 'landscape'});
	saveAs(converted, 'contrato.docx');
}

/**
 * Create a PDF file from the filtered table.
 * @param licitacao table.
 * @returns
 */
function createPDFFile(contrato){
	
	var columns = ["Numero", "Fornecedor", "Orgão", "Data Assinatura", "Data Vigência", "Data Publicação", "Valor"];
	var pdfData = [];
	
	contrato.forEach(function (value, i){
		pdfData.push( [value.numero, value.fornecedor.razaoSocial, value.orgao.orgao, value.dataAssinatura, value.dataVigencia, value.dataPublicacao, value.valor]);
	});
	
	var doc = new jsPDF('l');
    doc.setFontSize(8);
    doc.autoTable(columns, pdfData, {
    	 
    	styles: {overflow: 'linebreak'},
    	columnStyles: {
    		0: {columnWidth: 25},
    		1: {columnWidth: 60},
    		2: {columnWidth: 60},
    		3: {columnWidth: 22},
    		4: {columnWidth: 22},
    		5: {columnWidth: 22},
    		6: {columnWidth: 25}
    	}
    });
    doc.output("dataurlnewwindow");
}