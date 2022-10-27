package com.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.entities.Productos;
import com.tienda.service.PedidosService;
import com.tienda.service.ProductosService;

@SessionAttributes({"user"})
@Controller
public class EmpleadoAdminController {
	
	@Autowired
	ProductosService productoService;
	
	@Autowired
	PedidosService pedidoService;
	
	@GetMapping ("/adminInicio")
	public String dashboard () {
		
		return "empleados/adminInicio";
	}
	
	@GetMapping ("/adminProductos")
	public String adminProductos(Model modelo) {
		
		productoService.cargarProductos(modelo);
		
		return "empleados/adminProductos";
	}
	
	@GetMapping ("/nuevoProducto")
	String nuevoProducto(Model modelo) {
		
		System.out.println("Controlador nuevoProducto");
		
		modelo.addAttribute("producto",new Productos());
		
		return "empleados/formProducto";
	}
	
	@GetMapping("/modificarProducto")
	public String modificarProducto (@RequestParam int idProd, Model modelo) {
		
		productoService.getProductoById(idProd, modelo);

		return "empleados/formProducto";
	}
	
	@PostMapping ("/insertProducto")
	String insertOrUpdateProducto(Model modelo, Productos producto) {
		
		System.out.println("Controlador insertProducto");
		
		System.out.println(producto);
		
		productoService.insertProducto(producto);
		
		return adminProductos(modelo);
	}
	

	
	@GetMapping ("/darBajaProducto")
	public String darBajaProducto(Model modelo, @RequestParam("idProd") int id) {
		
		System.out.println("Controlador darBajaProducto");
		
		productoService.darBajaProducto(id);
		
		return adminProductos(modelo);
	}
	
	@GetMapping("/adminPedidos")
	public String adminPedidos(Model modelo) {
		
		System.out.println("Controlador adminPedidos");
		
		pedidoService.getPedidos(modelo);
		
		return "empleados/adminPedidos";
	}
	
	@GetMapping ("/modificarEstado")
	public String modificarEstado(@RequestParam("idPedido") int id, Model modelo) {
		
		System.out.println("Controlador modificarEstado");
		
		pedidoService.modificarEstadoPedidoAdmin(id);
		
		return adminPedidos(modelo);
		
	}

}
