package com.bancoDLRA.springboot.app.models.entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tarjetas")

public class Tarjeta implements Serializable{

	private static final long serialVersionUID = -1732362091652656124L;
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTarjeta;
	
	@Column(name = "numero_tarjeta", nullable =false, length=16)
	private String numeroTarjeta;
	
	@Column(name = "icv", nullable = false, length=3)
	private String icv;
	
	@Column(name = "tipo_tarjeta", nullable = false)
	private String tipoTarjeta;
	
	
	//Llave foranea //muchas tarjetas pertenecen a una cuenta
	@JoinColumn(name ="cuenta", referencedColumnName="idCuenta", nullable=false)
	@ManyToOne(optional=false, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)//multiplicidad //lazy no consume muchos recursos 
	private Cuenta cuenta;

	
	
	//Getters y setters
	public Long getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(Long idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getIcv() {
		return icv;
	}

	public void setIcv(String icv) {
		this.icv = icv;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
