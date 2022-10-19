package com.tienda.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tienda.dao.ProductosDao;
import com.tienda.entities.Categorias;
import com.tienda.entities.Productos;
import com.tienda.entities.Usuarios;
import com.tienda.service.ProductosService;

@Service
public class ProductosServiceIml implements ProductosService {
	
	@Autowired
	ProductosDao productoDao;

	@Override
	public ArrayList<Productos> ordenarProductosByPrecio() {
		
		return null;
	}

	@Override
	public ArrayList<Productos> ordenarProductosByFecha() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cargarCategorias(Model modelo) {
		ArrayList<Categorias> categorias = productoDao.getCategorias();
		modelo.addAttribute("categorias", categorias);
		
	}

	@Override
	public void insertOrUpdateProdCarrito(Productos producto, Usuarios user) {
		if(productoDao.comprobarProdCarritoById(producto, user)) {
			productoDao.aumentarProdCarrito(producto, user);
		}else {
			productoDao.insertProdCarrito(producto, user);
		}
		
	}

}
