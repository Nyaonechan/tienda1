package com.tienda.controllers;

import java.util.ArrayList;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.dao.ProductosDao;
import com.tienda.entities.Categorias;

@SessionAttributes({"categorias", "user"})
@Controller
public class IndexController {
	
	@Autowired
	private ProductosDao productoDao;
	
	@GetMapping("")
	public String muestraIndex(Model modelo) {
		System.out.println("Controlador index");
		
		// CARGAR LOS PRODUCTOS MAS COMPRADOS
		
		// CARGAR PRODUCTOS MAS NUEVOS
		
		//CARGAR CATEGORIAS
		ArrayList<Categorias> categorias = productoDao.getCategorias();
		modelo.addAttribute("categorias", categorias);
		

		return "index.html";
	}

}
