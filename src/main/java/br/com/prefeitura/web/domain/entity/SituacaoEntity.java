package br.com.prefeitura.web.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="situacao", schema="araguacu")
@Entity
public class SituacaoEntity {

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="desc")
	private String descricao;
}
