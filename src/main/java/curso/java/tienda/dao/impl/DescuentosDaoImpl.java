package curso.java.tienda.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import curso.java.tienda.dao.DescuentosDao;
import curso.java.tienda.entities.Descuentos;

@Repository
public class DescuentosDaoImpl implements DescuentosDao{
	
	@Autowired
	EntityManager entityManager;

	@Override
	public ArrayList<Descuentos> getDescuentos() {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Descuentos> query = session.createQuery("from Descuentos", Descuentos.class);

		ArrayList<Descuentos> descuentos =  (ArrayList<Descuentos>) query.getResultList();
		
		return descuentos;
	}

	@Override
	public Descuentos getDescuentoById(int id) {
		
		Session session = entityManager.unwrap(Session.class);

		Descuentos descuento = session.get(Descuentos.class, id);
		
		return descuento;
	}

	@Override
	public void insertDescuento(Descuentos descuento) {
		
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(descuento);
	}

	@Override
	public Descuentos getDescuentoByCodigo(String codigo) {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Descuentos> query = session.createQuery("from Descuentos where codigo=:codigo", Descuentos.class);
		
		query.setParameter("codigo", codigo);
		
		ArrayList<Descuentos> descuentos = (ArrayList<Descuentos>) query.getResultList();
		
		if (descuentos!=null && descuentos.size()!=0) {
			 return descuentos.get(0);
			}
		
		return null;
	}

}
