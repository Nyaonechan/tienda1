package curso.java.tienda.dao;

import java.util.ArrayList;

import curso.java.tienda.entities.Detalles_pedido;

public interface Detalles_pedidoDao {
	
	public ArrayList<Detalles_pedido> getDetallesByIdPedido(int id);
	
	public void insertDetallePedido(Detalles_pedido detallePedido); 
	
	public ArrayList<Detalles_pedido> getDetallesPedidosByIdPedido(int id); 
	
	public void modificarEstadoCliente (int id);

}
