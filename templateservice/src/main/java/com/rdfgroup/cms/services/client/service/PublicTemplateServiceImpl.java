package com.rdfgroup.cms.services.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfgroup.cms.services.client.repository.PublicTemplateRepository;
import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.domain.template.Template;
import com.rdfgroup.cms.velocity.directive.helper.TemplateHelper;


@Service
public class PublicTemplateServiceImpl implements PublicTemplateService {
	
	@Autowired
	PublicTemplateRepository templateRepository;

	@Override
	public Template getTemplateByKey(String key, Website website) {
		return TemplateHelper.getLatestTemplateForWebsite(templateRepository.getTemplatesByKey(key), website.getName(), key);
	}

}
