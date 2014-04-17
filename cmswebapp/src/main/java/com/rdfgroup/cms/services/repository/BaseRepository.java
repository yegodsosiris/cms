package com.rdfgroup.cms.services.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.rdfgroup.cms.services.domain.Website;

@Repository
public class BaseRepository {

	@Autowired
	protected MongoTemplate mongoTemplate;

	protected <T> T getOne(String key, Object value, Class<T> clazz) {
		return mongoTemplate.findOne(new Query(Criteria.where(key).is(value)), clazz);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void delete(String id, Class clazz) {
		mongoTemplate.remove(mongoTemplate.findById(id, clazz));
	}
	
	protected <T> List<T> find(String key, Object value, Class<T> clazz) {
		return mongoTemplate.find(new Query(Criteria.where(key).in(value)), clazz);
	}

	protected <T> List<T> findAll(Class<T> clazz) {
		return mongoTemplate.findAll(clazz);
	}
	
	protected Object insert(Object obj) {
		mongoTemplate.insert(obj);
		return obj;
	}
	
	public Object save(Object obj) {
		mongoTemplate.save(obj);
		return obj;
	}

	public <T> T findById(String id, Class<T> clazz) {
		return mongoTemplate.findById(id, clazz);
	}
	
	protected Criteria filterByWebsite(Website website, Criteria criteria) {
		if (website != null) {
			criteria = criteria.and("language").is(website.getLanguage());
		}
		return criteria;
	}

}
