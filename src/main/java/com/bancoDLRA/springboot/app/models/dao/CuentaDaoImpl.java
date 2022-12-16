package com.bancoDLRA.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bancoDLRA.springboot.app.models.entity.Cuenta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository//para marcar como componente propio  
public class CuentaDaoImpl implements ICuentaDao {
	
	@PersistenceContext//se comunica con las propiedades de la aplicacion, util para que lo reconozca el contexto del JPA
	private EntityManager em;//para que DAO funcione como la clase de acceso de datos
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)//se debe escribir para todos los metodos la anotacion @transactional
	@Override
	public List<Cuenta> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cuenta").getResultList();//retorna lista de todas las cuentas de la entidad Cuenta
	}

	@Override
	@Transactional
	public void save(Cuenta cuenta) {
		if(cuenta.getIdCuenta() != null && cuenta.getIdCuenta() > 0) //se valida que exista la cuenta y que la cuenta temga un ID mayor a cero
		{//si existe añadimos los cambios
			em.merge(cuenta);
		}
		else//si no esta creada, creamos una cuenta nueva
		{
			em.persist(cuenta);	
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = true)
	public Cuenta findOne(Long idCuenta) {
		return em.find(Cuenta.class,idCuenta);
	}

	@Override
	@Transactional
	public void delete(Long idCuenta) {
		em.remove(findOne(idCuenta));
		
	}

	

}
