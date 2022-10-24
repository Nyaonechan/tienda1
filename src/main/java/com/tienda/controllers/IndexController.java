package com.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.service.ProductosService;

@SessionAttributes({"categorias", "user"})
@Controller
public class IndexController {
	
	
	@Autowired
	private ProductosService productoService;
	
	@GetMapping("")
	public String muestraIndex(Model modelo, @RequestParam(value="cat") int id) {
		System.out.println("Controlador index");
		
		// CARGAR LOS PRODUCTOS MAS COMPRADOS
		
		// CARGAR PRODUCTOS MAS NUEVOS
		productoService.ochoProductosMasNuevos(modelo);
		
		//CARGAR CATEGORIAS
		productoService.cargarCategorias(modelo);
		
		//CARGAR CANTIDAD CATEGORIAS
		productoService.cantidadProductosCat(id, modelo);

		return "index";
	}

}
