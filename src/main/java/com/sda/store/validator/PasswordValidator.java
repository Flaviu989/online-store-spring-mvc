package com.sda.store.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String message = null;
		if (value == null)
			message = "Invalid password: must not be null";
		else if (value.length() < 8)
			message = "Invalid password: must be at least 8-character long";
		if (message == null)
			return true;
		else {
			context.disableDefaultConstraintViolation();
			context
				.buildConstraintViolationWithTemplate(message)
				.addConstraintViolation();
			return false;
		}
	}
}
