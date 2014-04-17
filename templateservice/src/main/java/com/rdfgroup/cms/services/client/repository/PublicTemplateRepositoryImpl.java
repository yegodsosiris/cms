package com.rdfgroup.cms.services.client.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rdfgroup.cms.services.domain.template.Template;
import com.rdfgroup.cms.services.repository.BaseRepository;

@Repository
public class PublicTemplateRepositoryImpl extends BaseRepository implements PublicTemplateRepository {

	@Override
	public List<Template> getTemplatesByKey(String key) {
		return find("key", key, Template.class);
		
	}

}
