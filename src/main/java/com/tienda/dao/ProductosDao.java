package com.tienda.dao;

import java.util.ArrayList;
import com.tienda.entities.Articulos_carrito;
import com.tienda.entities.Categorias;
import com.tienda.entities.Productos;
import com.tienda.entities.Usuarios;

public interface ProductosDao {
	
	public void insertOrUpdateProducto(Productos producto);
	
	public void darBajaProducto(int id);
	
	public ArrayList<Productos> getProductos();
	
	public ArrayList<Productos> getProductosByCat(int cat);
	
	public Productos getProductoById(int id);
	
	public ArrayList<Categorias> getCategorias();
	
	public boolean comprobarProdCarritoById (int id,  Usuarios user);
	
	public boolean comprobarProdCarritoTablaVacia (Usuarios user);
	
	public void insertProdCarrito (int id_producto, int id_usuario);
	
	public void aumentarProdCarrito (int id,Usuarios user);
	
	public void descenderProdCarrito (int id, Usuarios user);
	
	public void eliminarProdCarrito (int id, Usuarios user);
	
	public void eliminarProdCarritoCantidadCero ();
	
	public ArrayList<Productos> getProductosCarritoTablaCruzada (Usuarios user);
	
	public ArrayList<Articulos_carrito> getProductosCarritoTabla (Usuarios user);

}
