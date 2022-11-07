package curso.java.tienda.service;

import org.springframework.ui.Model;

import curso.java.tienda.entities.Categorias;

public interface CategoriasService {
	
	public void cargarCategorias(Model modelo);
	
	public void insertarCategoria(Categorias categoria);
	
	public Categorias getCategoriaById(int id);

}
