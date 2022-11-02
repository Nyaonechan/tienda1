package curso.java.tienda.dao;

import java.util.ArrayList;

import curso.java.tienda.entities.Usuarios;

public interface UsuariosDao {

	public Usuarios getPersonaByEmail(String email);

	public void insertarUsuario(Usuarios usuario);
	
	public Usuarios getUsuarioById(int id);
	
	public ArrayList<Usuarios> getUsuariosPorRol(int id);
	
	public void darBajaUsuarioById(int id);
	
	

}
