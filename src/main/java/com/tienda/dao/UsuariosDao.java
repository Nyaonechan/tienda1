package com.tienda.dao;

import com.tienda.entities.Usuarios;

public interface UsuariosDao {

	public Usuarios getPersonaByEmailAndPass(String email, String clave);

	public void insertarUsuario(Usuarios usuario);
	
	public void modificarUsuarioById(int id);
	
	

}
