package com.tienda.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.ProductosDao;
import com.tienda.entities.Categorias;
import com.tienda.entities.Productos;



@Repository
public class ProductosDaoImpl implements ProductosDao {
	
	@Autowired
	EntityManager entityManager;

	@Override
	public ArrayList<Productos> getProductos() {
		Session session = entityManager.unwrap(Session.class);

		Query<Productos> query = session.createQuery("from Productos",Productos.class);

		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList();

		return productos;
	}

	@Override
	public ArrayList<Categorias> getCategorias() {

		Session session = entityManager.unwrap(Session.class);

		Query<Categorias> query = session.createQuery("from Categorias", Categorias.class);

		ArrayList<Categorias> categorias =  (ArrayList<Categorias>) query.getResultList();
		
		return categorias;
	}

	@Override
	public Productos getProductoById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Productos producto = session.get(Productos.class, id);
		
		return producto;
	}

	@Override
	public ArrayList<Productos> getProductosByCat(int cat) {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("from Productos where id_categoria=: id_cat",Productos.class);
		
		query.setParameter("id_cat", cat);

		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList();
		
		return productos;
		
	}

}
