package com.bancoDLRA.springboot.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bancoDLRA.springboot.app.models.entity.Cuenta;

@Component//para meter la clase al contenedor de Spring Framework y asi poder utilizarla
public class CuentaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		//Asegura que la clase es asignable
		return Cuenta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Cuenta cuenta =(Cuenta)target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "saldoActual", "NotNull.cuenta.saldoActual");
		
		//usamos los campos del controller
		if(cuenta.getSaldoActual()<=99.0) {
			errors.rejectValue("saldoActual", "MinRequerido.cuenta.saldoActual");
		}
		
		if(cuenta.getDiaCreacion()==null) {
			errors.rejectValue("diaCreacion", "typeMismatch.cuenta.diaCreacion");
		}
		

	}

}
