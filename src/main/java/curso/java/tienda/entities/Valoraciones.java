package curso.java.tienda.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="valoraciones")
public class Valoraciones {
	
	@Override
	public String toString() {
		return "Valoraciones [id=" + id + ", producto=" + producto + ", usuario=" + usuario + ", valoracion="
				+ valoracion + ", comentario=" + comentario + ", fecha=" + fecha + "]";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Productos producto;
	
	@ManyToOne
	private Usuarios usuario;
	
	private int valoracion;
	
	private String comentario;
	
	private LocalDate fecha;
	
	public Valoraciones() {
		
	}

	public Valoraciones(int id, Productos producto, Usuarios usuario, int valoracion, String comentario,
			LocalDate fecha) {
		this.id = id;
		this.producto = producto;
		this.usuario= usuario;
		this.valoracion = valoracion;
		this.comentario = comentario;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
	
	

}
