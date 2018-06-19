package br.com.prefeitura.web.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="fornecedores", schema="araguacu")
public class FornecedorEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="razao_social")
	private String razaoSocial;
	
	@Column(name="tipo_fornecedor")
	private String tipoFornecedor;
	
	@Column(name="cpf")
	private String cpf;
	
	@Column(name="cnpj")
	private String cnpj;
}
