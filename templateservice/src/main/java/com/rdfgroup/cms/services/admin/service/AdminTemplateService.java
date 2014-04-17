package com.rdfgroup.cms.services.admin.service;

import java.util.List;

import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.domain.template.Template;


public interface AdminTemplateService {
	
	public List<Template> getTemplates(Website website) throws Exception;
	
	public Template getTemplate(String id) throws Exception;
	
	public Template saveTemplate(Template template);

	public Template updateTemplate(Template template);

	public List<Template> getTemplateByType(String key, Website website);

	public List<Template> getTemplatesByKey(String key, Website website);

	public Template revertTemplate(Template template, Website website);

	public void deleteTemplate(String id);

}
