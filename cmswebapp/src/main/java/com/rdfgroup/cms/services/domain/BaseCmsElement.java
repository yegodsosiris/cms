package com.rdfgroup.cms.services.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;


public class BaseCmsElement implements CmsElement, Serializable {

	private static final long serialVersionUID = -2157795068626201532L;
	
	private String title;
	private String id;
	private String key;
	private String type;
	private int version;
	private Date lastModified;
	private String comments;
	private String language;
	private String updatedBy;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getComments() {
		return comments;
	}
	
	public void increaseVersion() {
		setVersion(++version);
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getLanguage() {
		return language;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public boolean isNewerThan(CmsElement t) {
		return version > t.getVersion();
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		// empty strings would confuse Mongo - so empty should be null
		this.id = StringUtils.isBlank(id) ? null : id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
	
}
