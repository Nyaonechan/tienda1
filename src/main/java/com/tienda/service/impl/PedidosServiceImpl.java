package com.tienda.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tienda.dao.PedidosDao;
import com.tienda.entities.Detalles_pedido;
import com.tienda.entities.Pedidos;
import com.tienda.entities.Productos;
import com.tienda.entities.Usuarios;
import com.tienda.service.PedidosService;

@Service
public class PedidosServiceImpl implements PedidosService{
	
	@Autowired
	PedidosDao pedidoDao;

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
		ArrayList<Pedidos> pedidos=pedidoDao.getPedidos();
		modelo.addAttribute("pedidos", pedidos);
	}

	@Override
	public void getPedidosByIdUsuario(int id_usuario, Model modelo) {
		
		ArrayList<Pedidos> pedidos= pedidoDao.getPedidosByIdUsuario(id_usuario);
		
		modelo.addAttribute("pedidos", pedidos);
		
	}
	
	@Override
	public void modificarEstadoPedido(int id) {
		
		Pedidos pedido = pedidoDao.getPedidoById(id);
		pedidoDao.modificarEstado(id, pedido.getEstado());
		pedido = pedidoDao.getPedidoById(id);
		if (pedido.getEstado().equals("E")) pedidoDao.establecerNumFactura(id);
		
	}

}
