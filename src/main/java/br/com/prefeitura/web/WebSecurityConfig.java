package br.com.prefeitura.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.prefeitura.web.service.UsuarioService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioService usuarioRepositoryImpl;

	/**
	 * Configurações de acesso.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			/*
			 * DETERMINA QUE PARA REALIZAR ESSA REQUEST PRECISA TER UMA DAS PERMISSÕES ABAIXO
			 * EXEMPLO DA URL: http://localhost:8095/usuario/novoCadastro
			 */
			//.antMatchers("/usuario/novoCadastro").access("hasRole('ADMIN') or hasRole('ROLE_CADASTRO_USUARIO')")
			
			.antMatchers("/editais/licitacao").permitAll()
			.antMatchers("/menu/noticias").permitAll()
			//.access("hasRole('ADMIN') or hasRole('ROLE_CADASTRO_USUARIO')").
			.antMatchers("/editais/contratos").permitAll()
			//.access("hasRole('ADMIN') or hasRole('ROLE_CADASTRO_USUARIO')")
			.antMatchers("/referencias/noticias").permitAll()
			//.access("hasRole('ADMIN') or hasRole('ROLE_CADASTRO_USUARIO')")
			.antMatchers("/editais/filtrarLegislacao").permitAll()
			//.access("hasRole('ADMIN') or hasRole('ROLE_CADASTRO_USUARIO')")
			/*
			 * DETERMINA QUE PARA REALIZAR ESSA REQUEST PRECISA TER UMA DAS PERMISSÕES ABAIXO
			 * EXEMPLO DA URL: http://localhost:8095/usuario/consultar
			 */
			//.antMatchers("/usuario/consultar").access("hasRole('ADMIN') or hasRole('CONSULTA_USUARIO')")
			.antMatchers("/editais/pesquisaLicitacao").permitAll()
			//.access("hasRole('ADMIN') or hasRole('ROLE_CADASTRO_USUARIO')")
			
			/*DETERMINA QUE PARA ACESSAR A PÁGINA INICIAL DA APLICAÇÃO PRECISA ESTÁ AUTENTICADO
			 */
			//.antMatchers("/home").authenticated()
			.antMatchers("/home").permitAll()
			.antMatchers("/").permitAll()
			//.antMatchers("/resources/**").permitAll().anyRequest().permitAll()
//			.
//			.
//			.anyRequest().
			//.anyRequest().authenticated()
			//.anyRequest()
			//.and()
				//.formLogin()
				/*INFORMANDO O CAMINHO DA PÁGINA DE LOGIN, E SE O LOGIN FOR EFETUADO COM SUCESSO
				 * O USUÁRIO DEVE SER REDIRECIONADO PARA /home(http://localhost:8095/home)
				 */
				//.loginPage("/").defaultSuccessUrl("/home", true)
				/*TODOS TEM ACESSO A PAGINA DE LOGIN
				 */
				//.permitAll()
			
			.and()
				/*
				 * AQUI ESTAMOS INFORMANDO QUE QUANDO FOR REDIRECIONADO PARA  O LINK http://localhost:8095/logout
			     *O USUÁRIO DEVE TER SUA SESSÃO FINALIZADA E REDIRECIONADO PARA A PÁGINA DE LOGIN 
				 */
				.logout()
				//.logoutSuccessUrl("/")
				.logoutUrl("/logout")
				.permitAll();
		
		/* PÁGINA COM A MENSAGEM DE ACESSO NEGADO
		 * QUANDO O USUÁRIO NÃO TIVER UMA DETERMINADA PERMISSÃO DE ACESSO AO SISTEMA ELE VAI SER REDIRECIONADO
		 * PARA A URL ABAIXO */
		http.exceptionHandling().accessDeniedPage("/acessoNegado");
			
		/* AQUI ESTOU INFORMANDO QUE QUALQUER REQUEST TEM ACESSO AO DIRETÓRIO src/main/resources 
		 * */
//		http.authorizeRequests().antMatchers("/classes/**").permitAll().anyRequest().permitAll();
		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();
		http.authorizeRequests().antMatchers("/licitacoes/**").permitAll().anyRequest().permitAll();
			
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(usuarioRepositoryImpl).passwordEncoder(new BCryptPasswordEncoder());
	}
	
//	public static void main(String[] args) {
//		 
//		System.out.println(new BCryptPasswordEncoder().encode("123"));
//	}
}
