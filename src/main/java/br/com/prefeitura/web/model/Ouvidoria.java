package br.com.prefeitura.web.model;

import lombok.Builder;
import lombok.Data;

@Data
public class Ouvidoria {

	private Long id;
	private short type;
	
	private Long totalSugestions;
	private Long totalComplaint;
	private Long totalDelation;
	private Long totalCompliment;
	
	public Ouvidoria(Long id, short type) {
		this.id = id;
		this.type = type;
	}

	public Ouvidoria() {
	}
	
	@Builder(builderMethodName = "ouvidoriaBuilder")
	public static Ouvidoria newOuvidoria(Long id, short type) {
		return new Ouvidoria(id, type);
	}
	
}
