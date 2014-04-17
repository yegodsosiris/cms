package com.rdfgroup.security.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable{

	
	protected String id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	
}
