package com.tienda.dao;

import java.util.ArrayList;

import com.tienda.entities.Opciones_menu;

public interface Opciones_menuDao {
	
	public ArrayList<Opciones_menu> getOpciones();
	
	public ArrayList<Opciones_menu> getOpcionesById(int id);

}
