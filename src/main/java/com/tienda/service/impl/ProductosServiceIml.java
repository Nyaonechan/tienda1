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
	public void cargarProductos(Model modelo) {
		ArrayList<Productos> productos=productoDao.getProductos();
		modelo.addAttribute("productos", productos);	
	}
	
	@Override
	public void cargarProductosByIdCat(Model modelo, int id_categoria) {
		ArrayList<Productos> productos=productoDao.getProductosByCat(id_categoria);
        
        modelo.addAttribute("productos", productos);
        
        productos.forEach(System.out::println);
	}
	
	@Override
	public void cargarProductoById(Model modelo, int id) {
		Productos productoDetalle = productoDao.getProductoById(id);
		
		System.out.println(productoDetalle);

		modelo.addAttribute("productoDetalle", productoDetalle);
	}

	@Override
	public void insertOrUpdateProdCarrito(Productos producto, Usuarios user) {
		if(productoDao.comprobarProdCarritoById(producto, user)) {
			productoDao.aumentarProdCarrito(producto, user);
		}else {
			productoDao.insertProdCarrito(producto, user);
		}
		
	}
	
	@Override
	public void carritoUserNull(Model modelo, Productos productoCesta) {
		if (modelo.getAttribute("carrito")==null) {
			ArrayList<Productos> carrito = new ArrayList<Productos>();
			carrito.add(productoCesta);
			modelo.addAttribute("carrito", carrito);
		} else {
			ArrayList<Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
			carrito.add(productoCesta);
			modelo.addAttribute("carrito", carrito);
		}
	}



}
