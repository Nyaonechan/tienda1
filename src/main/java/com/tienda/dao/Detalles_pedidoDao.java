package com.tienda.dao;

import java.util.ArrayList;

import com.tienda.entities.Detalles_pedido;

public interface Detalles_pedidoDao {
	
	public ArrayList<Detalles_pedido> getDetallesByIdPedido(int id);

}
