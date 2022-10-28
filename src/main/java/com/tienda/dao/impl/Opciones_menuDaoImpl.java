package com.tienda.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.Opciones_menuDao;
import com.tienda.entities.Opciones_menu;

@Repository
public class Opciones_menuDaoImpl implements Opciones_menuDao{
	
	@Autowired
	EntityManager entityManager;

	@Override
	public ArrayList<Opciones_menu> getOpciones() {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Opciones_menu> query = session.createQuery("from Opciones_menu",Opciones_menu.class);

		ArrayList<Opciones_menu> opciones =  (ArrayList<Opciones_menu>) query.getResultList();
		
		return opciones;
	}

	@Override
	public ArrayList<Opciones_menu> getOpcionesById(int id) {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Opciones_menu> query = session.createQuery("from Opciones_menu where id_rol=:id_rol",Opciones_menu.class);
		
		query.setParameter("id_rol", id);

		ArrayList<Opciones_menu> opciones =  (ArrayList<Opciones_menu>) query.getResultList();
		
		return opciones;
	}
	
	

}
