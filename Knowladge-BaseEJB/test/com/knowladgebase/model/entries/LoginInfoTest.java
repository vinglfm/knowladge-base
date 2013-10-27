package com.knowladgebase.model.entries;

import java.util.Set;

import javax.validation.ConstraintViolation;

import junit.framework.TestCase;

import org.junit.Test;

import com.knowladgebase.model.AuthRights;
import com.knowladgebase.model.utilities.ValidationHelper;

public class LoginInfoTest extends BaseTest {

	@Test
	public void shouldThrowValidationErrorWhenLoginOrPasswordIsNull() {
		int exceptionsCount = 3;
		LoginInfo loginInfo = new LoginInfo(null, null, null);
		Set<ConstraintViolation<LoginInfo>> constraintViolations = validator.validate(loginInfo);

		TestCase.assertEquals(exceptionsCount, constraintViolations.size());
		for (ConstraintViolation<LoginInfo> msg : constraintViolations) {
			TestCase.assertEquals(ValidationHelper.MAY_NOT_BE_NULL, msg.getMessage());
		}
	}

	@Test
	public void shouldSaveDataOnValidParams() {
		String login = "validLogin";
		String password = "validPassword";
		AuthRights rights = AuthRights.ADMIN;
		LoginInfo loginInfo = new LoginInfo(login, password, rights);
		TestCase.assertEquals(ValidationHelper.inccorectFieldMessage("login"), login, loginInfo.getLogin());
		TestCase.assertEquals(ValidationHelper.inccorectFieldMessage("password"), password, loginInfo.getPassword());
		TestCase.assertEquals(ValidationHelper.inccorectFieldMessage("rights"), rights, loginInfo.getRights());
	}
}
