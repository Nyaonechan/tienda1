package com.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.entities.Usuarios;
import com.tienda.service.PedidosService;
import com.tienda.service.ProductosService;

@SessionAttributes({"categorias", "user", "precioTotal", "carrito"})
@Controller
public class PedidosController {
	
	@Autowired
	ProductosService productoService;
	
	@Autowired
	PedidosService pedidoService;
	
	@GetMapping ("/checkout")
	public String checkout (Model modelo) {
		
		productoService.cargarCategorias(modelo);
		
		modelo.getAttribute("carrito");
		
		return "checkout";
	}
	
	@GetMapping ("/confirmarCompra")
	public String confirmarCompra (Model modelo /*@RequestParam("payment") String payment*/) {
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		pedidoService.insertPedido(user, "Card" , productoService.precioTotalCarro(modelo));
		pedidoService.insertDetallesPedido(modelo);
		pedidoService.modificarStock(modelo);
		
		modelo.addAttribute("carrito", null);
		
		pedidoService.eliminarArticulosCarritoById();
		
		return "ty_purchase"; 
	}

}
