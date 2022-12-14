package com.bancoDLRA.springboot.app.models.dao;

import java.util.List;
import com.bancoDLRA.springboot.app.models.entity.Banco;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class BancoDaoImpl implements IBancoDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Banco> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Banco").getResultList();
	}

	@Override
	public void save(Banco banco) {
		if(banco.getIdBanco() != null && banco.getIdBanco() > 0){
			em.merge(banco);
		}else{
			em.persist(banco);	
		}
	}

}
