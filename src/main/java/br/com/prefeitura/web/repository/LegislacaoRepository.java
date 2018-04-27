package br.com.prefeitura.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.LegislacaoEntity;

public interface LegislacaoRepository extends JpaRepository<LegislacaoEntity, Long> {

	List<LegislacaoEntity> findByTipoOrderByDataDesc(int tipo);
	
	/**
	 * between x and y
	 * @param tipo
	 * @param dataInicio
	 * @param dataFinal
	 * @return
	 */
//	List<LegislacaoEntity> findByTipoAndDataBetweenOrderByDataDesc(int tipo, String dataInicio, String dataFinal);
	
	/**
	 * data <=
	 * @param tipo
	 * @param dataInicio
	 * @param dataFinal
	 * @return
	 */
//	List<LegislacaoEntity> findByTipoAndDataLessThanEqualOrderByDataDesc(int tipo, String dataFinal);
	
	/**
	 * >=
	 * @param tipo
	 * @param dataInicio
	 * @param dataFinal
	 * @return
	 */
//	List<LegislacaoEntity> findByTipoAndDataGreaterThanEqualOrderByDataDesc(int tipo, String dataInicio);
	
	
//	List<LegislacaoEntity> findByTipoAndDataGreaterThanEqualAndObjetoLikeOrderByDataDesc(int tipo, String dataInicio);
	
	
	
//	List<LegislacaoEntity> findByTipoAndOrderByExercicioDescMesDesc(int tipo);
}
