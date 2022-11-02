package curso.java.tienda.dao;

import java.util.ArrayList;

import curso.java.tienda.entities.Categorias;

public interface CategoriasDao {
	
	public void insertarCategoria(Categorias categoria);
	
	public ArrayList<Categorias> getCategorias();

}
