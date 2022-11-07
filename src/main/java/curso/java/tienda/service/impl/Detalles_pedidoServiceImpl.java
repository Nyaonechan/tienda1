package curso.java.tienda.service.impl;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import curso.java.tienda.dao.Detalles_pedidoDao;
import curso.java.tienda.dao.PedidosDao;
import curso.java.tienda.dao.ProductosDao;
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
	@Autowired
	ProductosDao productoDao;

	@Override
	public void getDetallesPedidoByIdPedido(Model modelo, int id_pedido) {
		
		ArrayList<Detalles_pedido> detalles = detalle_pedidoDao.getDetallesByIdPedido(id_pedido);
		
		modelo.addAttribute("detalles", detalles);
		
	}
	
	@Override
	public void insertDetallesPedido(Model modelo) {
		
		Pedidos pedido = pedidoDao.getLastPedido();
		
		Detalles_pedido detallePedido;
		
		ArrayList <Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
		
		for (Productos e: carrito) {
			
			detallePedido = new Detalles_pedido(pedido.getId(), e, e.getCantidad(), (float)e.getPrecio(), e.getImpuesto(), e.getPrecio()*e.getCantidad(), pedido.getEstado());
			
			detalle_pedidoDao.insertDetallePedido(detallePedido);
			
		}
	}
	
	@Transactional
	@Override
	public void modificarEstadoCliente(int id) {
		
		detalle_pedidoDao.modificarEstadoCliente(id);
		
	}
	
	@Transactional
	@Override
	public void modificarEstadoAdminCanc(int id) {
		
		detalle_pedidoDao.modificarEstadoAdminCanc(id);
		
	}
	
	public void modificarEstadoAdminEnv (int id) {
		
		detalle_pedidoDao.modificarEstadoAdminEnv(id);
		
	}
	
	@Transactional
	@Override
	public void restablecerStockCancelado(int id_detalle) {
		
		Detalles_pedido detalle = detalle_pedidoDao.getDetalleById(id_detalle);
		
		productoDao.modificarStock(detalle.getProducto().getId(), detalle.getUnidades());
		
	}

	@Override
	public boolean comprobarEstadoDetalle(int id_pedido) {
		
		boolean bandera = true;
		ArrayList<Detalles_pedido> detalles = detalle_pedidoDao.getDetallesByIdPedido(id_pedido);
		
		for (Detalles_pedido e: detalles) {
			if (e.getEstado().equals("P.C.")) {
				bandera = false;
			}
		}
		
		return bandera;
	}

	@Override
	public void cantidadTotalProductos(Model modelo) {
		
		int unidades=0;
		
		for (Detalles_pedido e:detalle_pedidoDao.getDetalles()) {
			unidades+=e.getUnidades();
		}
		
		modelo.addAttribute("unidades", unidades);
		
	}

}
