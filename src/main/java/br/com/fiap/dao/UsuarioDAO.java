package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


import br.com.fiap.model.Usuario;
import br.com.fiap.utils.JPAUtil;

public class UsuarioDAO {

	public void save(Usuario usuario) {
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.getTransaction().commit();
		
		manager.close();
	}
	
	public List<Usuario> getAll(){
		EntityManager manager = JPAUtil.getEntityManager();
		String jpql = "SELECT s FROM Usuario s";
		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		return query.getResultList();
	}
	
	public Usuario findById(int id) {
		EntityManager manager = JPAUtil.getEntityManager();
		return manager.find(Usuario.class, id);
	}
	
	public void update(Usuario usuario){
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(usuario);
		manager.flush();
		manager.getTransaction().commit();
		
		manager.close();
	}

	public boolean exist(Usuario usuario) {
		EntityManager manager = JPAUtil.getEntityManager();
		TypedQuery<Usuario> query = manager.createQuery("SELECT u FROM Usuario u WHERE "
									+ "u.email = :email AND "
									+ "u.senha = :senha", Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		
		try {
			query.getSingleResult();
		}catch(NoResultException e) {
			return false;
		}
		return true;
	}
}
