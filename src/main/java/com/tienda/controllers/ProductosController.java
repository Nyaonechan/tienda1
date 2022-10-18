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


@SessionAttributes({"categorias", "user"})
@Controller
public class ProductosController {
	
	@Autowired
	private ProductosDao productoDao;
	
	@GetMapping("/shop")
	public String listaProductos(Model modelo) {
		System.out.println("llamando a controlador shop");
	
		ArrayList<Productos> productos=productoDao.getProductos();

		modelo.addAttribute("productos", productos);
		
		return "shop";
	}
	
	@GetMapping("/shop/{id}")
    public String shop(@PathVariable("id") int id_categoria, Model modelo) {
		
		System.out.println("llamando a controlador shop/id");
		
		ArrayList<Productos> productos=productoDao.getProductosByCat(id_categoria);
        
        modelo.addAttribute("productos", productos);
        
        productos.forEach(System.out::println);

        return "shop";
    }
	
	@GetMapping ("/detail/{idProd}")
	public String detail (Model modelo, @PathVariable("idProd") int id) {
		
		System.out.println("llamando a controlador detail");
		
		Productos productoDetalle = productoDao.getProductoById(id);
		
		System.out.println(productoDetalle);

		modelo.addAttribute("productoDetalle", productoDetalle);
		
		return "detail";
	}
	
	@GetMapping ("/añadirCarrito/{prodId}")
	public String añadirCarrito (Model modelo, @PathVariable("idProd") int id) {
		
		System.out.println("llamando a controlador añadirCarrito");
		
		Productos productoCesta = productoDao.getProductoById(id);
		
		System.out.println(productoCesta);
		
		
		
		return "shop";
	}

}
