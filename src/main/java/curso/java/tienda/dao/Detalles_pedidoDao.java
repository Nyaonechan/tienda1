package curso.java.tienda.dao;

import java.util.ArrayList;

import curso.java.tienda.entities.Detalles_pedido;

public interface Detalles_pedidoDao {
	
	public ArrayList<Detalles_pedido> getDetallesByIdPedido(int id);

}
