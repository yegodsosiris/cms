package com.rdfgroup.cms.services.domain.template;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Template implements Serializable {

	private static final long serialVersionUID = -6078921639643702944L;

	private String key;
	private String type;
	private String content;
	private int version;
	private boolean active;
	private String description;
	private Date lastModified;
	private String comments;
	private String website;
	
	public Template() {
		
	}
	
	public Template(String content, int version, String website) {
		super();
		this.content = content;
		this.version = version;
		this.website = website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getWebsite() {
		return website;
	}
	
	protected String id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	public Date getLastModified() {
		return lastModified;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean isNewerThan(Template t) {
		return version > t.getVersion();
	}
	
	public int getVersion() {
		return version;
	}
	
	public void increaseVersion() {
		setVersion(++version);
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	
}
