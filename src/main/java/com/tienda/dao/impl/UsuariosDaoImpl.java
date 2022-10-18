package com.tienda.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.UsuariosDao;
import com.tienda.entities.Usuarios;

@Repository
public class UsuariosDaoImpl implements UsuariosDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Usuarios getPersonaByEmailAndPass(String email, String clave) {
		System.out.println("email: "+email);
		System.out.println("pass: "+clave);

		Session session=entityManager.unwrap(Session.class);
		Query<Usuarios> consulta=session.createQuery("from Usuarios where email=:email and clave=:clave", Usuarios.class );
		consulta.setParameter("email", email);
		consulta.setParameter("clave", clave);
		List<Usuarios> usuarios = consulta.getResultList();
		if (usuarios!=null && usuarios.size()!=0) {
		 return usuarios.get(0);
		}
		return null;
	}

	@Override
	public void insertarUsuario(Usuarios usuario) {
		
		Session session = entityManager.unwrap(Session.class);

		session.save(usuario);
		
	}

}
