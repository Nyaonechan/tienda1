package com.tienda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpleadoAdminController {
	
	@GetMapping ("/dashboard")
	public String dashboard () {
		
		
		return "adminInicio";
	}

}
