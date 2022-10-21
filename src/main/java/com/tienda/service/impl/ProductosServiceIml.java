package com.tienda.service.impl;

import java.util.ArrayList;
import java.util.Optional;

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
			productoCesta.setCantidad(1);
			carrito.add(productoCesta);
			modelo.addAttribute("carrito", carrito);
		} else {
			ArrayList<Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
			if (!comprobarProductoCarrito(carrito, productoCesta)) {
				productoCesta.setCantidad(1);
				carrito.add(productoCesta);
				modelo.addAttribute("carrito", carrito);
			}
		}
	}
	
	@Override
	public boolean comprobarProductoCarrito(ArrayList <Productos> carrito, Productos productoCesta) {
		boolean bandera = false;
		
		for (Productos e:carrito) {
			if (e.getId()==productoCesta.getId()) {
				e.setCantidad(e.getCantidad()+1);
				bandera = true;
			}
		}
		
		return bandera;
	}

	@Override
	public void aumentarCantidadCarritoSession(ArrayList <Productos> carrito, int id) {
		
		for (Productos e: carrito) {
			if (e.getId()==id) e.setCantidad(e.getCantidad()+1);
		}
		
	}

	@Override
	public void descenderCantidadCarritoSession(ArrayList <Productos> carrito, int id) {
		
		Optional<Productos> optional = carrito.stream().filter(n -> n.getId()==id).findFirst();
		
		Productos p = optional.get();
		
		p.setCantidad(p.getCantidad()-1);
		
		carrito.removeIf(n -> (n.getCantidad()==0));
		
	}

	@Override
	public void eliminarProductoCarritoSession(ArrayList<Productos> carrito, int id) {
		
		carrito.removeIf(n -> (n.getId()==id));
		
	}

	@Override
	public void insertarProductosListaCarritoATabla(Usuarios user, Model modelo) {
		
		if (!productoDao.comprobarProdCarritoTablaVacia(user)){
			
			ArrayList<Productos> carrito;
			
			if (modelo.getAttribute("carrito")==null) {
				 carrito = new ArrayList<Productos>();
				 modelo.addAttribute("carrito", carrito);
			}
			carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
			
			for (Productos e: carrito) {
				productoDao.insertProdCarrito(e, user);
			}
			
			modelo.addAttribute("carrito", null);
			
		}
		
	}

	@Override
	public void meterListaEnCarrito(Model modelo, Usuarios user) {
		
		ArrayList <Productos> carrito= productoDao.consultaCruzada(user);
		modelo.addAttribute("carrito", carrito);
		
	}



}
