package com.tienda.controllers;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.DemoApplication;
import com.tienda.dao.UsuariosDao;
import com.tienda.entities.Usuarios;
import com.tienda.service.PedidosService;
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
	
	@Autowired
	private PedidosService pedidoService;
	
	static Logger logger = Logger.getLogger(DemoApplication.class);
	
	@GetMapping("/login") 
	public String formularioLogin (Model modelo) {
		System.out.println("llamando a controlador Login GET"); 

		modelo.addAttribute("user",new Usuarios());
		return "perfil/formularioLogin"; 
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
		
		logger.info("Loggeo correcto " + u.getId());
		
		productoService.insertarProductosListaCarritoATabla(u, modelo); // SI NO HAY REGISTROS DE ESE USUARIO EN LA TABLA
				
		return  productosController.todosProductos(modelo);

	}
	
	@GetMapping ("/logout")
	public String logout (Model modelo) {
		System.out.println("llamando a controlador logout");
		modelo.addAttribute("user", null);
		modelo.addAttribute("carrito", null);
		
		logger.info("Desloggeo correcto ");
		
		return productosController.todosProductos(modelo); // mirar como redirigir a la misma pagina de donde vengas
	}
		
	
	@GetMapping("/register")
	public String formularioRegistro (Model modelo) {
		
		System.out.println("llamando a controlador registerGET");

		modelo.addAttribute("usuario",new Usuarios());
		return "perfil/formularioRegistro";
	}
	

	
	@PostMapping("/register")
	public String processRegister(Model modelo, Usuarios usuario) {
		
		System.out.println("llamando a controlador registerPOST");
		
		usuario.setBaja(false);
		usuario.setId_rol(1);
		usuario.setFecha_alta(LocalDate.now());
		
	    System.out.println(usuario);
	    usuarioDao.insertarUsuario(usuario);
	    
	    logger.info("Registro correcto " + usuario.getId());
	     
	    return formularioLogin(modelo);
	}
	
	@GetMapping("/perfilResumen")
	public String perfilResumen() {
		
		return "perfil/perfilResumen";
	}
	
	@GetMapping ("/modificarDatos")
	public String modificarDatos() {
		
		return "perfil/perfilCambios";
	}
	
	@PostMapping("/modificarDatosForm")
	public String modificarDatosForm(Model modelo) {
		
		Usuarios user = (Usuarios) modelo.addAttribute("user");
		
		
		return perfilResumen();
	}
	
	@GetMapping ("/perfilPedidos")
	public String  perfilPedidos (Model modelo) {
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		pedidoService.getPedidosByIdUsuario(user.getId(), modelo);
		
		return "perfil/perfilPedidos";
	}
	
	@GetMapping ("/perfilDetallesPedido")
	public String  perfilDetallesPedido () {
		
		return "perfil/perfilDetallesPedido";
	}
	


}
