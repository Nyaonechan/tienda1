package curso.java.tienda.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import curso.java.tienda.dao.Opciones_menuDao;
import curso.java.tienda.entities.Opciones_menu;
import curso.java.tienda.service.Opciones_menuService;

@Service
public class Opciones_menuServiceImpl implements Opciones_menuService{
	
	@Autowired
	Opciones_menuDao opciones_menuDao;

	@Override
	public void getOpciones(Model modelo) {
		
		ArrayList<Opciones_menu> opciones = opciones_menuDao.getOpciones();
		
		modelo.addAttribute("opciones", opciones);
		
	}

	@Override
	public void getOpcionesByIdRol(Model modelo, int id) {
		
		ArrayList<Opciones_menu> opciones = opciones_menuDao.getOpcionesById(id);
		
		modelo.addAttribute("opciones", opciones);
		
	}

	@Override
	public void elegirOpciones(Model modelo,int id_rol) {
		
		if (id_rol==2) getOpcionesByIdRol(modelo, id_rol);
		else getOpciones(modelo);
	}

}
