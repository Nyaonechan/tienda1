package curso.java.tienda.dao;

import java.util.ArrayList;

import curso.java.tienda.entities.Descuentos;

public interface DescuentosDao {
	
	public ArrayList<Descuentos> getDescuentos();
	
	public Descuentos getDescuentoById(int id);
	
	public void insertDescuento(Descuentos descuento);
	
	public Descuentos getDescuentoByCodigo(String codigo);

}
