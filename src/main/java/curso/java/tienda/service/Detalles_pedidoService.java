package curso.java.tienda.service;

import org.springframework.ui.Model;

public interface Detalles_pedidoService {
	
	public void getDetallesPedidoById(Model modelo, int id);
	
	public void insertDetallesPedido(Model modelo);

	public void modificarEstadoCliente(int id);

}
