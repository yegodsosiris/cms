package com.rdfgroup.cms.services.repository;

import java.util.List;

import com.rdfgroup.cms.services.domain.Content;
import com.rdfgroup.cms.services.domain.Website;

public interface ContentRepository {

	public List<Content> getContents(Website website) throws Exception;

	public Content getContent(String id) throws Exception;

	public Content saveContent(Content Content);

	public Content updateContent(Content Content);

	public List<Content> getContentByType(String key, Website website);

	public List<Content> getContentsByKey(String key, Website website);

	public void deleteContent(String id);

	public List<Content> getContentListByKey(String key);


}
