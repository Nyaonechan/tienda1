package com.tienda.dao;

import com.tienda.entities.Configuracion;

public interface ConfiguracionDao {
	
	public Configuracion getByClave(String clave);
	
	public void insertConfiguracion(Configuracion configuracion);

}
