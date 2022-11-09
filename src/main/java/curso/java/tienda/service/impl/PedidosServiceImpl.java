package curso.java.tienda.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import curso.java.tienda.dao.PedidosDao;
import curso.java.tienda.dao.Detalles_pedidoDao;
import curso.java.tienda.dao.impl.PedidosDaoImpl;
import curso.java.tienda.entities.Descuentos;
import curso.java.tienda.entities.Detalles_pedido;
import curso.java.tienda.entities.Metodos_pago;
import curso.java.tienda.entities.Pedidos;
import curso.java.tienda.entities.Productos;
import curso.java.tienda.entities.Usuarios;
import curso.java.tienda.service.ConfiguracionService;
import curso.java.tienda.service.Detalles_pedidoService;
import curso.java.tienda.service.PedidosService;

@Service
public class PedidosServiceImpl implements PedidosService{
	
	@Autowired
	PedidosDao pedidoDao;
	@Autowired
	Detalles_pedidoDao detalle_pedidoDao;
	@Autowired
	Detalles_pedidoService detalle_pedidoService;
	@Autowired
	ConfiguracionService configuracionService;
	
	@Transactional
	@Override
	public void insertPedido(Usuarios user, String metodoPago, double total, Descuentos descuento) {
		
		Pedidos pedido = new Pedidos(user.getId(),LocalDate.now(), metodoPago, "P.E.", descuento, total); //null
		
		System.out.println(pedido.getId());
		
		pedidoDao.insertPedido(pedido);
		
	}
		
	@Override
	public void eliminarArticulosCarritoById () {
		
		Pedidos pedido = pedidoDao.getLastPedido();
		
		pedidoDao.eliminarArticulosCarritoByIdPedido(pedido.getId_usuario());
		
	}
	
	@Override
	public boolean comprobarStock(Model modelo){
		
		boolean stock = false;
		ArrayList <Productos> carrito = (ArrayList<Productos>) modelo.getAttribute("carrito");
		
		for (Productos e: carrito) {
			if (e.getCantidad()>e.getStock()) {
				stock = true;
				modelo.addAttribute("nombre_producto", e.getNombre());
			}
		}
		
		return stock;
	}

	@Override
	public void modificarStock(Model modelo) {
		
		ArrayList<Productos> productos = (ArrayList<Productos>) modelo.getAttribute("carrito");
		
		for (Productos e: productos) {
			if (e.getStock()>e.getCantidad()) pedidoDao.modificarStock(e.getCantidad(), e.getId());
			// falta el else
		}
		
	}
	
	@Transactional
	@Override
	public void getPedidos(Model modelo) {

		ArrayList<Pedidos> pedidos=pedidoDao.getPedidos();
		modelo.addAttribute("pedidos", pedidos);
	}

	@Override
	public void getPedidosByIdUsuario(int id_usuario, Model modelo) {
		
		ArrayList<Pedidos> pedidos= pedidoDao.getPedidosByIdUsuario(id_usuario);
		
		modelo.addAttribute("pedidos", pedidos);
		
	}
	
	@Override
	public void getPedidoById(int id, Model modelo) {
		
		modelo.addAttribute("pedido", pedidoDao.getPedidoById(id));
		
	}
	
	@Transactional
	@Override
	public void modificarEstadoPedidoAdmin(int id) {
		
		Pedidos pedido = pedidoDao.getPedidoById(id);
		ArrayList<Detalles_pedido> detalles = detalle_pedidoDao.getDetallesByIdPedido(pedido.getId());
		System.out.println(pedido);
		if (pedido.getEstado().equals("P.E.")) {
			pedidoDao.modificarEstadoAdminEnviado(id);
			
			for (Detalles_pedido e: detalles) {
				if (e.getEstado().equals("P.E.")) {
					detalle_pedidoDao.modificarEstadoAdminEnv(id);
				}
			}
			
		}else {
			pedidoDao.modificarEstadoAdminCancelado(id);
			for (Detalles_pedido e: detalles) {
				detalle_pedidoDao.modificarEstadoAdminEnv(id);
			}
		}
		

		
	}
	
	@Transactional
	@Override
	public void establecerFactura(int id) {
		Pedidos pedido = pedidoDao.getPedidoById(id);
		System.out.println(pedido);
		if (pedido.getEstado().equals("E")|| pedido.getEstado().equals("P.E.")) {
			
			String factura = configuracionService.generarFactura();
			pedidoDao.establecerNumFactura(id, factura);
			configuracionService.aumentarNumFactura();
		}
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
	
	@Transactional
	@Override
	public void modificarPedidoTotal(int id, String estado) {
		
		ArrayList<Detalles_pedido> detalles = detalle_pedidoDao.getDetallesByIdAndEstado(id, estado);
		
		double total=0;
		for(Detalles_pedido e: detalles) {
			total+=e.getTotal();
		}
		
		pedidoDao.modificarTotalPedido(id, total);
		
	}

	@Override
	public Pedidos getLastPedido() {
		
		return pedidoDao.getLastPedido();
	}

	@Override
	public void totalPedidos(Model modelo) {
		
		double total = 0;
		
		for(Pedidos e: pedidoDao.getPedidos()) {
			total+=e.getTotal();
		}
		
		modelo.addAttribute("total", total);
		
	}

	@Override
	public void getPedidosGroupUsuarioId(Model modelo) {
		
		modelo.addAttribute("usuariosCompras", pedidoDao.getPedidosGroupUsuario());
		
	}

}
