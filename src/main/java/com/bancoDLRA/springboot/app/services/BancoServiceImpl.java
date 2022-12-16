package com.bancoDLRA.springboot.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancoDLRA.springboot.app.models.entity.Banco;

@Service
public class BancoServiceImpl implements IBancoService {
	
	
	private List<Banco> lista;
	
	public BancoServiceImpl(){//constructor vacio
		
	}

	@Override
	public Banco getById(Long idBanco, List<Banco> lista) {
		this.lista=lista;
		Banco bancoResult=null;
		
		for(Banco banco: this.lista) {
			if(idBanco==banco.getIdBanco()) {
				bancoResult=banco;
				break;
			}
		}
		return bancoResult;
	}

}
