package br.com.prefeitura.web.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.GraphEntity;

public interface GraphRepository extends JpaRepository<GraphEntity, Long> {

	/**
	 * Encontra graficos por tipo.
	 * @param tipo
	 * @return
	 */
	List<GraphEntity> findByTipo(int tipo);
	
	List<GraphEntity> findGraphEntityByTipoOrderByDataDesc(int tipo, Pageable pageable);
	
	List<GraphEntity> findByTipoOrderByDataDesc(int tipo, Pageable pageable);
	
	List<GraphEntity> findGraphEntityTopByTipo(int tipo, Pageable pageable);
}
