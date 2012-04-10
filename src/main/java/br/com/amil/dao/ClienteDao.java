package br.com.amil.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.amil.model.Cliente;
import br.com.amil.model.Endereco;
import br.com.amil.model.UF;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class ClienteDao {
	
	private List<Cliente> lista = new ArrayList<Cliente>();
	private Integer id = 1;
	
	public List<Cliente> listaClientes(){
		lista.add(criarCliente());
		return lista;
	}
	
	public void salvar(Cliente cliente){
		lista.add(cliente);
		cliente.setId(id++);
	}
	
	public void excluir(Integer id){
		Cliente clienteExcluido = null;
		
		for (Cliente itemExcluido : lista) {
			if (itemExcluido.getId().equals(id)) {
				clienteExcluido = itemExcluido;
				break;
			}
		}
		
		excluirCliente(clienteExcluido);
	}

	private void excluirCliente(Cliente cliente) {
		lista.remove(cliente);	
	}
	
	public Cliente criarCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome("Thiago");
		cliente.setCpf(11111111);
		cliente.setEmail("thiagoandrade6@gmail.com");
		
		Endereco endereco = new Endereco();
		endereco.setId(id);
		endereco.setLogradouro("av dr eduardo cotching");
		endereco.setNumero(822);
		endereco.setComplemento("ap 81");
		endereco.setBairro("Jd Analia Franco");
		endereco.setCidade("Sao Paulo");
		endereco.setUf(UF.SP);
		
		cliente.setEndereco(endereco);
		return cliente;
	}

}
