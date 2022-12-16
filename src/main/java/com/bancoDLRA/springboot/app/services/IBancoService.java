package com.bancoDLRA.springboot.app.services;

import java.util.List;

import com.bancoDLRA.springboot.app.models.entity.Banco;

public interface IBancoService {
	
	public Banco getById(Long idBanco, List<Banco> lista);


}
