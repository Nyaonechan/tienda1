package com.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.entities.Usuarios;
import com.tienda.service.PedidosService;
import com.tienda.service.ProductosService;

@SessionAttributes({"categorias", "user", "carrito"})
@Controller
public class PedidosController {
	
	@Autowired
	ProductosService productoService;
	
	@Autowired
	PedidosService pedidoService;
	
	@GetMapping ("/checkout")
	public String checkout (Model modelo) {
		
		productoService.cargarCategorias(modelo);
		double precioTotal=productoService.precioTotalCarro(modelo);
		productoService.desgloseIva(modelo, precioTotal);
		pedidoService.getMetodosPago(modelo);
		
		return "checkout";
	}
	
	@GetMapping ("/confirmarCompra")
	public String confirmarCompra (Model modelo) {
		
		System.out.println("Controlador confirmarCompra");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		double precioTotal=productoService.precioTotalCarro(modelo);
		productoService.desgloseIva(modelo, precioTotal);
		pedidoService.insertPedido(user, "Card" , precioTotal);
		pedidoService.insertDetallesPedido(modelo);
		pedidoService.modificarStock(modelo);
		
		modelo.addAttribute("carrito", null);
		
		pedidoService.eliminarArticulosCarritoById();
		
		return "ty_purchase"; 
	}
	


}
