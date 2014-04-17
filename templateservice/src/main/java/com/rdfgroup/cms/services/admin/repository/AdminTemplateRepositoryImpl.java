package com.rdfgroup.cms.services.admin.repository;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.domain.template.Template;
import com.rdfgroup.cms.services.repository.BaseRepository;

@Repository
public class AdminTemplateRepositoryImpl extends BaseRepository implements AdminTemplateRepository {

	@Override
	public List<Template> getTemplates(Website website) throws Exception {
		if (Website.ALL_WEBSITES.equals(website.getName())) {
			return findAll(Template.class);
		}
		Criteria criteria = Criteria.where("website").is(website.getName());
		return mongoTemplate.find(new Query(criteria), Template.class);
	}

	@Override
	public Template getTemplate(String id) throws Exception {
		return findById(id, Template.class);
	}

	//TODO ADP review the use of saveTemplate and updateTemplate as these operations appear to be doing exactly the same thing.
	@Override
	public Template saveTemplate(Template template) {
		if (StringUtils.isEmpty(template.getId())) {
			return (Template) insert(template);
		}
		return (Template) insert(template);
	}

	@Override
	public Template updateTemplate(Template template) {
		return (Template) save(template);
	}

	@Override
	public List<Template> getTemplateByType(String type, Website website) {
		Criteria criteria = Criteria.where("type").is(type);
		criteria = filterByWebsite(website, criteria);
		return mongoTemplate.find(new Query(criteria), Template.class);
	}

	private Criteria filterByWebsite(Website website, Criteria criteria) {
		if (!Website.ALL_WEBSITES.equals(website.getName())) {
			criteria = criteria.and("website").is(website.getName());
		}
		return criteria;
	}

	@Override
	public List<Template> getTemplatesByKey(String key, Website website) {
		Criteria criteria = Criteria.where("key").is(key);
		criteria = filterByWebsite(website, criteria);
		return mongoTemplate.find(new Query(criteria), Template.class);
	}

	@Override
	public void deleteTemplate(String id) {
		delete(id, Template.class);
	}

}
