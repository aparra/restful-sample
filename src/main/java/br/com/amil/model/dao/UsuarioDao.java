package br.com.amil.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.amil.model.Usuario;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class UsuarioDao {

	private final Session session;

	public UsuarioDao(Session session) {
		this.session = session;
	}

	public boolean existeUsuario(Usuario usuario) {
		Usuario encontrado = (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("login", usuario.getLogin()))
				.uniqueResult();
		return encontrado != null;
	}

	public void add(Usuario usuario) {
		Transaction tx = this.session.beginTransaction();
		this.session.save(usuario);
		tx.commit();
	}

	public Usuario load(Usuario usuario) {
		return (Usuario) session.createCriteria(Usuario.class).add(
				Restrictions.eq("login", usuario.getLogin())).add(
				Restrictions.eq("senha", usuario.getSenha())).uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findAll() {
		return (List<Usuario>) session.createCriteria(Usuario.class).list();
	}

	public void update(Usuario usuario) {
		Transaction tx = this.session.beginTransaction();
		session.saveOrUpdate(usuario);
		tx.commit();
	}
	
	public Usuario findById(Long id) {
		return (Usuario) session.load(Usuario.class, id);
	}

	public void exclusao(Usuario usuario) {
		session.delete(usuario);
	}
}
