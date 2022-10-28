package curso.java.tienda.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import curso.java.tienda.dao.PedidosDao;
import curso.java.tienda.dao.impl.PedidosDaoImpl;
import curso.java.tienda.entities.Detalles_pedido;
import curso.java.tienda.entities.Metodos_pago;
import curso.java.tienda.entities.Pedidos;
import curso.java.tienda.entities.Productos;
import curso.java.tienda.entities.Usuarios;
import curso.java.tienda.service.ConfiguracionService;
import curso.java.tienda.service.PedidosService;

@Service
public class PedidosServiceImpl implements PedidosService{
	
	@Autowired
	PedidosDao pedidoDao;
	@Autowired
	ConfiguracionService configuracionService;
	
	@Transactional
	@Override
	public void insertPedido(Usuarios user, String metodoPago, double total) {
		
		Pedidos pedido = new Pedidos(user.getId(),LocalDate.now(), metodoPago, "P.E.", total); //null
		
		System.out.println(pedido.getId());
		
		pedidoDao.insertPedido(pedido);
		
	}

	@Override
	public void insertDetallesPedido(Model modelo) {
		
		Pedidos pedido = pedidoDao.getLastPedido();
		
		Detalles_pedido detallePedido;
		
		ArrayList <Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
		
		for (Productos e: carrito) {
			
			detallePedido = new Detalles_pedido(pedido.getId(), e.getId(), e.getCantidad(), (float)e.getPrecio(), e.getImpuesto(), e.getPrecio()*e.getCantidad());
			
			pedidoDao.insertDetallePedido(detallePedido);
			
		}
		
	}
	
	@Override
	public void eliminarArticulosCarritoById () {
		
		Pedidos pedido = pedidoDao.getLastPedido();
		
		pedidoDao.eliminarArticulosCarritoByIdPedido(pedido.getId_usuario());
		
	}

	@Override
	public void modificarStock(Model modelo) {
		
		ArrayList<Productos> productos = (ArrayList<Productos>) modelo.getAttribute("carrito");
		
		for (Productos e: productos) {
			pedidoDao.modificarStock(e.getCantidad(), e.getId());
		}
		
	}
	
	@Override
	public void getPedidos(Model modelo) {
		//PedidosDao pedidoDao = new PedidosDaoImpl();
		ArrayList<Pedidos> pedidos=pedidoDao.getPedidos();
		modelo.addAttribute("pedidos", pedidos);
	}

	@Override
	public void getPedidosByIdUsuario(int id_usuario, Model modelo) {
		
		ArrayList<Pedidos> pedidos= pedidoDao.getPedidosByIdUsuario(id_usuario);
		
		modelo.addAttribute("pedidos", pedidos);
		
	}
	
	@Transactional
	@Override
	public void modificarEstadoPedidoAdmin(int id) {
		
		Pedidos pedido = pedidoDao.getPedidoById(id);
		System.out.println(pedido);
		if (pedido.getEstado().equals("P.E."))pedidoDao.modificarEstadoAdminEnviado(id);
		else pedidoDao.modificarEstadoAdminCancelado(id);
		

		
	}
	
	@Transactional
	@Override
	public void establecerFactura(int id) {
		Pedidos pedido = pedidoDao.getPedidoById(id);
		System.out.println(pedido);
		//if (pedido.getEstado().equals("E")) {
			
			String factura = configuracionService.generarFactura();
			pedidoDao.establecerNumFactura(id, factura);
			configuracionService.aumentarNumFactura();
		//}
	}
	
	@Transactional
	@Override
	public void modificarEstadoPedidoCliente(int id) {
		pedidoDao.modificarEstadoCliente(id);
	}
	
	@Override
	public void getMetodosPago(Model modelo) {
		
		ArrayList<Metodos_pago> metodos = pedidoDao.getMetodosPago();
		modelo.addAttribute("metodos", metodos);
	}

}
