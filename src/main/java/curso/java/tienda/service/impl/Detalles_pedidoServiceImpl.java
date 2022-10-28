package curso.java.tienda.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import curso.java.tienda.dao.Detalles_pedidoDao;
import curso.java.tienda.entities.Detalles_pedido;
import curso.java.tienda.service.Detalles_pedidoService;

@Service
public class Detalles_pedidoServiceImpl implements Detalles_pedidoService {
	
	@Autowired
	Detalles_pedidoDao detalle_pedidoDao;

	@Override
	public void getDetallesPedidoById(Model modelo, int id) {
		
		ArrayList<Detalles_pedido> detalles = detalle_pedidoDao.getDetallesByIdPedido(id);
		
		modelo.addAttribute("detalles", detalles);
		
	}

}
