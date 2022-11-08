package curso.java.tienda.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import curso.java.tienda.dao.ProductosDao;
import curso.java.tienda.entities.Categorias;
import curso.java.tienda.entities.Descuentos;
import curso.java.tienda.entities.Productos;
import curso.java.tienda.entities.Usuarios;
import curso.java.tienda.entities.Valoraciones;
import curso.java.tienda.service.CategoriasService;
import curso.java.tienda.service.DescuentosService;
import curso.java.tienda.service.ProductosService;
import curso.java.tienda.service.ValoracionesService;


@SessionAttributes({"categorias", "categoria", "user", "carrito", "cantidad", "descuentoNuevo"})
@Controller
public class ProductosController {
	
	@Autowired
	private ProductosDao productoDao;
	
	@Autowired
	private ProductosService productoService;
	
	@Autowired
	CategoriasService categoriaService;
	
	@Autowired
	ValoracionesService valoracionService;
	
	@Autowired
	DescuentosService descuentoService;
	
	@GetMapping("/shop")
	public String todosProductos(Model modelo) {
		System.out.println("llamando a controlador shop");
		
		categoriaService.cargarCategorias(modelo);
	
		productoService.cargarProductos(modelo);
		
		productoService.cantidadCarro(modelo);
		
		//productoService.precioTotalCarro(modelo);
		
		Categorias categoria = new Categorias();
		
		categoria.setId(0);
		
		modelo.addAttribute("categoria", categoria);
		
		return "shop";
	}
	
	@GetMapping("/shop/{id}")
    public String categoriaProductos(@PathVariable("id") int id_categoria, Model modelo) {
		
		System.out.println("llamando a controlador shop/id");
		
		categoriaService.cargarCategorias(modelo);
		
		productoService.cargarProductosByIdCat(modelo, id_categoria);
		
		Categorias categoria = new Categorias();
		
		categoria.setId(id_categoria);
		
		modelo.addAttribute("categoria", categoria);

        return "shop";
    }
	@PostMapping ("/precios")
	public String precios(@RequestParam String price, @RequestParam String orden, Model modelo) {
		
		System.out.println("Controlador precios");
		
		categoriaService.cargarCategorias(modelo);
		
		Categorias cat= (Categorias) modelo.getAttribute("categoria");
		
		productoService.filtroPorPrecio(modelo, cat.getId(), price, orden);
		
		return "shop";
	}
	
	@PostMapping("/nombres")
	public String nombres(Model modelo, @RequestParam String nombre) {
		
		System.out.println("Controlador nombres");
		
		productoService.getProductosByNombre(nombre, modelo);
		
		return "shop";
	}
	
	@GetMapping ("/fecha")
	public String fecha(Model modelo) {
		
		ArrayList<Productos> productos = productoService.ordenarProductosByFecha();
		modelo.addAttribute("productos", productos);
		
		return "shop";
	}
	
	@GetMapping("/stock")
	public String stock (Model modelo) {
		ArrayList<Productos> productos = productoDao.getProductosByStock();
		modelo.addAttribute("productos", productos);
		return "shop";
	}
	
	@GetMapping ("/detail")
	public String detail (Model modelo, @RequestParam("idProd") int id, @RequestParam(required=false) int idCat) {
		
		System.out.println("llamando a controlador detail");
		
		categoriaService.cargarCategorias(modelo);
		
		productoService.cargarProductoById(modelo, id);
		
		productoService.cargarProductosByIdCat(modelo, idCat);
		
		valoracionService.getValoracionesByIdProducto(id, modelo);
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user!=null && productoService.comprobarComprados(id, user.getId())) {
			modelo.addAttribute("comprado","");
		}
		
		modelo.addAttribute("valor", new Valoraciones());

		
		return "detail";
	}
	
	@GetMapping ("/añadirCarrito")
	public String añadirCarrito (Model modelo, @RequestParam("idProd") int id) {
		
		System.out.println("llamando a controlador añadirCarrito");
		
		categoriaService.cargarCategorias(modelo);
		
		Productos productoCesta = productoDao.getProductoById(id);
		
		System.out.println(productoCesta);
		
		Usuarios u = (Usuarios) modelo.getAttribute("user");
		
		if (u==null) {

			productoService.carritoUserNull(modelo, productoCesta);
			
			System.out.println(productoCesta.getNombre() + " añadido a la cesta");

		} else {
			
			productoService.insertOrUpdateProdCarrito(productoCesta.getId(), u);
		}
		
		return todosProductos(modelo);
	}
	
	
	@GetMapping ("/cart")
	public String cart(Model modelo) {
		
		System.out.println("llamando a controlador cart");
		
		categoriaService.cargarCategorias(modelo);
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user!=null) {
			ArrayList <Productos> carroTabla=productoService.getProductosCarritoTabla(user);
			modelo.addAttribute("carrito", carroTabla);
		}
		
		modelo.addAttribute("descuento", new Descuentos()); // para el formulario
		
		Descuentos descuento = (Descuentos) modelo.getAttribute("descuentoNuevo"); // recogido por el formulario
		System.out.println(descuento);

		if (descuento==null) {
			descuento = new Descuentos();
			descuento.setDescuento(0);
		}
		
		//if () modelo.addAttribute("nodescuento", "No existe un descuento con ese código");
		
		double precioTotal=productoService.precioTotalCarro(modelo, descuento.getDescuento());
		productoService.desgloseIva(modelo, precioTotal);
		
		return "cart";
	}
	
	@PostMapping("/descuento")
	public String descuento(Model modelo, Descuentos descuento) {
		
		System.out.println("Controlador descuento");
		
		// comprobar si el codigo coincide con alguno existente, si no existe cargar mensaje, si existe cargar descuento
		
		descuentoService.gestionarDescuento(descuento.getCodigo(), modelo);
		
		return "redirect:/cart";
	}
	
	// ACCIONES EN EL CARRO--------------------
	
	@GetMapping ("/aumentarCantidad")
	public String aumentarCantidad (Model modelo, @RequestParam("idProd") int id) {
		
		ArrayList<Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user==null) productoService.aumentarCantidadCarritoSession(carrito, id);
		else productoService.aumentarCantidadCarritoTabla(id, user);
		
		return cart(modelo);
	}
	
	@GetMapping ("/descenderCantidad")
	public String descenderCantidad (Model modelo, @RequestParam("idProd") int id) {
		
		ArrayList<Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user==null) productoService.descenderCantidadCarritoSession(carrito, id);
		else productoService.descenderCantidadCarritoTabla(id, user);
		
		return cart(modelo);
	}
	
	@GetMapping ("/eliminarProducto")
	public String eliminarProducto (Model modelo, @RequestParam("idProd") int id) {
		
		ArrayList<Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		
		if (user==null) productoService.eliminarProductoCarritoSession(carrito, id);
		else productoService.eliminarProductoCarritoTabla(id, user);
		
		return cart(modelo);
	}
	
	//---------------------------------------------------
	

	

}
