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

import com.bancoDLRA.springboot.app.models.dao.IBancoDao;
import com.bancoDLRA.springboot.app.models.entity.Banco;

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
	
	@RequestMapping(value="/formulario-banco")
	public String crear(Map<String, Object> model) {
		Banco banco = new Banco();
		model.put("banco", banco);
		model.put("titulo", "Llenar los datos del Banco");
		return "formulario-banco"; 
	}
	
	@RequestMapping(value="/formulario-banco/{idBanco}")
	public String editar(@PathVariable(value="idBanco") 
	Long idBanco, Map<String,Object> model) {
		
		Banco banco = null;
		
		if(idBanco>0) {
			banco = bancoDao.findOne(idBanco);
		}else {
			return "redirect:/index";
		}
		model.put("banco", banco);
		model.put("titulo", "Editar banco");
		
		return "formulario-banco";
	}
	
	@RequestMapping(value="/formulario-banco", method = RequestMethod.POST)
	public String guardar(@Valid Banco banco, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario del Banco");
			return "formulario-banco";
		}
		bancoDao.save(banco);
		status.setComplete();
		
		return "redirect:index";
	}
	
	@RequestMapping(value="/eliminarBanco/{idBanco}")
	public String eliminar(@PathVariable(value="idBanco") 
	Long idBanco){
		
		if(idBanco>0) {
			bancoDao.delete(idBanco);
		}
		return "redirect:index";
	}


}
