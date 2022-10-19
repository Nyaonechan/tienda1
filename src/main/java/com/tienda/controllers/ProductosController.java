package com.tienda.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tienda.dao.ProductosDao;
import com.tienda.entities.Productos;
import com.tienda.entities.Usuarios;
import com.tienda.service.ProductosService;


@SessionAttributes({"categorias", "user", "carrito"})
@Controller
public class ProductosController {
	
	@Autowired
	private ProductosDao productoDao;
	
	@Autowired
	private ProductosService productoService;
	
	@GetMapping("/shop")
	public String todosProductos(Model modelo) {
		System.out.println("llamando a controlador shop");
		
		productoService.cargarCategorias(modelo);
	
		ArrayList<Productos> productos=productoDao.getProductos();

		modelo.addAttribute("productos", productos);
		
		return "shop";
	}
	
	@GetMapping("/shop/{id}")
    public String categoriaProductos(@PathVariable("id") int id_categoria, Model modelo) {
		
		System.out.println("llamando a controlador shop/id");
		
		productoService.cargarCategorias(modelo);
		
		ArrayList<Productos> productos=productoDao.getProductosByCat(id_categoria);
        
        modelo.addAttribute("productos", productos);
        
        productos.forEach(System.out::println);

        return "shop";
    }
	
	@GetMapping ("/detail/{idProd}")
	public String detail (Model modelo, @PathVariable("idProd") int id) {
		
		System.out.println("llamando a controlador detail");
		
		productoService.cargarCategorias(modelo);
		
		Productos productoDetalle = productoDao.getProductoById(id);
		
		System.out.println(productoDetalle);

		modelo.addAttribute("productoDetalle", productoDetalle);
		
		return "detail";
	}
	
	@GetMapping ("/añadirCarrito/{idProd}")
	public String añadirCarrito (Model modelo, @PathVariable("idProd") int id) {
		
		System.out.println("llamando a controlador añadirCarrito");
		
		productoService.cargarCategorias(modelo);
		
		Productos productoCesta = productoDao.getProductoById(id);
		
		System.out.println(productoCesta);
		
		Usuarios u = (Usuarios) modelo.getAttribute("user");
		
		if (modelo.getAttribute("user")==null) {
			if (modelo.getAttribute("carrito")==null) {
				ArrayList<Productos> carrito = new ArrayList<Productos>();
				carrito.add(productoCesta);
				modelo.addAttribute("carrito", carrito);
			} else {
				ArrayList<Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
				carrito.add(productoCesta);
				modelo.addAttribute("carrito", carrito);
			}

		} else {
			productoService.insertOrUpdateProdCarrito(productoCesta, u);
		}
		
		
		
		return "shop";
	}

}
