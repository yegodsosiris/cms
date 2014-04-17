package com.rdfgroup.cms.controller.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.rdfgroup.cms.services.domain.Website;

@Controller
public class BaseJspController {


	@ModelAttribute("contextpath")
	protected String setContextPath(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		return "/".equals(contextPath) ? "" : contextPath;
	}
	
	@ModelAttribute(Website.KEY)
	protected Website setWebsite(HttpSession session) {
		Website website = (Website) session.getAttribute(Website.KEY);
		if (website==null || website.getName()==null) {
			website = Website.getWebsite(Website.DEFAULT_WEBSITE);
			session.setAttribute(Website.KEY,	 website);
		}
		return website;
	}
	
	protected Website getWebsite(ModelMap model) {
		return (Website) model.get(Website.KEY);
	}

	
}
