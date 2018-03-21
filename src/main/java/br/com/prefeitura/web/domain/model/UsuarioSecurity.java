package br.com.prefeitura.web.domain.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioSecurity extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioSecurity(String login, String senha, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
		super(login, senha, enabled, true, true, true, authorities);
	}

}
