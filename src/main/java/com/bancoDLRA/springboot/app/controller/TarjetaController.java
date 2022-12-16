package com.bancoDLRA.springboot.app.controller;

import java.util.List;
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

import com.bancoDLRA.springboot.app.editors.CuentaPropertyEditor;
import com.bancoDLRA.springboot.app.models.dao.ICuentaDao;
import com.bancoDLRA.springboot.app.models.dao.ITarjetaDao;
import com.bancoDLRA.springboot.app.models.entity.Cuenta;
import com.bancoDLRA.springboot.app.models.entity.Tarjeta;



@Controller
@SessionAttributes("tarjeta")
public class TarjetaController {
	
	@Autowired()
	private ITarjetaDao tarjetaDao;
	
	@Autowired()
	private ICuentaDao cuentaDao;
	
	@Autowired()
	private CuentaPropertyEditor cuentaEditor;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cuenta.class, "cuenta", cuentaEditor);
	}
	
	@RequestMapping(value="/tarjetaLista", method = RequestMethod.GET)
	public String tarjetaLista(Model model) {
		model.addAttribute("titulo", "Lista de tarjetas");
		model.addAttribute("tarjetas", tarjetaDao.findAll());
		return "tarjetaLista"; 
	}
	
	@RequestMapping(value="/formulario-tarjeta")
	public String crear(Map<String, Object> model, Model modelList) {
		Tarjeta tarjeta = new Tarjeta();
		List<Cuenta> cuentaLista = cuentaDao.findAll();
		model.put("tarjeta", tarjeta);
		modelList.addAttribute("cuentaLista",cuentaLista);
		model.put("titulo", "Llenar los datos de la tarjeta");
		return "formulario-tarjeta"; 
	}
	
	@RequestMapping(value="/formulario-tarjeta/{idTarjeta}")
	public String editar(@PathVariable(value="idTarjeta") 
	Long idTarjeta, Map<String,Object> model) {
		
		Tarjeta tarjeta = null;
		
		if(idTarjeta!=null && idTarjeta>0) {
			tarjeta = tarjetaDao.findOne(idTarjeta);
		}else {
			//return "redirect:/tarjetaLista";
			return "index";
		}
		model.put("tarjeta", tarjeta);
		model.put("titulo", "Editar tarjeta");
		
		return "formulario-tarjeta";
	}
	
	@RequestMapping(value="/formulario-tarjeta", method = RequestMethod.POST)
	public String guardar(@Valid Tarjeta tarjeta, BindingResult result, Model model, 
			SessionStatus status, RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Llene correctamente los datos");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al enviar los datos, favor de escribir la informacion correctamente");
			return "formulario-tarjeta";
		}else {
			model.addAttribute("result", false);
		}
		
		model.addAttribute("titulo", "Formulario de tarjeta");
		model.addAttribute("mensaje", "Se envió la informacion correctamente");
		try{
			tarjetaDao.save(tarjeta);
		}catch (Exception e){//exception personalizada
			e.printStackTrace();
			flash.addFlashAttribute("mensaje", e.getMessage());
		}
		status.setComplete();//al recargar se limpian los campos
		
		return "redirect:formulario-tarjeta";
	}
	
	@RequestMapping(value="/eliminarTarjeta/{idTarjeta}")
	public String eliminar(@PathVariable(value="idTarjeta") Long idTarjeta){
		
		if(idTarjeta>0) {
			tarjetaDao.delete(idTarjeta);
		}
		return "redirect:index";
	}

	
	
	
	
	

}
