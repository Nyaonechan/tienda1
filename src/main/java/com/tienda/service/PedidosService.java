package com.tienda.service;

import org.springframework.ui.Model;

import com.tienda.entities.Usuarios;

public interface PedidosService {
	
	public void insertPedido (Usuarios user, String metodoPago, double total);
	
	public void insertDetallesPedido(Model modelo, double total);
	
	public void eliminarArticulosCarritoById ();

}
