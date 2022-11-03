package curso.java.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import curso.java.tienda.service.CategoriasService;
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
	@Autowired
	CategoriasService categoriaService;
	
	@GetMapping("")
	public String muestraIndex(Model modelo) {
		System.out.println("Controlador index");
		
		Thread thread = new Thread(hiloEstadoPedidos);
		thread.start();
		
		//TODO Si el usuario ha sido creado por admin, llevar a /perfilCambios para obligar a editar la contraseña por defecto
		
		// CARGAR DATOS EMPRESA
		configuracionService.recogerDatosEmpresa(modelo);
		
		// CARGAR LOS PRODUCTOS MAS COMPRADOS
		productoService.ochoProductosMasVendidos(modelo);
		
		// CARGAR PRODUCTOS MAS NUEVOS
		productoService.ochoProductosMasNuevos(modelo);
		
		//CARGAR CATEGORIAS
		categoriaService.cargarCategorias(modelo);
		
		//CARGAR CANTIDAD CATEGORIAS
		//productoService.cantidadProductosCat(id, modelo);

		return "index";
	}

}
