package curso.java.tienda.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import curso.java.tienda.dao.Detalles_pedidoDao;
import curso.java.tienda.entities.Detalles_pedido;
import curso.java.tienda.entities.Pedidos;

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
	public void modificarEstadoCliente(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Detalles_pedido> query = session.createQuery("update Detalles_pedido set estado=:estado where id=:id");
		query.setParameter("id", id);
		query.setParameter("estado", "P.C.");

		query.executeUpdate();
		
	}

}
