package com.knowladgebase.model.entries;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Topic {

	@Id
	private int topicId;

	@NotNull
	private String title;

	// TODO: think about necessity of articles in the Topic,
	// probably it also will be easy to use this class without articles,
	// because we can get articles for a topic just by one SELECT...
	@ManyToMany(mappedBy = "topics")
	private List<Article> articles;

	/**
	 * @param title
	 *            title of the topic
	 * @param articles
	 *            articles related to the current topic
	 * @throws IllegalArgumentException
	 *             if list of topics is null
	 */
	public Topic(String title, List<Article> articles) {
		if (articles == null)
			throw new IllegalArgumentException("list of topics can't be null");
		this.title = title;
		this.articles = articles;
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}

	public List<Article> getArticles() {
		return Collections.unmodifiableList(articles);
	}

	/**
	 * Adding new article to the current topic
	 * 
	 * @param article
	 *            article to add
	 * @throws IllegalArgumentException
	 *             if article is null
	 */
	public void addArticle(Article article) {
		if (article == null)
			throw new IllegalArgumentException("article mustn't be null");
		this.articles.add(article);
	}

}
