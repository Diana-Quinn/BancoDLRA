package com.bancoDLRA.springboot.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bancoDLRA.springboot.app.models.entity.Tarjeta;

@Component
public class TarjetaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Tarjeta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Tarjeta tarjeta =  (Tarjeta)target;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoTarjeta", "NotNull.tarjeta.tipoTarjeta");
		
		if(!tarjeta.getTipoTarjeta().matches("[a-z,A-Z]{1,15}")) {
			errors.rejectValue("tipoTarjeta", "format.tarjeta.tipoTarjeta");
		}
		
		if(!tarjeta.getIcv().matches("[0-9]{3}")) {
			errors.rejectValue("icv","format.tarjeta.icv");
		}
		
		if(!tarjeta.getNumeroTarjeta().matches("[0-9]{16}")) {
			errors.rejectValue("numeroTarjeta","format.tarjeta.numeroTarjeta");
		}


	}

}
