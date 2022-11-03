package curso.java.tienda.service;

import org.springframework.ui.Model;

import curso.java.tienda.entities.Valoraciones;

public interface ValoracionesService {
	
	public void getValoraciones(Model modelo);
	
	public void insertValoracion(Valoraciones valoracion);

}
