package com.bancoDLRA.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bancoDLRA.springboot.app.models.dao.ICuentaDao;

@Controller
public class CuentaController {
	
	@Autowired()//para que se reconozca el archivo ICuentaDaoImp
	private ICuentaDao cuentaDao;//Declaramos la inferfaz

	
	@RequestMapping(value="/cuentaLista", method = RequestMethod.GET)//para enviar los datos a la vista
	public String cuentaLista(Model model) {
		model.addAttribute("titulo", "Lista de cuentas");
		model.addAttribute("cuentas", cuentaDao.findAll());
		return "cuentaLista"; 
	}
}
