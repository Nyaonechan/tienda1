package curso.java.tienda.service;

import org.springframework.ui.Model;

import curso.java.tienda.entities.Usuarios;

public interface UsuariosService {
	
	public void getUsuariosByRol(int id, Model modelo);
	
	public void getUsuariosById (int id, Model modelo);
	
	public void insertUsuario(Usuarios usuario, int rol);
	
	public void darBajaUsuarioById(int id);
	
	public boolean comprobarPass(String email, String clave, Usuarios user);

}
