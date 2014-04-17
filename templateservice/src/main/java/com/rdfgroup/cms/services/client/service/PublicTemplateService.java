package com.rdfgroup.cms.services.client.service;

import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.domain.template.Template;


public interface PublicTemplateService {
	
	public Template getTemplateByKey(String key, Website website);

}
