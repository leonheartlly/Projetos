package br.com.prefeitura.web.domain.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.prefeitura.web.domain.entity.AutorEntity;
import br.com.prefeitura.web.domain.entity.CategoriaEntity;
import br.com.prefeitura.web.domain.entity.NoticiaEntity;

@Repository
public class NoticiaJDBCRepository {

	private static final Logger LOGGER = Logger.getLogger(NoticiaJDBCRepository.class);
	
	/**
	 * JDBC Template.
	 */
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	public List<NoticiaEntity> findLastThreeNews() throws Exception{

		String sql = "SELECT noti.id, noti.noticia, noti.data_noticia, noti.id_autor, noti.id_categoria, noti.titulo, "
				+ "cat.id, cat.categoria, cat.descricao "
				+ "FROM araguacu.noticias noti "
				+ "JOIN araguacu.categoria_noticias cat ON cat.id = noti.id_categoria "
				+ "ORDER BY noti.data_noticia DESC LIMIT 3";

		try {

			List<NoticiaEntity> noticias = this.jdbcTemplate.query(sql,
					new RowMapper<NoticiaEntity>() {
						public NoticiaEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
							NoticiaEntity noticia = new NoticiaEntity();
							noticia.setId(rs.getLong(1));
							noticia.setNoticia(rs.getString(2));
							noticia.setDataNoticia(rs.getString(3));
							
							CategoriaEntity categoria = new CategoriaEntity();
							categoria.setId(rs.getLong(5));
							categoria.setCategoria(rs.getString(8));
							categoria.setDescricao(rs.getString(9));
							noticia.setCategoria(categoria);
							
							AutorEntity autor = new AutorEntity();
							noticia.setAutor(autor);
							
							noticia.setTitulo(rs.getString(6));

							return noticia;
						}
					});
			
			return noticias;
			
		} catch (DataAccessException dae) {
			LOGGER.error("[LOG-ERROR] " + NoticiaJDBCRepository.class.getSimpleName()
					+ " ERRO DE SQL AO EFETUAR A CONSULTA SOLICITADA. " + dae);
			throw dae;
		} catch (Exception ex) {
			LOGGER.error("[LOG-ERROR] " + NoticiaJDBCRepository.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA SOLICITADA. " + ex);
			throw ex;
		}
	}
}
