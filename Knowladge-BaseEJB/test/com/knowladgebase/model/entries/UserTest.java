package com.knowladgebase.model.entries;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;

import java.util.Set;

import javax.validation.ConstraintViolation;

import junit.framework.TestCase;

import org.junit.Test;

import com.knowladgebase.model.utilities.ValidationHelper;

public class UserTest extends BaseTest {

	private static final String ARTICLE_IS_NOT_PRESENT = "Added article wasn't found in the topic's articles";

	private User user;

	@Test
	public void shouldThrowValidationErrorWhenConstructorArgumentsAreNull() {
		int exceptionsCount = 2;

		user = new User(null, null, anyListOf(Article.class));
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

		TestCase.assertEquals(exceptionsCount, constraintViolations.size());
		for (ConstraintViolation<User> msg : constraintViolations) {
			TestCase.assertEquals(ValidationHelper.MAY_NOT_BE_NULL, msg.getMessage());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenArticlesIsNull() {
		user = new User(anyString(), any(LoginInfo.class), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionOnAddArticleWhenArticleIsNull() {
		user = new User(anyString(), any(LoginInfo.class), anyListOf(Article.class));
		user.addArticle(null);
	}

	@Test
	public void shouldSaveArticleOnAdding() {
		user = new User(anyString(), any(LoginInfo.class), anyListOf(Article.class));
		Article article = new Article(anyString(), anyString(), any(User.class), anyListOf(Topic.class));
		user.addArticle(article);
		TestCase.assertTrue(ARTICLE_IS_NOT_PRESENT, contains(article, user.getArticles()));
	}

}
