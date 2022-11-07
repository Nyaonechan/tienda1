package curso.java.tienda.service;

import org.springframework.ui.Model;

import curso.java.tienda.entities.Proveedores;


public interface ProveedoresService {
	
	public void getProveedores(Model modelo);
	
	public void getProveedorById(Model modelo, int id);
	
	public void insertProveedor(Proveedores proveedor);
	
	public void darBajaProveedor (int id);

}
