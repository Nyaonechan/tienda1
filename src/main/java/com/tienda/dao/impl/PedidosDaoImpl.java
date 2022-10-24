package com.tienda.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tienda.dao.PedidosDao;
import com.tienda.entities.Articulos_carrito;
import com.tienda.entities.Detalles_pedido;
import com.tienda.entities.Pedidos;



@Repository
public class PedidosDaoImpl implements PedidosDao {
	
	@Autowired
	EntityManager entityManager;

	@Override
	public ArrayList<Pedidos> getPedidos() {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Pedidos> query = session.createQuery("from Pedidos",Pedidos.class);

		ArrayList<Pedidos> pedidos =  (ArrayList<Pedidos>) query.getResultList();
		
		return pedidos;
	}

	@Override
	public void insertPedido(Pedidos pedido) {
		
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(pedido);
		
	}

	@Override
	public Pedidos getPedidoById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Pedidos pedido = session.get(Pedidos.class, id);
		
		return pedido;
		
	}
	
	@Override
	public Pedidos getLastPedido() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query <Pedidos> query = session.createQuery("FROM Pedidos WHERE id = (SELECT MAX(id) FROM pedidos)", Pedidos.class);
		
		Pedidos pedido = query.getSingleResult();
		
		return pedido;
		
	}

	@Override
	public void modificarEstado() {
		
		Session session = entityManager.unwrap(Session.class);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void establecerNumFactura() {
		
		Session session = entityManager.unwrap(Session.class);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertDetallePedido(Detalles_pedido detallePedido) {
		
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(detallePedido);
		
	}

	@Override
	public ArrayList<Detalles_pedido> getDetallesPedidosByIdPedido(int id) {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Detalles_pedido> query = session.createQuery("from Detalles_pedido where id_pedido=:id",Detalles_pedido.class);
		
		query.setParameter("id", id);

		ArrayList<Detalles_pedido> detallesPedido =  (ArrayList<Detalles_pedido>) query.getResultList();
		
		return detallesPedido;
		
	}
	
	@Override
	public void eliminarArticulosCarritoByIdPedido(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Articulos_carrito> query = session.createQuery("delete from Articulos_carrito where id_pedido=:id");
		
		query.setParameter("id", id);
		
		query.executeUpdate();
		
	}

}