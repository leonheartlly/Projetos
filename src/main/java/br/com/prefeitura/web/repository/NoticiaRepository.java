package br.com.prefeitura.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.NoticiaEntity;

public interface NoticiaRepository extends JpaRepository<NoticiaEntity, Long> {

}
