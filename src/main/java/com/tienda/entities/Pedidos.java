package com.tienda.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="pedidos")
@Data
public class Pedidos {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int id_usuario;
	
	private LocalDate fecha;
	
	private String metodo_pago;
	
	private String estado;
	
	private String num_factura;
	
	private double total;

}
