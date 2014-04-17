package com.rdfgroup.cms.controller.jsp;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.rdfgroup.cms.services.domain.CmsElement;
import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.util.LocaleHelper;

@Controller
public class BaseJspController {
	
	public static final String MESSAGE = "message";

	@ModelAttribute("contextpath")
	protected String setContextPath(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		return "/".equals(contextPath) ? "" : contextPath;
	}
	
	@ModelAttribute(Website.KEY)
	protected Website setWebsite(HttpSession session) {
		Website website = (Website) session.getAttribute(Website.KEY);
		if (website==null || website.getName()==null) {
			website = new Website(Locale.getDefault());
			session.setAttribute(Website.KEY,	 website);
		}
		return website;
	}

	protected void initWebsite(ModelMap model, CmsElement e) {
		e.setLanguage(((Website) model.get(Website.KEY)).getLanguage());
	}
	
	@ModelAttribute("locales")
	protected List<Locale> getLocals() {
		return LocaleHelper.getLocals();
	}
	
	protected Website getWebsite(ModelMap model) {
		return (Website) model.get(Website.KEY);
	}

	
}
