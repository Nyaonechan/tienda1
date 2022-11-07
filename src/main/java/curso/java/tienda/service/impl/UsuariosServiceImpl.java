package curso.java.tienda.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import curso.java.tienda.dao.UsuariosDao;
import curso.java.tienda.entities.Usuarios;
import curso.java.tienda.service.UsuariosService;
import curso.java.tienda.utils.Encriptacion;

@Service
public class UsuariosServiceImpl implements UsuariosService {
	
	@Autowired
	UsuariosDao usuarioDao;

	@Override
	public void getUsuariosByRol(int id, Model modelo) {
		
		ArrayList<Usuarios> usuarios = usuarioDao.getUsuariosPorRol(id);
		
		modelo.addAttribute("usuarios", usuarios);
		
	}

	@Override
	public void getUsuariosById(int id, Model modelo) {
		
		Usuarios usuario = usuarioDao.getUsuarioById(id);
		
		String desencriptada = Encriptacion.desencriptar(usuario.getClave());
		
		usuario.setClave(desencriptada);
		
		modelo.addAttribute("usuario", usuario);
		
	}
	
	@Transactional
	@Override
	public void insertUsuario(Usuarios usuario, int rol) {
		
		usuario.setId_rol(rol);
		usuario.setBaja(false);
		usuario.setFecha_alta(LocalDate.now());
		
		usuarioDao.insertarUsuario(usuario);
		
	}

	@Override
	public void darBajaUsuarioById(int id) {
		
		usuarioDao.darBajaUsuarioById(id);
		
	}

	@Override
	public boolean comprobarPass(String email, String clave, Usuarios user) {
		
		boolean bandera= false;
		
		String desencriptada = Encriptacion.desencriptar(user.getClave());
		
		if (desencriptada.equals(clave)) bandera = true;
		
		return bandera;
	}

	@Override
	public Usuarios getUsuarioById(int id_usuario) {
		
		return usuarioDao.getUsuarioById(id_usuario);
	}

	@Override
	public boolean comprobarDefecto(String pass, Model modelo) {

		boolean bandera = false;
		
		if (pass.equals("admin1")|| pass.equals("123456")) {
			
			bandera = true;
			
			modelo.addAttribute("passDefecto", "Debes cambiar la contraseña");
		}
		
		return bandera;
	}
	
	

}
