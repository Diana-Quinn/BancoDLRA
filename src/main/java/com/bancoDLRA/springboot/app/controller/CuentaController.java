package com.bancoDLRA.springboot.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.bancoDLRA.springboot.app.editors.BancoPropertyEditor;
import com.bancoDLRA.springboot.app.models.dao.IBancoDao;
import com.bancoDLRA.springboot.app.models.dao.ICuentaDao;
import com.bancoDLRA.springboot.app.models.entity.Banco;
import com.bancoDLRA.springboot.app.models.entity.Cuenta;
import com.bancoDLRA.springboot.app.validator.CuentaValidator;

@Controller
@SessionAttributes("cuenta")
public class CuentaController {
	
	@Autowired()//para que se reconozca el archivo ICuentaDaoImp
	private ICuentaDao cuentaDao;//Declaramos la inferfaz
	
	@Autowired()
	private IBancoDao bancoDao;
	
	@Autowired()
	private BancoPropertyEditor bancoEditor;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Banco.class, "banco", bancoEditor);
	}
	
	@Autowired
	private CuentaValidator cuentaValidator;//validaciones messages
	
	//validaciones implicitas
	@InitBinder//toma en cuenta vistas html?
	public void initBinder(WebDataBinder binder) {//WebDataBinder para hacer la validacion
		binder.addValidators(cuentaValidator);
	
	//validacion de fecha
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	dateFormat.setLenient(false);//valida formato correcto
	binder.registerCustomEditor(Date.class, "diaCreacion", new CustomDateEditor(dateFormat,false));
	}

	
	@RequestMapping(value="/cuentaLista", method = RequestMethod.GET)//para enviar los datos a la vista
	public String cuentaLista(Model model) {
		model.addAttribute("titulo", "Lista de cuentas");
		model.addAttribute("cuentas", cuentaDao.findAll());
		return "cuentaLista"; 
	}
	
	
	@RequestMapping(value="/formulario-cuenta")
	public String crear(Map<String, Object> model, Model modelList) {
		Cuenta cuenta = new Cuenta();
		List<Banco> bancoLista = bancoDao.findAll();
		model.put("cuenta", cuenta);
		modelList.addAttribute("bancoLista",bancoLista);
		model.put("titulo", "Nueva Cuenta, llene los datos solicitados");
		return "formulario-cuenta";
	}
	
	@RequestMapping(value="/formulario-cuenta/{idCuenta}")
	public String editar(@PathVariable(value="idCuenta") 
	Long idCuenta, Map<String,Object> model) {
		
		Cuenta cuenta = null;
		
		if(idCuenta!=null && idCuenta>0) {
			cuenta = cuentaDao.findOne(idCuenta);
		}else {
			//return "redirect:/cuentaLista";
			return "index";
		}
		model.put("cuenta", cuenta);
		model.put("titulo", "Editar cuenta");
		//return "/formulario-cuenta";
		return "formulario-cuenta";
		
	}
	
	@RequestMapping(value="/formulario-cuenta", method = RequestMethod.POST)
	public String guardar(@Valid Cuenta cuenta, BindingResult result, Model model, 
			SessionStatus status, RedirectAttributes flash) {
		
		//validacion de forma explicita
		//cuentaValidator.validate(cuenta, result);
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Llene correctamente los datos");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al enviar los datos, favor de escribir la informacion correctamente");
			return "formulario-cuenta";
		}else {
			model.addAttribute("result", false);
		}
		
		model.addAttribute("titulo", "Formulario de tarjeta");
		model.addAttribute("mensaje", "Se envió la informacion correctamente");
		try{
			cuentaDao.save(cuenta);
		}catch (Exception e){//exception personalizada
			e.printStackTrace();
			flash.addFlashAttribute("mensaje", e.getMessage());
		}
		status.setComplete();//al recargar se limpian los campos
		
		return "redirect:/formulario-cuenta";
	}
	
	@RequestMapping(value="/eliminarCuenta/{idCuenta}")
	public String eliminar(@PathVariable(value="idCuenta") Long idCuenta){
		
		/*
		//para validar --TODO
		try {
		if(idCuenta!=null && idCuenta>0) {
				cuentaDao.delete(idCuenta);
			}else {System.out.println("bien");}
			
		}catch (Exception e) {
				System.out.println("Elimina primero la tarjeta");
	}
	return "cuentaLista";*/
		
		if(idCuenta!=null && idCuenta>0) {
			cuentaDao.delete(idCuenta);
		}
		return "redirect:/cuentaLista";
	}
	
	}

