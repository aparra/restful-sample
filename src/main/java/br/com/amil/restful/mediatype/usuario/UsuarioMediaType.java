package br.com.amil.restful.mediatype.usuario;

import java.io.Serializable;

import br.com.amil.model.Usuario;
import br.com.caelum.vraptor.restfulie.hypermedia.HypermediaResource;
import br.com.caelum.vraptor.restfulie.relation.RelationBuilder;

public class UsuarioMediaType implements HypermediaResource, Serializable {

	private static final long serialVersionUID = 8235472813564735847L;
	
	private Usuario usuario;

	public UsuarioMediaType(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public void configureRelations(RelationBuilder builder) {
		builder.relation("fake").at("/fake");
	}
	
}
