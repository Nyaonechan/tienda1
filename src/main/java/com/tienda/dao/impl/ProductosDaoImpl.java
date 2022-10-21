package com.tienda.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tienda.dao.ProductosDao;
import com.tienda.entities.Categorias;
import com.tienda.entities.Productos;
import com.tienda.entities.Usuarios;



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
	
	//COMPROBAR TABLA CARRITO
	
	@Override
	public boolean comprobarProdCarritoById(Productos producto,  Usuarios user) {
		
		boolean bandera = false;
		
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createNativeQuery("select * from articulos_carrito a where id_producto=:id_producto and id_usuario=:id_usuario");
		query.setParameter("id_producto", producto.getId());
		query.setParameter("id_usuario", user.getId());
		
		ArrayList<Object> registros =  (ArrayList<Object>) query.getResultList();
		System.out.println(registros);
		
		bandera = !registros.isEmpty();
		
		return bandera;
	}
	
	@Override
	public boolean comprobarProdCarritoTablaVacia (Usuarios user) {
		
		boolean bandera = false;
		
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createNativeQuery("select * from articulos_carrito where id_usuario=:id_usuario");
		query.setParameter("id_usuario", user.getId());
		
		ArrayList<Object> registros =  (ArrayList<Object>) query.getResultList();
		System.out.println(registros);
		
		bandera = !registros.isEmpty();
		
		return bandera;

	}
	
	// CRUD TABLA CARRITO
	
	@Transactional
	@Override
	public void insertProdCarrito (Productos producto, Usuarios user) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createNativeQuery("INSERT INTO articulos_carrito (id_producto, id_usuario, cantidad) VALUES(?,?,?)");
		
		query.setParameter(1, producto.getId());
		query.setParameter(2, user.getId());
		query.setParameter(3, 1);
		
		query.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public void aumentarProdCarrito(Productos producto, Usuarios user) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createNativeQuery("update articulos_carrito set cantidad=cantidad+1 where id_producto=:id_producto and id_usuario=:id_usuario");
		query.setParameter("id_producto", producto.getId());
		query.setParameter("id_usuario", user.getId());
		
		query.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public void descenderProdCarrito(Productos producto, Usuarios user) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query consulta = session.createNativeQuery("update from articulos_carrito set cantidad=cantidad-1 where id_producto=:id_producto and id_usuario=:id_usuario");
		consulta.setParameter("id_producto", producto.getId());
		consulta.setParameter("id_usuario", user.getId());
		
		consulta.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public void eliminarProdCarrito(Productos producto, Usuarios user) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query consulta = session.createNativeQuery("delete from articulos_carrito where id_producto=:id_producto and id_usuario=:id_usuario");
		consulta.setParameter("id_producto", producto.getId());
		consulta.setParameter("id_usuario", user.getId());
		
		consulta.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public void eliminarProdCarritoCantidadCero() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query consulta = session.createNativeQuery("delete from articulos_carrito where cantidad=:cantidad");
		consulta.setParameter("cantidad", 0);
		
		consulta.executeUpdate();
		
	}

	@Override
	public ArrayList<Productos> consultaCruzada(Usuarios user) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> consulta=session.createNativeQuery("select * from productos p,articulos_carrito a where a.id_usuario=:id and a.id_producto = p.id");

		consulta.setParameter("id", user.getId());
		ArrayList<Productos> productos = (ArrayList<Productos>) consulta.getResultList(); // recorrer resultSet y convertir a Productos

		
		return productos;
	}








}
