package com.knowladgebase.model.entries;

import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;

public class BaseTest {
	protected static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	public <T> boolean contains(T element, List<T> elems) {
		for (T elem : elems)
			if (element.equals(elem))
				return true;
		return false;
	}

}
