package br.com.prefeitura.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.ModalidadeEntity;

public interface IModalidadeRespository extends JpaRepository<ModalidadeEntity, Long>{

}
