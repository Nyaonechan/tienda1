package curso.java.tienda.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import curso.java.tienda.dao.ProductosDao;
import curso.java.tienda.dao.ValoracionesDao;
import curso.java.tienda.entities.Productos;
import curso.java.tienda.entities.Valoraciones;
import curso.java.tienda.service.ValoracionesService;

@Service
public class ValoracionesServiceImpl implements ValoracionesService{
	
	@Autowired
	ValoracionesDao valoracionDao;
	
	@Autowired
	ProductosDao productoDao;

	@Override
	public void getValoraciones(Model modelo) {
		
		modelo.addAttribute("valoraciones", valoracionDao.getValoraciones());
		
	}
	
	@Override
	public void getValoracionesByIdProducto(int idProd, Model modelo) {
		
		modelo.addAttribute("valoraciones", valoracionDao.getValoracionesByIdProducto(idProd));
		
	}

	@Override
	public void insertValoracion(Valoraciones valoracion) {
		valoracion.setFecha(LocalDate.now());
		valoracionDao.insertValoracion(valoracion);
		
	}

	@Override
	public void calcularValoracionMedia(int idProd) {
		
		ArrayList<Valoraciones> valoraciones = valoracionDao.getValoracionesByIdProducto(idProd);
		
		int valoracionMedia = 0;
		
		for (Valoraciones e:valoraciones) {
			
			valoracionMedia+=e.getValoracion();
			
		}
		
		valoracionMedia/=valoraciones.size();
		
		Productos producto= productoDao.getProductoById(idProd);
		
		producto.setValoracion_media(valoracionMedia);
		
		productoDao.insertOrUpdateProducto(producto);
		
	}
	
	



}
