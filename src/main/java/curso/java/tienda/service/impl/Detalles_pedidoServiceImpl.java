package curso.java.tienda.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import curso.java.tienda.dao.Detalles_pedidoDao;
import curso.java.tienda.dao.PedidosDao;
import curso.java.tienda.entities.Detalles_pedido;
import curso.java.tienda.entities.Pedidos;
import curso.java.tienda.entities.Productos;
import curso.java.tienda.service.Detalles_pedidoService;

@Service
public class Detalles_pedidoServiceImpl implements Detalles_pedidoService {
	
	@Autowired
	Detalles_pedidoDao detalle_pedidoDao;
	@Autowired
	PedidosDao pedidoDao;

	@Override
	public void getDetallesPedidoById(Model modelo, int id) {
		
		ArrayList<Detalles_pedido> detalles = detalle_pedidoDao.getDetallesByIdPedido(id);
		
		modelo.addAttribute("detalles", detalles);
		
	}
	
	@Override
	public void insertDetallesPedido(Model modelo) {
		
		Pedidos pedido = pedidoDao.getLastPedido();
		
		Detalles_pedido detallePedido;
		
		ArrayList <Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
		
		for (Productos e: carrito) {
			
			detallePedido = new Detalles_pedido(pedido.getId(), e.getId(), e.getCantidad(), (float)e.getPrecio(), e.getImpuesto(), e.getPrecio()*e.getCantidad(), pedido.getEstado());
			
			detalle_pedidoDao.insertDetallePedido(detallePedido);
			
		}
	}

	@Override
	public void modificarEstadoCliente(int id) {
		
		detalle_pedidoDao.modificarEstadoCliente(id);
		
	}

}
