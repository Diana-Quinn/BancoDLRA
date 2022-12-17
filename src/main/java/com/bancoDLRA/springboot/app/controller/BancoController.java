package com.bancoDLRA.springboot.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bancoDLRA.springboot.app.models.dao.IBancoDao;
import com.bancoDLRA.springboot.app.models.entity.Banco;
import com.bancoDLRA.springboot.app.validator.BancoValidator;

@Controller
@SessionAttributes("banco")
public class BancoController {
	
	@Autowired()//para que se reconozca el archivo ICuentaDaoImp
	private IBancoDao bancoDao;//Declaramos la inferfaz
	
	
	@Autowired()//IMPORTANTE porque vamos a usar la implementacion
	private BancoValidator bancoValidator;//validaciones messages*/
	
	
	//validaciones implicitas
	@InitBinder//toma en cuenta vistas html?
	public void initBinder(WebDataBinder binder) {//WebDataBinder para hacer la validacion
		binder.addValidators(bancoValidator);
	}

	
	@RequestMapping(value="/bancoLista", method = RequestMethod.GET)//para enviar los datos a la vista
	public String bancoLista(Model model) {
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
		
		if(idBanco!=null && idBanco>0) {
			banco = bancoDao.findOne(idBanco);
		}else {
			return "index";
		}
		model.put("banco", banco);
		model.put("titulo", "Editar banco");
		return "formulario-banco";
	}
	
	@RequestMapping(value="/formulario-banco", method = RequestMethod.POST)
	public String guardar(@Valid Banco banco, BindingResult result, Model model, 
			SessionStatus status, RedirectAttributes flash) {
		
		//bancoValidator.validate(banco, result);//Importante, usamos la implementacion
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Llene correctamente los datos");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al enviar los datos, favor de escribir la informacion correctamente");
			return "formulario-tarjeta";
		}else {
			model.addAttribute("result", false);
		}
		
		model.addAttribute("titulo", "Formulario del banco");
		model.addAttribute("mensaje", "Se envió la informacion correctamente");
		try{
			bancoDao.save(banco);
		}catch (Exception e){//exception personalizada
			e.printStackTrace();
			flash.addFlashAttribute("mensaje", e.getMessage());
		}
		status.setComplete();//al recargar se limpian los campos
		
		return "redirect:/formulario-banco";
	}
	
	@RequestMapping(value="/eliminarBanco/{idBanco}")
	public String eliminar(@PathVariable(value="idBanco") Long idBanco){
		
		if(idBanco!=null && idBanco>0) {
			bancoDao.delete(idBanco);
		}
		return "redirect:/bancoLista";

}
}
