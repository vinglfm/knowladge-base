package com.knowladgebase.model.entries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Article {

	@Id
	private int articleId;

	@ManyToOne
	@JoinColumn(name = "userId")
	@NotNull
	private User author;

	@NotNull
	private String title;

	@Lob
	@NotNull
	private String text;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdDate;

	@ManyToMany
	@JoinTable(name = "Article_Topic", joinColumns = { @JoinColumn(name = " articleId",
			referencedColumnName = "articleId") }, inverseJoinColumns = { @JoinColumn(name = "topicId",
			referencedColumnName = "topicId") })
	private List<Topic> topics;

	/**
	 * @param title title of the article
	 * @param text body of the article
	 * @param author author of the article
	 * @param topics list of topic this article about
	 * @throws IllegalArgumentException if topics is null
	 */
	public Article(String title, String text, User author, List<Topic> topics) {
		if (topics == null)
			throw new IllegalArgumentException("list of topics can't be null");

		this.title = title;
		this.text = text;
		this.author = author;
		this.createdDate = Calendar.getInstance();
		this.topics = new ArrayList<>(topics);
	}

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public String getText() {
		return text;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
}
