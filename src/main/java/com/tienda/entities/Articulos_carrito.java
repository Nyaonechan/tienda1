package com.tienda.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="articulos_carrito")

public class Articulos_carrito {
	
	@Id
	private int id_producto;
	private int id_usuario;
	private int cantidad;
	
	public Articulos_carrito() {
		
	}

	public Articulos_carrito(int id_producto, int id_usuario, int cantidad) {
		this.id_producto = id_producto;
		this.id_usuario = id_usuario;
		this.cantidad = cantidad;
	}




	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
