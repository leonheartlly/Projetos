package br.com.prefeitura.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prefeitura.web.domain.entity.GrupoEntity;
import br.com.prefeitura.web.domain.entity.UsuarioEntity;

import java.util.List;

@Repository
public interface IGrupoRepository extends JpaRepository<GrupoEntity, Long>{
	

	List<GrupoEntity> findByUsuariosIn(UsuarioEntity usuarioEntity);
}
