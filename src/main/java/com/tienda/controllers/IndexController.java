package com.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.service.ProductosService;

@SessionAttributes({"categorias", "user"})
@Controller
public class IndexController {
	
	
	@Autowired
	private ProductosService productoService;
	
	@GetMapping("")
	public String muestraIndex(Model modelo) {
		System.out.println("Controlador index");
		
		// CARGAR LOS PRODUCTOS MAS COMPRADOS
		
		// CARGAR PRODUCTOS MAS NUEVOS
		
		//CARGAR CATEGORIAS
		productoService.cargarCategorias(modelo);

		return "index";
	}

}
