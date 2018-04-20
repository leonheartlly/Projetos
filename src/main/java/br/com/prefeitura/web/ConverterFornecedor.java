package br.com.prefeitura.web;

import org.springframework.core.convert.converter.Converter;

import br.com.prefeitura.web.model.Fornecedor;

public class ConverterFornecedor implements Converter<String, Fornecedor>{

	

	@Override
	public Fornecedor convert(String id) {
		if(id.equals("1")) { //Criar conversor fazendo select ao fornecedor específico quando buscado pelo nome ou cnpj
	    	Fornecedor person = new Fornecedor(1L, id, id, id, id, id);
	      return person;
	    }
		return null;
	}
}
