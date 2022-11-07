package curso.java.tienda.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import curso.java.tienda.entities.Productos;
import curso.java.tienda.entities.Usuarios;

public interface ProductosService {
	
	public void insertProducto(Productos producto);
	
	public void darBajaProducto(int id);
	
	// Filtros
	
	public void getProductoById(int id, Model modelo);
	
	public void filtroPorPrecio(Model modelo, Integer categoria, String precio, String orden);
	
	public void getProductosByNombre(String nombre, Model modelo);
	
	public ArrayList<Productos> ordenarProductosByFecha();
	
	public void ochoProductosMasNuevos(Model modelo);
	
	public void ochoProductosMasVendidos(Model modelo);
	
	//-------------------------
	
	//public void cantidadProductosCat(int id, Model modelo);
	
	public void cargarProductos(Model modelo);
	
	public void cargarProductosByIdCat(Model modelo, int id_categoria);
	
	public void cargarProductoById(Model modelo, int id);
	
	public void insertOrUpdateProdCarrito(int id, Usuarios user);
	
	public void carritoUserNull(Model modelo, Productos productoCesta);
	
	public boolean comprobarProductoCarrito(ArrayList <Productos> carrito, int id);
	
	// CARRITO LISTA
	
	public void aumentarCantidadCarritoSession(ArrayList <Productos> carrito, int id);
	
	public void descenderCantidadCarritoSession(ArrayList <Productos> carrito, int id);
	
	public void eliminarProductoCarritoSession (ArrayList <Productos> carrito, int id);
	
	// CARRITO TABLA
	
	public void aumentarCantidadCarritoTabla(int id, Usuarios user);
	
	public void descenderCantidadCarritoTabla(int id, Usuarios user);
	
	public void eliminarProductoCarritoTabla (int id, Usuarios user);
	
	//---------------------------------------------------------
	
	public void insertarProductosListaCarritoATabla(Usuarios user, Model modelo);
	
	public ArrayList<Productos> getProductosCarritoTabla(Usuarios user);
	
	public void cantidadCarro (Model modelo);
	
	public double precioTotalCarro (Model modelo);
	
	public void desgloseIva(Model modelo, double precioTotal);

	// comprobar comprados
	
	public boolean comprobarComprados(int producto_id, int id_usuario);

	public Productos getProductoById(int id_producto);

}
