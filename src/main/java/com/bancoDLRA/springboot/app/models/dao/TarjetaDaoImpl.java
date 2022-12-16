package com.bancoDLRA.springboot.app.models.dao;

import java.util.List;
import com.bancoDLRA.springboot.app.models.entity.Tarjeta;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class TarjetaDaoImpl implements ITarjetaDao {
	
	@PersistenceContext//se comunica con las propiedades de la aplicacion, util para que lo reconozca el contexto del JPA
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Tarjeta> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Tarjeta").getResultList();
	}

	@Override
	@Transactional
	public void save(Tarjeta tarjeta) {
		if(tarjeta.getIdTarjeta() != null  &&  tarjeta.getIdTarjeta() > 0){//maximo 2 tarjetas?
			em.merge(tarjeta);
		}else{
			em.persist(tarjeta);	
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Tarjeta findOne(Long idTarjeta) {
		return em.find(Tarjeta.class, idTarjeta);//objeto tarjeta, busca por id
	}

	@Override
	@Transactional
	public void delete(Long idTarjeta) {
		em.remove(findOne(idTarjeta));
		// TODO Auto-generated method stub
		
	}

}
