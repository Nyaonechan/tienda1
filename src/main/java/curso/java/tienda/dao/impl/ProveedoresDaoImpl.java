package curso.java.tienda.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import curso.java.tienda.dao.ProveedoresDao;
import curso.java.tienda.entities.Productos;
import curso.java.tienda.entities.Proveedores;

@Repository
public class ProveedoresDaoImpl implements ProveedoresDao{
	
	@Autowired
	EntityManager entityManager;

	@Override
	public ArrayList<Proveedores> getProveedores() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Proveedores> query = session.createQuery("from Proveedores", Proveedores.class);
		
		ArrayList<Proveedores> proveedores = (ArrayList<Proveedores>) query.getResultList();
		
		return proveedores;
	}

	@Override
	public Proveedores getProveedorById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Proveedores proveedor = session.get(Proveedores.class, id);
		
		return proveedor;
	}
	
	@Transactional
	@Override
	public void insertProveedor(Proveedores proveedor) {
		
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(proveedor);
		
	}
	
	@Transactional
	@Override
	public void darBajaProveedor(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Proveedores> query = session.createQuery("update Proveedores set baja='1' where id=:id");
		
		query.setParameter("id", id);
		
		query.executeUpdate();
		
	}

}
