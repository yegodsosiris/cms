package com.rdfgroup.cms.services.domain;

import java.util.Date;


public interface CmsElement {

	
	public String getType();
	
	public void setType(String type);
	
	public int getVersion();

	public void setVersion(int version);

	public Date getLastModified();

	public void setLastModified(Date lastModified);

	public String getComments();
	
	public void increaseVersion();

	public void setComments(String comments);

	public void setLanguage(String language);
	
	public String getLanguage();

	public String getUpdatedBy();

	public void setUpdatedBy(String updatedBy);

	public boolean isNewerThan(CmsElement t);
	
	public void setKey(String key);
	
	public String getKey();
	
	public String getId();
	
	public void setId(String id);
	
	public String getTitle();
	
	public void setTitle(String title);
	
}
