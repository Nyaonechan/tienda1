package com.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.dao.UsuariosDao;
import com.tienda.entities.Usuarios;


@SessionAttributes({"categorias", "user"})
@Controller
public class UsuariosController {
	
	@Autowired
	private ProductosController productosController;
	
	@Autowired
	private UsuariosDao usuarioDao;
	
	@GetMapping("/login") 
	public String formularioLogin (Model modelo) {
		Usuarios u = (Usuarios) modelo.getAttribute("user");
		if(u!=null) {
			System.out.println("!Estas ya logeado!");
			return productosController.todosProductos(modelo);
		}
		System.out.println("llamando a controlador Login GET"); 
		modelo.addAttribute("usuario",new Usuarios());
		return "formularioLogin"; 
	}
	
	
	@PostMapping("/login")
	public String formularioLoginPost (Model modelo,Usuarios usuario) {
		
		System.out.println("llamando a controlador Login POST");

		Usuarios u =usuarioDao.getPersonaByEmailAndPass(usuario.getEmail(), usuario.getClave());
		System.out.println("Persona obtenida:"+u);
		if(u==null) {
			modelo.addAttribute("error", "");
			return "formularioLogin"; 
		}
		modelo.addAttribute("user",u);
		return  productosController.todosProductos(modelo);

		
	}
	
	@GetMapping ("/logout")
	public String logout (Model modelo) {
		System.out.println("llamando a controlador logout");
		modelo.addAttribute("user", null);
		return productosController.todosProductos(modelo); // mirar como redirigir a la misma pagina de donde vengas
	}
		
	
	@GetMapping("/register")
	public String formularioRegistro (Model modelo) {
		
		System.out.println("llamando a controlador formularioRegistro");
		modelo.addAttribute("usuario",new Usuarios());
		return "formularioRegistro";
	}
	

	
	@PostMapping("/register")
	public String processRegister(Usuarios usuario) {
		
		System.out.println("llamando a controlador processRegister");
	    usuario.setBaja(false);
	    System.out.println(usuario);
	    usuarioDao.insertarUsuario(usuario);
	     
	    return "/login";
	}
	

}
