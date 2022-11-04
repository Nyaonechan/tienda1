package curso.java.tienda.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="detalles_pedido")
public class Detalles_pedido {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int id_pedido;
	
	private int id_producto;
	
	private int unidades;
	
	private float precio_unidad;
	
	private float impuesto;
	
	private double total;
	
	private String estado;
	
	public Detalles_pedido() {
		
	}

	public Detalles_pedido(int id_pedido, int id_producto, int unidades, float precio_unidad, float impuesto,
			double total, String estado) {
		this.id_pedido = id_pedido;
		this.id_producto = id_producto;
		this.unidades = unidades;
		this.precio_unidad = precio_unidad;
		this.impuesto = impuesto;
		this.total = total;
		this.estado=estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public float getPrecio_unidad() {
		return precio_unidad;
	}

	public void setPrecio_unidad(float precio_unidad) {
		this.precio_unidad = precio_unidad;
	}

	public float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	

}
