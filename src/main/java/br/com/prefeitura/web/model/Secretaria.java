package br.com.prefeitura.web.model;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class Secretaria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String secretaria;
	private String secretario;
	private String telefone;
	private String email;
	private String endereco;
	private String missao;
	private String horarioAbertura;
	private String horarioFechamento;
	
	private List<Projetos> listaProjetos;
	private List<ArquivoProjeto> listaArquivoProjeto;
	
	public Secretaria(Long id, String secretaria, String secretario, String telefone, String email, String endereco, String missao,
			String horarioAbertura, String horarioFechamento) {
		super();
		this.id = id;
		this.secretaria = secretaria;
		this.secretario = secretario;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.missao = missao;
		this.horarioAbertura = horarioAbertura;
		this.horarioFechamento = horarioFechamento;
	}
	
	public Secretaria() {
		super();
	}

	@Builder(builderMethodName="secretariaBuilder")
	public static Secretaria newSecretaria(Long id, String secretaria, String secretario, String telefone, String email, String endereco, String missao,
			String horarioAbertura, String horarioFechamento) {
		return new Secretaria(id, secretaria, secretario, telefone, email, endereco, missao,
				horarioAbertura, horarioFechamento);
	}
}
