package com.rdfgroup.cms.controller.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rdfgroup.cms.services.service.BookService;
import com.rdfgroup.cms.velocity.mvc.VelocityWriter;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DemoController extends BaseJspController{

	@Autowired
	BookService bookService;

	@RequestMapping(value = "/filedemo", method = RequestMethod.GET)
	public String getBook(ModelMap model)	throws Exception {
		model.addAttribute("demo", "called from the file system");
		model.addAttribute(VelocityWriter.MODEL, VelocityWriter.getVModel(model));
		return "demo";
	}

}
