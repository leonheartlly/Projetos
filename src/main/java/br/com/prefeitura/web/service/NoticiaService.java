package br.com.prefeitura.web.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.prefeitura.web.domain.entity.ImagemNoticiaEntity;
import br.com.prefeitura.web.domain.entity.NoticiaEntity;
import br.com.prefeitura.web.domain.jdbc.NoticiaJDBCRepository;
import br.com.prefeitura.web.model.ImagemNoticia;
import br.com.prefeitura.web.model.Noticia;
import br.com.prefeitura.web.repository.ImagemNoticaRepository;
import br.com.prefeitura.web.repository.NoticiaRepository;
import br.com.prefeitura.web.utils.PortalPrefeituraUtils;

@Service
public class NoticiaService extends ServiceHelper {

	private static final Logger LOGGER = Logger.getLogger(NoticiaService.class);

	@Autowired
	private ImagemNoticaRepository imagemNoticaRepository;

	@Autowired
	private NoticiaRepository noticiaRepository;
	
	@Autowired
	private NoticiaJDBCRepository noticiaJDBCRepository;
	

	/**
	 * Consulta usuarios cadastrados.
	 * 
	 * @return lista de noticias.
	 */
	public List<Noticia> findNoticias() {

		List<Noticia> noticias = new ArrayList<>();

		try {
			List<NoticiaEntity> noticiasEntity = this.noticiaRepository.findAll();
			noticias = convertNoticiasObject(noticiasEntity);

			findImagesByNoticias(noticias);
			convertDateFormat(noticias);
			noticias = filterOlderNews(noticias);
			
			return noticias;
		} catch (DataAccessException dae) {
			LOGGER.error("[LOG-ERROR] " + NoticiaService.class.getSimpleName() + " ERRO DE ACESSO AOS DADOS. " + dae);
			throw dae;
		} catch (Exception e) {
			LOGGER.error("[LOG-ERROR] " + NoticiaService.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
			throw e;
		}

	}
	
	/**
	 * Obtém as ultimas tres notícias relacionadas postadas para a página home.
	 * @return ultimas tres noticias filtradas por data.
	 */
	public List<Noticia> findLastThreeNews(){
		List<Noticia> noticias = new ArrayList<>();
		
		try {
			List<NoticiaEntity> noticiasEntity = this.noticiaJDBCRepository.findLastThreeNews();
			noticias = convertNoticiasObject(noticiasEntity);

			findImagesByNoticias(noticias);
			convertDateFormat(noticias);
			noticias = filterOlderNews(noticias);
			
			return noticias;
		} catch (DataAccessException dae) {
			LOGGER.error("[LOG-ERROR] " + NoticiaService.class.getSimpleName() + " ERRO DE ACESSO AOS DADOS. " + dae);
			throw dae;
		} catch (Exception e) {
			LOGGER.error("[LOG-ERROR] " + NoticiaService.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
		}
		
		return noticias;
	}

	/**
	 * Busca imagens de acordo com as noticias encontradas.
	 * @param noticias noticias com as imagens encontradas.
	 */
	private void findImagesByNoticias(List<Noticia> noticias) {
		
		try {
			if(!ObjectUtils.isEmpty(noticias)){
				
				noticias.forEach(noticia -> {
					Long newsId = noticia.getId();
					List<ImagemNoticiaEntity> imagesEntity = this.imagemNoticaRepository.findByIdNoticia(newsId);
					final List<ImagemNoticia> imagens = convertImages(imagesEntity);
					noticia.setImagens(imagens);
				});
			}
			
		} catch (DataAccessException dae) {
			LOGGER.error("[LOG-ERROR] " + NoticiaService.class.getSimpleName() + " ERRO DE ACESSO AOS DADOS. " + dae);
			throw dae;
		} catch (Exception e) {
			LOGGER.error("[LOG-ERROR] " + NoticiaService.class.getSimpleName()
					+ " ERRO INESPERADO AO EFETUAR A CONSULTA. " + e);
			throw e;
		}
	}
	
	/**
	 * Converte datas das noticias para o formato padrão.
	 * @param noticias noticias encontradas na base.
	 */
	private void convertDateFormat(List<Noticia> noticias){
		
		noticias.forEach(noticia -> {
			String date = PortalPrefeituraUtils.convertDateFromDB(noticia.getDataNoticia());
			String dateNewsFormat = PortalPrefeituraUtils.convertDateFormatMMM(date);
			noticia.setDataNoticia(dateNewsFormat);
		});
	}
	
	/**
	 * Ordena as noticias por data reversa.
	 * @param freshNews lista de noticias.
	 * @return lista de noticias ordenadas.
	 */
	public List<Noticia> filterOlderNews(List<Noticia> freshNews){
		
		if(!freshNews.isEmpty()){
			
			freshNews = freshNews.stream().sorted(
					(news, nextNews) -> PortalPrefeituraUtils.getDateTime(news.getDataNoticia())
					.compareTo(PortalPrefeituraUtils.getDateTime(nextNews.getDataNoticia())))
					.collect(Collectors.toList());
			Collections.reverse(freshNews);
		}
		return freshNews;
	}
	
	/**
	 * Obtem o objeto Date da data obtida no banco.
	 * @param date data do banco.
	 * @return objeto Date.
	 */
	private Date getDateTime(String date){
		try {
			Date data;
			SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
			data = formatter.parse(date);
			return data;
		} catch (ParseException e) {
			LOGGER.warn("[LOG-WARN] - NÃO FOI POSSÍVEL OBTER A DATA DAS MENSAGENS. " + e);
		}
		return null;
	}

}


