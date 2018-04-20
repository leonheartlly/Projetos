package br.com.prefeitura.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.prefeitura.web.domain.entity.GrupoEntity;
import br.com.prefeitura.web.domain.entity.PermissaoEntity;
import br.com.prefeitura.web.domain.entity.UsuarioEntity;
import br.com.prefeitura.web.model.Usuario;
import br.com.prefeitura.web.model.UsuarioSecurity;
import br.com.prefeitura.web.repository.IGrupoRepository;
import br.com.prefeitura.web.repository.IPermissaoRepository;
import br.com.prefeitura.web.repository.IUsuarioRepository;
import no.finn.unleash.DefaultUnleash;
import no.finn.unleash.Unleash;
import no.finn.unleash.util.UnleashConfig;

@Component
public class UsuarioService implements UserDetailsService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private IGrupoRepository grupoRepository;

	@Autowired
	private IPermissaoRepository permissaoRepository;
	


	/**
	 * Consulta Usuário por login.
	 */
	@Override
	public UserDetails loadUserByUsername(String login) throws BadCredentialsException,DisabledException {
		
		UnleashConfig config = UnleashConfig.builder()
	            .appName("Prefeitura")
	            .instanceId("LogDeLogin")
	            .unleashAPI("http://localhost:4242/Prefeitura/")
	            .build();
		Unleash unleash = new DefaultUnleash(config);
		
		if(unleash.isEnabled("activateLog")) {
			  System.out.println("ligado");
			} else {
			  System.out.println("desligado");
			}

		UsuarioEntity usuarioEntity = usuarioRepository.findByLogin(login);

		if (usuarioEntity == null)
			throw new BadCredentialsException("Usuário não encontrado no sistema!");
		{
		}
		if (!usuarioEntity.isAtivo()) {
			throw new DisabledException("Usuário não está ativo no sistema!");
		}

		return new UsuarioSecurity(usuarioEntity.getLogin(), usuarioEntity.getSenha(), usuarioEntity.isAtivo(),
				this.buscarPermissoesUsuario(usuarioEntity));
	}

	
	/**
	 * 
	 * @param usuarioEntity
	 * @return
	 */
	public List<GrantedAuthority> buscarPermissoesUsuario(UsuarioEntity usuarioEntity) {

		List<GrupoEntity> grupos = grupoRepository.findByUsuariosIn(usuarioEntity);

		return this.buscarPermissoesDosGrupos(grupos);
	}

	/**
	 * Busca Permissões do grupo.
	 * 
	 * @param grupos
	 */
	public List<GrantedAuthority> buscarPermissoesDosGrupos(List<GrupoEntity> grupos) {

		List<GrantedAuthority> auths = new ArrayList<>();

//		grupos.forEach(grupo -> {
//			permissaoRepository.findByGruposIn(grupo).forEach(permissao -> {
//				auths.add(new SimpleGrantedAuthority(permissao.getPermissao()));
//			});
//		});
		
		for (GrupoEntity grupo: grupos) {
			
			List<PermissaoEntity> lista = permissaoRepository.findByGruposIn(grupo);
			
			for (PermissaoEntity permissao: lista) {
				auths.add(new SimpleGrantedAuthority(permissao.getPermissao()));
			}
		}

//		System.out.println("permissao foi arrumada");
		return auths;
	}
	
	/**
	 * Salva um novo registo de usuário.
	 * @param usuarioModel
	 */
	public void salvarUsuario(Usuario usuarioModel){
		
		UsuarioEntity usuarioEntity =  new UsuarioEntity();
		
		usuarioEntity.setAtivo(true);
		usuarioEntity.setNome(usuarioModel.getNome());
		usuarioEntity.setLogin(usuarioModel.getLogin());
		usuarioEntity.setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));
		
		GrupoEntity grupoEntity = null;
		List<GrupoEntity> grupos =  new ArrayList<>();
		for(Long codigoGrupo : usuarioModel.getGrupos()){
			if(codigoGrupo != null){
				grupoEntity = grupoRepository.findOne(codigoGrupo);
				grupos.add(grupoEntity);
			}
		}
		
		usuarioEntity.setGrupos(grupos);
		this.usuarioRepository.save(usuarioEntity);
	}
	
	/**
	 * Consulta usuarios cadastrados.
	 * @return
	 */
	 public List<Usuario> consultarUsuarios(){
		 
		 List<Usuario> usuariosModel = new ArrayList<>();
		 List<UsuarioEntity> usuariosEntity = this.usuarioRepository.findAll();
		 
		 usuariosEntity.forEach(usuarioEntity -> {
			 usuariosModel.add(new Usuario(usuarioEntity.getCodigo(),
					 usuarioEntity.getNome(),
					 usuarioEntity.getLogin(),
					 null,
					 usuarioEntity.isAtivo(),
					 null));
		 });
		 
		 return usuariosModel;
	 }

	 /**
	  * Exclui um usuário.
	  * @param codigoUsuario
	  */
	 public void removeUsuario(Long codigoUsuario){
		 this.usuarioRepository.delete(codigoUsuario);
	 }
	 
	 /**
	  * Consulta usuário pelo código.
	  * @param codigoUsuario
	  * @return
	  */
	 public Usuario consultarUsuario(Long codigoUsuario){
		 UsuarioEntity usuarioEntity = this.usuarioRepository.findOne(codigoUsuario);
		 
		 List<Long> grupos =  new ArrayList<Long>();

		 usuarioEntity.getGrupos().forEach(grupo ->{
			 grupos.add(grupo.getCodigo());
		 });
		 
		 return new Usuario(usuarioEntity.getCodigo(),
				 usuarioEntity.getNome(),
				 usuarioEntity.getLogin(),
				 null,
				 usuarioEntity.isAtivo(),
				 grupos);
	 }
	 
	 /**
	  * Altera Informações do usuário
	  * @param usuarioModel
	  */
	 public void alterarUsuario(Usuario usuarioModel){
		 UsuarioEntity usuarioEntity = this.usuarioRepository.findOne(usuarioModel.getCodigo());
		 
		 usuarioEntity.setAtivo(usuarioModel.isAtivo());
		 usuarioEntity.setLogin(usuarioModel.getLogin());
		 usuarioEntity.setNome(usuarioModel.getNome());
		 
		 /*CRIPTOGRAMA E INFORMA A SENHA*/
		if(!StringUtils.isEmpty(usuarioModel.getSenha()))
			 usuarioEntity.setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));
		
		GrupoEntity grupoEntity = null;
		List<GrupoEntity> grupos = new ArrayList<>();
		
		for(Long codigoGrupo : usuarioModel.getGrupos()){
			if(codigoGrupo != null){
				grupoEntity = grupoRepository.findOne(codigoGrupo);
				grupos.add(grupoEntity);
			}
		}
		
		usuarioEntity.setGrupos(grupos);
		
		this.usuarioRepository.saveAndFlush(usuarioEntity);
	 }
}
