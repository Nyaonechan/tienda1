package curso.java.tienda.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import curso.java.tienda.dao.ValoracionesDao;
import curso.java.tienda.entities.Valoraciones;

@Repository
public class ValoracionesDaoImpl implements ValoracionesDao{
	
	@Autowired
	EntityManager entityManager;

	@Override
	public ArrayList<Valoraciones> getValoraciones() {
		
		Session session=entityManager.unwrap(Session.class);
		
		Query<Valoraciones> query=session.createQuery("from Valoraciones", Valoraciones.class );
		
		ArrayList<Valoraciones> valoraciones = (ArrayList<Valoraciones>) query.getResultList();
		
		return valoraciones;
	}
	
	@Override
	@Transactional
	public void insertValoracion(Valoraciones valoracion) {
		
		Session session=entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(valoracion);
		
	}

}
