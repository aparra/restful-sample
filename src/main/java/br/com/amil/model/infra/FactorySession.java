package br.com.amil.model.infra;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
public class FactorySession implements ComponentFactory<Session> {

	private SessionFactory sessionFactory;
	private Session session;

	public FactorySession(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println("Criando FactorySession.");
	}

	public Session getInstance() {
		System.out.println("Retornando sessão.");
		return this.session;
	}

	@PostConstruct
	public void openSession() {
		this.session = sessionFactory.openSession();
		System.out.println("Abrindo sessão.");
	}

	@PreDestroy
	public void closeSession() {
		this.session.close();
		System.out.println("Fechando sessão.");
	}

}
