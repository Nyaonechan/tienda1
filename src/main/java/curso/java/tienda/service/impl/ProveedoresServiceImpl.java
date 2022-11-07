package curso.java.tienda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import curso.java.tienda.dao.ProveedoresDao;
import curso.java.tienda.entities.Proveedores;
import curso.java.tienda.service.ProveedoresService;

@Service
public class ProveedoresServiceImpl implements ProveedoresService{
	
	@Autowired
	ProveedoresDao proveedorDao;

	@Override
	public void getProveedores(Model modelo) {
		
		modelo.addAttribute("proveedores", proveedorDao.getProveedores());
		
	}

	@Override
	public void getProveedorById(Model modelo, int id) {
		
		modelo.addAttribute("proveedor", proveedorDao.getProveedorById(id));
		
	}

	@Override
	public void insertProveedor(Proveedores proveedor) {
		
		proveedor.setBaja(false);
		
		proveedorDao.insertProveedor(proveedor);
		
	}

	@Override
	public void darBajaProveedor(int id) {
		
		proveedorDao.darBajaProveedor(id);
		
	}
	
	

}
