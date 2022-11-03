package curso.java.tienda.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import curso.java.tienda.dao.ValoracionesDao;
import curso.java.tienda.entities.Valoraciones;
import curso.java.tienda.service.ValoracionesService;

@Service
public class ValoracionesServiceImpl implements ValoracionesService{
	
	@Autowired
	ValoracionesDao valoracionDao;

	@Override
	public void getValoraciones(Model modelo) {
		
		modelo.addAttribute("valoraciones", valoracionDao.getValoraciones());
		
	}

	@Override
	public void insertValoracion(Valoraciones valoracion) {
		valoracion.setFecha(LocalDate.now());
		valoracionDao.insertValoracion(valoracion);
		
	}

}
