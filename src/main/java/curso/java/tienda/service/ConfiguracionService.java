package curso.java.tienda.service;

import org.springframework.ui.Model;

public interface ConfiguracionService {
	
	public String generarFactura();
	
	public void aumentarNumFactura();
	
	public void recogerDatosEmpresa(Model modelo);

}
