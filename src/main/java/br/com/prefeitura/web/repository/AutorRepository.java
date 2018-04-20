package br.com.prefeitura.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.AutorEntity;

public interface AutorRepository extends JpaRepository<AutorEntity, Long> {

}
