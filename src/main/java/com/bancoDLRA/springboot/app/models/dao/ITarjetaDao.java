package com.bancoDLRA.springboot.app.models.dao;

import java.util.List;

import com.bancoDLRA.springboot.app.models.entity.Tarjeta;

public interface ITarjetaDao {
public List<Tarjeta> findAll();//metodo por default para traer todos los datos de la tabla
	
	public void save(Tarjeta tarjeta);

}
