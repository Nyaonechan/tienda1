package com.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.entities.Productos;
import com.tienda.entities.Usuarios;
import com.tienda.service.PedidosService;
import com.tienda.service.ProductosService;
import com.tienda.service.UsuariosService;

@SessionAttributes({"user", "idRol"})
@Controller
public class EmpleadoAdminController {
	
	@Autowired
	ProductosService productoService;
	
	@Autowired
	PedidosService pedidoService;
	
	@Autowired
	UsuariosService usuarioService;
	
	
	@GetMapping ("/adminInicio")
	public String dashboard () {
		
		// cargar las opciones
		
		return "empleados/adminInicio";
	}
	
	//Gestion Productos
	
	@GetMapping ("/adminProductos")
	public String adminProductos(Model modelo) {
		
		productoService.cargarProductos(modelo);
		
		return "empleados/adminProductos";
	}
	
	@GetMapping ("/nuevoProducto")
	String nuevoProducto(Model modelo) {
		
		System.out.println("Controlador nuevoProducto");
		
		modelo.addAttribute("producto",new Productos());
		
		return "empleados/formProducto";
	}
	
	@GetMapping("/modificarProducto")
	public String modificarProducto (@RequestParam int idProd, Model modelo) {
		
		productoService.getProductoById(idProd, modelo);

		return "empleados/formProducto";
	}
	
	@PostMapping ("/insertProducto")
	String insertOrUpdateProducto(Model modelo, Productos producto) {
		
		System.out.println("Controlador insertProducto");
		
		System.out.println(producto);
		
		productoService.insertProducto(producto);
		
		return adminProductos(modelo);
	}
	
	@GetMapping ("/darBajaProducto")
	public String darBajaProducto(Model modelo, @RequestParam("idProd") int id) {
		
		System.out.println("Controlador darBajaProducto");
		
		productoService.darBajaProducto(id);
		
		return adminProductos(modelo);
	}
	
	//Gestion Pedidos
	
	@GetMapping("/adminPedidos")
	public String adminPedidos(Model modelo) {
		
		System.out.println("Controlador adminPedidos");
		
		pedidoService.getPedidos(modelo);
		
		return "empleados/adminPedidos";
	}
	
	@GetMapping ("/modificarEstado")
	public String modificarEstado(@RequestParam("idPedido") int id, Model modelo) {
		
		System.out.println("Controlador modificarEstado");
		
		pedidoService.modificarEstadoPedidoAdmin(id);
		
		return adminPedidos(modelo);
		
	}
	
	//Gestion Usuarios
	
	@GetMapping("/adminUsuarios")
	public String adminUsuarios(Model modelo, @RequestParam int idRol) {
		
		System.out.println("Controlador adminClientes");
		
		usuarioService.getUsuariosByRol(idRol, modelo);
		
		modelo.addAttribute("idRol", idRol);
		
		return "empleados/adminUsuarios";
	}
	
	@GetMapping ("/nuevoUsuario")
	public String nuevoCliente(Model modelo) {
		
		System.out.println("Controlador nuevoUsuario");
		
		modelo.addAttribute("usuario", new Usuarios());
		
		return "empleados/formUsuarios";
		
	}
	
	@GetMapping ("/modificarUsuario")
	public String modificarUsuario(@RequestParam int idUsuario, Model modelo) {
		
		System.out.println("Controlador modificarUsuario");
		
		usuarioService.getUsuariosById(idUsuario, modelo);
		
		return "empleados/formUsuarios";
		
	}
	
	@PostMapping ("/insertUsuario")
	public String insertUsuario(Model modelo, Usuarios usuario) {
		
		System.out.println("Controlador insertUsuario");
		
		int rol = (int) modelo.getAttribute("idRol");
		
		usuarioService.insertUsuario(usuario, rol);
		
		return adminUsuarios(modelo, rol);
	}
	
	@GetMapping("/darBajaUsuario")
	public String darBajaUsuario(Model modelo, @RequestParam int idUsuario) {
		
		int rol = (int) modelo.getAttribute("idRol");
		
		usuarioService.darBajaUsuarioById(idUsuario);
		
		return adminUsuarios(modelo, rol);
	}

}
