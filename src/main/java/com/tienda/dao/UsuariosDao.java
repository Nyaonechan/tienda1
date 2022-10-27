package com.tienda.dao;

import java.util.ArrayList;

import com.tienda.entities.Usuarios;

public interface UsuariosDao {

	public Usuarios getPersonaByEmailAndPass(String email, String clave);

	public void insertarUsuario(Usuarios usuario);
	
	public Usuarios getUsuarioById(int id);
	
	public ArrayList<Usuarios> getUsuariosPorRol(int id);
	
	public void darBajaUsuarioById(int id);
	
	

}
