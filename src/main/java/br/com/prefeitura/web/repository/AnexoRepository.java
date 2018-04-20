package br.com.prefeitura.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.AnexoEntity;

public interface AnexoRepository extends JpaRepository<AnexoEntity, Long> {

}
