package com.bancoDLRA.springboot.app.models.dao;

import java.util.List;

import com.bancoDLRA.springboot.app.models.entity.Cuenta;

public interface ICuentaDao { //interface contiene los metodos que se van a implementar de manera abstracta en clases hijas

	public List<Cuenta> findAll();//metodo por default para traer todos los datos de la tabla
	
	public void save(Cuenta cuenta);//guardar de Cuenta una cuenta
}
