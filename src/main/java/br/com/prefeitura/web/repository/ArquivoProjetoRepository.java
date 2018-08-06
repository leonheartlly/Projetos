package br.com.prefeitura.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.ArquivoProjetoEntity;

public interface ArquivoProjetoRepository extends JpaRepository<ArquivoProjetoEntity, Long>{

}
