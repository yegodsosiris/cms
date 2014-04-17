package com.rdfgroup.cms.services.service;

import java.util.List;

import com.rdfgroup.cms.services.domain.Content;
import com.rdfgroup.cms.services.domain.Website;


public interface ContentService {
	
	public List<Content> getContents(Website website) throws Exception;
	
	public Content getContent(String id) throws Exception;
	
	public Content saveContent(Content template);

	public Content updateContent(Content template);

	public List<Content> getContentByType(String key, Website website);

	public List<Content> getContentsByKey(String key, Website website);

	public Content revertContent(Content template, Website website);

	public void deleteContent(String id);

	public List<Content> getContentListByKey(String key);


}
