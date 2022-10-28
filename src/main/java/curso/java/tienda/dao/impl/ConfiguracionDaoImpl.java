package curso.java.tienda.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import curso.java.tienda.dao.ConfiguracionDao;
import curso.java.tienda.entities.Configuracion;

@Repository
public class ConfiguracionDaoImpl implements ConfiguracionDao{
	
	@Autowired
	EntityManager entityManager;

	@Override
	public Configuracion getByClave(String clave) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Configuracion> query = session.createQuery("from Configuracion where clave=:clave");
		
		query.setParameter("clave", clave);
		
		List<Configuracion> configuraciones=query.getResultList();
		
		if (configuraciones!=null && configuraciones.size()!=0) {
			 return configuraciones.get(0);
			}
		
		return null;
	}

	@Override
	public void insertConfiguracion(Configuracion configuracion) {
		
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(configuracion);
		
	}

}
