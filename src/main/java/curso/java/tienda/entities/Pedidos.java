package curso.java.tienda.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="pedidos")
public class Pedidos {
	
	@Override
	public String toString() {
		return "Pedidos [id=" + id + ", id_usuario=" + id_usuario + ", fecha=" + fecha + ", metodo_pago=" + metodo_pago
				+ ", estado=" + estado + ", num_factura=" + num_factura + ", total=" + total + "]";
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int id_usuario;
	
	private LocalDate fecha;
	
	private String metodo_pago;
	
	private String estado;
	
	private String num_factura;
	
	private double total;
	
	public Pedidos() {
		
	}

	public Pedidos(int id_usuario, LocalDate fecha, String metodo_pago, String estado, double total) {
		this.id_usuario = id_usuario;
		this.fecha = fecha;
		this.metodo_pago = metodo_pago;
		this.estado = estado;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getMetodo_pago() {
		return metodo_pago;
	}

	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNum_factura() {
		return num_factura;
	}

	public void setNum_factura(String num_factura) {
		this.num_factura = num_factura;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	

}
