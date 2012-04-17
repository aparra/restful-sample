package br.com.portal.model.test.test;

import java.util.Date;

import org.hibernate.classic.Session;

import br.com.amil.model.Usuario;
import br.com.amil.model.dao.UsuarioDao;
import br.com.amil.model.infra.FactorySessionCreate;

public class testeDeSessao {

	public static void main(String[] args) {

		Session session = new FactorySessionCreate().getInstance()
				.openSession();
		testeAdicao(session);
		testeEdicao(2L, session);
		testeExclusao(1L, session);

	}

	private static void testeExclusao(Long id, Session session) {
		Usuario usuario = new UsuarioDao(session).findById(id);
		if (usuario != null) {
			usuario.setSenha("123qwe123");
			new UsuarioDao(session).update(usuario);
		}
	}

	private static void testeEdicao(Long id, Session session) {
		Usuario usuario = new UsuarioDao(session).findById(id);
		if (usuario != null) {
			usuario.setSenha("123456");
			new UsuarioDao(session).update(usuario);
		}

	}

	private static void testeAdicao(Session session) {
		Usuario usuario = new Usuario();
		usuario.setLogin("malemos");
		usuario.setNome("Marcos");
		usuario.setSenha("123qwe");
		usuario.setUltimoAcesso(new Date());
		//new UsuarioDao(session).add(usuario);
	}

}
