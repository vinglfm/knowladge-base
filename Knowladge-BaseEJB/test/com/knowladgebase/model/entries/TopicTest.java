package com.knowladgebase.model.entries;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;

import java.util.Set;

import javax.validation.ConstraintViolation;

import junit.framework.TestCase;

import org.junit.Test;

import com.knowladgebase.model.utilities.ValidationHelper;

public class TopicTest extends BaseTest {

	private static final String ARTICLE_IS_NOT_PRESENT = "Added article wasn't found in the topic's articles";

	private Topic topic;

	@Test
	public void shouldThrowValidationErrorWhenConstructorArgumentsAreNull() {
		int exceptionsCount = 1;

		topic = new Topic(null, anyListOf(Article.class));
		Set<ConstraintViolation<Topic>> constraintViolations = validator.validate(topic);

		TestCase.assertEquals(exceptionsCount, constraintViolations.size());
		for (ConstraintViolation<Topic> msg : constraintViolations) {
			TestCase.assertEquals(ValidationHelper.MAY_NOT_BE_NULL, msg.getMessage());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenArticlesIsNull() {
		topic = new Topic(anyString(), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionOnAddArticleWhenArticleIsNull() {
		topic = new Topic(anyString(), anyListOf(Article.class));
		topic.addArticle(null);
	}

	@Test
	public void shouldSaveArticleOnAdding() {
		topic = new Topic(anyString(), anyListOf(Article.class));
		Article article = new Article(anyString(), anyString(), any(User.class), anyListOf(Topic.class));		
		topic.addArticle(article);
		TestCase.assertTrue(ARTICLE_IS_NOT_PRESENT, contains(article, topic.getArticles()));
	}
}
