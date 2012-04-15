package br.com.amil.repository;

import java.util.List;

import br.com.amil.model.Beneficiario;
import br.com.amil.repository.util.BaseDao;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class BeneficiarioDao extends BaseDao{
	
	public List<Beneficiario> listar() {
		return beneficiarios;
	}
	
	public Beneficiario buscar(Long id) {
		return beneficiarios.get(id.intValue()); 
	}
	
	public void salvar(Beneficiario beneficiario) {
		beneficiarios.add(beneficiario);
	}
	
	public void atualizar(Beneficiario beneficiario) {
		Beneficiario beneficiarioAtualizado = beneficiarios.get(beneficiario.getId().intValue());
		beneficiarioAtualizado.setCpf(beneficiario.getCpf());
		beneficiarioAtualizado.setDataNascimento(beneficiario.getDataNascimento());
		beneficiarioAtualizado.setModalidade(beneficiario.getModalidade());
		beneficiarioAtualizado.setNome(beneficiario.getNome());
	}
	
	public void excluir(Long id) {
		beneficiarios.remove(id.intValue());
	}
	
}
