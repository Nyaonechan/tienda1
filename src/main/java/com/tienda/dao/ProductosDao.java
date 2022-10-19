package com.tienda.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.tienda.entities.Categorias;
import com.tienda.entities.Productos;
import com.tienda.entities.Usuarios;

public interface ProductosDao {
	
	public ArrayList<Productos> getProductos();
	
	public ArrayList<Productos> getProductosByCat(int cat);
	
	public Productos getProductoById(int id);
	
	public ArrayList<Categorias> getCategorias();
	
	public boolean comprobarProdCarritoById (Productos producto,  Usuarios user);
	
	public void insertProdCarrito (Productos producto, Usuarios user);
	
	public void aumentarProdCarrito (Productos producto,Usuarios user);
	
	public void descenderProdCarrito (Productos producto, Usuarios user);
	
	public void eliminarProdCarrito (Productos producto, Usuarios user);
	
	public void eliminarProdCarritoCantidadCero ();

}
