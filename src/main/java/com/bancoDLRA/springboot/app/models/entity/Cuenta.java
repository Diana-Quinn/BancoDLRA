package com.bancoDLRA.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

//import org.springframework.format.annotation.DateTimeFormat;

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
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;

//Mark the class as an Entity by using the annotation @Entity.
@Entity
//@Table(), the JPA annotation is used for adding the table name in the particular MySQL database. 
@Table(name = "cuentas")

public class Cuenta implements Serializable {

	private static final long serialVersionUID = -6186390900397141908L;

	@Id // Mark Id as the primary key by using the annotation @Id.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // to indicate that the ID should be generated automatically.
	private Long idCuenta;

	@Column(name = "saldo_actual")
	@NotEmpty
	private double saldoActual;

	@Column(name = "dia_creacion") // CAMEL CASE
	// muestra la fecha de creacion
	@NotEmpty
	//@Temporal(TemporalType.DATE) // This annotation must be specified for persistent fields or properties of type
									// java.util.Date and java.util.Calendar
	//@DateTimeFormat(pattern = "yyyy-MM-dd") // formato de año, mes, dia
	private Date diaCreacion;

	// Llave foranea //muchas cuentas pertenecen a un banco
	@JoinColumn(name = "banco", referencedColumnName = "idBanco", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY,cascade=CascadeType.MERGE) // multiplicidad //lazy no consume muchos recursos
	private Banco banco;

	// getters y setters
	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public Date getDiaCreacion() {
		return diaCreacion;
	}

	public void setDiaCreacion(Date diaCreacion) {
		this.diaCreacion = diaCreacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

}
