package com.bancoDLRA.springboot.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bancoDLRA.springboot.app.models.entity.Banco;

@Component//IMPORTANTE AGREGARLO
public class BancoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Banco.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Banco banco =(Banco)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotNull.banco.nombre");
		
		if(!banco.getNombre().matches("[a-z,A-Z]{1,15}?[ ]?[a-z,A-Z]{1,15}")) {
			errors.rejectValue("nombre", "format.banco.nombre");
		}

		
		// TODO Auto-generated method stub

	}

}
