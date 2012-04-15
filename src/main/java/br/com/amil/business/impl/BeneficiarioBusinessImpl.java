package br.com.amil.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.amil.business.BeneficiarioBusiness;
import br.com.amil.model.Beneficiario;

public class BeneficiarioBusinessImpl implements BeneficiarioBusiness {

	@Autowired
	private BeneficiarioBusiness beneficiarioBusiness;
	
	@Override
	public Beneficiario buscar(Long id) {
		return beneficiarioBusiness.buscar(id);
	}

	@Override
	public List<Beneficiario> listar() {
		return beneficiarioBusiness.listar();
	}

	@Override
	public void salvar(Beneficiario beneficiario) {
		beneficiarioBusiness.salvar(beneficiario);
	}

	@Override
	public void atualizar(Beneficiario beneficiario) {
		beneficiarioBusiness.atualizar(beneficiario);
	}

	@Override
	public void excluir(Long id) {
		beneficiarioBusiness.excluir(id);
	}

}
