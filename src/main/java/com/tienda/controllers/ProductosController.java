package com.tienda.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.dao.ProductosDao;
import com.tienda.entities.Productos;
import com.tienda.entities.Usuarios;
import com.tienda.service.ProductosService;


@SessionAttributes({"categorias", "user", "carrito"})
@Controller
public class ProductosController {
	
	@Autowired
	private ProductosDao productoDao;
	
	@Autowired
	private ProductosService productoService;
	
	@GetMapping("/shop")
	public String todosProductos(Model modelo) {
		System.out.println("llamando a controlador shop");
		
		productoService.cargarCategorias(modelo);
	
		productoService.cargarProductos(modelo);
		
		return "shop";
	}
	
	@GetMapping("/shop/{id}")
    public String categoriaProductos(@PathVariable("id") int id_categoria, Model modelo) {
		
		System.out.println("llamando a controlador shop/id");
		
		productoService.cargarCategorias(modelo);
		
		productoService.cargarProductosByIdCat(modelo, id_categoria);

        return "shop";
    }
	
	@GetMapping ("/detail")
	public String detail (Model modelo, @RequestParam("idProd") int id) {
		
		System.out.println("llamando a controlador detail");
		
		productoService.cargarCategorias(modelo);
		
		productoService.cargarProductoById(modelo, id);
		
		return "detail";
	}
	
	@GetMapping ("/añadirCarrito")
	public String añadirCarrito (Model modelo, @RequestParam("idProd") int id) {
		
		System.out.println("llamando a controlador añadirCarrito");
		
		productoService.cargarCategorias(modelo);
		
		Productos productoCesta = productoDao.getProductoById(id);
		
		System.out.println(productoCesta);
		
		Usuarios u = (Usuarios) modelo.getAttribute("user");
		
		if (modelo.getAttribute("user")==null) {

			productoService.carritoUserNull(modelo, productoCesta);
			
			System.out.println(productoCesta.getNombre() + " añadido a la cesta");

		} else {
			productoService.insertOrUpdateProdCarrito(productoCesta, u);
		}
		
		return todosProductos(modelo);
	}
	
	
	@GetMapping ("/cart")
	public String cart(Model modelo) {
		
		System.out.println("llamando a controlador cart");
		
		productoService.cargarCategorias(modelo);
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (modelo.getAttribute("user")!=null) {
			productoService.meterListaEnCarrito(modelo, user);
		}
		
		modelo.getAttribute("carrito");
		
		return "cart";
	}
	
	// ACCIONES EN EL CARRO--------------------
	
	@GetMapping ("/aumentarCantidad")
	public String aumentarCantidad (Model modelo, @RequestParam("idProd") int id) {
		
		ArrayList<Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
		
		productoService.aumentarCantidadCarritoSession(carrito, id);
		
		return cart(modelo);
	}
	
	@GetMapping ("/descenderCantidad")
	public String descenderCantidad (Model modelo, @RequestParam("idProd") int id) {
		
		ArrayList<Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
		
		productoService.descenderCantidadCarritoSession(carrito, id);
		
		return cart(modelo);
	}
	
	@GetMapping ("/eliminarProducto")
	public String eliminarProducto (Model modelo, @RequestParam("idProd") int id) {
		
		ArrayList<Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
		
		productoService.eliminarProductoCarritoSession(carrito, id);
		
		return cart(modelo);
	}
	
	//---------------------------------------------------
	
	@GetMapping ("/checkout")
	public String checkout () {
		
		return "checkout";
	}
	
	@GetMapping ("/confirmarCompra")
	public String confirmarCompra () {
		
		//meter los productos en las tablas pedido y detalles pedido
		// vaciar carro en sesion y borrar registros de la tabla articulos_carrito
		
		return "checkout"; // vista para compra con exito
	}
	

}
