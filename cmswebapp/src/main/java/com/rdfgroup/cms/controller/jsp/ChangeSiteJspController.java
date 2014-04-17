package com.rdfgroup.cms.controller.jsp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.util.LocaleHelper;

/**
 * Handles requests for the application home page.
 */
@SessionAttributes(Website.KEY)
@Controller
@Scope("session")
public class ChangeSiteJspController extends BaseJspController {
	
	@RequestMapping(value = "/language/{language}", method = RequestMethod.GET)
	public String changesite(ModelMap model, @PathVariable String language) throws Exception {
		Website w = new Website(LocaleHelper.getLocale(language));
		model.addAttribute(Website.KEY, w);
		return "forward:/";
	}
	
}
