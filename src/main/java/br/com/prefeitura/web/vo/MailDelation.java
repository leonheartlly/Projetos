package br.com.prefeitura.web.vo;

import lombok.Data;

@Data
public class MailDelation {

	private short type;
	private boolean identity;
	private String email;
	private String description;
	private int response;
	private String name;
	private String phone;
}
