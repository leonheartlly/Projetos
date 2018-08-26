package br.com.prefeitura.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.prefeitura.web.domain.entity.OuvidoriaEntity;


public interface OuvidoriaRepository extends JpaRepository<OuvidoriaEntity, Long> {

	@Query(value= "select count(*) from ouvidoria o where tipo = ?", nativeQuery = true)
	public Long findCountGroupByTipo(int tipo);
	
	
}
