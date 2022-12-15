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

import com.bancoDLRA.springboot.app.models.dao.ICuentaDao;
import com.bancoDLRA.springboot.app.models.entity.Cuenta;

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
	
	
	@RequestMapping(value="/formulario-cuenta")
	public String crear(Map<String, Object> model) {
		Cuenta cuenta = new Cuenta();
		model.put("cuenta", cuenta);
		model.put("titulo", "Nueva Cuenta, llene los datos solicitados");
		return "formulario-cuenta";
	}
	
	@RequestMapping(value="/formulario-cuenta/{idCuenta}")
	public String editar(@PathVariable(value="idCuenta") 
	Long idCuenta, Map<String,Object> model) {
		
		Cuenta cuenta = null;
		
		if(idCuenta>0) {
			cuenta = cuentaDao.findOne(idCuenta);
		}else {
			return "redirect:/cuentaLista";
		}
		model.put("cuenta", cuenta);
		model.put("titulo", "Editar cuenta");
		return "/formulario-cuenta";
		
	}
	
	@RequestMapping(value="/formulario-cuenta", method = RequestMethod.POST)
	public String guardar(@Valid Cuenta cuenta, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cuenta");
			return "formulario-cuenta";
		}
		cuentaDao.save(cuenta);
		status.setComplete();
		
		return "redirect:index";
	}
	
	@RequestMapping(value="/eliminarCuenta/{idCuenta}")
	public String eliminar(@PathVariable(value="idCuenta") Long idCuenta){
		
		if(idCuenta>0) {
			cuentaDao.delete(idCuenta);
		}
		return "redirect:index";
	}
	
	
	
	
}
