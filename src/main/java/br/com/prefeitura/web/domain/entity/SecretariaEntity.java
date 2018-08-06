package br.com.prefeitura.web.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="secretaria", schema="araguacu")
public class SecretariaEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="secretaria")
	private String secretaria;
	
	@Column(name="secretario")
	private String secretario;
	
	@Column(name="telefone")
	private String telefone;
	
	@Column(name="email")
	private String email;
	
	@Column(name="endereco")
	private String endereco;
	
	@Column(name="visao")
	private String missao;
	
	@Column(name="horario_abertura")
	private String horarioAbertura;
	
	@Column(name="horario_fechamento")
	private String horarioFechamento;
}
