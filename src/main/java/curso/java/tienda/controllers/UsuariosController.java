package curso.java.tienda.controllers;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import curso.java.tienda.DemoApplication;
import curso.java.tienda.dao.UsuariosDao;
import curso.java.tienda.entities.Categorias;
import curso.java.tienda.entities.Productos;
import curso.java.tienda.entities.Usuarios;
import curso.java.tienda.entities.Valoraciones;
import curso.java.tienda.service.CategoriasService;
import curso.java.tienda.service.Detalles_pedidoService;
import curso.java.tienda.service.PedidosService;
import curso.java.tienda.service.ProductosService;
import curso.java.tienda.service.UsuariosService;
import curso.java.tienda.service.ValoracionesService;
import curso.java.tienda.utils.Encriptacion;
import curso.java.tienda.utils.EnviarEmail;
import curso.java.tienda.utils.FacturasPdf;
import curso.java.tienda.utils.FileUpload;


@SessionAttributes({"categorias", "user"})
@Controller
public class UsuariosController {
	
	@Autowired
	private CategoriasService categoriaService;
	
	@Autowired
	private ProductosController productosController;
	
	@Autowired
	private UsuariosDao usuarioDao;
	
	@Autowired
	private ProductosService productoService;
	
	@Autowired
	private PedidosService pedidoService;
	
	@Autowired
	private Detalles_pedidoService detalle_pedidoService;
	
	@Autowired
	UsuariosService usuarioService;
	
	@Autowired
	ValoracionesService valoracionService;
	
	@Autowired
	EnviarEmail email;
	
	@Autowired
	FacturasPdf factura;
	
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
		
		Usuarios user = usuarioDao.getPersonaByEmail(usuario.getEmail());

		if( user==null) {
			modelo.addAttribute("error", "Usuario no registrado");
			return formularioLogin(modelo); 
		}
		if (!usuarioService.comprobarPass(usuario.getEmail(), usuario.getClave(), user)) {
			modelo.addAttribute("error", "Contraseña incorrecta");
			return formularioLogin(modelo); 
		}
		modelo.addAttribute("user",user);
		
		logger.info("Loggeo correcto " + user.getId());
		
		productoService.insertarProductosListaCarritoATabla(user, modelo); // SI NO HAY REGISTROS DE ESE USUARIO EN LA TABLA
		
		//comprobar si es contraseña por defecto
		
		String clave = Encriptacion.desencriptar(user.getClave());
		
		if (usuarioService.comprobarDefecto(clave, modelo)) return "perfil/perfilCambios";
			
		return  "redirect:";

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
	

	
	@PostMapping("/registerPost")
	public String processRegister(Model modelo, Usuarios usuario) {
		
		System.out.println("llamando a controlador registerPOST");
		
		usuario.setBaja(false);
		usuario.setId_rol(1);
		usuario.setFecha_alta(LocalDate.now());
		
	    System.out.println(usuario);
	    usuarioDao.insertarUsuario(usuario);
	    email.enviarEmail(usuario.getEmail());
	    
	    logger.info("Registro correcto " + usuario.getId());
	     
	    return formularioLogin(modelo);
	}
	
	@GetMapping("/perfilResumen")
	public String perfilResumen(Model modelo) {
		
		System.out.println("Controlador perfilResumen");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		String desencriptada = Encriptacion.desencriptar(user.getClave()); //null cuando vuelves a home
		
		modelo.addAttribute("desencriptada", desencriptada);
		
		return "perfil/perfilResumen";
	}
	
	@GetMapping ("/modificarDatos")
	public String modificarDatos(Model modelo) {
		
		System.out.println("Controlador modificarDatos");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		String desencriptada = Encriptacion.desencriptar(user.getClave());
		
		modelo.addAttribute("desencriptada", desencriptada);
		
		return "perfil/perfilCambios";
	}
	
	@PostMapping("/modificarDatosPost")
	public String modificarDatosForm(Model modelo, Usuarios usuario, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		System.out.println("Controlador modificarDatosPost");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		// Pasar el nombre del archivo al user
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		usuario.setImagen(fileName);
		
		//Guardar cambios
		
		usuarioDao.insertarUsuario(usuario);
		
		modelo.addAttribute("user", usuario);
		
		// Cargar la imagen en el proyecto
		
		String uploadDir = "src/main/resources/static/img/users";
		
		FileUpload.saveFile(uploadDir, fileName, multipartFile);
		
		//Comprobar si la clave sigue siendo la de por defecto
		
		String clave = Encriptacion.desencriptar(usuario.getClave());
		
		if (usuarioService.comprobarDefecto(clave, modelo)) return "perfil/perfilCambios";
		
		return perfilResumen(modelo);
	}
	
	@GetMapping ("/perfilPedidos")
	public String  perfilPedidos (Model modelo) {
		
		System.out.println("Controlador perfilPedidos");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		pedidoService.getPedidosByIdUsuario(user.getId(), modelo);
		
		return "perfil/perfilPedidos";
	}
	
	@GetMapping ("/perfilDetallesPedido")
	public String  perfilDetallesPedido (Model modelo, @RequestParam int id_pedido) {
		
		System.out.println("Controlador perfilDetallesPedido");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		pedidoService.getPedidoById(id_pedido, modelo);
		
		detalle_pedidoService.getDetallesPedidoByIdPedido(modelo, id_pedido);
		
		return "perfil/perfilDetallesPedido";
	}
	
	@GetMapping ("/pedirCancelacion")
	public String pedirCancelacion(@RequestParam int id_pedido, Model modelo) {
		
		System.out.println("Controlador pedirCancelacion");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		pedidoService.modificarEstadoPedidoCliente(id_pedido);
		
		logger.info("Solicitada cancelación del pedido con id: "+id_pedido);
		
		return perfilPedidos(modelo);
	}
	
	@GetMapping ("/facturasPdf")
	public String facturasPdf(@RequestParam int id_pedido, Model modelo) {
		
		System.out.println("Controlador facturasPdf");
		
		factura.crearFactura(id_pedido, modelo);

		return perfilPedidos(modelo);
		
	}
	

	@PostMapping ("/guardarValoracion")
	public String guardarValoracion(Model modelo,@ModelAttribute Valoraciones valoracion, @RequestParam int idProd, @RequestParam int idUser) {
		
		System.out.println("Controlador guardarValoracion");
		
		System.out.println(valoracion);
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		Productos producto = productoService.getProductoById(idProd);
		valoracion.setProducto(producto);
		
		Usuarios usuario = usuarioService.getUsuarioById(idUser);
		valoracion.setUsuario(usuario);
		
		valoracionService.insertValoracion(valoracion);
		
		return "redirect:/shop";
	}
	
	@GetMapping("/cancelarProducto")
	public String cancelarProducto(@RequestParam int idDet, @RequestParam int idPedido, Model modelo) {
		
		System.out.println("Controlador cancelarProducto");
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user == null) return "redirect:/login";
		
		detalle_pedidoService.modificarEstadoCliente(idDet);
		
		return perfilDetallesPedido(modelo, idPedido);
	}


}
