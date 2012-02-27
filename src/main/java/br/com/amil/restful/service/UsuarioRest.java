package br.com.amil.restful.service;

import static br.com.caelum.vraptor.view.Results.representation;

import java.util.Date;

import br.com.amil.model.Usuario;
import br.com.amil.restful.mediatype.usuario.UsuarioMediaType;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
@Path("/servicos/usuario")
public class UsuarioRest {

	private final Result result;
	
	private UsuarioRest(Result result) {
		this.result = result;
	}

	@Get("")
	public void usuario() {
		Usuario usuario = new Usuario();
		usuario.setLogin("meu_login");
		usuario.setSenha("123qwe");
		usuario.setUltimoAcesso(new Date());
		
		result.use(representation()).from(new UsuarioMediaType(usuario)).recursive().serialize();
	}
	
}
