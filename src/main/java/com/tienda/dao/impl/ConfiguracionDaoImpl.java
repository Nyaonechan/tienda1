package com.tienda.dao.impl;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.ConfiguracionDao;
import com.tienda.entities.Configuracion;

@Repository
public class ConfiguracionDaoImpl implements ConfiguracionDao{
	
	@Autowired
	EntityManager entityManager;

	@Override
	public Configuracion getByClave(String clave) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Configuracion configuracion = session.get(Configuracion.class, clave);
		
		return configuracion;
	}

	@Override
	public void insertConfiguracion(Configuracion configuracion) {
		
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(configuracion);
		
	}

}
