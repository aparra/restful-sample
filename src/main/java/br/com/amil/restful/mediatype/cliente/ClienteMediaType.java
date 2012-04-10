package br.com.amil.restful.mediatype.cliente;

import java.io.Serializable;
import java.util.List;

import br.com.amil.model.Cliente;
import br.com.caelum.vraptor.restfulie.hypermedia.HypermediaResource;
import br.com.caelum.vraptor.restfulie.relation.RelationBuilder;

public class ClienteMediaType implements HypermediaResource, Serializable {

	private static final long serialVersionUID = -3837572030994944887L;
	
	private Cliente cliente;
	private List<Cliente> clientes;
	
	public ClienteMediaType(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public ClienteMediaType(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	@Override
	public void configureRelations(RelationBuilder builder) {
		builder.relation("clientes").at("/clientes");
	}
}

