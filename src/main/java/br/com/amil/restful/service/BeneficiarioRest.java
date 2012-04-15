package br.com.amil.restful.service;

import static br.com.caelum.vraptor.view.Results.representation;

import java.util.Date;

import br.com.amil.model.Beneficiario;
import br.com.amil.restful.mediatype.usuario.BeneficiarioMediaType;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
@Path("/servicos/beneficiario")
public class BeneficiarioRest {

	private final Result result;
	
	private BeneficiarioRest(Result result) {
		this.result = result;
	}

	@Get("")
	public void listarBeneficiario() {
		System.out.println(" entrou aqui ");
		
		Beneficiario beneficiario = new Beneficiario();
		beneficiario.setNome("Julio");
		beneficiario.setCpf(67843593729L);
		beneficiario.setDataNascimento(new Date());
		beneficiario.setModalidade("Saude");
		
		result.use(representation()).from(new BeneficiarioMediaType(beneficiario)).recursive().serialize();
		result.nothing();
	}
	
}
