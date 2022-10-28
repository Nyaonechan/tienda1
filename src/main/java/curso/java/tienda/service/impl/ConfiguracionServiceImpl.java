package curso.java.tienda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.dao.ConfiguracionDao;
import curso.java.tienda.entities.Configuracion;
import curso.java.tienda.service.ConfiguracionService;

@Service
public class ConfiguracionServiceImpl implements ConfiguracionService{
	
	@Autowired
	ConfiguracionDao configuracionDao;

	@Override
	public String generarFactura() {
		
		Configuracion configuracion= configuracionDao.getByClave("prefijo");
		String prefijo = configuracion.getValor();
		configuracion= configuracionDao.getByClave("sufijo");
		String sufijo = configuracion.getValor();
		configuracion= configuracionDao.getByClave("num_factura");
		String num_factura=configuracion.getValor();
		
		String factura =prefijo+num_factura+sufijo;
		System.out.println(factura);

		return factura;
	}

	@Override
	public void aumentarNumFactura() {
		
		Configuracion configuracion= configuracionDao.getByClave("num_factura");
		int num_factura = Integer.parseInt(configuracion.getValor());
		num_factura+=1;
		configuracion.setValor(String.valueOf(num_factura));
		System.out.println("Numero factura" +num_factura);
		configuracionDao.insertConfiguracion(configuracion);
		
	}
	
	

}
