package com.tienda.entities;

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

}
