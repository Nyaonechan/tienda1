package curso.java.tienda.controllers;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import curso.java.tienda.DemoApplication;
import curso.java.tienda.entities.Categorias;
import curso.java.tienda.entities.Configuracion;
import curso.java.tienda.entities.Detalles_pedido;
import curso.java.tienda.entities.Productos;
import curso.java.tienda.entities.Usuarios;
import curso.java.tienda.service.CategoriasService;
import curso.java.tienda.service.ConfiguracionService;
import curso.java.tienda.service.Detalles_pedidoService;
import curso.java.tienda.service.Opciones_menuService;
import curso.java.tienda.service.PedidosService;
import curso.java.tienda.service.ProductosService;
import curso.java.tienda.service.UsuariosService;
import curso.java.tienda.utils.Encriptacion;
import curso.java.tienda.utils.Excel;


@SessionAttributes({"user", "idRol", "categorias"})
@Controller
public class EmpleadoAdminController {
	
	@Autowired
	ProductosService productoService;
	
	@Autowired
	PedidosService pedidoService;
	
	@Autowired
	Detalles_pedidoService detalle_pedidoService;
	
	@Autowired
	UsuariosService usuarioService;
	
	@Autowired
	Opciones_menuService opciones_menuService;
	
	@Autowired
	CategoriasService categoriaService;
	
	@Autowired
	ConfiguracionService configuracionService;
	
	@Autowired
	Excel excel;
	
	static Logger logger = Logger.getLogger(DemoApplication.class);
	
	
	@GetMapping ("/adminInicio")
	public String adminInicio (Model modelo) {
		
		System.out.println("Controlador adminInicio");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		opciones_menuService.elegirOpciones(modelo, user.getId());
		
		return "empleados/adminInicio";
	}
	
	//Gestion Productos
	
	@GetMapping ("/adminProductos")
	public String adminProductos(Model modelo) {
		
		System.out.println("Controlador adminProductos");
		
		productoService.cargarProductos(modelo);
		
		return "empleados/adminProductos";
	}
	
	@GetMapping ("/nuevoProducto")
	String nuevoProducto(Model modelo) {
		
		System.out.println("Controlador nuevoProducto");
		
		modelo.addAttribute("producto",new Productos());
		//modelo.addAttribute("categoria",new Categorias());
		
		return "empleados/formProducto";
	}
	
	@GetMapping("/modificarProducto")
	public String modificarProducto (@RequestParam int idProd, Model modelo) {
		
		System.out.println("Controlador modificarProducto");
		
		productoService.getProductoById(idProd, modelo);

		return "empleados/formProducto";
	}
	
	@PostMapping ("/insertProducto")
	@Transactional
	String insertOrUpdateProducto(Model modelo, Productos producto) {
		
		System.out.println("Controlador insertProducto");
		
		System.out.println(producto);
		
		productoService.insertProducto(producto);
		
		logger.info("Producto con id: " + producto.getId() + " añadido o modificado");
		
		return adminProductos(modelo);
	}
	
	@GetMapping ("/darBajaProducto")
	public String darBajaProducto(Model modelo, @RequestParam("idProd") int id) {
		
		System.out.println("Controlador darBajaProducto");
		
		productoService.darBajaProducto(id);
		
		logger.info("Producto con id: " + id + " dado de baja del catálogo");
		
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
		
		pedidoService.establecerFactura(id);
		
		modelo.addAttribute("id_pedido", id);
		
		logger.info("Estado del pedido con id: " + id + " modificado");
		
		return "empleados/pedidoModificado";
		
	}
	
	//Gestion Usuarios
	
	@GetMapping("/adminUsuarios")
	public String adminUsuarios(Model modelo, @RequestParam int idRol) {
		
		System.out.println("Controlador adminClientes");
		
		usuarioService.getUsuariosByRol(idRol, modelo);
		
		modelo.addAttribute("idRol", idRol);
		
		if (idRol==1) modelo.addAttribute("tipoUsuario", "Listado clientes");
		if (idRol==2) modelo.addAttribute("tipoUsuario", "Listado empleados");
		if (idRol==3) modelo.addAttribute("tipoUsuario", "Listado administradores");
		
		return "empleados/adminUsuarios";
	}
	
	@GetMapping ("/nuevoUsuario")
	public String nuevoCliente(Model modelo) {
		
		System.out.println("Controlador nuevoUsuario");
		
		Usuarios user = new Usuarios();
		
		user.setClave("123456");
		
		modelo.addAttribute("usuario", user);
		
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
		
		logger.info("Usuario con rol: " + rol + " dado de baja");
		
		return adminUsuarios(modelo, rol);

	}
	
	@GetMapping ("/exportarCatalogo")
	public String exportarCatalogo(Model modelo) {
		
		excel.exportarCatalogo();
		
		return adminInicio(modelo);
	}
	
	@GetMapping ("/importarProductos")
	public String importarProductos(Model modelo) {
		
		excel.importarProductos();
		return adminInicio(modelo);
	}
	
	@GetMapping ("/formCategoria")
	public String formCategoria(Model modelo) {
		
		System.out.println("Controlador formCategoria");
		
		modelo.addAttribute("categoria", new Categorias());
		
		return "empleados/formCategoria";
	}
	
	@PostMapping("/crearCategoría")
	public String crearCategoria(Model modelo, Categorias categoria) {
		
		System.out.println("Controlador crearCategorias");
		
		categoriaService.insertarCategoria(categoria);
		
		return adminInicio(modelo);
	}
	
	@GetMapping ("/adminConfiguracion")
	public String adminConfiguracion(Model modelo) {
		
		System.out.println("Controlador adminConfiguracion");
		
		configuracionService.getConfiguraciones(modelo);
		
		return "empleados/adminConfiguracion";
	}
	
	@GetMapping("/formConfiguracion")
	public String formConfiguracion(@RequestParam int idConfiguracion, Model modelo) {
		
		System.out.println("Controlador formConfiguracion");
		
		configuracionService.getConfiById(modelo, idConfiguracion);
		
		return "empleados/formConfiguracion";
	}
	
	@PostMapping("/modificarConfiguracion")
	String modificarConfiguracion(Model modelo, Configuracion configuracion) {
		
		System.out.println("Controlador modificarConfiguracion");
		
		configuracionService.insertConfiguracion(configuracion);
		
		return adminConfiguracion(modelo);
	}
	
	@GetMapping("/verDetalles")
	public String verDetalles(@RequestParam int idPedido, Model modelo) {
		
		System.out.println("Controlador verDetalles");
		
		detalle_pedidoService.getDetallesPedidoById(modelo, idPedido);
		
		return "empleados/adminDetallesPedido";
	}
	
	@GetMapping ("/confirmarCancProducto")
	public String confirmarCancProducto(@RequestParam int idPedido, @RequestParam int idDet, @RequestParam String estado, Model modelo) {
		
		detalle_pedidoService.modificarEstadoAdminCanc(idDet);
		
		pedidoService.modificarPedidoTotal(idPedido, estado);
		
		//sumar el stock del producto cancelado
		
		detalle_pedidoService.restablecerStockCancelado(idDet);
		
		return verDetalles(idPedido, modelo);
	}

}
