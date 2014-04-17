package com.rdfgroup.cms.velocity.mvc;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.rdfgroup.cms.services.client.service.PublicTemplateService;
import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.domain.template.Template;


@Component
public class VelocityWriter {
	
	private static final String RESULT = "result";
	private static final String TEMPLATE = "template";
	public static final String LOG = "LOG";
	public static final String MODEL = "vmodel";

	@Autowired
	VelocityEngine velocityEngine;
	
	@Autowired
	PublicTemplateService templateService;

	
	private String write(String templateId, Map<String, Object> m) throws Exception {
		Template template = templateService.getTemplateByKey(templateId, (Website) m.get(Website.KEY));
		
		VelocityContext context = new VelocityContext();
		StringWriter writer = new StringWriter();
		context.put(MODEL, m);
		
		velocityEngine.evaluate(context, writer, LOG, template.getContent());
		return writer.getBuffer().toString();
	}
	
	public String write(String templateId, ModelMap model) throws Exception {
		Map<String, Object> mapForVelocity = getVModel(model);
		model.addAttribute(RESULT, write(templateId, mapForVelocity));
		return TEMPLATE;
	}

	public static Map<String, Object> getVModel(ModelMap model) {
		Map<String, Object> mapForVelocity = new HashMap<String, Object>();
		Set<Entry<String, Object>> entrySet = model.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			mapForVelocity.put(entry.getKey(), entry.getValue());
		}
		return mapForVelocity;
	}

}
