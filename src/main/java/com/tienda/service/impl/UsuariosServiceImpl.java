package com.tienda.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.tienda.dao.UsuariosDao;
import com.tienda.entities.Usuarios;
import com.tienda.service.UsuariosService;

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
	
	

}
