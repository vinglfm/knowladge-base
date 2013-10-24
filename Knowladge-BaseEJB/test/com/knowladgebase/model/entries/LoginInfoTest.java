package com.knowladgebase.model.entries;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;

import com.knowladgebase.model.utilities.ValidationHelper;

public class LoginInfoTest {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory validatorFactory = Validation
				.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@Test
	public void shouldThrowValidationErrorWhenLoginOrPasswordIsNull() {
		int exceptionsCount = 2;
		LoginInfo loginInfo = new LoginInfo(null, null);
		Set<ConstraintViolation<LoginInfo>> constraintViolations = validator
				.validate(loginInfo);

		TestCase.assertEquals(exceptionsCount, constraintViolations.size());
		for (ConstraintViolation<LoginInfo> msg : constraintViolations) {
			TestCase.assertEquals(ValidationHelper.MAY_NOT_BE_NULL,
					msg.getMessage());
		}
	}
}
