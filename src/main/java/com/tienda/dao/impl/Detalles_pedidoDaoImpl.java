package com.tienda.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.Detalles_pedidoDao;
import com.tienda.entities.Detalles_pedido;

@Repository
public class Detalles_pedidoDaoImpl implements Detalles_pedidoDao {
	
	@Autowired
	EntityManager entityManager;

	@Override
	public ArrayList<Detalles_pedido> getDetallesByIdPedido(int id) {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Detalles_pedido> query = session.createQuery("from Detalles_pedido where id_pedido=:id_pedido",Detalles_pedido.class);
		
		query.setParameter("id_pedido", id);

		ArrayList<Detalles_pedido> detalles =  (ArrayList<Detalles_pedido>) query.getResultList();
		
		return detalles;
		
	}

}
