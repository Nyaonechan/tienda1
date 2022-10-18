package com.tienda.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.dao.ProductosDao;
import com.tienda.entities.Productos;
import com.tienda.service.ProductosService;

@Service
public class ProductosServiceIml implements ProductosService {
	
	@Autowired
	ProductosDao productosDao;

	@Override
	public ArrayList<Productos> ordenarProductosByPrecio() {
		
		return null;
	}

	@Override
	public ArrayList<Productos> ordenarProductosByFecha() {
		// TODO Auto-generated method stub
		return null;
	}

}
