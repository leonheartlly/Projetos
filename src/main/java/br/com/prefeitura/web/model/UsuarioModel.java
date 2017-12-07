package br.com.prefeitura.web.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

@Data
public class UsuarioModel {

	private Long codigo;
	
	@NotEmpty(message="O nome é de preenchimento obrigatório.")
	private String nome;
	@NotEmpty(message="O Login é de preenchimento obrigatório.")
	private String login;
	@NotEmpty(message="A Senha é de preenchimento obrigatório.")
	private String senha;
	
	private boolean ativo;
	@NotEmpty(message="Não existe nenhum grupo selecionado.")
	private List<Long> grupos;
	
	public UsuarioModel (Long codigo, String nome, String login, String senha, boolean ativo, List<Long> grupos) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.grupos = grupos;
	}	
	
	public UsuarioModel(){
		 
		System.out.println("Passei " + LocalDate.now());
	}
}


