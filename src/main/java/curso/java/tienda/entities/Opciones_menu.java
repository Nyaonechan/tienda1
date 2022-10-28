package curso.java.tienda.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="opciones_menu")
public class Opciones_menu {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int id_rol;
	
	private String nombre_opcion;
	
	private String url_opcion;
	
	public Opciones_menu() {
		
	}

	public Opciones_menu(int id_rol, String nombre_opcion, String url_opcion) {
		this.id_rol = id_rol;
		this.nombre_opcion = nombre_opcion;
		this.url_opcion = url_opcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}

	public String getNombre_opcion() {
		return nombre_opcion;
	}

	public void setNombre_opcion(String nombre_opcion) {
		this.nombre_opcion = nombre_opcion;
	}

	public String getUrl_opcion() {
		return url_opcion;
	}

	public void setUrl_opcion(String url_opcion) {
		this.url_opcion = url_opcion;
	}

	@Override
	public String toString() {
		return "Opciones_menu [id=" + id + ", id_rol=" + id_rol + ", nombre_opcion=" + nombre_opcion + ", url_opcion="
				+ url_opcion + "]";
	}
	
	

}
