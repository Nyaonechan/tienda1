package curso.java.tienda.dao;

import curso.java.tienda.entities.Configuracion;

public interface ConfiguracionDao {
	
	public Configuracion getByClave(String clave);
	
	public void insertConfiguracion(Configuracion configuracion);

}
