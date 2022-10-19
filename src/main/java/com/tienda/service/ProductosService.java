package com.tienda.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.tienda.entities.Productos;
import com.tienda.entities.Usuarios;

public interface ProductosService {
	
	public ArrayList<Productos> ordenarProductosByPrecio();
	
	public ArrayList<Productos> ordenarProductosByFecha();
	
	public void cargarCategorias(Model modelo);
	
	public void insertOrUpdateProdCarrito(Productos producto, Usuarios user);

}
