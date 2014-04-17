package com.rdfgroup.cms.services.domain;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Content extends BaseCmsElement {

	private static final long serialVersionUID = -2157795068626201532L;
	
	private String body;
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}

	
	
}
