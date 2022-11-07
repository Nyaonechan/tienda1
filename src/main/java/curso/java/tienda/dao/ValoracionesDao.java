package curso.java.tienda.dao;

import java.util.ArrayList;

import curso.java.tienda.entities.Valoraciones;

public interface ValoracionesDao {
	
	public ArrayList<Valoraciones> getValoraciones();
	
	public ArrayList<Valoraciones> getValoracionesByIdProducto(int idProd);
	
	public void insertValoracion(Valoraciones valoracion);

}
