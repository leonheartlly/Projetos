package br.com.prefeitura.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import br.com.prefeitura.web.domain.entity.AutorEntity;
import br.com.prefeitura.web.domain.entity.CalendarioEventosEntity;
import br.com.prefeitura.web.domain.entity.CategoriaEntity;
import br.com.prefeitura.web.domain.entity.ContratoEntity;
import br.com.prefeitura.web.domain.entity.FornecedorEntity;
import br.com.prefeitura.web.domain.entity.GraphEntity;
import br.com.prefeitura.web.domain.entity.ImagemNoticiaEntity;
import br.com.prefeitura.web.domain.entity.LegislacaoEntity;
import br.com.prefeitura.web.domain.entity.LicitacaoEntity;
import br.com.prefeitura.web.domain.entity.NoticiaEntity;
import br.com.prefeitura.web.domain.entity.OrgaoEntity;
import br.com.prefeitura.web.model.Autor;
import br.com.prefeitura.web.model.CalendarioEventos;
import br.com.prefeitura.web.model.Categoria;
import br.com.prefeitura.web.model.Contrato;
import br.com.prefeitura.web.model.Fornecedor;
import br.com.prefeitura.web.model.Grafico;
import br.com.prefeitura.web.model.ImagemNoticia;
import br.com.prefeitura.web.model.Legislacao;
import br.com.prefeitura.web.model.Licitacao;
import br.com.prefeitura.web.model.Modalidade;
import br.com.prefeitura.web.model.Noticia;
import br.com.prefeitura.web.model.Orgao;
import br.com.prefeitura.web.model.Situacao;
import br.com.prefeitura.web.utils.GraphEnum;
import br.com.prefeitura.web.utils.PortalPrefeituraUtils;

public class ServiceHelper {

	private static final Logger LOGGER = 
		      Logger.getLogger(ServiceHelper.class); 
		
	@Autowired
	private FornecedorService fornecedorService;
	
	/**
	 * Converte um objeto do tipo Licitacao Entity para Licitacao Model.
	 * @param entities Lista de entidades.
	 * @return Lista de Licitacao Model.
	 */
	protected List<Licitacao> convertLicitacaoObject(List<LicitacaoEntity> entities){
		
		List<Licitacao> licitacoes = entities.stream().map(entity -> {
			return Licitacao.licitacaoBuilder()
			 	.id(entity.getId())
			 	.processo(entity.getProcesso())
			 	.edital(entity.getEdital())
			 	.dataAbertura(entity.getDataAbertura())
			 	.dataHomolog(entity.getDataHomolog())
			 	.dataAdjudicacao(entity.getDataAdjudicacao())
			 	.objeto(entity.getObjeto())
			 	.valor(entity.getValor())
			 	.modalidade(findModalidade(entity))
			 	.orgao(findOrgao(entity.getOrgao()))
			 	.situacao(findSituacao(entity))
			 	.possuiAnexo(entity.isPossuiAnexo())
			 	.fornecedor(findFornecedor(entity.getFornecedor()))
			 	.build();
		}).collect(Collectors.toList());
		
		return licitacoes;
	}
	
	 /**
	 * Converte um objeto do tipo Licitacao Entity para Licitacao Model.
	 * @param entities Lista de entidades.
	 * @return Lista de Licitacao Model.
	 */
	protected List<Contrato> convertContratoObject(List<ContratoEntity> entities){
		
		List<Contrato> contratos = entities.stream().map(entity -> {
			return Contrato.contratoBuilder()
			 	.id(entity.getId())
			 	.numero(entity.getNumero())
			 	.dataAssinatura(entity.getDataAssinatura())
			 	.dataVigencia(entity.getDataVigencia())
			 	.dataPublicacao(entity.getDataPublicacao())
			 	.valor(entity.getValor())
			 	.objeto(entity.getObjeto())
			 	.orgao(findOrgao(entity.getOrgao()))
			 	.fornecedor(findFornecedor(entity.getFornecedor()))
			 	.build();
		}).collect(Collectors.toList());
		
		return contratos;
	}
	
	 /**
		 * Converte um objeto do tipo Licitacao Entity para Licitacao Model.
		 * @param entities Lista de entidades.
		 * @return Lista de Licitacao Model.
		 */
	protected List<Noticia> convertNoticiasObject(List<NoticiaEntity> entities){
		
		List<Noticia> noticias = entities.stream().map(entity -> {
			return Noticia.noticiaBuilder()
			 	.id(entity.getId())
			 	.noticia(entity.getNoticia())
			 	.dataNoticia(entity.getDataNoticia())
			 	.autor(findAutor(entity.getAutor()))
			 	.categoria(findCategoria(entity.getCategoria()))
			 	.titulo(entity.getTitulo())
			 	.imagens(new ArrayList<ImagemNoticia>())
			 	.build();
		}).collect(Collectors.toList());
		
		return noticias;
	}
	
	/**
	 * Converte um objeto do tipo Calendario entity para calendario model.
	 * @param entities lista de entidades.
	 * @return lista de eventos.
	 */
	public List<CalendarioEventos> convertEventsObject(List<CalendarioEventosEntity> entities) {
		List<CalendarioEventos> noticias = entities.stream().map(entity -> {
			return CalendarioEventos.calendarBuilder()
			 	.id(entity.getId())
			 	.titulo(entity.getTitulo())
			 	.dataInicio(entity.getDataInicio())
			 	.dataFim(entity.getDataFim())
			 	.horarioInicio(entity.getHorarioInicio())
			 	.horarioFim(entity.getHorarioFim())
			 	.rua(entity.getRua())
			 	.bairro(entity.getBairro())
			 	.numero(entity.getNumero())
			 	.descricao(entity.getDescricao())
			 	.build();
		}).collect(Collectors.toList());
		
		return noticias;
	}
	
	/**
	 * Converte um ou mais objetos do tipo legislação entity para legislacao model.
	 * @param entities lista de entidades.
	 * @return lista de legislacoes.
	 */
	public List<Legislacao> convertLegislacaoObject(List<LegislacaoEntity> entities) {
		List<Legislacao> legislacoes = entities.stream().map(entity -> {
			return Legislacao.legislacaoBuilder()
			 	.id(entity.getId())
			 	.nome(entity.getNome())
			 	.data(entity.getData())
			 	.resumo(entity.getResumo())
			 	.orgao(findOrgao(entity.getOrgao()))
			 	.arquivo(entity.getArquivo())
			 	.tipo(entity.getTipo())
			 	.build();
		}).collect(Collectors.toList());
		
		return legislacoes;
	}
	
	/**
	 * Converte uma entidade grafico em modelo grafico.
	 * @param entities entidade resgatada da base.
	 * @return modelo grafico.
	 */
	protected List<Grafico> convertGraficosObject(List<GraphEntity> entities) {
		List<Grafico> graficos = entities.stream().map(entity -> {
			return Grafico.graphBuilder()
			 	.id(entity.getId())
			 	.receita(entity.getReceita())
			 	.despesa(entity.getDespesa())
			 	.data(entity.getData())
			 	.tipoGrafico(findWichGraphIs(entity.getTipo()))
			 	.orgao(findOrgao(entity.getOrgao()))
			 	.build();
		}).collect(Collectors.toList());
		
		return graficos;
	}
	
	/**
	 * Obtém o tipo de gráfico correspondente.
	 * @param graph grafico resgatado da base.
	 * @return grafico.
	 */
	protected GraphEnum findWichGraphIs(int graph){
		return GraphEnum.DEFAULT.findGraph(graph);
	}
	
	/**
	 * Obtém um modelo de autor.
	 * @param entity entidade autor.
	 * @return model autor.
	 */
	protected Autor findAutor(AutorEntity entity){
		
		if(!ObjectUtils.isEmpty(entity)){
			return Autor.autorBuilder()
					.id(entity.getId())
					.nome(entity.getNome())
					.cargo(entity.getCargo())
					.build();
		}
		
		return new Autor();
		
	}
	
	/**
	 * Converte um objeto Categoria entity.
	 * @param entity objeto categoria entity.
	 * @return objeto categoria.
	 */
	protected Categoria findCategoria(CategoriaEntity entity){
		return Categoria.categoriaBuilder()
			.id(entity.getId())
			.descricao(entity.getDescricao())
			.categoria(entity.getCategoria())
			.build();
	}

	/**
	 * Converter um objeto fornecedor entity.
	 * @param entity licitacao entity;
	 */
	protected Fornecedor findFornecedor(FornecedorEntity entity) {
		return new Fornecedor().fornecedorBuilder()
		 .id(entity.getId())
		 .nome(entity.getNome())
		 .razaoSocial(entity.getRazaoSocial())
		 .tipoFornecedor(entity.getTipoFornecedor())
		 .cpf(entity.getCpf())
		 .cnpj(entity.getCnpj())
		 .build();
	}

	/**
	 * Converte um objeto Situacao entity.
	 * @param entity licitacao entity.
	 * @return Situacao Model.
	 */
	protected Situacao findSituacao(LicitacaoEntity entity) {
		return new Situacao(entity.getSituacao().getId(), entity.getSituacao().getDescricao());
	}

	/**
	 * Converte um objeto Orgao Entity.
	 * @param entity licitacao Entity.
	 * @return Orgao Model.
	 */
	protected Orgao findOrgao(OrgaoEntity entity) {
		if(!ObjectUtils.isEmpty(entity)){
			return new Orgao(entity.getId(), entity.getOrgao());
		}
		return new Orgao();
	}

	/**
	 * Converte um objeto Modalidade Entity.
	 * @param entity licitacao entity.
	 * @return Modalidade Model.
	 */
	protected Modalidade findModalidade(LicitacaoEntity entity) {
		return new Modalidade(entity.getModalidade().getId(), entity.getModalidade().getDescricao());
	}
	
	 /**
	  * Busca um fornecedor baseado nas informações passadas através do filtro de consulta.
	  * @param cnpj cnpj do fornecedor.
	  * @param nome nome do fornecedor.
	  * @return fornecedor.
	  */
	protected Fornecedor findFornecedor(String cnpj, String nome) {

		Fornecedor fornecedor = new Fornecedor();
		
		try{
			if(!StringUtils.isBlank(cnpj) && PortalPrefeituraUtils.cnpjFormatIsValid(cnpj)){
				fornecedor = fornecedorService.consultarFornecedorPorCnpj(cnpj);
			}else if (!StringUtils.isBlank(nome) && fornecedor.getId() == null){
				fornecedor = fornecedorService.consultarFornecedorPorNome(nome);
			}else{
				LOGGER.info("[LOG-WARN] "+ ServiceHelper.class.getSimpleName() +" FILTRO DE FORNECEDOR NÃO FOI PREENCHIDO.");
				fornecedor.setId(0L);
			}
		}catch(Exception e){
			LOGGER.error("[LOG-ERROR] "+ ServiceHelper.class.getSimpleName() +" ERRO NA CONSULTA DE FORNECEDORES. " + e);
			fornecedor.setId(0L);
		}
		
		return fornecedor;
	}
	
	/**
	 * Converte uma lista de entidade de imagens em modelos;
	 * @param entities entidade Lista de imagens referentes a uma notícia.
	 * @return lista de modelo de noticias.
	 */
	protected List<ImagemNoticia> convertImages(List<ImagemNoticiaEntity> entities){
		
		List<ImagemNoticia> images = new ArrayList<>();
		if(!ObjectUtils.isEmpty(entities)){
			images = entities.stream().map(entity -> {
				return ImagemNoticia.imageBuilder()
				 	.id(entity.getId())
				 	.caminho(entity.getCaminho())
				 	.nome(entity.getNome())
				 	.titulo(entity.getTitulo())
				 	.descricao(entity.getDescricao())
				 	.idNoticia(entity.getIdNoticia())
				 	.build();
			}).collect(Collectors.toList());
		}
		return images;
	}
}
