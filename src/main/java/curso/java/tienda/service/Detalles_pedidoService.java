package curso.java.tienda.service;

import org.springframework.ui.Model;

public interface Detalles_pedidoService {
	
	public void getDetallesPedidoByIdPedido(Model modelo, int id);
	
	public void insertDetallesPedido(Model modelo);

	public void modificarEstadoCliente(int id);
	
	public void modificarEstadoAdminCanc (int id);
	
	public void modificarEstadoAdminEnv (int id);
	
	public void restablecerStockCancelado(int id_detalle);
	
	public boolean comprobarEstadoDetalle(int id_pedido);
	
	public void cantidadTotalProductos(Model modelo);

}
