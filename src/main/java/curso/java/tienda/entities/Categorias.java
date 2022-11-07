package curso.java.tienda.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="categorias")

public class Categorias {
	
    @Override
	public String toString() {
		return "Categorias [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen=" + imagen
				+ "]";
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String nombre;
    
    private String descripcion;
    
    private String imagen;

	public Categorias() {	
	}
	
	public Categorias(int id) {
		this.id = id;
	}

	public Categorias(int id, String nombre, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
	}
	
	public Categorias(int id, String nombre, String descripcion, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion= descripcion;
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
    
    


}
