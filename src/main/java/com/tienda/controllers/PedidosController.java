package com.tienda.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.entities.Usuarios;
import com.tienda.service.PedidosService;
import com.tienda.service.ProductosService;

@SessionAttributes({"categorias", "user", "precioTotal"})
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
	public String confirmarCompra (Model modelo, HttpServletRequest request) {
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		pedidoService.insertPedido(user, request.getParameter("payment"), (double)modelo.getAttribute("precioTotal"));
		pedidoService.insertDetallesPedido(modelo, (double)modelo.getAttribute("precioTotal"));
		
		modelo.addAttribute("carrito", null);
		
		pedidoService.eliminarArticulosCarritoById();
		
		return "ty_purchase"; 
	}

}
