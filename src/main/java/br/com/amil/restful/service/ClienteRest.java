package br.com.amil.restful.service;

import static br.com.caelum.vraptor.view.Results.representation;

import java.util.List;

import br.com.amil.dao.ClienteDao;
import br.com.amil.model.Cliente;
import br.com.amil.restful.mediatype.cliente.ClienteMediaType;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
@Path("/servicos/clientes")
public class ClienteRest {
	
	private final Result result;
	private ClienteDao dao;
	
	private ClienteRest(Result result, ClienteDao dao) {
		this.result = result;
		this.dao = dao;
	}
	
	@Get
	@Path("/")
	public void buscarClientes(){		
		listarClientes();
	}
	
	@Post
	@Path("/salvar")
	public void salvar(){
		dao.salvar(dao.criarCliente());
		listarClientes();
	}
	
	@Delete
	@Path("/deletar")
	public void delete(){
		dao.excluir(2);
		listarClientes();
	}

	private void listarClientes() {
		List<Cliente> lista = dao.listaClientes();
		result.use(representation()).from(new ClienteMediaType(lista)).recursive().serialize();
	}
}
