package curso.java.tienda.service;

import org.springframework.ui.Model;

import curso.java.tienda.entities.Descuentos;

public interface DescuentosService {
	
	public void getDescuentos(Model modelo);
	
	public void getDescuentoById(Model modelo, int id);
	
	public void insertDescuento(Descuentos descuento);

}
