package curso.java.tienda.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import curso.java.tienda.dao.CategoriasDao;
import curso.java.tienda.entities.Categorias;
import curso.java.tienda.service.CategoriasService;

@Service
public class CategoriasServiceImpl implements CategoriasService{
	
	@Autowired
	CategoriasDao categoriaDao;
	
	
	@Override
	public void cargarCategorias(Model modelo) {
		ArrayList<Categorias> categorias = categoriaDao.getCategorias();
		modelo.addAttribute("categorias", categorias);
		
	}

	@Override
	public void insertarCategoria(Categorias categoria) {
		
		categoriaDao.insertarCategoria(categoria);
		
	}

	@Override
	public Categorias getCategoriaById(int id) {
		Categorias categoria = categoriaDao.getCategoriaById(id);
		return categoria;
		
	}

}
