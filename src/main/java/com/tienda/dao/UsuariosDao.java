package com.tienda.dao;

import com.tienda.entities.Usuarios;

public interface UsuariosDao {

	Usuarios getPersonaByEmailAndPass(String email, String clave);

	void insertarUsuario(Usuarios usuario);
	
	

}
