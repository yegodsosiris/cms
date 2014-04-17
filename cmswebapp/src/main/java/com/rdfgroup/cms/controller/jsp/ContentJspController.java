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

import com.rdfgroup.cms.services.domain.Content;
import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.service.ContentService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/content")
public class ContentJspController extends BaseJspController{


	private static final String CONTENT_REVERTLIST = "content/revertlist";
	private static final String CONTENTS_LIST = "content/list";
	public static final String REVERT = "content/revert";
	private static final String CONTENT_MODEL = "contentModel";
	private static final String CONTENT = "content/edit";
	
	@Autowired
	ContentService contentService;

	/* =================================================================== */
	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_NEW, method = RequestMethod.GET)
	public String createContent(ModelMap model)	throws Exception {
		Content content = new Content();
		content.setType("generic");
		model.addAttribute(CONTENT_MODEL, content);
		return CONTENT;
	}

	@RequestMapping(value=UrlMappings.REQUEST_MAPPING_NEW, method = RequestMethod.POST)
	public String processSubmit(ModelMap model, @ModelAttribute(CONTENT_MODEL) Content content,	BindingResult result) {
		initWebsite(model, content);
		content = contentService.saveContent(content);
		model.addAttribute(MESSAGE, "Content successfully saved. Now at version "+content.getVersion());
		return CONTENT;
	}

	/* =================================================================== */
	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_REVERT, method = RequestMethod.GET)
	public String getContentToRevert(ModelMap model, @PathVariable String id)	throws Exception {
		model.addAttribute(CONTENT_MODEL, contentService.getContent(id));
		return REVERT;
	}

	@RequestMapping(value=UrlMappings.REQUEST_MAPPING_REVERT, method = RequestMethod.POST)
	public String revertContent(ModelMap model, @ModelAttribute(CONTENT_MODEL) Content content,	BindingResult result) {
		contentService.revertContent(content, getWebsite(model));
		model.addAttribute(MESSAGE, "Content successfully reverted. Now at version "+content.getVersion());
		return REVERT;
	}

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_VERSIONS, method = RequestMethod.GET)
	public String getContentsByTypeToRevert(ModelMap model, @PathVariable String key) throws Exception {
		return getRevertList(model, key, getWebsite(model));
	}

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_ALL_VERSIONS, method = RequestMethod.GET)
	public String getGlobalContentsByTypeToRevert(ModelMap model, @PathVariable String key) throws Exception {
		return getRevertList(model, key, null);
	}

	/* =================================================================== */
	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_EDIT, method = RequestMethod.GET)
	public String getContent(ModelMap model, @PathVariable String id)	throws Exception {
		model.addAttribute(CONTENT_MODEL, contentService.getContent(id));
		return CONTENT;
	}

	@RequestMapping(value=UrlMappings.REQUEST_MAPPING_EDIT, method = RequestMethod.POST)
	public String updateContent(ModelMap model, @ModelAttribute(CONTENT_MODEL) Content content,	BindingResult result) {
		initWebsite(model, content);
		contentService.updateContent(content);
		model.addAttribute(MESSAGE, "Content successfully saved. Now at version "+content.getVersion());
		return CONTENT;
	}

	/* =================================================================== */

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_DELETE, method = RequestMethod.GET)
	public String deleteContent(ModelMap model, @PathVariable String id)	throws Exception {
		Content content = contentService.getContent(id);
		String p = content.getType();
		contentService.deleteContent(id);
		return "forward:/content/"+p;
	}

	/* =================================================================== */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap model) throws Exception {
		return "forward:generic";
	}

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_ALL, method = RequestMethod.GET)
	public String getGlobalContentsByType(ModelMap model, @PathVariable String type) throws Exception {
		List<Content> contents = contentService.getContentByType(type, null);
		model.addAttribute("contents", contents);
		model.addAttribute("type", type);
		return CONTENTS_LIST;
	}

	/* =================================================================== */

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_DEFAULT, method = RequestMethod.GET)
	public String getContentsByType(ModelMap model, @PathVariable String type) throws Exception {
		List<Content> contents = contentService.getContentByType(type, getWebsite(model));
		model.addAttribute("contents", contents);
		model.addAttribute("type", type);
		return CONTENTS_LIST;
	}

	private String getRevertList(ModelMap model, String key, Website website) {
		List<Content> contents = contentService.getContentsByKey(key, website);
		model.addAttribute("contents", contents);
		model.addAttribute("key", key);
		if (contents != null && !contents.isEmpty()) {
			model.addAttribute("type", contents.get(0).getType());
		}
		return CONTENT_REVERTLIST;
	}
	
	@ModelAttribute("types")
	public Map<String,String> populateTypes() {
		
		//Data referencing for java skills list box
		Map<String,String> types = new LinkedHashMap<String,String>();
		types.put("generic", "Generic Content");
		
		return types;
	}

}
