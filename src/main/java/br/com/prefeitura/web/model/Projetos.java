package br.com.prefeitura.web.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
public class Projetos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String titulo;
	private String descricao;
	private String fotoCapa;
	private Long idSecretaria;
	
	public Projetos(Long id, String titulo, String descricao, String fotoCapa, Long idSecretaria) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.fotoCapa = fotoCapa;
		this.idSecretaria = idSecretaria;
	}
	
	/**
	 * Cria uma instancia do objeto Projetos com o padrão Builder.
	 * @param id
	 * @param titulo
	 * @param descricao
	 * @param fotoCapa
	 * @param idSecretaria
	 * @return
	 */
	@Builder(builderMethodName="projetosBuilder")
	public static Projetos newProjetos(Long id, String titulo, String descricao, String fotoCapa, Long idSecretaria) {
		
		return new Projetos(id, titulo, descricao, fotoCapa, idSecretaria);
	}

}
