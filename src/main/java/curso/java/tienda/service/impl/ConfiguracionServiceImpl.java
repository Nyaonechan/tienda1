package curso.java.tienda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

	@Override
	public void recogerDatosEmpresa(Model modelo) {
		
		Configuracion configuracion = configuracionDao.getByClave("nombre");
		String nombre  = configuracion.getValor();
		modelo.addAttribute("nombre", nombre);
		
		Configuracion configuracion2 = configuracionDao.getByClave("direccion");
		String direccion = configuracion2.getValor();
		modelo.addAttribute("direccion", direccion);
		
		Configuracion configuracion3 = configuracionDao.getByClave("provincia");
		String provincia = configuracion3.getValor();
		modelo.addAttribute("provincia", provincia);
		
		Configuracion configuracion4 = configuracionDao.getByClave("ciudad");
		String ciudad = configuracion4.getValor();
		modelo.addAttribute("ciudad", ciudad);
		
	}
	
	

}
