package br.com.prefeitura.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.OrgaoEntity;

public interface IOrgaoRepository extends JpaRepository<OrgaoEntity, Long>{

//	List<OrgaoEntity> findAll(OrgaoEntity orgaoEntity);
}
