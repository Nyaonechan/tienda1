package curso.java.tienda.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import curso.java.tienda.dao.UsuariosDao;
import curso.java.tienda.entities.Productos;
import curso.java.tienda.entities.Usuarios;

@Repository
public class UsuariosDaoImpl implements UsuariosDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Usuarios getPersonaByEmailAndPass(String email, String clave) {
		System.out.println("email: "+email);
		System.out.println("pass: "+clave);

		Session session=entityManager.unwrap(Session.class);
		Query<Usuarios> query=session.createQuery("from Usuarios where email=:email and clave=:clave", Usuarios.class );
		query.setParameter("email", email);
		query.setParameter("clave", clave);
		List<Usuarios> usuarios = query.getResultList();
		if (usuarios!=null && usuarios.size()!=0) {
		 return usuarios.get(0);
		}
		return null;
	}

	@Override
	public void insertarUsuario(Usuarios usuario) {
		
		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(usuario);
		
	}

	@Override
	public Usuarios getUsuarioById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Usuarios usuario = session.get(Usuarios.class, id);	
		
		return usuario;
	}

	@Override
	public ArrayList<Usuarios> getUsuariosPorRol(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Usuarios> query=session.createQuery("from Usuarios where id_rol=:id", Usuarios.class);
		
		query.setParameter("id", id);
		
		ArrayList<Usuarios> usuarios = (ArrayList<Usuarios>) query.getResultList();
		
		return usuarios;
	}
	
	@Transactional
	@Override
	public void darBajaUsuarioById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Usuarios> query = session.createQuery("update Usuarios set baja='1' where id=:id_usuario");
		
		query.setParameter("id_usuario", id);
		
		query.executeUpdate();
		
	}

}
