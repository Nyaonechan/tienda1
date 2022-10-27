package com.tienda.service;

import org.springframework.ui.Model;

import com.tienda.entities.Usuarios;

public interface UsuariosService {
	
	public void getUsuariosByRol(int id, Model modelo);
	
	public void getUsuariosById (int id, Model modelo);
	
	public void insertUsuario(Usuarios usuario, int rol);
	
	public void darBajaUsuarioById(int id);

}
