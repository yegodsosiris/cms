package com.rdfgroup.cms.controller.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rdfgroup.cms.velocity.mvc.VelocityWriter;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SimpleContentJspController extends BaseJspController {
	
	@Autowired
	VelocityWriter velocityWriter;


	@RequestMapping(value = "/c/{templateIn}", method = RequestMethod.GET)
	public String getContent(@PathVariable String templateIn, ModelMap model) throws Exception {
		return velocityWriter.write(templateIn, model);
		
	}
	
}
