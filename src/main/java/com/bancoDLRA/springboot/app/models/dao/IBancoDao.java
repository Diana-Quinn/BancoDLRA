package com.bancoDLRA.springboot.app.models.dao;

import com.bancoDLRA.springboot.app.models.entity.Banco;

import java.util.List;

public interface IBancoDao {

	public List<Banco> findAll();// metodo por default para traer todos los datos de la tabla

	public void save(Banco banco);

	public Banco findOne(Long idBanco);

	public void delete(Long idBanco);

}
