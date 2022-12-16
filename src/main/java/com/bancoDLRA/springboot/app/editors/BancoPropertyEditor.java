package com.bancoDLRA.springboot.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bancoDLRA.springboot.app.models.dao.IBancoDao;
import com.bancoDLRA.springboot.app.services.IBancoService;

@Component
public class BancoPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private IBancoService bancoService;
	
	@Autowired
	private IBancoDao bancoDao;
	
	@Override
	public void setAsText(String idStr) throws IllegalArgumentException {
		try {
			Long idBanco = Long.parseLong(idStr);
			this.setValue(bancoService.getById(idBanco, bancoDao.findAll()));
		}catch(Exception e) {
			System.out.println("Hubo un error al asignar el objeto banco a la cuenta :c");
		}
		
	}
	
	
	
	

}
