package curso.java.tienda.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import curso.java.tienda.dao.Detalles_pedidoDao;
import curso.java.tienda.entities.Detalles_pedido;

@Repository
public class Detalles_pedidoDaoImpl implements Detalles_pedidoDao {
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public ArrayList<Detalles_pedido> getDetalles(){
		
		Session session = entityManager.unwrap(Session.class);

		Query<Detalles_pedido> query = session.createQuery("from Detalles_pedido",Detalles_pedido.class);

		ArrayList<Detalles_pedido> detalles =  (ArrayList<Detalles_pedido>) query.getResultList();
		
		return detalles;
		
	}
	
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
	public Detalles_pedido getDetalleById(int id) {
		
		Session session = entityManager.unwrap(Session.class);

		Detalles_pedido detalle = session.get(Detalles_pedido.class, id);
		
		return detalle;
		
	}
	
	@Transactional
	@Override
	public void modificarEstadoCliente(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Detalles_pedido> query = session.createQuery("update Detalles_pedido set estado=:estado where id=:id");
		query.setParameter("id", id);
		query.setParameter("estado", "P.C.");

		query.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public void modificarEstadoAdminCanc (int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Detalles_pedido> query = session.createQuery("update Detalles_pedido set estado=:estado where id=:id");
		query.setParameter("id", id);
		query.setParameter("estado", "C");

		query.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public void modificarEstadoAdminEnv (int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Detalles_pedido> query = session.createQuery("update Detalles_pedido set estado=:estado where id=:id");
		query.setParameter("id", id);
		query.setParameter("estado", "E");

		query.executeUpdate();
		
	}
	
	@Override
	public ArrayList<Detalles_pedido> getDetallesByIdAndEstado(int id, String estado){
		
		Session session = entityManager.unwrap(Session.class);

		Query<Detalles_pedido> query = session.createQuery("from Detalles_pedido where id_pedido=:id and estado=:estado",Detalles_pedido.class);
		
		query.setParameter("id", id);
		
		query.setParameter("estado", estado);

		ArrayList<Detalles_pedido> detallesPedido =  (ArrayList<Detalles_pedido>) query.getResultList();
		
		return detallesPedido;
		
	}

}
