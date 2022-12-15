package com.bancoDLRA.springboot.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.bancoDLRA.springboot.app.models.dao.ITarjetaDao;
import com.bancoDLRA.springboot.app.models.entity.Tarjeta;



@Controller
public class TarjetaController {
	
	@Autowired()
	private ITarjetaDao tarjetaDao;
	
	@RequestMapping(value="/tarjetaLista", method = RequestMethod.GET)
	public String tarjetaLista(Model model) {
		model.addAttribute("titulo", "Lista de tarjetas");
		model.addAttribute("tarjetas", tarjetaDao.findAll());
		return "tarjetaLista"; 
	}
	
	@RequestMapping(value="/formulario-tarjeta")
	public String crear(Map<String, Object> model) {
		Tarjeta tarjeta = new Tarjeta();
		model.put("tarjeta", tarjeta);
		model.put("titulo", "Llenar los datos de la tarjeta");
		return "formulario-tarjeta"; 
	}
	
	@RequestMapping(value="/formulario-tarjeta/{idTarjeta}")
	public String editar(@PathVariable(value="idTarjeta") 
	Long idTarjeta, Map<String,Object> model) {
		
		Tarjeta tarjeta = null;
		
		if(idTarjeta>0) {
			tarjeta = tarjetaDao.findOne(idTarjeta);
		}else {
			return "redirect:/index";
		}
		model.put("tarjeta", tarjeta);
		model.put("titulo", "Editar tarjeta");
		
		return "formulario-tarjeta";
	}
	
	@RequestMapping(value="/formulario-tarjeta", method = RequestMethod.POST)
	public String guardar(@Valid Tarjeta tarjeta, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de tarjeta");
			return "formulario-tarjeta";
		}
		tarjetaDao.save(tarjeta);
		status.setComplete();
		
		return "redirect:index";
	}
	
	@RequestMapping(value="/eliminarTarjeta/{idTarjeta}")
	public String eliminar(@PathVariable(value="idTarjeta") Long idTarjeta){
		
		if(idTarjeta>0) {
			tarjetaDao.delete(idTarjeta);
		}
		return "redirect:index";
	}

	
	
	
	
	

}
