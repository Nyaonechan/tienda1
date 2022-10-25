package com.tienda.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.entities.Productos;
import com.tienda.entities.Usuarios;
import com.tienda.service.PedidosService;
import com.tienda.service.ProductosService;

@SessionAttributes({"user"})
@Controller
public class EmpleadoAdminController {
	
	@Autowired
	ProductosService productoService;
	
	@Autowired
	PedidosService pedidoService;
	
	@GetMapping ("/dashboard")
	public String dashboard () {
		
		return "empleados/adminInicio";
	}
	
	@GetMapping ("/adminProductos")
	public String adminProductos(Model modelo) {
		
		productoService.cargarProductos(modelo);
		
		return "empleados/adminProductos";
	}
	
	@GetMapping ("/formProducto")
	String formProducto(Model modelo) {
		
		modelo.addAttribute("producto",new Productos());
		
		return "empleados/formProducto";
	}
	
	@PostMapping ("insertOrUpdateProducto")
	String insertOrUpdateProducto(Model modelo) {
		
		Productos producto = (Productos) modelo.getAttribute("producto");

		productoService.insertOrUpdateProducto(producto);
		
		return adminProductos(modelo);
	}
	
	@GetMapping ("/darBajaProducto")
	public String darBajaProducto(Model modelo, @RequestParam("idProd") int id) {
		
		productoService.darBajaProducto(id);
		
		return adminProductos(modelo);
	}
	
	@GetMapping("/adminPedidos")
	public String adminPedidos(Model modelo) {
		
		pedidoService.getPedidos(modelo);
		
		return "empleados/adminPedidos";
	}

}
