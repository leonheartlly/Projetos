package br.com.prefeitura.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.LegislacaoEntity;

public interface LegislacaoRepository extends JpaRepository<LegislacaoEntity, Long> {

}
