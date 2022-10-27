package com.tienda.dao;

import java.util.ArrayList;

import com.tienda.entities.Detalles_pedido;
import com.tienda.entities.Metodos_pago;
import com.tienda.entities.Pedidos;

public interface PedidosDao {
	
	public ArrayList<Pedidos> getPedidos();
	
	public void insertPedido (Pedidos pedido);
	
	public Pedidos getPedidoById(int id);
	
	public ArrayList<Pedidos> getPedidosByIdUsuario(int id_usuario);
	
	public Pedidos getLastPedido();
	
	//Estado
	
	public void modificarEstadoAdmin(int id, String estado);
	
	public void modificarEstadoCliente(int id);
	
	//Factura
	
	public Pedidos getLastFactura();
	
	public void establecerNumFactura(int id);
	
	// ---
	
	public void insertDetallePedido(Detalles_pedido detallePedido); // mover a detalles
	
	public ArrayList<Detalles_pedido> getDetallesPedidosByIdPedido(int id); // mover a detalles
	
	public void eliminarArticulosCarritoByIdPedido(int id);
	
	public void modificarStock(int cantidad, int id_producto);
	
	public ArrayList<Metodos_pago> getMetodosPago();

}
