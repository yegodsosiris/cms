package com.rdfgroup.cms.services.domain;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Book extends BaseEntity {

	private static final long serialVersionUID = -6078921639643702944L;
	
	private String title;
	private String author;
	private String content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
