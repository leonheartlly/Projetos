package br.com.prefeitura.web.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="calendario_eventos", schema="prefeitura")
@Entity
public class CalendarioEventosEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="data_inicio")
	private String dataInicio;
	
	@Column(name="data_fim")
	private String dataFim;
	
	@Column(name="horario_inicio")
	private String horarioInicio;
	
	@Column(name="horario_fim")
	private String horarioFim;
	
	@Column(name="rua")
	private String rua;
	
	@Column(name="bairro")
	private String bairro;
	
	@Column(name="numero")
	private String numero;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="imagem")
	private String imagem;

}
