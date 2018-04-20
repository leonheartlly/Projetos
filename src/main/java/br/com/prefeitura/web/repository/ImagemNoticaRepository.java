package br.com.prefeitura.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.ImagemNoticiaEntity;
import br.com.prefeitura.web.model.ImagemNoticia;

public interface ImagemNoticaRepository extends JpaRepository<ImagemNoticiaEntity, Long> {

	List<ImagemNoticiaEntity> findByIdNoticia(Long id);
}
