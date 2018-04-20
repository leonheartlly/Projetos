package br.com.prefeitura.web.model;

import lombok.Builder;
import lombok.Data;

@Data
public class Fornecedor {

	private Long id;
	private String nome;
	private String razaoSocial;
	private String tipoFornecedor;
	private String cpf;
	private String cnpj;
	
	public Fornecedor(Long id, String nome, String razaoSocial, String tipoFornecedor, String cpf, String cnpj) {
		super();
		this.id = id;
		this.nome = nome;
		this.razaoSocial = razaoSocial;
		this.tipoFornecedor = tipoFornecedor;
		this.cpf = cpf;
		this.cnpj = cnpj;
	}

	public Fornecedor(String nome) {
		super();
		this.nome = nome;
	}

	public Fornecedor() {
		super();
	}
	
	@Builder(builderMethodName = "fornecedorBuilder")
	public Fornecedor newFornecedor(Long id, String nome, String razaoSocial, String tipoFornecedor, String cpf, String cnpj) {
		return new Fornecedor(id, nome, razaoSocial, tipoFornecedor, cpf, cnpj);
	}
	
}
