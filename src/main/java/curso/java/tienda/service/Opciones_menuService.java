package curso.java.tienda.service;

import org.springframework.ui.Model;

public interface Opciones_menuService {
	
	public void getOpciones(Model modelo);
	
	public void getOpcionesByIdRol(Model modelo, int id);
	
	public void elegirOpciones(Model modelo, int id_rol);

}
