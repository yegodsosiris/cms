package com.rdfgroup.cms.controller.jsp;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.velocity.mvc.VelocityWriter;
import com.rdfgroup.security.domain.User;
import com.rdfgroup.security.service.UserService;

@Controller
public class BaseJspController {
	
	@Autowired
	protected VelocityWriter velocityWriter;
	
	@Autowired
	UserService userService;


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
	
	@ModelAttribute("currentuser")
    public User getLoggedInUser(Principal principal) {
		if (principal==null) return new User();
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        if(user == null) {
            user = new User();
        }
        return user;        
    }

	
}
