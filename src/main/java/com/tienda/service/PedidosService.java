package com.tienda.service;

import org.springframework.ui.Model;

import com.tienda.entities.Usuarios;

public interface PedidosService {
	
	public void insertPedido (Usuarios user, String metodoPago, double total);
	
	public void insertDetallesPedido(Model modelo);
	
	public void eliminarArticulosCarritoById ();
	
	public void modificarStock(Model modelo);
	
	public void getPedidos(Model modelo);
	
	public void getPedidosByIdUsuario(int id_usuario, Model modelo);
	
	public void modificarEstadoPedidoAdmin(int id);
	
	public void modificarEstadoPedidoCliente(int id);
	
	public void establecerFactura(int id);
	
	public void getMetodosPago(Model modelo);

}
