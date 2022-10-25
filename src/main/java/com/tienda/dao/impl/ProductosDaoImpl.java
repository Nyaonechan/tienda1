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
import com.tienda.entities.Articulos_carrito;
import com.tienda.entities.Categorias;
import com.tienda.entities.Productos;
import com.tienda.entities.Usuarios;



@Repository
public class ProductosDaoImpl implements ProductosDao {
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public void insertOrUpdateProducto(Productos producto) {
		
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(producto);
		
	}
	
	@Transactional
	@Override
	public void darBajaProducto(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("update Productos set baja='1' where id=:id_producto");
		
		query.setParameter("id_producto", id);
		
		query.executeUpdate();
		
	}

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
	public boolean comprobarProdCarritoById(int id,  Usuarios user) {
		
		boolean bandera = false;
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Articulos_carrito> query = session.createQuery("from Articulos_carrito a where id_producto=:id_producto and id_usuario=:id_usuario", Articulos_carrito.class);
		query.setParameter("id_producto", id);
		query.setParameter("id_usuario", user.getId());
		
		ArrayList<Articulos_carrito> registros =  (ArrayList<Articulos_carrito>) query.getResultList();
		System.out.println(registros);
		
		bandera = !registros.isEmpty();
		
		return bandera;
	}
	
	@Override
	public boolean comprobarProdCarritoTablaVacia (Usuarios user) {
		
		boolean bandera = false;
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Articulos_carrito> query = session.createQuery("from Articulos_carrito where id_usuario=:id_usuario", Articulos_carrito.class);
		query.setParameter("id_usuario", user.getId());
		
		ArrayList<Articulos_carrito> registros =  (ArrayList<Articulos_carrito>) query.getResultList();
		System.out.println(registros);
		
		bandera = !registros.isEmpty();
		
		return bandera;

	}
	
	// CRUD TABLA CARRITO
	
	@Transactional
	@Override
	public void insertProdCarrito (int id_producto, int id_usuario) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Articulos_carrito articulo = new Articulos_carrito (id_producto, id_usuario, 1);
		
		session.saveOrUpdate(articulo);
		
	}
	
	@Transactional
	@Override
	public void aumentarProdCarrito(int id, Usuarios user) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Articulos_carrito> query = session.createQuery("update Articulos_carrito set cantidad=cantidad+1 where id_producto=:id_producto and id_usuario=:id_usuario");
		query.setParameter("id_producto", id);
		query.setParameter("id_usuario", user.getId());
		
		query.executeUpdate();
	}
	
	@Transactional
	@Override
	public void descenderProdCarrito(int id, Usuarios user) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Articulos_carrito> query = session.createQuery("update Articulos_carrito set cantidad=cantidad-1 where id_producto=:id_producto and id_usuario=:id_usuario");
		query.setParameter("id_producto", id);
		query.setParameter("id_usuario", user.getId());
		
		query.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public void eliminarProdCarrito(int id, Usuarios user) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Articulos_carrito> query = session.createQuery("delete from Articulos_carrito where id_producto=:id_producto and id_usuario=:id_usuario");
		query.setParameter("id_producto", id);
		query.setParameter("id_usuario", user.getId());
		
		query.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public void eliminarProdCarritoCantidadCero() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Articulos_carrito> query = session.createNativeQuery("delete from articulos_carrito where cantidad=:cantidad", Articulos_carrito.class);
		query.setParameter("cantidad", 0);
		
		query.executeUpdate();
		
	}

	@Override
	public ArrayList<Productos> getProductosCarritoTablaCruzada(Usuarios user) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query=session.createQuery("select p from Productos p, Articulos_carrito a where a.id_usuario=:id and a.id_producto = p.id", Productos.class);
		
		query.setParameter("id", user.getId());
		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList(); 
		
		
		return productos;
	}
	
	public ArrayList<Articulos_carrito> getProductosCarritoTabla (Usuarios user){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Articulos_carrito> query=session.createQuery("from Articulos_carrito  where id_usuario=:id", Articulos_carrito.class);
		
		query.setParameter("id", user.getId());
		ArrayList<Articulos_carrito> productos =  (ArrayList<Articulos_carrito>) query.getResultList(); 
		
		
		return productos;
		
	}








}
