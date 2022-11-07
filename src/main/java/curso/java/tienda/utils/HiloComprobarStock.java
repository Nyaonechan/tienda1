package curso.java.tienda.utils;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import curso.java.tienda.dao.ProductosDao;
import curso.java.tienda.entities.Articulos_carrito;
import curso.java.tienda.service.ProductosService;

@Component
public class HiloComprobarStock implements Runnable{
	
	@Autowired
	ProductosDao productoDao;
	
	@Autowired
	ProductosService productoService;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		do {
			
			
		}while(true);
		
	}
	
	public boolean contrastarStock(int id_producto) {
		
		boolean bandera = false;
		
		// sacar de articulos carrito los productos con ese id
		ArrayList<Articulos_carrito> carrito = productoDao.getProductosCarritoTablaByIdProducto(id_producto);
		
		// sumar las cantidades
		int cantidad = 0;
		for (Articulos_carrito e: carrito) {	
			cantidad+=e.getCantidad();
		}
		//contrastar con stock del producto
		//si es menor el stock mensaje modal a los usuarios
		
		if (productoService.compararCantidadConStock(cantidad, id_producto)) {
			bandera = true;
		}
		
		return bandera;
	}

}
