package com.tienda.dao;

import java.util.ArrayList;
import com.tienda.entities.Articulos_carrito;
import com.tienda.entities.Categorias;
import com.tienda.entities.Productos;
import com.tienda.entities.Usuarios;

public interface ProductosDao {
	
	public void insertOrUpdateProducto(Productos producto);
	
	public void darBajaProducto(int id);
	
	// FILTROS
	
	public ArrayList<Productos> getProductos();
	
	public ArrayList<Productos> getProductosByCat(int cat);
	
	//Precio desc
	
	public ArrayList<Productos> getProductosByPrecioSinCatDesc();
	
	public ArrayList<Productos> getProductosByPrecioSinCatDesc(double min, double max);
	
	public ArrayList<Productos> getProductosByPrecioConCatDesc(int cat);
	
	public ArrayList<Productos> getProductosByPrecioConCatDesc(double min, double max, int cat);
	
	// Precio asc
	
	public ArrayList<Productos> getProductosByPrecioSinCatAsc();
	
	public ArrayList<Productos> getProductosByPrecioSinCatAsc(double min, double max);
	
	public ArrayList<Productos> getProductosByPrecioConCatAsc(int cat);
	
	public ArrayList<Productos> getProductosByPrecioConCatAsc(double min, double max, int cat);
	
	
	// Id
	
	public Productos getProductoById(int id);
	
	//Nombre
	
	public ArrayList<Productos> getProductosByNombre(String nombre);
	
	//------------------------------
	
	public ArrayList<Categorias> getCategorias();
	
	//COMPROBACIONES
	
	public boolean comprobarProdCarritoById (int id,  Usuarios user);
	
	public boolean comprobarProdCarritoTablaVacia (Usuarios user);
	
	//ACCIONES CARRITO
	
	public void insertProdCarrito (int id_producto, int id_usuario);
	
	public void aumentarProdCarrito (int id,Usuarios user);
	
	public void descenderProdCarrito (int id, Usuarios user);
	
	public void eliminarProdCarrito (int id, Usuarios user);
	
	public void eliminarProdCarritoCantidadCero ();
	
	public ArrayList<Productos> getProductosCarritoTablaCruzada (Usuarios user);
	
	public ArrayList<Articulos_carrito> getProductosCarritoTabla (Usuarios user);

}
