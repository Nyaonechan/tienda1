package curso.java.tienda.service;

import org.springframework.ui.Model;

import curso.java.tienda.entities.Configuracion;

public interface ConfiguracionService {
	
	public String generarFactura();
	
	public void aumentarNumFactura();
	
	public void recogerDatosEmpresa(Model modelo);
	
	public void getConfiguraciones(Model modelo);
	
	public void getConfiById(Model modelo, int id);
	
	public void insertConfiguracion(Configuracion configuracion);

}
