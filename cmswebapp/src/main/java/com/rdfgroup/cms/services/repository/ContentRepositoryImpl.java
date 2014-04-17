package com.rdfgroup.cms.services.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.rdfgroup.cms.services.domain.Content;
import com.rdfgroup.cms.services.domain.Website;

@Repository
public class ContentRepositoryImpl extends BaseRepository implements ContentRepository {

	@Override
	public List<Content> getContents(Website website) throws Exception {
		if (website==null) {
			return findAll(Content.class);
		}
		Criteria criteria = Criteria.where("language").is(website.getLanguage());
		return mongoTemplate.find(new Query(criteria), Content.class);
	}

	@Override
	public Content getContent(String id) throws Exception {
		return findById(id, Content.class);
	}

	@Override
	public Content saveContent(Content content) {
		return (Content) insert(content);
	}

	@Override
	public Content updateContent(Content Content) {
		return (Content) save(Content);
	}

	@Override
	public List<Content> getContentByType(String type, Website website) {
		Criteria criteria = Criteria.where("type").is(type);
		criteria = filterByWebsite(website, criteria);
		return mongoTemplate.find(new Query(criteria), Content.class);
	}

	@Override
	public List<Content> getContentsByKey(String key, Website website) {
		Criteria criteria = Criteria.where("key").is(key);
		criteria = filterByWebsite(website, criteria);
		return mongoTemplate.find(new Query(criteria), Content.class);
	}

	@Override
	public void deleteContent(String id) {
		delete(id, Content.class);
	}

	@Override
	public List<Content> getContentListByKey(String key) {
		return find("key", key, Content.class);
	}

}
