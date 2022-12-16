package com.bancoDLRA.springboot.app.services;

import java.util.List;

import com.bancoDLRA.springboot.app.models.entity.Cuenta;

public interface ICuentaService {
	
	public Cuenta getById(Long idCuenta, List<Cuenta> lista);

}
