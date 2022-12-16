package com.bancoDLRA.springboot.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancoDLRA.springboot.app.models.entity.Cuenta;

@Service//especializacion de @Component //Nos da informacion
public class CuentaServiceImpl implements ICuentaService {
	
	private List<Cuenta> lista;
	
	public CuentaServiceImpl(){//constructor vacio
		
	}

	@Override
	public Cuenta getById(Long idCuenta, List<Cuenta> lista) {
		this.lista=lista;
		Cuenta cuentaResult=null;
		
		for(Cuenta cuenta: this.lista) {
			if(idCuenta==cuenta.getIdCuenta()) {
				cuentaResult=cuenta;
				break;
			}
		}
		return cuentaResult;
	}

}
