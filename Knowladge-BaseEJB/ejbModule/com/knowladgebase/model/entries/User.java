package com.knowladgebase.model.entries;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	private int userId;

	@NotNull
	private String name;

	// TODO: think about necessity of articles in the User,
	// probably it also will be easy to use this class without articles,
	// because we can get articles for a specific user just by one SELECT...
	@OneToMany(mappedBy = "User")
	private List<Article> articles;

	@OneToOne(orphanRemoval = true)
	@NotNull
	private LoginInfo loginInfo;

	public User(String name, LoginInfo loginInfo, List<Article> articles) {
		if (articles == null)
			throw new IllegalArgumentException("list of topics can't be null");
		this.name = name;
		this.loginInfo = loginInfo;
		this.articles = articles;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void addArticle(Article article) {
		// TODO: check do I need this when @NotNull is used ?
		if (article == null)
			throw new IllegalArgumentException("Article can't be null");

		articles.add(article);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

}
