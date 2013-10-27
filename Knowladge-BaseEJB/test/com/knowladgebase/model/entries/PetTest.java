package com.knowladgebase.model.entries;

import java.util.Set;

import javax.validation.ConstraintViolation;

import junit.framework.TestCase;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.knowladgebase.model.utilities.ValidationHelper;

@RunWith(JUnitParamsRunner.class)
public class PetTest extends BaseTest {

	@Test
	public void shouldThrowValidationErrorWhenPetNameIsNull() {
		int exceptionsCount = 1;

		Pet pet = new Pet(null);
		Set<ConstraintViolation<Pet>> constraintViolations = validator.validate(pet);

		TestCase.assertEquals(exceptionsCount, constraintViolations.size());
		for (ConstraintViolation<Pet> msg : constraintViolations) {
			TestCase.assertEquals(ValidationHelper.MAY_NOT_BE_NULL, msg.getMessage());
		}
	}

	public static final Object[] getPetNames() {
		return new Object[] { "Kt", "Kronoprionous-Wilkines" };
	}

	@Parameters(method = "getPetNames")
	@Test
	public void shodThrowValidationErrorWhenPetNameIsNotValid(String petName) {
		int exceptionsCount = 1;
		int minLength = 3;
		int maxLength = 10;

		Pet pet = new Pet(petName);
		Set<ConstraintViolation<Pet>> constraintViolations = validator.validate(pet);

		TestCase.assertEquals(exceptionsCount, constraintViolations.size());

		for (ConstraintViolation<Pet> msg : constraintViolations) {
			TestCase.assertEquals(ValidationHelper.lengthBetweenMessage(minLength, maxLength), msg.getMessage());
		}
	}

}
