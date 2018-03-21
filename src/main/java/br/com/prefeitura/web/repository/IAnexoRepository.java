package br.com.prefeitura.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.AnexoEntity;

public interface IAnexoRepository extends JpaRepository<AnexoEntity, Long> {

}
