package curso.java.tienda.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import curso.java.tienda.dao.ProductosDao;
import curso.java.tienda.entities.Articulos_carrito;
import curso.java.tienda.entities.Categorias;
import curso.java.tienda.entities.Productos;
import curso.java.tienda.entities.Usuarios;
import curso.java.tienda.service.ProductosService;

@Service
public class ProductosServiceIml implements ProductosService {
	
	@Autowired
	ProductosDao productoDao;
	
	@Transactional
	@Override
	public void insertProducto(Productos producto) {
		
		producto.setBaja(false);
		producto.setFecha_alta(LocalDate.now());
		productoDao.insertOrUpdateProducto(producto);
	}
	
	@Transactional
	@Override
	public void darBajaProducto(int id) {
		productoDao.darBajaProducto(id);
	}
	
	// Filtros
	
	@Override
	public void getProductoById(int id, Model modelo) {
		Productos producto =productoDao.getProductoById(id);
		modelo.addAttribute("producto", producto);
		
	}

	@Override
	public void filtroPorPrecio(Model modelo, Integer categoria, String precio, String orden) {
		ArrayList<Productos> productos;

		if (categoria==0) {
			switch(precio) {
			case "0":
				if (orden.equals("asc")) productos=productoDao.getProductosByPrecioSinCatAsc();
				else productos=productoDao.getProductosByPrecioSinCatDesc();
				modelo.addAttribute("productos", productos);
				break;
			case "1":
				if (orden.equals("asc")) productos=productoDao.getProductosByPrecioSinCatAsc(0, 50);
				else productos=productoDao.getProductosByPrecioSinCatDesc(0, 50);
				modelo.addAttribute("productos", productos);
				break;
			case "2":
				if (orden.equals("asc")) productos=productoDao.getProductosByPrecioSinCatAsc(50.01, 100);
				else productos=productoDao.getProductosByPrecioSinCatDesc(50.01, 100);
				modelo.addAttribute("productos", productos);
				break;
			case "3":
				if (orden.equals("asc")) productos=productoDao.getProductosByPrecioSinCatAsc(100.01, 150);
				else productos=productoDao.getProductosByPrecioSinCatDesc(100.01, 150);
				modelo.addAttribute("productos", productos);
				break;
			}
		}else {
			
			switch(precio) {
			case "0":
				if (orden.equals("asc")) productos=productoDao.getProductosByPrecioConCatAsc(categoria);
				else productos=productoDao.getProductosByPrecioConCatDesc(categoria);
				modelo.addAttribute("productos", productos);
				break;
			case "1":
				if (orden.equals("asc")) productos=productoDao.getProductosByPrecioConCatAsc(0, 50, categoria);
				else productos=productoDao.getProductosByPrecioConCatDesc(0, 50, categoria);
				modelo.addAttribute("productos", productos);
				break;
			case "2":
				if (orden.equals("asc")) productos=productoDao.getProductosByPrecioConCatAsc(50.01, 100, categoria);
				else productos=productoDao.getProductosByPrecioConCatDesc(50.01, 100, categoria);
				modelo.addAttribute("productos", productos);
				break;
			case "3":
				if (orden.equals("asc")) productos=productoDao.getProductosByPrecioConCatAsc(100.01, 150, categoria);
				else productos=productoDao.getProductosByPrecioConCatDesc(100.01, 150, categoria);
				modelo.addAttribute("productos", productos);
				break;
			}
			
		}
		
	}
	
	@Override
	public void getProductosByNombre(String nombre, Model modelo) {
		
		ArrayList<Productos> productos = productoDao.getProductosByNombre(nombre);
		modelo.addAttribute("productos", productos);
		
	}

	@Override
	public ArrayList<Productos> ordenarProductosByFecha() {
		
		ArrayList<Productos> productos = productoDao.getProductos();
		
		productos.sort((p1,p2) -> p2.getFecha_alta().compareTo(p1.getFecha_alta()));

		return productos;
	}
	
	@Override
	public void ochoProductosMasNuevos(Model modelo) {
		ArrayList<Productos> productos = ordenarProductosByFecha();
		ArrayList<Productos> masNuevos = new ArrayList<Productos>();
		
		for (int i=0; i<8;i++) {
			masNuevos.add(productos.get(i));
		}
		
		modelo.addAttribute("productos", masNuevos);
		
	}
	
	@Override
	public void ochoProductosMasVendidos(Model modelo) {
		ArrayList<Productos> productos = productoDao.getProductosByStock();
		ArrayList<Productos> masVendidos = new ArrayList<Productos>();
		
		for (int i=0; i<8;i++) {
			masVendidos.add(productos.get(i));
		}
		
		modelo.addAttribute("masVendidos", masVendidos);
	}
	
	/*@Override
	public void cantidadProductosCat(int id, Model modelo) { // de la categoria
		
		ArrayList<Productos> productos = productoDao.getProductosByCat(id);
		
		int cantidad = productos.size();
		
		modelo.addAttribute("cantidadCat", cantidad);

	}*/
	
	@Override
	public void cargarProductos(Model modelo) {
		ArrayList<Productos> productos=productoDao.getProductos();
		modelo.addAttribute("productos", productos);	
	}
	
	@Override
	public void cargarProductosByIdCat(Model modelo, int id_categoria) {
		ArrayList<Productos> productos=productoDao.getProductosByCat(id_categoria);
        
        modelo.addAttribute("productos", productos);
        
        productos.forEach(System.out::println);
	}
	
	@Override
	public void cargarProductoById(Model modelo, int id) {
		Productos productoDetalle = productoDao.getProductoById(id);
		
		System.out.println(productoDetalle);

		modelo.addAttribute("productoDetalle", productoDetalle);
	}
	
	

	@Override
	public void insertOrUpdateProdCarrito(int id, Usuarios user) {
		if(productoDao.comprobarProdCarritoById(id, user)) {
			productoDao.aumentarProdCarrito(id, user);
		}else {
			productoDao.insertProdCarrito(id, user.getId());
		}
		
	}
	
	@Override
	public void carritoUserNull(Model modelo, Productos productoCesta) { //si se navega sin login
		if (modelo.getAttribute("carrito")==null) {
			ArrayList<Productos> carrito = new ArrayList<Productos>();
			productoCesta.setCantidad(1);
			carrito.add(productoCesta);
			modelo.addAttribute("carrito", carrito);
		} else {
			ArrayList<Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
			if (!comprobarProductoCarrito(carrito, productoCesta.getId())) {
				productoCesta.setCantidad(1);
				carrito.add(productoCesta);
				modelo.addAttribute("carrito", carrito);
			}else {
				aumentarCantidadCarritoSession(carrito, productoCesta.getId());
			}
		}
	}
	
	@Override
	public boolean comprobarProductoCarrito(ArrayList <Productos> carrito, int id) {
		boolean bandera = false;
		
		for (Productos e:carrito) {
			if (e.getId()==id) {
				//e.setCantidad(e.getCantidad()+1);
				bandera = true;
			}
		}
		
		return bandera;
	}
	
	// CARRITO LISTA

	@Override
	public void aumentarCantidadCarritoSession(ArrayList <Productos> carrito, int id) {
		
		for (Productos e: carrito) {
			if (e.getId()==id) e.setCantidad(e.getCantidad()+1);
		}
		
	}

	@Override
	public void descenderCantidadCarritoSession(ArrayList <Productos> carrito, int id) {
		
		Optional<Productos> optional = carrito.stream().filter(n -> n.getId()==id).findFirst();
		
		Productos p = optional.get();
		
		p.setCantidad(p.getCantidad()-1);
		
		carrito.removeIf(n -> (n.getCantidad()==0));
		
	}

	@Override
	public void eliminarProductoCarritoSession(ArrayList<Productos> carrito, int id) {
		
		carrito.removeIf(n -> (n.getId()==id));
		
	}
	
	// CARRITO TABLA
	
	@Override
	public void aumentarCantidadCarritoTabla(int id, Usuarios user) {
		
		productoDao.aumentarProdCarrito(id, user);
		
	}
	
	@Override
	public void descenderCantidadCarritoTabla(int id, Usuarios user) {
		
		productoDao.descenderProdCarrito(id, user);
		productoDao.eliminarProdCarritoCantidadCero();
		
	}
	
	@Override
	public void eliminarProductoCarritoTabla (int id, Usuarios user) {
		
		productoDao.eliminarProdCarrito(id, user);
		
	}

	@Override
	public void insertarProductosListaCarritoATabla(Usuarios user, Model modelo) {
		
		if (!productoDao.comprobarProdCarritoTablaVacia(user)){
			
			ArrayList<Productos> carrito;
			
			if (modelo.getAttribute("carrito")==null) {
				 carrito = new ArrayList<Productos>();
				 modelo.addAttribute("carrito", carrito);
			}
			carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
			
			for (Productos e: carrito) {
				productoDao.insertProdCarrito(e.getId(), user.getId());
			}
			
			//modelo.addAttribute("carrito", null);
			
		}
		
	}
	
	@Override
	public ArrayList<Productos> getProductosCarritoTabla(Usuarios user){
		
		ArrayList<Productos> carritoOficial = productoDao.getProductosCarritoTablaCruzada(user);
		ArrayList<Articulos_carrito> carritoTabla = productoDao.getProductosCarritoTabla(user);
		
		for  (int i=0; i<carritoOficial.size();i++) {
			
			carritoOficial.get(i).setCantidad(carritoTabla.get(i).getCantidad());
			
		}
		
		return carritoOficial;
		
	}
	
	@Override
	public void cantidadCarro (Model modelo) {
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		int cantidad = 0;
		
		if (user ==null) {
			ArrayList<Productos> carrito;
			
			if (modelo.getAttribute("carrito")==null) {
				 carrito = new ArrayList<Productos>();
				 modelo.addAttribute("carrito", carrito);
			}
			
			carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");

			for (Productos e: carrito) {
				cantidad+=e.getCantidad();
			}
		}else {
			
			ArrayList<Articulos_carrito> productos =productoDao.getProductosCarritoTabla(user);
			
			for (Articulos_carrito e: productos) {
				cantidad+=e.getCantidad();
			}
		}
		
		modelo.addAttribute("cantidad", cantidad);
		
	}
	
	@Override
	public double precioTotalCarro (Model modelo) {
		
		Usuarios user = (Usuarios) modelo.getAttribute("user");
		float precioTotal=0;
		
		if (user ==null) {
			ArrayList<Productos> carrito;
			
			if (modelo.getAttribute("carrito")==null) {
				 carrito = new ArrayList<Productos>();
				 modelo.addAttribute("carrito", carrito);
			}
			
			carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");

			for (Productos e: carrito) {
				precioTotal+=e.getCantidad()*e.getPrecio();
			}
		}else {
			ArrayList<Productos> productos =productoDao.getProductosCarritoTablaCruzada(user);
			ArrayList<Articulos_carrito> articulos=productoDao.getProductosCarritoTabla(user);
			
			for (int i=0; i<productos.size();i++) {
				productos.get(i).setCantidad(articulos.get(i).getCantidad());
			}
			
			for (Productos e: productos) {
				precioTotal+=e.getCantidad()*e.getPrecio();
			}
		}
		
		
		modelo.addAttribute("precioTotal", precioTotal);
		return precioTotal;
		
	}
	
	@Override
	public void desgloseIva(Model modelo, double precioTotal) {
		
		double precioSinIva = precioTotal / 1.21;
		modelo.addAttribute("precioSinIva", precioSinIva);
	}
	
	// comprobar comprados
	
	@Override
	public boolean comprobarComprados(int producto_id, int id_usuario) {
		boolean bandera=false;
		if(productoDao.productosComprados(producto_id, id_usuario)!=null) {
			bandera = true;
		}
		return bandera;
	}

	@Override
	public Productos getProductoById(int id_producto) {
		
		return productoDao.getProductoById(id_producto);
	}
	
	//comparar cantidad y stock

	@Override
	public boolean compararCantidadConStock(int cantidad, int id_producto) {
		
		boolean bandera = false;
		
		Productos producto = productoDao.getProductoById(id_producto);
		
		if (cantidad>producto.getStock()) {
			bandera=true;
		}
		
		return bandera;
	}


}
