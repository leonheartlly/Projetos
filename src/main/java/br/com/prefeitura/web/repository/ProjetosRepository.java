package br.com.prefeitura.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.ProjetosEntity;

public interface ProjetosRepository extends JpaRepository<ProjetosEntity, Long>{

	public List<ProjetosEntity> findByIdSecretaria(Long id);
}
