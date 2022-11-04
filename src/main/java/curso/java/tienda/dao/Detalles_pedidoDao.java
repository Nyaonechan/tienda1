package curso.java.tienda.dao;

import java.util.ArrayList;

import curso.java.tienda.entities.Detalles_pedido;

public interface Detalles_pedidoDao {
	
	public ArrayList<Detalles_pedido> getDetallesByIdPedido(int id);
	
	public void insertDetallePedido(Detalles_pedido detallePedido); 
	
	public Detalles_pedido getDetalleById(int id);
	
	public void modificarEstadoCliente (int id);
	
	public void modificarEstadoAdminCanc (int id);
	public void modificarEstadoAdminEnv(int id);
	
	public ArrayList<Detalles_pedido> getDetallesByIdAndEstado(int id, String estado);

}
