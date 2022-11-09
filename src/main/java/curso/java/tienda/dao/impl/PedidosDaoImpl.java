package curso.java.tienda.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import curso.java.tienda.dao.PedidosDao;
import curso.java.tienda.entities.Articulos_carrito;
import curso.java.tienda.entities.Detalles_pedido;
import curso.java.tienda.entities.Metodos_pago;
import curso.java.tienda.entities.Pedidos;
import curso.java.tienda.entities.Productos;



@Repository
public class PedidosDaoImpl implements PedidosDao {
	
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	@Override
	public ArrayList<Pedidos> getPedidos() {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Pedidos> query = session.createQuery("from Pedidos",Pedidos.class);

		ArrayList<Pedidos> pedidos =  (ArrayList<Pedidos>) query.getResultList();
		
		return pedidos;
	}
	
	@Transactional
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
	public ArrayList<Pedidos> getPedidosByIdUsuario(int id_usuario){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query <Pedidos> query = session.createQuery("FROM Pedidos WHERE id_usuario=:id_usuario", Pedidos.class);
		
		query.setParameter("id_usuario", id_usuario);
		
		ArrayList<Pedidos> pedidos = (ArrayList<Pedidos>) query.getResultList();
		
		return pedidos;
		
	}
	
	@Override
	public Pedidos getLastPedido() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query <Pedidos> query = session.createQuery("FROM Pedidos WHERE id = (SELECT MAX(id) FROM Pedidos)", Pedidos.class);
		
		Pedidos pedido = null;
		List<Pedidos> result = query.getResultList();
		if (!result.isEmpty()) {
			pedido=(Pedidos) result.get(0);
		}
		
		return pedido;
		
	}
	
	// Estado pedido
	
	@Transactional
	@Override
	public void modificarEstadoAdminEnviado(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Pedidos> query = session.createQuery("update Pedidos set estado=:estado where id=:id");
		query.setParameter("id", id);
		query.setParameter("estado", "E");
		
		query.executeUpdate();
		
		
	}
	
	@Transactional
	@Override
	public void modificarEstadoAdminCancelado(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Pedidos> query = session.createQuery("update Pedidos set estado=:estado where id=:id");
		query.setParameter("id", id);
		query.setParameter("estado", "C");
		
		query.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public void modificarEstadoCliente (int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Pedidos> query = session.createQuery("update Pedidos set estado=:estado where id=:id");
		query.setParameter("id", id);
		query.setParameter("estado", "P.C.");

		query.executeUpdate();
		
	}
	
	/*@Override
	public Pedidos getLastFactura() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query <Pedidos> query = session.createQuery("FROM Pedidos WHERE num_factura = (SELECT MAX(num_factura) FROM Pedidos)", Pedidos.class);
		
		Pedidos pedido = null;
		List<Pedidos> result = query.getResultList();
		if (!result.isEmpty()) {
			pedido=(Pedidos) result.get(0);
		}
		
		return pedido;
		
	}*/

	@Transactional
	@Override
	public void establecerNumFactura(int id, String factura) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Pedidos> query = session.createQuery("update Pedidos set num_factura=:num_factura where id=:id");
		
		query.setParameter("id", id);
		
		query.setParameter("num_factura", factura);

		query.executeUpdate();
	}	
	
	@Transactional
	@Override
	public void eliminarArticulosCarritoByIdPedido(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Articulos_carrito> query = session.createQuery("delete from Articulos_carrito where id_usuario=:id");
		
		query.setParameter("id", id);
		
		query.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public void modificarStock(int cantidad, int id_producto) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("update Productos set stock=stock-"+cantidad+" where id=:id_producto");
		query.setParameter("id_producto", id_producto);
		
		query.executeUpdate();
		
	}
	
	@Override
	public ArrayList<Metodos_pago> getMetodosPago(){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Metodos_pago> query = session.createQuery("from Metodos_pago", Metodos_pago.class);
		
		ArrayList<Metodos_pago> metodos =  (ArrayList<Metodos_pago>) query.getResultList();
		
		return metodos;
	}

	@Override
	public void modificarTotalPedido(int id, double total) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("update Pedidos set total=:total where id=:id");
		query.setParameter("id", id);
		query.setParameter("total", total);
		
		query.executeUpdate();
		
	}

	@Override
	public ArrayList<Pedidos> getPedidosGroupUsuario() {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Pedidos> query = session.createQuery("SELECT id_usuario, COUNT(id_usuario)FROM Pedidos GROUP BY id_usuario");

		ArrayList<Pedidos> pedidos =  (ArrayList<Pedidos>) query.getResultList();
		
		return pedidos;
		
	}

}
