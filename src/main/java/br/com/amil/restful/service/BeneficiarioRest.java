package br.com.amil.restful.service;

import static br.com.caelum.vraptor.view.Results.representation;

import java.util.List;

import br.com.amil.business.BeneficiarioBusiness;
import br.com.amil.model.Beneficiario;
import br.com.amil.restful.mediatype.usuario.BeneficiarioMediaType;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
@Path("/servicos/beneficiario")
public class BeneficiarioRest {

	private final Result result;
	private BeneficiarioBusiness beneficiarioBusiness;
	
	private BeneficiarioRest(Result result, BeneficiarioBusiness beneficiarioBusiness) {
		this.result = result;
		this.beneficiarioBusiness = beneficiarioBusiness;
	}

	@Get("")
	public void listar() {
		List<Beneficiario> beneficiarios = beneficiarioBusiness.listar();
		result.use(representation()).from(beneficiarios, "beneficiarios").recursive().serialize();
	}
	
	@Get("/{id}")
	public void buscar(Long id) {
		Beneficiario beneficiario = beneficiarioBusiness.buscar(id);
		result.use(representation()).from(new BeneficiarioMediaType(beneficiario), "beneficiario").recursive().serialize();
	}
	
	@Post("")
	public void salvar(Beneficiario beneficiario) {
		beneficiarioBusiness.salvar(beneficiario);
		result.nothing();
	}
	
	@Put("")
	public void atualizar(Beneficiario beneficiario) {
		beneficiarioBusiness.atualizar(beneficiario);
		result.nothing();
	}
	
	@Delete("/{id}")
	public void excluir(Long id) {
		beneficiarioBusiness.excluir(id);
		result.nothing();
	}
	

}
