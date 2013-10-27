package com.knowladgebase.model.entries;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;

import java.util.Set;

import javax.validation.ConstraintViolation;

import junit.framework.TestCase;

import org.junit.Test;

import com.knowladgebase.model.utilities.ValidationHelper;

public class ArticleTest extends BaseTest {

	@Test
	public void shouldThrowValidationErrorWhenConstructorArgumentsAreNull() {
		int exceptionsCount = 3;

		Article article = new Article(null, null, null, anyListOf(Topic.class));
		Set<ConstraintViolation<Article>> constraintViolations = validator.validate(article);

		TestCase.assertEquals(exceptionsCount, constraintViolations.size());
		for (ConstraintViolation<Article> msg : constraintViolations) {
			TestCase.assertEquals(ValidationHelper.MAY_NOT_BE_NULL, msg.getMessage());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenTopicsIsNull() {
		new Article(anyString(), anyString(), any(User.class), null);
	}

	@Test
	public void shouldSetCurrentDateAfterCreation() {
		// TODO: check if the calendar is created for the moment of creation
		Article article = new Article(anyString(), anyString(), any(User.class), anyListOf(Topic.class));
		TestCase.assertNotNull(article.getCreatedDate());
	}
}
