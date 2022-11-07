package curso.java.tienda.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import curso.java.tienda.DemoApplication;
import curso.java.tienda.entities.Pedidos;
import curso.java.tienda.entities.Usuarios;
import curso.java.tienda.service.CategoriasService;
import curso.java.tienda.service.Detalles_pedidoService;
import curso.java.tienda.service.PedidosService;
import curso.java.tienda.service.ProductosService;

@SessionAttributes({"categorias", "user", "carrito", "cantidad"})
@Controller
public class PedidosController {
	
	@Autowired
	ProductosService productoService;
	
	@Autowired
	PedidosService pedidoService;
	
	@Autowired
	Detalles_pedidoService detalles_pedidoService;
	
	@Autowired
	ProductosController productosController;
	@Autowired
	CategoriasService categoriaService;
	
	static Logger logger = Logger.getLogger(DemoApplication.class);
	
	@GetMapping ("/checkout")
	public String checkout (Model modelo) {
		
		categoriaService.cargarCategorias(modelo);
		double precioTotal=productoService.precioTotalCarro(modelo);
		productoService.desgloseIva(modelo, precioTotal);
		pedidoService.getMetodosPago(modelo);
		
		return "checkout";
	}
	
	@PostMapping ("/confirmarCompra")
	public String confirmarCompra (Model modelo, @RequestParam (required=false) String pago) {
		
		System.out.println("Controlador confirmarCompra");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		if (pago == null) {
			modelo.addAttribute("eligeMetodo", "Debes elegir un método de pago");
			return checkout(modelo);
		}

		double precioTotal=productoService.precioTotalCarro(modelo);
		productoService.desgloseIva(modelo, precioTotal);
		if (!pedidoService.comprobarStock(modelo)) {
			pedidoService.modificarStock(modelo);
			pedidoService.insertPedido(user, pago, precioTotal);
			detalles_pedidoService.insertDetallesPedido(modelo);
			modelo.addAttribute("carrito", null);
			pedidoService.eliminarArticulosCarritoById();
			logger.info("Pedido realizado por el usuario con id: " + user.getId());
		} else {
			modelo.addAttribute("stock", "No hay suficiente stock del producto " + modelo.getAttribute("nombre_producto"));
			return productosController.cart(modelo);
		}
		Pedidos pedido = pedidoService.getLastPedido();
		modelo.addAttribute("idPedido", pedido.getId());
		modelo.addAttribute("precioTotal", pedido.getTotal());
		detalles_pedidoService.getDetallesPedidoByIdPedido(modelo, pedido.getId());
		
		return "ty_purchase"; 
	}
	


}
