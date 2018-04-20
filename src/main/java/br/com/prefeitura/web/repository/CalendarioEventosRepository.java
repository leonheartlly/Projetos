package br.com.prefeitura.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.CalendarioEventosEntity;

public interface CalendarioEventosRepository extends JpaRepository<CalendarioEventosEntity, Long>{

}
