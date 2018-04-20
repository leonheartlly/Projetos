package br.com.prefeitura.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.LicitacaoEntity;

public interface ILicitacaoRepository extends JpaRepository<LicitacaoEntity, Long>{

	/**
	 * 
	 * @param objeto
	 * @param idModalidade
	 * @param idFornecedor
	 * @param idOrgao
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
//	@Query(value = "SELECT * " +
//			"FROM " + 
//			"licitacao li " +
//			"WHERE li.objeto LIKE CONCAT('%',:objeto,'%') "+ 
//			"AND li.id_modalidade = :modalidade " +  
//			"AND li.id_fornecedor = :fornecedor " + 
//			"AND li.id_orgao = :orgao " +
//			"AND li.data_abertura BETWEEN :dataInicial AND :dataFinal", nativeQuery=true)
//	List<LicitacaoEntity> findLicitacao(@Param("objeto") String objeto, @Param("modalidade") Long idModalidade, @Param("fornecedor") Long idFornecedor, @Param("orgao") Long idOrgao, @Param("dataInicial") String dataInicial, @Param("dataFinal") String dataFinal);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	List<LicitacaoEntity> findById(Long id);
	
}
