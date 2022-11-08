package curso.java.tienda.service.impl;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import curso.java.tienda.dao.DescuentosDao;
import curso.java.tienda.entities.Descuentos;
import curso.java.tienda.service.DescuentosService;

@Service
public class DescuentosServiceImpl implements DescuentosService{
	
	@Autowired
	DescuentosDao descuentoDao;

	@Override
	public void getDescuentos(Model modelo) {
		
		modelo.addAttribute("descuentos", descuentoDao.getDescuentos());
		
	}

	@Override
	public void getDescuentoById(Model modelo, int id) {
		
		modelo.addAttribute("descuento", descuentoDao.getDescuentoById(id));
		
	}
	
	@Transactional
	@Override
	public void insertDescuento(Descuentos descuento) {
		
		descuentoDao.insertDescuento(descuento);
		
	}

	@Override
	public void gestionarDescuento(String codigo, Model modelo) {
		

		Descuentos descuento = descuentoDao.getDescuentoByCodigo(codigo);
		System.out.println(descuento);

		if (descuento!=null) {
			int inicio = LocalDate.now().compareTo(descuento.getFecha_inicio());
			int fin = LocalDate.now().compareTo(descuento.getFecha_fin());
			System.out.println("Inicio: " + inicio);
			System.out.println("Fin: " + fin);
			if (inicio>0 && fin<0) {
				modelo.addAttribute("descuentoNuevo", descuento);
			}
		} else {
			modelo.addAttribute("nodescuento", "No existe un descuento con ese código");
		}

		
	}

}
