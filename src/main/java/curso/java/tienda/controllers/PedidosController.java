package curso.java.tienda.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import curso.java.tienda.DemoApplication;
import curso.java.tienda.entities.Usuarios;
import curso.java.tienda.service.PedidosService;
import curso.java.tienda.service.ProductosService;

@SessionAttributes({"categorias", "user", "carrito"})
@Controller
public class PedidosController {
	
	@Autowired
	ProductosService productoService;
	
	@Autowired
	PedidosService pedidoService;
	
	static Logger logger = Logger.getLogger(DemoApplication.class);
	
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
		
		logger.info("Pedido realizado por el usuario con id: " + user.getId());
		
		return "ty_purchase"; 
	}
	


}
