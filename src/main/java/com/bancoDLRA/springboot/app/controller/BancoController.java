package com.bancoDLRA.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bancoDLRA.springboot.app.models.dao.IBancoDao;

@Controller
public class BancoController {
	
	@Autowired()//para que se reconozca el archivo ICuentaDaoImp
	private IBancoDao bancoDao;//Declaramos la inferfaz

	
	@RequestMapping(value="/bancoLista", method = RequestMethod.GET)//para enviar los datos a la vista
	public String cuentaLista(Model model) {
		model.addAttribute("titulo", "Lista de bancos");
		model.addAttribute("bancos", bancoDao.findAll());
		return "bancoLista"; 
	}

}
