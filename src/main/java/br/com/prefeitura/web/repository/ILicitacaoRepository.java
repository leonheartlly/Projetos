package br.com.prefeitura.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.prefeitura.web.domain.entity.LicitacaoEntity;

public interface ILicitacaoRepository extends JpaRepository<LicitacaoEntity, Long>{

//	@Query("SELECT " +
//			"li.id," +  
//			"li.processo, " +
//			"li.edital, " + 
//			"li.data_abertura," +
//			"li.data_homolog," + 
//			"li.data_adjudicacao," + 
//			"modali.desc," + 
//			"li.objeto," + 
//			"li.valor," +
//			"orgs.desc," +
//			"li.flag_anexo," +
//			"fornec.razao_social " + 
//			"FROM " + 
//			"prefeitura.licitacao li " +
//			"inner join prefeitura.modalidade_licitacao modali on li.id_modalidade = modali.id " +
//			"inner join prefeitura.orgaos orgs on li.id_orgao = orgs.id " +
//			"inner join prefeitura.fornecedores fornec on li.id_fornecedor = fornec.id")
//	List<LicitacaoEntity> findAll();
}
