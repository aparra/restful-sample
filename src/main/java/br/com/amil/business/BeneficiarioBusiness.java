package br.com.amil.business;

import java.util.List;

import br.com.amil.model.Beneficiario;


public interface BeneficiarioBusiness {
	
	Beneficiario buscar(Long id);
	
	List<Beneficiario> listar();
	
	void salvar(Beneficiario beneficiario);
	
	void atualizar(Beneficiario beneficiario);
	
	void excluir(Long id);
	
}
