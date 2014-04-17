package com.rdfgroup.cms.services.admin.repository;

import java.util.List;

import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.domain.template.Template;

public interface AdminTemplateRepository {

	public List<Template> getTemplates(Website website) throws Exception;

	public Template getTemplate(String id) throws Exception;

	public Template saveTemplate(Template template);

	public Template updateTemplate(Template template);

	public List<Template> getTemplateByType(String key, Website website);

	public List<Template> getTemplatesByKey(String key, Website website);

	public void deleteTemplate(String id);

}
