package com.catalogo.productos.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Debe ingresar su nombre del producto")
	private String nombre;

	@NotNull(message = "Debe ingresar el preicio del producto")
	@DecimalMin(value= "0.01", message = "El precio debe ser mayor a 0")
	private Float precio;

	@NotNull(message = "Debe ingresar la cantidad de producto")
	@Min(value = 1, message = "La cantidad debe ser mayor a 0")
	private Integer cantidad;

	@NotBlank(message = "Debe ingresar el codigo del producto")
	private String codigo;

	@DateTimeFormat(iso = ISO.DATE)
	@Past
	@NotNull(message = "Debe ingresar una fecha")
	private LocalDate fecha;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getPrecio() { return precio;}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigo() { return codigo;}

	public void setCodigo(String codigo) {this.codigo = codigo;}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}



}
