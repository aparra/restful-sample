package br.com.amil.restful.service;

import static br.com.caelum.vraptor.view.Results.representation;

import java.util.ArrayList;
import java.util.List;

import br.com.amil.model.Usuario;
import br.com.amil.model.dao.UsuarioDao;
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
	
	private Status status;
	
	private UsuarioDao usuarioDao;
	
	private UsuarioRest(Result result, Status status, UsuarioDao usuarioDao) {
		this.result = result;
		this.status = status;
		this.usuarioDao = usuarioDao;
	}

	@Get("")
	public void usuario() {
		System.out.println("teste");
		List<Usuario> usuarios = this.usuarioDao.findAll();
		result.use(representation()).from(new UsuarioMediaType(usuarios)).recursive().serialize();
	}
	
	@Post("")
	public void usuario(Usuario usuario) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		if (this.usuarioDao.existeUsuario(usuario)) {
			this.usuarioDao.update(usuario);
			usuarios.add(usuario);
		}
		result.use(representation()).from(new UsuarioMediaType(usuarios)).recursive().serialize();
	}

	@Post("/login")
	public void login(Usuario usuarioLogado) {
		
		Usuario usuario = this.usuarioDao.load(usuarioLogado);
		if (usuario != null) {
			this.status.ok();
		} else {
			this.status.forbidden("Usuário ou senha inválido.");
		}
		this.result.nothing();
	}
}
