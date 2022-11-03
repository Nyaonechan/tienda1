package curso.java.tienda.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="productos")

public class Productos {
	
    @Override
	public String toString() {
		return "Productos [id=" + id + ", categoria=" + categoria + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", precio=" + precio + ", stock=" + stock + ", impuesto=" + impuesto + ", imagen="
				+ imagen + ", baja=" + baja + ", fecha_alta=" + fecha_alta + "]";
	}


	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	@ManyToOne(cascade=CascadeType.ALL)
    private Categorias categoria;

    private String nombre;

    private String descripcion;

    private double precio;

    private int stock;

    private float impuesto;

    private String imagen;

    private Boolean baja;
    
    private LocalDate fecha_alta;
    
    @Transient
    private int cantidad;
    
    
    public Productos() {
    	
    }


	public Productos(int id, Categorias categoria, String nombre, String descripcion, double precio, int stock,
			float impuesto, String imagen, boolean baja, LocalDate fecha_alta) {
		this.id = id;
		this.categoria = categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.impuesto = impuesto;
		this.imagen = imagen;
		this.baja = baja;
		this.fecha_alta = fecha_alta;
		cantidad = 1;
	}


	public Categorias getCategoria() {
		return categoria;
	}


	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public float getImpuesto() {
		return impuesto;
	}


	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public boolean isBaja() {
		return baja;
	}


	public void setBaja(boolean baja) {
		this.baja = baja;
	}


	public LocalDate getFecha_alta() {
		return fecha_alta;
	}


	public void setFecha_alta(LocalDate localDate) {
		this.fecha_alta = localDate;
	}


	public int getId() {
		return id;
	}


	public Boolean getBaja() {
		return baja;
	}


	public void setBaja(Boolean baja) {
		this.baja = baja;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
    
    

}
