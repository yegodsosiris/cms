package com.rdfgroup.cms.services.client.repository;

import java.util.List;

import com.rdfgroup.cms.services.domain.template.Template;

public interface PublicTemplateRepository {

	public List<Template> getTemplatesByKey(String key);

}
