package curso.java.tienda.utils;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import curso.java.tienda.DemoApplication;
import curso.java.tienda.dao.PedidosDao;
import curso.java.tienda.entities.Pedidos;
import curso.java.tienda.service.PedidosService;

@Component
public class HiloEstadoPedidos implements Runnable {
	
	@Autowired
	PedidosService pedidoService;
	
	@Autowired
	PedidosDao pedidoDao;
	
	static Logger logger = Logger.getLogger(DemoApplication.class);

	@Override
	public void run() {
		boolean bandera = true;
		do {
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		modificarEstadoPedidos();
		}while(bandera);
		
	}
	
	public void modificarEstadoPedidos() {
		
		ArrayList<Pedidos> pedidos = pedidoDao.getPedidos();
		for (Pedidos e: pedidos) {
			if(e.getEstado().equals("P.E.")) {
				pedidoDao.modificarEstadoAdminEnviado(e.getId());
				pedidoService.establecerFactura(e.getId());
				System.out.println("Estado del pedido con id: " + e.getId() + " modificado");
				logger.info("Estado del pedido con id: " + e.getId() + " modificado");
			}
		}

	}
	
	
	
	

}