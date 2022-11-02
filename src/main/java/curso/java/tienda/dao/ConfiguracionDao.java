package curso.java.tienda.dao;

import java.util.ArrayList;

import curso.java.tienda.entities.Configuracion;

public interface ConfiguracionDao {
	
	public Configuracion getConfiById(int id);
	
	public Configuracion getByClave(String clave);
	
	public ArrayList<Configuracion> getConfiguraciones();
	
	public void insertConfiguracion(Configuracion configuracion);

}
