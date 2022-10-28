package curso.java.tienda.dao;

import java.util.ArrayList;

import curso.java.tienda.entities.Opciones_menu;

public interface Opciones_menuDao {
	
	public ArrayList<Opciones_menu> getOpciones();
	
	public ArrayList<Opciones_menu> getOpcionesById(int id);

}
