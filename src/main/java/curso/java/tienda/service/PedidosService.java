package curso.java.tienda.service;

import org.springframework.ui.Model;

import curso.java.tienda.entities.Usuarios;

public interface PedidosService {
	
	public void insertPedido (Usuarios user, String metodoPago, double total);
	
	public void eliminarArticulosCarritoById ();
	
	public boolean comprobarStock(Model modelo);
	
	public void modificarStock(Model modelo);
	
	public void getPedidos(Model modelo);
	
	public void getPedidosByIdUsuario(int id_usuario, Model modelo);
	
	public void modificarEstadoPedidoAdmin(int id);
	
	public void modificarEstadoPedidoCliente(int id);
	
	public void establecerFactura(int id);
	
	public void getMetodosPago(Model modelo);

}
