package com.bancoDLRA.springboot.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bancoDLRA.springboot.app.models.dao.ICuentaDao;
import com.bancoDLRA.springboot.app.services.ICuentaService;

@Component//para poder trabajar con la clase
public class CuentaPropertyEditor extends PropertyEditorSupport{

	
	@Autowired
	private ICuentaService cuentaService;
	
	@Autowired
	private ICuentaDao cuentaDao;
	
	
	
	
	@Override//source->Override/Methods
	public void setAsText(String idStr) throws IllegalArgumentException {

		try {
			Long idCuenta = Long.parseLong(idStr);
			this.setValue(cuentaService.getById(idCuenta, cuentaDao.findAll()));
		}catch(Exception e) {
			System.out.println("Hubo un error al asignar el objeto cuenta a la tarjeta :c");
		}
	}
	
	

}
