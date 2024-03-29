package curso.java.tienda.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import curso.java.tienda.dao.ProductosDao;
import curso.java.tienda.entities.Articulos_carrito;
import curso.java.tienda.entities.Productos;
import curso.java.tienda.entities.Usuarios;



@Repository
public class ProductosDaoImpl implements ProductosDao {
	
	@Autowired
	EntityManager entityManager;
	
	@Transactional
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
	public Productos getProductoById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Productos producto = session.get(Productos.class, id);
		
		return producto;
	}

	@Override
	public ArrayList<Productos> getProductosByCat(int cat) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("from Productos where categoria_id=: id_cat",Productos.class);
		
		query.setParameter("id_cat", cat);

		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList();
		
		return productos;
		
	}
	
	// Precio desc
	
	@Override
	public ArrayList<Productos> getProductosByPrecioSinCatDesc(){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("from Productos order by precio desc",Productos.class);
		
		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList();
		
		return productos;
		
	}
	
	@Override
	public ArrayList<Productos> getProductosByPrecioSinCatDesc(double min, double max){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("from Productos where precio between " + min + "and " + max + " order by precio desc",Productos.class);
		
		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList();
		
		return productos;

	}
	
	@Override
	public ArrayList<Productos> getProductosByPrecioConCatDesc(int cat){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("from Productos where categoria_id=:id_categoria order by precio desc",Productos.class);
		
		query.setParameter("id_categoria", cat);
		
		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList();
		
		return productos;
		
	}
	
	
	@Override
	public ArrayList<Productos> getProductosByPrecioConCatDesc(double min, double max, int cat){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("from Productos where precio between " + min + "and " + max + " and categoria_id=:id_categoria order by precio desc",Productos.class);
		
		query.setParameter("id_categoria", cat);
		
		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList();
		
		return productos;
		
	}
	
	// Precio asc
	
	@Override
	public ArrayList<Productos> getProductosByPrecioSinCatAsc(){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("from Productos order by precio",Productos.class);
		
		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList();
		
		return productos;
		
	}
	
	@Override
	public ArrayList<Productos> getProductosByPrecioSinCatAsc(double min, double max){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("from Productos where precio between " + min + "and " + max + " order by precio",Productos.class);
		
		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList();
		
		return productos;

	}
	
	@Override
	public ArrayList<Productos> getProductosByPrecioConCatAsc(int cat){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("from Productos where categoria_id=:id_categoria order by precio",Productos.class);
		
		query.setParameter("id_categoria", cat);
		
		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList();
		
		return productos;
		
	}
	
	
	@Override
	public ArrayList<Productos> getProductosByPrecioConCatAsc(double min, double max, int cat){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("from Productos where precio between " + min + "and " + max + " and categoria_id=:id_categoria order by precio",Productos.class);
		
		query.setParameter("id_categoria", cat);
		
		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList();
		
		return productos;
	}
	
	// Nombre
	
	@Override
	public ArrayList<Productos> getProductosByNombre(String nombre){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("from Productos where nombre like :nombre",Productos.class);
		
		query.setParameter("nombre", "%"+nombre+"%");
		
		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList();
		
		return productos;
		
	}
	
	// Más vendidos
	
	@Override
	public ArrayList<Productos> getProductosByStock(){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("from Productos order by stock",Productos.class);
		
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
	
	@Override
	public ArrayList<Articulos_carrito> getProductosCarritoTablaByIdProducto(int id_producto){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Articulos_carrito> query=session.createQuery("from Articulos_carrito where id_producto=:id_producto", Articulos_carrito.class);

		query.setParameter("id_producto", id_producto);
		
		ArrayList<Articulos_carrito> productos =  (ArrayList<Articulos_carrito>) query.getResultList(); 

		return productos;
	}
	
	@Override
	public ArrayList<Articulos_carrito> getProductosCarritoTabla (Usuarios user){
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Articulos_carrito> query=session.createQuery("from Articulos_carrito  where id_usuario=:id", Articulos_carrito.class);
		
		query.setParameter("id", user.getId());
		ArrayList<Articulos_carrito> productos =  (ArrayList<Articulos_carrito>) query.getResultList(); 
		
		return productos;	
	}
	
	@Transactional
	@Override
	public void modificarStock(int id, int cantidad) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Productos> query = session.createQuery("update Productos set stock=stock+"+cantidad+" where id=:id");
		
		query.setParameter("id", id);

		query.executeUpdate();
		
	}
	
	// comprobar comprados

	@Override
	public List<Object> productosComprados(int producto_id, int id_usuario) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Object> query= session.createQuery("FROM Pedidos p INNER JOIN Detalles_pedido d ON p.id =d.id_pedido where d.producto.id =:producto_id and p.id_usuario =:id_usuario");
		
		query.setParameter("producto_id", producto_id);
		
		query.setParameter("id_usuario", id_usuario);
		
		List<Object> comprados = query.getResultList();
		
		return comprados;
	}

	@Override
	public ArrayList<Productos> getMasValorados() {
		
		Session session = entityManager.unwrap(Session.class);

		Query<Productos> query = session.createQuery("from Productos order by valoracion_media desc",Productos.class);

		ArrayList<Productos> productos =  (ArrayList<Productos>) query.getResultList();

		return productos;

	}

	@Override
	public Long getProductosPorCantidad(int userId) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query query=session.createQuery("SELECT SUM(cantidad) from Articulos_carrito where id_usuario=:userId");
		
		query.setParameter("userId", userId);
		
		Long suma = (Long) query.uniqueResult();
		
		System.out.println(suma);
		
		return suma;
	}

}
