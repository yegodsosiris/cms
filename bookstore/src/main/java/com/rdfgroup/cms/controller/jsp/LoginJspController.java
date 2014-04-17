package com.rdfgroup.cms.controller.jsp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the users page.
 */
@Controller
public class LoginJspController extends BaseJspController{
	

@Value("#{config[velocityroot]}")
public String PATH_TO_TEMPLATES;

    /**
     * Simply selects the home view to render by returning its name.
     * @throws Exception 
     */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) throws Exception {
		return velocityWriter.write("login", model);
	}
	
}
