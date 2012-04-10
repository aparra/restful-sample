package br.com.amil.restful.service;

import static br.com.caelum.vraptor.view.Results.representation;

import java.util.Date;

import br.com.amil.model.Usuario;
import br.com.amil.restful.mediatype.usuario.UsuarioMediaType;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Status;

@Resource
@Path("/servicos/usuario")
public class UsuarioRest {

	private final Result result;
	
	private Usuario usuario;
	
	private Status status;
	
	private UsuarioRest(Result result, Status status) {
		this.result = result;
		this.status = status;
		Usuario usuario = new Usuario();
		usuario.setLogin("meu_login");
		usuario.setSenha("123qwe");
		usuario.setUltimoAcesso(new Date());
		this.usuario = usuario;
	}

	@Get("")
	public void usuario() {
		result.use(representation()).from(new UsuarioMediaType(this.usuario)).recursive().serialize();
	}
	
	@Post("")
	public void usuario(Usuario usuario) {
		this.usuario.setLogin(usuario.getLogin());
		this.usuario.setSenha(usuario.getSenha());
		result.use(representation()).from(new UsuarioMediaType(this.usuario)).recursive().serialize();
	}

	@Post("/login")
	public void login(Usuario usuario) {
		if (this.usuario.getLogin().equals(usuario.getLogin()) && this.usuario.getSenha().equals(usuario.getSenha())) {
			this.status.accepted();
		} else {
			this.status.forbidden("Usuário ou senha inválido.");
		}
	}
}
