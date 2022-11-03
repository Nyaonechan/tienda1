package curso.java.tienda.dao;

import java.util.ArrayList;

import curso.java.tienda.entities.Valoraciones;

public interface ValoracionesDao {
	
	public ArrayList<Valoraciones> getValoraciones();
	
	public void insertValoracion(Valoraciones valoracion);

}
