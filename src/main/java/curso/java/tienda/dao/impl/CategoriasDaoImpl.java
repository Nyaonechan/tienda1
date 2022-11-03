package curso.java.tienda.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import curso.java.tienda.dao.CategoriasDao;
import curso.java.tienda.entities.Categorias;

@Repository
public class CategoriasDaoImpl implements CategoriasDao{
	
	@Autowired
	EntityManager entityManager;

	@Override
	public void insertarCategoria(Categorias categoria) {
		
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(categoria);
		
	}
	
	@Override
	public ArrayList<Categorias> getCategorias() {

		Session session = entityManager.unwrap(Session.class);

		Query<Categorias> query = session.createQuery("from Categorias", Categorias.class);

		ArrayList<Categorias> categorias =  (ArrayList<Categorias>) query.getResultList();
		
		return categorias;
	}
	
	

}
