package br.com.prefeitura.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prefeitura.web.domain.entity.FornecedorEntity;

public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Long> {

	/**
	 * Busca um fornecedor através do nome.
	 * @param nome nome da empresa.
	 * @return Fornecedor.
	 */
	FornecedorEntity findByNome(String nome);
	
	/**
	 * Busca um fornecedor através do cnpj.
	 * @param cnpj cnpj da empresa.
	 * @return Fornecedor.
	 */
	FornecedorEntity findByCnpj(String cnpj);
	
	/**
	 * Busca um fornecedor através do cnpj e nome.
	 * @param cnpj
	 * @param nome
	 * @return Fornecedor.
	 */
	FornecedorEntity findByCnpjAndNome(String cnpj, String nome);
}
