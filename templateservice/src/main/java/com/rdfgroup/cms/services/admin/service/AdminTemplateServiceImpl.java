package com.rdfgroup.cms.services.admin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfgroup.cms.services.admin.repository.AdminTemplateRepository;
import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.domain.template.Template;

@Service
public class AdminTemplateServiceImpl implements AdminTemplateService {
	
	@Autowired
	AdminTemplateRepository templateRepository;

	@Override
	public List<Template> getTemplates(Website website) throws Exception {
		List<Template> templates = templateRepository.getTemplates(website);
		return getLatest(templates);
	}

	private List<Template> getLatest(List<Template> templates) {
		Map<String, Template> latest = new HashMap<String, Template>();
		for (Template template : templates) {
			String key = template.getKey();
			if (!latest.containsKey(key)) {
				latest.put(key, template);
				continue;
			}
			Template inserted = latest.get(key);
			if (template.isNewerThan(inserted)) {
				latest.put(key, template);
			}
		}
		List<Template> result = new ArrayList<Template>();
		Set<String> keySet = latest.keySet();
		for (String string : keySet) {
			result.add(latest.get(string));
		}
		Collections.sort(result, templateKeyComparator);
		return result;
	}
	
	private Comparator<Template> templateKeyComparator = new Comparator<Template>() {

		public int compare(Template t1, Template t2) {
			return t2.getKey().compareTo(t1.getKey());
		}

	};
	
	private Comparator<Template> templateVersionComparator = new Comparator<Template>() {
		public int compare(Template t1, Template t2) {
			if (t1.getVersion() > t2.getVersion()) return -1;
			if (t1.getVersion() < t2.getVersion()) return 1;
			return 0;
		}

	};
	
	@Override
	public Template getTemplate(String id) throws Exception {
		return templateRepository.getTemplate(id);
	}

	@Override
	public Template saveTemplate(Template template) {
		return templateRepository.saveTemplate(template);
	}
	
	@Override
	public Template revertTemplate(Template template, Website website) {
		try {
			List<Template> templatesByKey = templateRepository.getTemplatesByKey(template.getKey(), website);
			List<Template> latest = getLatest(templatesByKey);
			Template t = latest.get(0);
			template.setVersion(t.getVersion());
			template.setId(null);
			template.increaseVersion();
			template.setLastModified(new Date());
		} catch (Exception e) {
			// There should be no error as we are saving an existing document
			e.printStackTrace();
		}
		return templateRepository.updateTemplate(template);
	}

	@Override
	public Template updateTemplate(Template template) {
		try {
			Template orig = templateRepository.getTemplate(template.getId());
			orig.setId(null);
			// to facilitate moving of content to new categories we must change the type.
			orig.setType(template.getType());
			templateRepository.saveTemplate(orig);
		} catch (Exception e) {
			// There should be no error as we are saving an existing document
			e.printStackTrace();
		}
		template.increaseVersion();
		template.setLastModified(new Date());
		return templateRepository.updateTemplate(template);
	}

	@Override
	public List<Template> getTemplateByType(String type, Website website) {
		List<Template> templateByType = templateRepository.getTemplateByType(type, website);
		return getLatest(templateByType);
	}

	@Override
	public List<Template> getTemplatesByKey(String key, Website website) {
		List<Template> result = templateRepository.getTemplatesByKey(key, website);
		Collections.sort(result, templateVersionComparator);
		return result;
	}

	@Override
	public void deleteTemplate(String id) {
		templateRepository.deleteTemplate(id);
	}

}
