package curso.java.tienda.dao;

import java.util.ArrayList;

import curso.java.tienda.entities.Proveedores;

public interface ProveedoresDao {
	
	public ArrayList<Proveedores> getProveedores();
	
	public Proveedores getProveedorById(int id);
	
	public void insertProveedor(Proveedores proveedor);
	
	public void darBajaProveedor(int id);

}
