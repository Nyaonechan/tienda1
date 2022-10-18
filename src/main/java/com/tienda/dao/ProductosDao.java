package com.tienda.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.tienda.entities.Categorias;
import com.tienda.entities.Productos;

public interface ProductosDao {
	
	public ArrayList<Productos> getProductos();
	
	public ArrayList<Productos> getProductosByCat(int cat);
	
	public Productos getProductoById(int id);
	
	public ArrayList<Categorias> getCategorias();

}
