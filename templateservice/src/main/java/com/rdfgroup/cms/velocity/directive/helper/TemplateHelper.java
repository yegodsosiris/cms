package com.rdfgroup.cms.velocity.directive.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.exception.ResourceNotFoundException;

import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.domain.template.Template;

public class TemplateHelper {
	
	public static Template getLatestTemplateForWebsite(List<Template> templatesByKey, String website, String key) {
		Map<String, Template> map = new HashMap<String, Template>();
		for (Template template : templatesByKey) {
			String websiteAsKey = template.getWebsite();
			websiteAsKey = websiteAsKey==null?Website.NULL_WEBSITE:websiteAsKey;
			if (map.containsKey(websiteAsKey)) {
				Template mappedT = map.get(websiteAsKey);
				if (template.isNewerThan(mappedT)) { // versioning
					map.put(websiteAsKey, template);
				}
			}
			else {
				map.put(websiteAsKey, template);
			}
			
		}
		Template template = map.get(website);
		if (template != null) {
			return template;
		}
		template = map.get(Website.DEFAULT_WEBSITE);
		if (template != null) {
			return template;
		}
		template = map.get(Website.NULL_WEBSITE);
		if (template != null) {
			return template;
		}
		throw new ResourceNotFoundException("Template is empty - Unable to load template (key=" + key + ", website=" + website + ") from the database");
	}
	
}
