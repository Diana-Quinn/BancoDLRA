package com.bancoDLRA.springboot.app.models.entity;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="bancos")
public class Banco implements Serializable {
	
	private static final long serialVersionUID = -3806704469148550170L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBanco;
	
	@Column
	@NotEmpty//para strings 
	private String nombre;
	
	@Column
	@NotEmpty//para strings 
	private String ubicacion;

	
	//getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
