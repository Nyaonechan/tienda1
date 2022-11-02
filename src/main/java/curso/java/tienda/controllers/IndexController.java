package curso.java.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import curso.java.tienda.service.ConfiguracionService;
import curso.java.tienda.service.ProductosService;
import curso.java.tienda.utils.HiloEstadoPedidos;

@SessionAttributes({"categorias", "user"})
@Controller
public class IndexController {
	
	
	@Autowired
	private ProductosService productoService;
	@Autowired
	private ConfiguracionService configuracionService;
	@Autowired
	HiloEstadoPedidos hiloEstadoPedidos;
	
	@GetMapping("")
	public String muestraIndex(Model modelo) {
		System.out.println("Controlador index");
		
		Thread thread = new Thread(hiloEstadoPedidos);
		thread.start();
		
		// CARGAR DATOS EMPRESA
		configuracionService.recogerDatosEmpresa(modelo);
		
		// CARGAR LOS PRODUCTOS MAS COMPRADOS
		productoService.ochoProductosMasVendidos(modelo);
		
		// CARGAR PRODUCTOS MAS NUEVOS
		productoService.ochoProductosMasNuevos(modelo);
		
		//CARGAR CATEGORIAS
		productoService.cargarCategorias(modelo);
		
		//CARGAR CANTIDAD CATEGORIAS
		//productoService.cantidadProductosCat(id, modelo);

		return "index";
	}

}