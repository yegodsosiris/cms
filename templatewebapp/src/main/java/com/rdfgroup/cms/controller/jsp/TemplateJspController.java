package com.rdfgroup.cms.controller.jsp;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rdfgroup.cms.services.admin.service.AdminTemplateService;
import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.domain.template.Template;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes
public class TemplateJspController extends BaseJspController{

	@Autowired
	AdminTemplateService templateService;

	@RequestMapping(value = "/template", method = RequestMethod.GET)
	public String createTemplate(ModelMap model)	throws Exception {
		model.addAttribute("template", new Template());
		return "template";
	}

	@RequestMapping(value = "/revert/{id}", method = RequestMethod.GET)
	public String getTemplateToRevert(ModelMap model, @PathVariable String id)	throws Exception {
		model.addAttribute("template", templateService.getTemplate(id));
		return "revert";
	}

	@RequestMapping(value="revert/{id}", method = RequestMethod.POST)
	public String revertTemplate(ModelMap model, @ModelAttribute("template") Template template,	BindingResult result) {
		templateService.revertTemplate(template, getWebsite(model));
		model.addAttribute("message", "Template successfully reverted. Now at version "+template.getVersion());
		return "revert";
	}

	@RequestMapping(value = "/template/{id}", method = RequestMethod.GET)
	public String getTemplate(ModelMap model, @PathVariable String id)	throws Exception {
		model.addAttribute("template", templateService.getTemplate(id));
		return "template";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteTemplate(ModelMap model, @PathVariable String id)	throws Exception {
		Template template = templateService.getTemplate(id);
		String p = template.getType();
		templateService.deleteTemplate(id);
		return "redirect:/"+p;
	}

	@RequestMapping(value="template/{id}", method = RequestMethod.POST)
	public String updateTemplate(ModelMap model, @ModelAttribute("template") Template template,	BindingResult result) {
		initWebsite(model, template);
		templateService.updateTemplate(template);
		model.addAttribute("message", "Template successfully saved. Now at version "+template.getVersion());
		return "template";
	}

	private void initWebsite(ModelMap model, Template template) {
		template.setWebsite(((Website) model.get(Website.KEY)).getName());
	}

	@RequestMapping(value="template", method = RequestMethod.POST)
	public String processSubmit(ModelMap model, @ModelAttribute("template") Template template,	BindingResult result) {
		initWebsite(model, template);
		templateService.saveTemplate(template);
		return "template";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap model) throws Exception {
		List<Template> templates = templateService.getTemplates(getWebsite(model));
		model.addAttribute("templates", templates);
		return "forward:/page";
	}

	@RequestMapping(value = "/a/{type}", method = RequestMethod.GET)
	public String getGlobalTemplatesByType(ModelMap model, @PathVariable String type) throws Exception {
		List<Template> templates = templateService.getTemplateByType(type, Website.getWebsite(Website.ALL_WEBSITES));
		model.addAttribute("templates", templates);
		model.addAttribute("type", type);
		return "templates";
	}

	@RequestMapping(value = "/{type}", method = RequestMethod.GET)
	public String getTemplatesByType(ModelMap model, @PathVariable String type) throws Exception {
		List<Template> templates = templateService.getTemplateByType(type, getWebsite(model));
		model.addAttribute("templates", templates);
		model.addAttribute("type", type);
		return "templates";
	}

	@RequestMapping(value = "/r/{key}", method = RequestMethod.GET)
	public String getTemplatesByTypeToRevert(ModelMap model, @PathVariable String key) throws Exception {
		return getRevertList(model, key, getWebsite(model));
	}

	@RequestMapping(value = "/ra/{key}", method = RequestMethod.GET)
	public String getGlobalTemplatesByTypeToRevert(ModelMap model, @PathVariable String key) throws Exception {
		return getRevertList(model, key, Website.getWebsite(Website.ALL_WEBSITES));
	}

	private String getRevertList(ModelMap model, String key, Website website) {
		List<Template> templates = templateService.getTemplatesByKey(key, website);
		model.addAttribute("templates", templates);
		model.addAttribute("key", key);
		if (templates != null && !templates.isEmpty()) {
			model.addAttribute("type", templates.get(0).getType());
		}
		return "revertlist";
	}
	
	@ModelAttribute("types")
	public Map<String,String> populateTypes() {
		
		//Data referencing for java skills list box
		Map<String,String> types = new LinkedHashMap<String,String>();
		types.put("layout", "Layout");
		types.put("page", "Page");
		types.put("content", "CMS Content");
		types.put("component", "Page Component");
		types.put("widgetComponent", "Widget Component");
		types.put("widget", "Widget");
		types.put("css", "CSS");
		types.put("javascript", "JavaScript");
		
		return types;
	}

}
