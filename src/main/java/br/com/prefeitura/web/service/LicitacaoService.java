package br.com.prefeitura.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.prefeitura.web.domain.entity.LicitacaoEntity;
import br.com.prefeitura.web.domain.model.Licitacao;
import br.com.prefeitura.web.domain.model.Modalidade;
import br.com.prefeitura.web.domain.model.Orgao;
import br.com.prefeitura.web.domain.model.Situacao;
import br.com.prefeitura.web.repository.ILicitacaoRepository;
import br.com.prefeitura.web.repository.IOrgaoRepository;

@Component
public class LicitacaoService {

	@Autowired
	private IOrgaoRepository orgaoRepository;
	
	@Autowired
	private ILicitacaoRepository licitacaoRepository;
	
	/**
	 * 
	 * @param usuarioEntity
	 * @return
	 */
//	public List<OrgaoModel> buscarOrgaos() {
//
//		List<OrgaoEntity> orgaos = orgaoRepository.findAll();
//		
//		orgaos.forEach(
//				orgao ->{
//					System.out.println(orgao.getOrgao());
//				}
//				);
//
//		List<OrgaoModel> orgaoModel = new ArrayList<>();
////		orgaoModel.set(orgaos.get(0));
//		return orgaoModel;
////		return this.buscarPermissoesDosGrupos(grupos);
//	}
	
	/**
	 * Consulta usuarios cadastrados.
	 * @return
	 */
	 public List<Licitacao> consultarLicitacoes(){
		 
		 List<Licitacao> licitacaoModel = new ArrayList<>();
		 List<LicitacaoEntity> licitacoesEntity = this.licitacaoRepository.findAll();
		 
		 licitacoesEntity.forEach(licitacao -> {
			 licitacaoModel.add(
					 new Licitacao(
							 licitacao.getId(),
							 licitacao.getProcesso(),
							 licitacao.getEdital(),
							 licitacao.getDataAbertura(),
							 licitacao.getDataHomolog(),
							 licitacao.getDataAdjudicacao(),
							 new Modalidade(licitacao.getModalidade().getId(), licitacao.getModalidade().getDescricao()),
							 licitacao.getObjeto(),
							 licitacao.getValor(),
							 new Orgao (licitacao.getOrgao().getId(), licitacao.getOrgao().getOrgao()),
							 licitacao.isPossuiAnexo(),
							 licitacao.getFornecedor(),
							 new Situacao(licitacao.getSituacao().getId(), licitacao.getSituacao().getDescricao())
							 ));
		 });
		 
		 return licitacaoModel;
	 }
}
