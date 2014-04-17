package com.rdfgroup.cms.controller.jsp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rdfgroup.cms.services.domain.Website;

/**
 * Handles requests for the application home page.
 */
@SessionAttributes(Website.KEY)
@Controller
@Scope("session")
public class ChangeSiteJspController extends BaseJspController {
	
	@RequestMapping(value = "/website/{websiteName}", method = RequestMethod.GET)
	public String changesite(ModelMap model, @PathVariable String websiteName) throws Exception {
		model.addAttribute(Website.KEY, Website.getWebsite(websiteName));
		return "forward:/page";
	}
	
}
