package com.rdfgroup.cms.controller.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the users page.
 */
@Controller
public class LoginJspController extends BaseJspController{

    /**
     * Simply selects the home view to render by returning its name.
     */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
}
