package br.com.prefeitura.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.ArquivoProjetoEntity;

public interface ArquivoProjetoRepository extends JpaRepository<ArquivoProjetoEntity, Long>{

	public List<ArquivoProjetoEntity> findByIdProjeto(Long id);
}
