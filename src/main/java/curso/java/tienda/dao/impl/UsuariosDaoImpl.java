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
import curso.java.tienda.utils.Encriptacion;

@Repository
public class UsuariosDaoImpl implements UsuariosDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Usuarios getPersonaByEmail(String email) {
		System.out.println("email: "+email);

		Session session=entityManager.unwrap(Session.class);
		Query<Usuarios> query=session.createQuery("from Usuarios where email=:email", Usuarios.class );
		query.setParameter("email", email);
		List<Usuarios> usuarios = query.getResultList();
		if (usuarios!=null && usuarios.size()!=0) {
		 return usuarios.get(0);
		}
		return null;
	}
	
	@Transactional
	@Override
	public void insertarUsuario(Usuarios usuario) {
		
		Session session = entityManager.unwrap(Session.class);
		
		String encriptada = Encriptacion.encriptar(usuario.getClave());
		
		usuario.setClave(encriptada);

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
