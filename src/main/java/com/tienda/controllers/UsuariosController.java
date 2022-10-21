package com.tienda.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.dao.UsuariosDao;
import com.tienda.entities.Usuarios;
import com.tienda.service.ProductosService;


@SessionAttributes({"categorias", "user"})
@Controller
public class UsuariosController {
	
	@Autowired
	private ProductosController productosController;
	
	@Autowired
	private UsuariosDao usuarioDao;
	
	@Autowired
	private ProductosService productoService;
	
	@GetMapping("/login") 
	public String formularioLogin (Model modelo) {
		System.out.println("llamando a controlador Login GET"); 

		modelo.addAttribute("user",new Usuarios());
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
		
		productoService.insertarProductosListaCarritoATabla(u, modelo); // SI NO HAY REGISTROS DE ESE USUARIO EN LA TABLA
				
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
		
<<<<<<< HEAD
		System.out.println("llamando a controlador registerGET");
=======
		System.out.println("llamando a controlador formularioRegistro");
<<<<<<< HEAD
>>>>>>> f98ee11d036b823647517b441d4ebae59ff6e97d
=======
>>>>>>> f98ee11d036b823647517b441d4ebae59ff6e97d
		modelo.addAttribute("usuario",new Usuarios());
		return "formularioRegistro";
	}
	

	
	@PostMapping("/register")
	public String processRegister(Model modelo, Usuarios usuario) {
		
		System.out.println("llamando a controlador registerPOST");
		
		usuario.setBaja(false);
		usuario.setId_rol(1);
		usuario.setFecha_alta(LocalDate.now());
		
	    System.out.println(usuario);
	    usuarioDao.insertarUsuario(usuario);
	     
	    return formularioLogin(modelo);
	}
	

}
