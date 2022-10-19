package com.tienda.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.tienda.entities.Productos;
import com.tienda.entities.Usuarios;

public interface ProductosService {
	
	public ArrayList<Productos> ordenarProductosByPrecio();
	
	public ArrayList<Productos> ordenarProductosByFecha();
	
	public void cargarCategorias(Model modelo);
	
	public void cargarProductos(Model modelo);
	
	public void cargarProductosByIdCat(Model modelo, int id_categoria);
	
	public void cargarProductoById(Model modelo, int id);
	
	public void insertOrUpdateProdCarrito(Productos producto, Usuarios user);
	
	public void carritoUserNull(Model modelo, Productos productoCesta);

}
