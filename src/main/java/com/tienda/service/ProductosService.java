package com.tienda.service;

import java.util.ArrayList;

import com.tienda.entities.Productos;

public interface ProductosService {
	
	public ArrayList<Productos> ordenarProductosByPrecio();
	
	public ArrayList<Productos> ordenarProductosByFecha();

}
