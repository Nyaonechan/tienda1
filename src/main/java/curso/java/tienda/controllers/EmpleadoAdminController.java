package curso.java.tienda.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import curso.java.tienda.utils.HiloBackups;

import curso.java.tienda.DemoApplication;
import curso.java.tienda.dao.CategoriasDao;
import curso.java.tienda.entities.Categorias;
import curso.java.tienda.entities.Configuracion;
import curso.java.tienda.entities.Descuentos;
import curso.java.tienda.entities.Detalles_pedido;
import curso.java.tienda.entities.Productos;
import curso.java.tienda.entities.Proveedores;
import curso.java.tienda.entities.Usuarios;
import curso.java.tienda.service.CategoriasService;
import curso.java.tienda.service.ConfiguracionService;
import curso.java.tienda.service.DescuentosService;
import curso.java.tienda.service.Detalles_pedidoService;
import curso.java.tienda.service.Opciones_menuService;
import curso.java.tienda.service.PedidosService;
import curso.java.tienda.service.ProductosService;
import curso.java.tienda.service.ProveedoresService;
import curso.java.tienda.service.UsuariosService;
import curso.java.tienda.utils.Encriptacion;
import curso.java.tienda.utils.Excel;
import curso.java.tienda.utils.FileUpload;


@SessionAttributes({"user", "idRol"})
@Controller
public class EmpleadoAdminController {
	
	@Autowired
	private ObjectMapper mapper; // Jackson JSON
	
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
	ProveedoresService proveedorService;
	
	@Autowired
	DescuentosService descuentoService;
	
	@Autowired
	Excel excel;
	
	static Logger logger = Logger.getLogger(DemoApplication.class);
	
	
	@GetMapping ("/adminInicio")
	public String adminInicio (Model modelo) {
		
		System.out.println("Controlador adminInicio");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		opciones_menuService.elegirOpciones(modelo, user.getId());
		
		return "empleados/adminInicio";
	}
	
	//Gestion Productos
	
	@GetMapping ("/adminProductos")
	public String adminProductos(Model modelo) {
		
		System.out.println("Controlador adminProductos");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		productoService.cargarProductos(modelo);
		
		return "empleados/adminProductos";
	}
	
	@GetMapping ("/nuevoProducto")
	String nuevoProducto(Model modelo) {
		
		System.out.println("Controlador nuevoProducto");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		modelo.addAttribute("producto",new Productos());
		
		categoriaService.cargarCategorias(modelo);

		return "empleados/formProducto";
	}
	
	@GetMapping("/modificarProducto")
	public String modificarProducto (@RequestParam int idProd, Model modelo) {
		
		System.out.println("Controlador modificarProducto");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		productoService.getProductoById(idProd, modelo);
		
		categoriaService.cargarCategorias(modelo);

		return "empleados/formProducto";
	}
	
	@PostMapping ("/insertProducto")
	@Transactional
	String insertOrUpdateProducto(Model modelo, @ModelAttribute Productos producto, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		System.out.println("Controlador insertProducto");
		
		System.out.println(producto);
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		int id_categoria = producto.getCategoria().getId();
		Categorias categoria = categoriaService.getCategoriaById(id_categoria);
		producto.setCategoria(categoria);
		
		// Pasar el nombre del archivo
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		System.out.println("Nombre del fichero: " +fileName);
		
		if (fileName.equals("")) fileName = "logoOtaku.jpg";
				
		producto.setImagen(fileName);
		
		// Cargar la imagen en el proyecto
		
		String uploadDir = "src/main/resources/static/img/productos";
				
		FileUpload.saveFile(uploadDir, fileName, multipartFile);
		
		productoService.insertProducto(producto);
		
		logger.info("Producto con id: " + producto.getId() + " añadido o modificado");
		
		return adminProductos(modelo);
	}
	
	@GetMapping ("/darBajaProducto")
	public String darBajaProducto(Model modelo, @RequestParam("idProd") int id) {
		
		System.out.println("Controlador darBajaProducto");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		productoService.darBajaProducto(id);
		
		logger.info("Producto con id: " + id + " dado de baja del catálogo");
		
		return adminProductos(modelo);
	}
	
	//Gestion Pedidos
	
	@GetMapping("/adminPedidos")
	public String adminPedidos(Model modelo) {
		
		System.out.println("Controlador adminPedidos");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		pedidoService.getPedidos(modelo);
		
		return "empleados/adminPedidos";
	}
	
	@GetMapping ("/modificarEstado")
	public String modificarEstado(@RequestParam("idPedido") int id, Model modelo) {
		
		System.out.println("Controlador modificarEstado");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
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
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
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
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		Usuarios usuario = new Usuarios();
		
		user.setClave("123456");
		
		modelo.addAttribute("usuario", usuario);
		
		return "empleados/formUsuarios";
		
	}
	
	@GetMapping ("/modificarUsuario")
	public String modificarUsuario(@RequestParam int idUsuario, Model modelo) {
		
		System.out.println("Controlador modificarUsuario");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		usuarioService.getUsuariosById(idUsuario, modelo);
		
		return "empleados/formUsuarios";
		
	}
	
	@PostMapping ("/insertUsuario")
	public String insertUsuario(Model modelo, Usuarios usuario) {
		
		System.out.println("Controlador insertUsuario");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		int rol = (int) modelo.getAttribute("idRol");
		
		usuarioService.insertUsuario(usuario, rol);
		
		return adminUsuarios(modelo, rol);
	}
	
	@GetMapping("/darBajaUsuario")
	public String darBajaUsuario(Model modelo, @RequestParam int idUsuario) {
		
		System.out.println("Controlador darBajaUsuario");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		int rol = (int) modelo.getAttribute("idRol");
		
		usuarioService.darBajaUsuarioById(idUsuario);
		
		logger.info("Usuario con rol: " + rol + " dado de baja");
		
		return adminUsuarios(modelo, rol);

	}
	
	@GetMapping ("/exportarCatalogo")
	public String exportarCatalogo(Model modelo) {
		
		System.out.println("Controlador exportarCatalogo");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		excel.exportarCatalogo();
		
		modelo.addAttribute("export", "Exportación realizada con éxito");
		
		return adminProductos(modelo);
	}
	
	@PostMapping ("/importarProductos")
	public String importarProductos(Model modelo, @RequestParam("file") MultipartFile multipartFile) throws IOException {
		
		System.out.println("Controlador importarProductos");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		String uploadDir = "src/main/resources/ficheros";
		
		FileUpload.saveFile(uploadDir, fileName, multipartFile);
		
		excel.importarProductos(fileName);
		
		modelo.addAttribute("import", "Importación realizada con éxito");
		
		return adminProductos(modelo);
	}
	
	@GetMapping ("/formCategoria")
	public String formCategoria(Model modelo) {
		
		System.out.println("Controlador formCategoria");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		Categorias categoria = new Categorias();
		
		modelo.addAttribute("cat", categoria);
		
		return "empleados/formCategoria";
	}
	
	@PostMapping("/crearCategoria")
	public String crearCategoria(Model modelo, @ModelAttribute Categorias cat, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		System.out.println("Controlador crearCategorias");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		// Pasar el nombre del archivo al user
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
						
		cat.setImagen(fileName);
				
		// Cargar la imagen en el proyecto
				
		String uploadDir = "src/main/resources/static/img/categorias";
						
		FileUpload.saveFile(uploadDir, fileName, multipartFile);
		
		categoriaService.insertarCategoria(cat);
		
		return adminInicio(modelo);
	}
	
	@GetMapping ("/adminConfiguracion")
	public String adminConfiguracion(Model modelo) {
		
		System.out.println("Controlador adminConfiguracion");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		configuracionService.getConfiguraciones(modelo);
		
		return "empleados/adminConfiguracion";
	}
	
	@GetMapping("/formConfiguracion")
	public String formConfiguracion(@RequestParam int idConfiguracion, Model modelo) {
		
		System.out.println("Controlador formConfiguracion");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		configuracionService.getConfiById(modelo, idConfiguracion);
		
		return "empleados/formConfiguracion";
	}
	
	@PostMapping("/modificarConfiguracion")
	String modificarConfiguracion(Model modelo, Configuracion configuracion) {
		
		System.out.println("Controlador modificarConfiguracion");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		configuracionService.insertConfiguracion(configuracion);
		
		return adminConfiguracion(modelo);
	}
	
	@GetMapping("/verDetalles")
	public String verDetalles(@RequestParam int idPedido, Model modelo) {
		
		System.out.println("Controlador verDetalles");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		detalle_pedidoService.getDetallesPedidoByIdPedido(modelo, idPedido);
		
		return "empleados/adminDetallesPedido";
	}
	
	@GetMapping ("/confirmarCancProducto")
	public String confirmarCancProducto(@RequestParam int idPedido, @RequestParam int idDet, @RequestParam String estado, Model modelo) {
		
		System.out.println("Controlador confirmarCancProducto");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		detalle_pedidoService.modificarEstadoAdminCanc(idDet);
		
		pedidoService.modificarPedidoTotal(idPedido, estado);
		
		//sumar el stock del producto cancelado
		
		detalle_pedidoService.restablecerStockCancelado(idDet);
		
		return verDetalles(idPedido, modelo);
	}
	
	@GetMapping ("/adminProveedores")
	public String adminProveedores(Model modelo) {
		
		System.out.println("Controlador adminProveedores");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		proveedorService.getProveedores(modelo);
		
		return "empleados/adminProveedores";
	}
	
	@GetMapping ("/nuevoProveedor")
	String nuevoProveedor(Model modelo) {
		
		System.out.println("Controlador nuevoProveedor");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		modelo.addAttribute("proveedor",new Proveedores());

		return "empleados/formProveedor";
	}
	
	@GetMapping("/modificarProveedor")
	public String modificarProveedor (@RequestParam int idProv, Model modelo) {
		
		System.out.println("Controlador modificarProveedor");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		proveedorService.getProveedorById(modelo, idProv);

		return "empleados/formProveedor";
	}
	
	@PostMapping ("/insertProveedor")
	@Transactional
	String insertOrUpdateProveedor(Model modelo, @ModelAttribute Proveedores proveedor) {
		
		System.out.println("Controlador insertProveedor");
		
		System.out.println(proveedor);
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		proveedorService.insertProveedor(proveedor);
		
		logger.info("Proveedor con id: " + proveedor.getId() + " añadido o modificado");
		
		return adminProveedores(modelo);
	}
	
	@GetMapping ("/darBajaProveedor")
	public String darBajaProveedor(Model modelo, @RequestParam("idProv") int id) {
		
		System.out.println("Controlador darBajaProveedor");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		proveedorService.darBajaProveedor(id); // pendientes los métodos
		
		logger.info("Proveedor con id: " + id + " dado de baja.");
		
		return adminProveedores(modelo);
	}
	
	// Gestión descuentos
	
	@GetMapping("/adminDescuentos")
	public String adminDescuentos(Model modelo) {
		
		System.out.println("Controlador adminDescuentos");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		descuentoService.getDescuentos(modelo);
		
		return "empleados/adminDescuentos";
	}
	
	@GetMapping ("/nuevoDescuento")
	public String nuevoDescuento(Model modelo) {
		
		System.out.println("Controlador nuevoDescuento");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		modelo.addAttribute("descuento", new Descuentos());
		
		return "empleados/formDescuentos";
		
	}
	
	@GetMapping ("/modificarDescuento")
	public String modificarDescuento(@RequestParam int idDesc, Model modelo) {
		
		System.out.println("Controlador modificarDescuento");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		descuentoService.getDescuentoById(modelo, idDesc);
		
		return "empleados/formDescuentos";
	}
	
	@PostMapping ("/insertDescuento")
	public String insertDescuento(Model modelo, Descuentos descuento) {
		
		System.out.println("Controlador insertDescuento");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		descuentoService.insertDescuento(descuento);
		
		logger.info("Descuento con id: " + descuento.getId() + " añadido o modificado");
		
		return adminDescuentos(modelo);
		
	}
	
	@GetMapping ("/adminEstadisticas")
	public String adminEstadisticas(Model modelo) {
		
		System.out.println("Controlador adminEstadisticas");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		usuarioService.getUsuariosByRol(1, modelo); // clientes registrados
		
		pedidoService.getPedidos(modelo);
		
		detalle_pedidoService.cantidadTotalProductos(modelo);
		
		pedidoService.totalPedidos(modelo);
		
		pedidoService.getPedidosGroupUsuarioId(modelo);
		
		return "empleados/adminEstadisticas";
	}
	
	/* =============================================== */

	@GetMapping(value = "/pruebaJSON", produces="application/json")
	public @ResponseBody String getJSON(Model modelo) {
		
		ObjectNode raiz = mapper.createObjectNode();
		
		ArrayNode categorias = mapper.createArrayNode();
		ArrayList<Categorias> categoriasList = (ArrayList<Categorias>) modelo.getAttribute("categorias");
		
		ArrayNode cantidades = mapper.createArrayNode();
		ArrayList<Integer> cantidadesList = new ArrayList<Integer>();
		
		//cargar categorias en json
		for (Categorias cat: categoriasList) {
			categorias.addPOJO(cat);
			detalle_pedidoService.getProductosByCat(cantidadesList, cat.getId()); //cargar cantidad productos vendidos por cat en lista
		}
		// cargar cantidades productos por cat en json
		for (Integer element: cantidadesList) {
			cantidades.add(element);
		}
		
		ArrayNode productos = mapper.createArrayNode();
		ArrayNode cantidades2 = mapper.createArrayNode();
		ArrayList<Productos> productosList = productoService.seisMasValorados();
		
		for (Productos prod: productosList) {
			productos.add(prod.getNombre());
			cantidades2.add(prod.getValoracion_media());
		}
		
		raiz.set("categorias", categorias);
		raiz.set("cantidades", cantidades);
		raiz.set("productos", productos);
		raiz.set("cantidades2", cantidades2);
		
		String json = null;
		
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(raiz);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.err.println(json);
		
		return json;
		
	}
	
	//------------------- Backup--------------------
	
	@GetMapping("/lanzarHiloBackups")
	public String lanzarHiloBackups(Model model) {
		
		System.out.println("Controlador lanzar hilos");
	
		HiloBackups hilo= new HiloBackups();
		hilo.start();
		
		model.addAttribute("backup","Iniciando backups");
		
		return adminInicio(model);
}
	
	
	@GetMapping("/detenerHiloBackups")
	public String detenerHiloBackups(Model model) {
		
		System.out.println("Controlador parar hilos");
		
		HiloBackups.detenerHilo();
		
		model.addAttribute("backup","Deteniendo backups");
		
		return adminInicio(model);
}
	
}
