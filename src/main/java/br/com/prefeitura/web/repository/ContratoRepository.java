package br.com.prefeitura.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.ContratoEntity;

public interface ContratoRepository extends JpaRepository<ContratoEntity, Long>{

}
